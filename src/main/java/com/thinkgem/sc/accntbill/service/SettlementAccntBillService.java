/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntbill.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mk.office.service.IOfficeService;
import com.mk.settlement.enums.AccountRoleEnums;
import com.mk.settlement.enums.AccountStatusEnums;
import com.mk.settlement.enums.BizTypeEnums;
import com.mk.settlement.enums.SettlementBillStateEnums;
import com.mk.settlement.enums.SettlementStatusEnums;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.sc.accntbill.dao.SettlementAccntBillDao;
import com.thinkgem.sc.accntbill.entity.SettlementAccntBill;
import com.thinkgem.sc.accntdetail.dao.SettlementAccntDetailDao;
import com.thinkgem.sc.accntledger.dao.SettlementAccntLedgerDao;

/**
 * accntbillService
 * @author lm
 * @version 2016-03-19
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementAccntBillService extends CrudService<SettlementAccntBillDao, SettlementAccntBill> {
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 调用结算中心doubble
	 */
	@Autowired
	private IOfficeService iOfficeService;
	
	@Autowired
    private SettlementAccntLedgerDao accntLedgerDao;
	
	@Autowired
    private SettlementAccntDetailDao accntDetailDao;
	
	@Autowired
    private SettlementAccntBillDao accntBillDao;
	
	public SettlementAccntBill get(String id) {
		return super.get(id);
	}
	
	public List<SettlementAccntBill> findList(SettlementAccntBill settlementAccntBill) {
		return super.findList(settlementAccntBill);
	}
	
	public Page<SettlementAccntBill> findPage(Page<SettlementAccntBill> page, SettlementAccntBill settlementAccntBill) {
		return super.findPage(page, settlementAccntBill);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementAccntBill settlementAccntBill) {
		super.save(settlementAccntBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementAccntBill settlementAccntBill) {
		super.delete(settlementAccntBill);
	}

	// 批量结算
	@Transactional(readOnly = false)
	public int bill(String ids, int status) {
		try {
			int count = iOfficeService.settlement(ids, SettlementStatusEnums.DONE_SETTLEMENT.getIndex());
			return count;
		} catch (Exception e) {
			log.error("结算中心报错！", e);
			return 0;
		}
	}

    public boolean rebill(long id) {
        return iOfficeService.resettlement(id);
    }
    
    /**
     * 酒店老板周账单重新结算.
     * @param id
     * 参数：酒店老板周账单id.
     * @return
     */
    public boolean hotelReBill(long id) {
        return iOfficeService.HotelBillResettlement(id);
    }
	
	/**
	 * 酒店老板周账单财务确认
	 * 
	 * @param settlementAccntBill
	 */
	@Transactional(readOnly = false)
	public void fiConfirmHotelBill(SettlementAccntBill settlementAccntBill) {
	    try {
	        // 更新酒店账单状态为财务确认
	        settlementAccntBill.setBillstate(SettlementBillStateEnums.FINANCE_CONFIRM.getIndex());
	        settlementAccntBill.setAccountstate(AccountStatusEnums.CAN.getIndex());
	        
	        if (settlementAccntBill.getAccountrole().intValue() == AccountRoleEnums.BOSS.getIndex() && 
	        		settlementAccntBill.getBiztype().intValue() == BizTypeEnums.WASH.getIndex()) {
	        	//洗涤老板月账单不扣减余额 && scstate不变(保持为1)
				
				
			} else {
				
			    settlementAccntBill.setScstate(SettlementStatusEnums.DONE_SETTLEMENT.getIndex());
				
				// 财务确认后更新酒店老板账户总账余额
				int nums = accntLedgerDao
						.insertOrUpdateBalanceAfterFIConfirmHotelBossAccountBill(settlementAccntBill);
				log.info("酒店老板周账单财务确认后更新了{}条总账余额.", nums);
			}
	        
	        this.save(settlementAccntBill);
	        log.info("酒店老板周账单财务确认后更新账单状态成功.");
	        
			// 财务确认后根据账单id关联明细账no字段更新明细账orderstatus字段值为2.
	        int updateNums = accntDetailDao.updateOrderStatusAfterHotelBillConfirm(settlementAccntBill);
	        log.info("酒店老板周账单财务确认后更新了{}条明细账订单状态.", updateNums);
        } catch (Exception e) {
            log.error("酒店老板周账单财务确认时出错: {}", e.getLocalizedMessage());
        }
	}
	
	/**
     * 酒店老板周账单财务确认
     * 
     * @param settlementAccntBill
     */
    @Transactional(readOnly = false)
    public void fiBatchConfirmHotelBill(List<String> ids) {
        try {
            // 更新酒店账单状态为财务确认
            for (String id : ids) {
                SettlementAccntBill settlementAccntBill = accntBillDao.get(id);
                if (settlementAccntBill == null || 
                        SettlementBillStateEnums.FINANCE_CONFIRM.getIndex() == settlementAccntBill.getBillstate()) {
                    continue;
                }
                settlementAccntBill.setBillstate(SettlementBillStateEnums.FINANCE_CONFIRM.getIndex());
                settlementAccntBill.setAccountstate(AccountStatusEnums.CAN.getIndex());
                this.save(settlementAccntBill);
                log.info("酒店老板周账单{}财务确认后更新账单状态成功.", id);
                
                // 财务确认后更新酒店老板账户总账余额
                int nums = accntLedgerDao.insertOrUpdateBalanceAfterFIConfirmHotelBossAccountBill(settlementAccntBill);
                log.info("酒店老板周账单{}财务确认后更新了{}条总账余额.", id, nums);
                
                // 财务确认后根据账单id关联明细账no字段更新明细账orderstatus字段值为2.
                int updateNums = accntDetailDao.updateOrderStatusAfterHotelBillConfirm(settlementAccntBill);
                log.info("酒店老板周账单{}财务确认后更新了{}条明细账订单状态.", id, updateNums);
            }
        } catch (Exception e) {
            log.error("酒店老板周账单财务确认时出错: {}", e.getLocalizedMessage());
        }
    }
    
    /**
     * 洗涤月账单重新结算.
     * 
     * @param id
     * 参数：洗涤月账单id.
     * @return
     */
    public boolean washingReBill(Long id) {
        boolean flag = false;
        int updateNums = accntDetailDao.updateStatusBeforeWashingBillResettlement(id);
        if (updateNums > 0) {
            flag = iOfficeService.WashingBillResettlement(id);
        }
        return flag;
    }
	
}