///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.accntwithdraw.service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.mk.office.service.IOfficeService;
//import com.mk.settlement.enums.AccountStatusEnums;
//import com.mk.settlement.enums.BizTypeEnums;
//import com.thinkgem.jeesite.common.annotation.DataSourceName;
//import com.thinkgem.jeesite.common.persistence.Page;
//import com.thinkgem.jeesite.common.service.CrudService;
//import com.thinkgem.sc.accntdetail.entity.SettlementAccntDetail;
//import com.thinkgem.sc.accntdetail.service.SettlementAccntDetailService;
//import com.thinkgem.sc.accntwithdraw.dao.SettlementAccountWithdrawcashDao;
//import com.thinkgem.sc.accntwithdraw.entity.SettlementAccountWithdrawcash;
//import com.thinkgem.sc.enums.WithdrawStatusEnums;
//
///**
// * accntwithdrawService
// * @author lm
// * @version 2016-03-16
// */
//@Service
//@DataSourceName("gds")
//@Transactional(readOnly = true)
//public class SettlementAccountWithdrawcashService extends CrudService<SettlementAccountWithdrawcashDao, SettlementAccountWithdrawcash> {
//	
//	/**
//	 * 日志对象
//	 */
//	private Logger log = LoggerFactory.getLogger(getClass());
//	
//	@Autowired
//	private SettlementAccountWithdrawcashDao withdrawcashDao;
//	
//	@Autowired
//	private IOfficeService iOfficeService;
//	
//	@Autowired
//	private SettlementAccntDetailService settlementAccntDetailService;
//	
//	public SettlementAccountWithdrawcash get(String id) {
//		return super.get(id);
//	}
//	
//	public List<SettlementAccountWithdrawcash> findList(SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//		return super.findList(settlementAccountWithdrawcash);
//	}
//	
//	public Page<SettlementAccountWithdrawcash> findPage(Page<SettlementAccountWithdrawcash> page, SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//		return super.findPage(page, settlementAccountWithdrawcash);
//	}
//	
//	@Transactional(readOnly = false)
//	public void save(SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//		super.save(settlementAccountWithdrawcash);
//	}
//	
//	@Transactional(readOnly = false)
//	public void delete(SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//		super.delete(settlementAccountWithdrawcash);
//	}
//
//	// 更新状态 审核通过
//	@Transactional(readOnly = false)
//	public int updateStatus(List<Long> lids) {
//		// 根据ids 查询提现申请记录表
//		List<SettlementAccountWithdrawcash> lists = withdrawcashDao.selectWithdrawcashByids(lids);
//		int uCount = 0;
//		for (SettlementAccountWithdrawcash accountWithdrawcash : lists) {
//			String withdrawcashid = accountWithdrawcash.getId();
//			String accountid = accountWithdrawcash.getAccountid();
//			// String hotelpms = accountWithdrawcash.getHotelpms();
//			String hotelid = accountWithdrawcash.getHotelid();
//			BigDecimal prebalance = accountWithdrawcash.getPrebalance();
//			BigDecimal cash = accountWithdrawcash.getCash();
//			try {
//				// 查询账户当前余额
//				// BigDecimal banlance = iOfficeService.getOwnerBanlance(accountid, hotelpms);
//				// 更新 提现申请记录表中 取现后账户余额
//				// 更新 提现申请记录表中 状态为 审核通过2
//				accountWithdrawcash.setBalance(prebalance.subtract(cash));
//				accountWithdrawcash.setStatus(WithdrawStatusEnums.CHECK.getIndex());
//				int updateCount = withdrawcashDao.update(accountWithdrawcash);
//				if(updateCount == 1){
//					uCount = uCount + updateCount;
//				}
//				// 流水明细中 状态修改
//				// accountid hotelid biztype:房费 orderid:外键id提现申请记录表中ID
//				Integer status = 9;//已结算
//				int count = withdrawcashDao.updateDoneAccntDetailStatus(accountid, hotelid, withdrawcashid, status);
//				log.info("提现申请第一步审核,流水明细中更新count:{}条状态为9已结算.参数:accountid:{},hotelid{},withdrawcashid{}", count, accountid, hotelid, withdrawcashid);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return uCount;
//	}
//
//	// 驳回
//	@Transactional(readOnly = false)
//	public int updateNo(List<Long> lids) {
//		// 根据ids 查询提现申请记录表
//		List<SettlementAccountWithdrawcash> lists = withdrawcashDao.selectWithdrawcashByids(lids);
//		int uCount = 0;
//		for (SettlementAccountWithdrawcash accountWithdrawcash : lists) {
//			String withdrawcashid = accountWithdrawcash.getId();
//			String accountid = accountWithdrawcash.getAccountid();
//			// String hotelpms = accountWithdrawcash.getHotelpms();
//			String hotelid = accountWithdrawcash.getHotelid();
//			Integer role = accountWithdrawcash.getAccountrole();
//			BigDecimal cash = accountWithdrawcash.getCash();
//			BigDecimal prebalance = accountWithdrawcash.getPrebalance();
//			try {
//				// 查询账户当前余额 将提现申请余额重新加到账户余额中 更新返回账户余额表id,当前余额
//				// BigDecimal balance = iOfficeService.getOwnerBanlance(accountid, hotelpms);
//				log.info("提现驳回,修改账户余额表余额参数:accountid={}, hotelid={}, role={}, cash={}", accountid, hotelid, role, cash);
//				int ledgerCount = iOfficeService.updateOwnerBanlance(accountid, hotelid, role, cash);
//				log.info("提现驳回,修改账户余额表余额记录数:{}", ledgerCount);
//				
//				// 更新 提现申请记录表中 取现后账户余额
//				// 更新 提现申请记录表中 状态为 审核驳回4
//				accountWithdrawcash.setBalance(prebalance);
//				accountWithdrawcash.setStatus(WithdrawStatusEnums.CANCEL.getIndex());
//				int updateCount = withdrawcashDao.update(accountWithdrawcash);
//				if(updateCount == 1){
//					uCount = uCount + updateCount;
//				}
//				
//				// 流水明细中 状态修改
//				// accountid hotelid biztype:房费 orderid:外键id提现申请记录表中ID
//				Integer status = 4;//取消
//				int count = withdrawcashDao.updateNoAccntDetailStatus(accountid, hotelid, withdrawcashid, status);
//				log.info("提现申请第一步审核,流水明细中更新count:{}条状态为4已取消.参数:accountid:{},hotelid{},withdrawcashid{}", count, accountid, hotelid, withdrawcashid);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return uCount;
//	}
//
//	/**
//	 * 已打款 
//	 * @param lids
//	 * @return
//	 */
//	@Transactional(readOnly = false)
//	public int updatePaydone(List<Long> lids) {
//		int count = 0;
//		try {
//			log.info("修改已打款状态记录接收参数:{}", lids);
//			count = withdrawcashDao.updatePaydone(lids);
//			log.info("修改已打款状态记录{}条数据.", count);
//		} catch (Exception e) {
//			log.error("修改已打款状态报错.", e);
//		}
//		return count;
//	}
//	
//	/**
//	 * 销售提现运营审核确认.
//	 * 
//	 * @param settlementAccountWithdrawcash
//	 * @return
//	 */
//	@Transactional(readOnly = false)
//	public int salerWithdrawcashOperatorConfirm(SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//	    int result = 0;
//	    String id = settlementAccountWithdrawcash.getId();
//	    try {
//	        // 更新提现申请状态为: 10-运营确认
//	        SettlementAccountWithdrawcash record = new SettlementAccountWithdrawcash();
//	        record.setId(id);
//	        record.setStatus(WithdrawStatusEnums.OPERATOR_CONFIRM.getIndex());
//	        withdrawcashDao.updateByPrimaryKeySelective(record);
//	        result = 1;
//        } catch (Exception e) {
//            result = -1;
//            log.error("销售提现申请ID: {}运营审核确认时出错: {}", id, e.getLocalizedMessage());
//        }
//	    return result;
//	}
//	
//	/**
//     * 销售提现人事审核确认.
//     * 
//     * @param settlementAccountWithdrawcash
//     * @return
//     */
//    @Transactional(readOnly = false)
//    public int salerWithdrawcashHrConfirm(SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//        int result = 0;
//        String id = settlementAccountWithdrawcash.getId();
//        try {
//            // 更新提现申请状态为: 12-人事确认
//            SettlementAccountWithdrawcash record = new SettlementAccountWithdrawcash();
//            record.setId(id);
//            record.setStatus(WithdrawStatusEnums.HR_CONFIRM.getIndex());
//            withdrawcashDao.updateByPrimaryKeySelective(record);
//            result = 1;
//        } catch (Exception e) {
//            result = -1;
//            log.error("销售提现申请ID: {}人事审核确认时出错: {}", id, e.getLocalizedMessage());
//        }
//        return result;
//    }
//    
//    /**
//     * 销售提现人事审核确认.
//     * 
//     * @param settlementAccountWithdrawcash
//     * @return
//     */
//    @Transactional(readOnly = false)
//    public int salerWithdrawcashFiConfirm(SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//        int result = 0;
//        String id = settlementAccountWithdrawcash.getId();
//        try {
//            // 更新提现申请状态为: 3-财务打款
//            SettlementAccountWithdrawcash record = new SettlementAccountWithdrawcash();
//            record.setId(id);
//            record.setStatus(WithdrawStatusEnums.PLAYMONEY.getIndex());
//            withdrawcashDao.updateByPrimaryKeySelective(record);
//            
//            // 查找账单明细提现记录
//            SettlementAccntDetail settlementAccntDetail = new SettlementAccntDetail();
//            settlementAccntDetail.setBiztype(BizTypeEnums.WITHDRAWCASH.getIndex());
//            settlementAccntDetail.setOrderid(id);
//            List<SettlementAccntDetail> list = settlementAccntDetailService.findList(settlementAccntDetail);
//            for (SettlementAccntDetail item : list) {
//                // 更新账单明细提现记录状态
//                SettlementAccntDetail updateRecord = new SettlementAccntDetail();
//                updateRecord.setId(item.getId());
//                updateRecord.setStatus(AccountStatusEnums.DONE.getIndex());
//                settlementAccntDetailService.updateByPrimaryKeySelective(updateRecord);
//            }
//            result = 1;
//        } catch (Exception e) {
//            result = -1;
//            log.error("销售提现申请ID: {}人事审核确认时出错: {}", id, e.getLocalizedMessage());
//        }
//        return result;
//    }
//	
//	/**
//     * 销售提现运营审核驳回.
//     * 
//     * @param settlementAccountWithdrawcash
//     * @return
//     */
//    @Transactional(readOnly = false)
//    public int salerWithdrawcashOperatorCancel(SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//        int result = 0;
//        String id = settlementAccountWithdrawcash.getId();
//        String accountid = settlementAccountWithdrawcash.getAccountid();
//        String hotelid = settlementAccountWithdrawcash.getHotelid();
//        Integer role = settlementAccountWithdrawcash.getAccountrole();
//        BigDecimal cash = settlementAccountWithdrawcash.getCash();
//        BigDecimal prebalance = settlementAccountWithdrawcash.getPrebalance();
//        try {
//            // 更新提现申请状态为: 11-运营驳回
//            SettlementAccountWithdrawcash record = new SettlementAccountWithdrawcash();
//            record.setId(id);
//            // 更新 提现申请记录表中 取现后账户余额
//            record.setBalance(prebalance);
//            // 更新 提现申请记录表中 状态为 运营审核驳回11
//            record.setStatus(WithdrawStatusEnums.OPERATOR_CANCEL.getIndex());
//            withdrawcashDao.updateByPrimaryKeySelective(record);
//            
//            // 查找账单明细提现记录
//            SettlementAccntDetail settlementAccntDetail = new SettlementAccntDetail();
//            settlementAccntDetail.setBiztype(BizTypeEnums.WITHDRAWCASH.getIndex());
//            settlementAccntDetail.setOrderid(id);
//            List<SettlementAccntDetail> list = settlementAccntDetailService.findList(settlementAccntDetail);
//            for (SettlementAccntDetail item : list) {
//                // 更新账单明细提现记录状态
//                SettlementAccntDetail updateRecord = new SettlementAccntDetail();
//                updateRecord.setId(item.getId());
//                updateRecord.setStatus(AccountStatusEnums.WithdrawBOHUI.getIndex());
//                settlementAccntDetailService.updateByPrimaryKeySelective(updateRecord);
//            }
//            
//            // 更新账户总账余额
//            log.info("提现驳回,修改账户余额表余额参数:accountid={}, hotelid={}, role={}, cash={}", accountid, hotelid, role, cash);
//            int ledgerCount = iOfficeService.updateOwnerBanlance(accountid, hotelid, role, cash);
//            log.info("提现驳回,修改账户余额表余额记录数:{}", ledgerCount);
//            result = 1;
//        } catch (Exception e) {
//            result = -1;
//            log.error("销售提现申请ID: {}运营审核驳回时出错: {}", id, e.getLocalizedMessage());
//        }
//        return result;
//    }
//    
//    /**
//     * 销售提现人事审核驳回.
//     * 
//     * @param settlementAccountWithdrawcash
//     * @return
//     */
//    @Transactional(readOnly = false)
//    public int salerWithdrawcashHrCancel(SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//        int result = 0;
//        String id = settlementAccountWithdrawcash.getId();
//        String accountid = settlementAccountWithdrawcash.getAccountid();
//        String hotelid = settlementAccountWithdrawcash.getHotelid();
//        Integer role = settlementAccountWithdrawcash.getAccountrole();
//        BigDecimal cash = settlementAccountWithdrawcash.getCash();
//        BigDecimal prebalance = settlementAccountWithdrawcash.getPrebalance();
//        try {
//            // 更新提现申请状态为: 13-人事驳回
//            SettlementAccountWithdrawcash record = new SettlementAccountWithdrawcash();
//            record.setId(id);
//            // 更新 提现申请记录表中 取现后账户余额
//            record.setBalance(prebalance);
//            // 更新 提现申请记录表中 状态为 人事审核驳回13
//            record.setStatus(WithdrawStatusEnums.HR_CANCEL.getIndex());
//            withdrawcashDao.updateByPrimaryKeySelective(record);
//            
//            // 查找账单明细提现记录
//            SettlementAccntDetail settlementAccntDetail = new SettlementAccntDetail();
//            settlementAccntDetail.setBiztype(BizTypeEnums.WITHDRAWCASH.getIndex());
//            settlementAccntDetail.setOrderid(id);
//            List<SettlementAccntDetail> list = settlementAccntDetailService.findList(settlementAccntDetail);
//            for (SettlementAccntDetail item : list) {
//                // 更新账单明细提现记录状态
//                SettlementAccntDetail updateRecord = new SettlementAccntDetail();
//                updateRecord.setId(item.getId());
//                updateRecord.setStatus(AccountStatusEnums.WithdrawBOHUI.getIndex());
//                settlementAccntDetailService.updateByPrimaryKeySelective(updateRecord);
//            }
//            
//            // 更新账户总账余额
//            log.info("提现驳回,修改账户余额表余额参数:accountid={}, hotelid={}, role={}, cash={}", accountid, hotelid, role, cash);
//            int ledgerCount = iOfficeService.updateOwnerBanlance(accountid, hotelid, role, cash);
//            log.info("提现驳回,修改账户余额表余额记录数:{}", ledgerCount);
//            result = 1;
//        } catch (Exception e) {
//            result = -1;
//            log.error("销售提现申请ID: {}人事审核驳回时出错: {}", id, e.getLocalizedMessage());
//        }
//        return result;
//    }
//    
//    /**
//     * 销售提现人事审核驳回.
//     * 
//     * @param settlementAccountWithdrawcash
//     * @return
//     */
//    @Transactional(readOnly = false)
//    public int salerWithdrawcashFiCancel(SettlementAccountWithdrawcash settlementAccountWithdrawcash) {
//        int result = 0;
//        String id = settlementAccountWithdrawcash.getId();
//        String accountid = settlementAccountWithdrawcash.getAccountid();
//        String hotelid = settlementAccountWithdrawcash.getHotelid();
//        Integer role = settlementAccountWithdrawcash.getAccountrole();
//        BigDecimal cash = settlementAccountWithdrawcash.getCash();
//        BigDecimal prebalance = settlementAccountWithdrawcash.getPrebalance();
//        try {
//            // 更新提现申请状态为: 4-财务驳回
//            SettlementAccountWithdrawcash record = new SettlementAccountWithdrawcash();
//            record.setId(id);
//            // 更新 提现申请记录表中 取现后账户余额
//            record.setBalance(prebalance);
//            // 更新 提现申请记录表中 状态为 财务审核驳回4
//            record.setStatus(WithdrawStatusEnums.CANCEL.getIndex());
//            withdrawcashDao.updateByPrimaryKeySelective(record);
//            
//            // 查找账单明细提现记录
//            SettlementAccntDetail settlementAccntDetail = new SettlementAccntDetail();
//            settlementAccntDetail.setBiztype(BizTypeEnums.WITHDRAWCASH.getIndex());
//            settlementAccntDetail.setOrderid(id);
//            List<SettlementAccntDetail> list = settlementAccntDetailService.findList(settlementAccntDetail);
//            for (SettlementAccntDetail item : list) {
//                // 更新账单明细提现记录状态
//                SettlementAccntDetail updateRecord = new SettlementAccntDetail();
//                updateRecord.setId(item.getId());
//                updateRecord.setStatus(AccountStatusEnums.WithdrawBOHUI.getIndex());
//                settlementAccntDetailService.updateByPrimaryKeySelective(updateRecord);
//            }
//            
//            // 更新账户总账余额
//            log.info("提现驳回,修改账户余额表余额参数:accountid={}, hotelid={}, role={}, cash={}", accountid, hotelid, role, cash);
//            int ledgerCount = iOfficeService.updateOwnerBanlance(accountid, hotelid, role, cash);
//            log.info("提现驳回,修改账户余额表余额记录数:{}", ledgerCount);
//            result = 1;
//        } catch (Exception e) {
//            result = -1;
//            log.error("销售提现申请ID: {}人事审核驳回时出错: {}", id, e.getLocalizedMessage());
//        }
//        return result;
//    }
//	
//}