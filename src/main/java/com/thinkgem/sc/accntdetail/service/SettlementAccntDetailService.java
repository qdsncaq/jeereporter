///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.accntdetail.service;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.thinkgem.jeesite.common.annotation.DataSourceName;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.mk.office.service.IOfficeService;
//import com.mk.settlement.enums.FeeTypeEnums;
//import com.mk.settlement.enums.TripAccntTypeEnums;
//import com.thinkgem.jeesite.common.persistence.Page;
//import com.thinkgem.jeesite.common.service.CrudService;
//import com.thinkgem.sc.accntbill.dao.SettlementAccntBillDao;
//import com.thinkgem.sc.accntbill.entity.SettlementAccntBill;
//import com.thinkgem.sc.accntdetail.dao.SettlementAccntDetailDao;
//import com.thinkgem.sc.accntdetail.entity.SettlementAccntDetail;
//import com.thinkgem.sc.accntledger.dao.SettlementAccntLedgerDao;
//
///**
// * accntdetailService
// * @author lm
// * @version 2016-03-18
// */
//@Service
//@DataSourceName("gds")
//@Transactional(readOnly = true)
//public class SettlementAccntDetailService extends CrudService<SettlementAccntDetailDao, SettlementAccntDetail> {
//	
//	/**
//	 * 日志对象
//	 */
//	private Logger log = LoggerFactory.getLogger(getClass());
//	
//	@Autowired
//	private SettlementAccntDetailDao accntDetailDao;
//	
//	@Autowired
//	private SettlementAccntBillDao accntBillDao;
//	
//	@Autowired
//	private SettlementAccntLedgerDao accntLedgerDao;
//	
//	@Autowired
//	private IOfficeService iOfficeService;
//
//	public SettlementAccntDetail get(String id) {
//		return super.get(id);
//	}
//	
//	public List<SettlementAccntDetail> findList(SettlementAccntDetail settlementAccntDetail) {
//		return super.findList(settlementAccntDetail);
//	}
//	
//	public Page<SettlementAccntDetail> findPage(Page<SettlementAccntDetail> page, SettlementAccntDetail settlementAccntDetail) {
//		return super.findPage(page, settlementAccntDetail);
//	}
//	
//	@Transactional(readOnly = false)
//	public void save(SettlementAccntDetail settlementAccntDetail) {
//		super.save(settlementAccntDetail);
//	}
//	
//	@Transactional(readOnly = false)
//	public void delete(SettlementAccntDetail settlementAccntDetail) {
//		super.delete(settlementAccntDetail);
//	}
//
//	@Transactional(readOnly = false)
//	public int addchangeaccnt(SettlementAccntDetail settlementAccntDetail) {
//		SettlementAccntDetail accntDetail = accntDetailDao.get(settlementAccntDetail.getId());
//		BigDecimal sum = settlementAccntDetail.getOrdertotal();
//		/** 重新计算left_date */
//		Date leftDate = accntDetail.getLeftDate();
//		String accountid = accntDetail.getAccountid();
//		Integer accountrole = accntDetail.getAccountrole();
//		Integer biztype = accntDetail.getBiztype();
//		SettlementAccntBill accntBill = accntBillDao.selectAccBillByConditions(leftDate, accountid, accountrole, biztype);
//		log.info("旅行社明细调整金额查询账单:{}, 计算leftDate.");
//		int paytype = accntBill.getPaytype();
//		
//		accntDetail.setCreateTime(new Date());
//		accntDetail.setUpdateTime(new Date());
//		accntDetail.setOrdertotal(sum);
//		accntDetail.setStatus(2);
//		accntDetail.setRemarks(settlementAccntDetail.getRemarks());
//		accntDetail.setFeetype(FeeTypeEnums.MODIFY.getIndex());
//		if(accntBill != null){
//			accntDetail.setLeftDate(accntBill.getBeginDate());
//		}
//		try {
//			int count = accntDetailDao.insert(accntDetail);
//			/** 调整明细数据之后，同步修改该用户账号余额 */
//			if(paytype == TripAccntTypeEnums.PRE.getIndex() ){
//				// 储值账户
//				int resuConut = accntLedgerDao.depositRecharge(accountid, sum.multiply(new BigDecimal(-1)), accountrole);
//				log.info("旅行社明细调整金额完成后,修改账户余额.账户信息:accountid: {}, accontrole: {}, 修改金额为: {} , 修改记录数{}条.", accountid, accountrole, sum, resuConut);
//			} else if (paytype == TripAccntTypeEnums.POST.getIndex()){
//				// 后付费账户
//				int resuCount = accntLedgerDao.postpayRecharge(accountid, sum.multiply(new BigDecimal(-1)), accountrole);
//
//				log.info("旅行社明细调整金额完成后,修改账户余额.账户信息:accountid: {}, accontrole: {}, 修改金额为: {} , 修改记录数{}条.", accountid, accountrole, sum, resuCount);
//			}
//			return count;
//		} catch (Exception e) {
//			log.error("调整明细订单失败。", e);
//		}
//		return 0;
//	}
//
//	/**
//	 * 旅行社调整金额 明细表添加一条记录
//	 * 	--- left_date 动态计算
//	 * 	--- 账户余额变更
//	 * @throws Exception 
//	 */
//	@Transactional(readOnly = false)
//	public int modify(SettlementAccntDetail settlementAccntDetail) throws Exception {
//		long id = Long.parseLong(settlementAccntDetail.getId());
//		BigDecimal ordertotal = settlementAccntDetail.getOrdertotal();
//		String remarks = settlementAccntDetail.getRemarks();
//		int count = iOfficeService.udpateDetailsByid(id, ordertotal, remarks);
//		return count;
//	}
//	
//	/**
//	 * 
//	 * @param record
//	 * @return
//	 */
//	@Transactional(readOnly = false)
//	public int updateByPrimaryKeySelective(SettlementAccntDetail record) {
//	    return accntDetailDao.updateByPrimaryKeySelective(record);
//	}
//	
//	/**
//	 * 
//	 * @param page
//	 * @param settlementAccntDetail
//	 * @return
//	 */
//    public Page<SettlementAccntDetail> findAllBalanceDetails(Page<SettlementAccntDetail> page, SettlementAccntDetail settlementAccntDetail) {
//        settlementAccntDetail.setPage(page);
//        page.setList(accntDetailDao.findAllBalanceDetails(settlementAccntDetail));
//        return page;
//    }
//	
//}