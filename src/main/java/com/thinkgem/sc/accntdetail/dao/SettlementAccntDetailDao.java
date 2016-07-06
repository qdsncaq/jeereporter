///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.accntdetail.dao;
//
//import java.util.List;
//
//import com.thinkgem.jeesite.common.persistence.CrudDao;
//import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
//import com.thinkgem.sc.accntbill.entity.SettlementAccntBill;
//import com.thinkgem.sc.accntdetail.entity.SettlementAccntDetail;
//
///**
// * accntdetailDAO接口
// * @author lm
// * @version 2016-03-18
// */
//@MyBatisDao
//public interface SettlementAccntDetailDao extends CrudDao<SettlementAccntDetail> {
//    /**
//     * 酒店周账单财务确认后更新明细账orderstatus字段.
//     * @param settlementAccntBill
//     * 参数：酒店账单
//     * @return
//     */
//	int updateOrderStatusAfterHotelBillConfirm(SettlementAccntBill settlementAccntBill);
//	
//	/**
//	 * 洗涤月账单重新结算前更新明细状态.
//	 * 
//	 * @param id
//	 * 参数：洗涤账单id
//	 * @return
//	 */
//	int updateStatusBeforeWashingBillResettlement(Long id);
//	
//	/**
//	 * 选择性更新.
//	 * 
//	 * @param record
//	 * @return
//	 */
//	int updateByPrimaryKeySelective(SettlementAccntDetail record);
//	
//	/**
//	 * 查询所有影响账户余额的账单流水明细数据.
//	 * 
//	 * @return
//	 */
//	List<SettlementAccntDetail> findAllBalanceDetails(SettlementAccntDetail entity);
//}