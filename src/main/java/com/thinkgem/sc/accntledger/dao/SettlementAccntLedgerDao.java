///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.accntledger.dao;
//
//import java.math.BigDecimal;
//
//import org.apache.ibatis.annotations.Param;
//
//import com.thinkgem.jeesite.common.persistence.CrudDao;
//import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
//import com.thinkgem.sc.accntbill.entity.SettlementAccntBill;
//import com.thinkgem.sc.accntledger.entity.SettlementAccntLedger;
//
///**
// * accntledgerDAO接口
// * @author lm
// * @version 2016-03-29
// */
//@MyBatisDao
//public interface SettlementAccntLedgerDao extends CrudDao<SettlementAccntLedger> {
//	
//    //储蓄账户充值 调整金额-
//    int depositRecharge(@Param(value = "accountid") String accountid, @Param(value = "sum") BigDecimal sum, @Param(value = "role") int role);
//
//    //调整金额-后付费账户 金额修改
//    int postpayRecharge(@Param(value = "accountid") String accountid, @Param(value = "sum") BigDecimal sum, @Param(value = "role") int role);
//    
//    /**
//     * 酒店老板周账单财务确认后更新总账余额.
//     * @param begindate
//     * 参数: 账单起始日期.
//     * @param enddate
//     * 参数: 账单截止日期.
//     * @return
//     */
//    int insertOrUpdateBalanceAfterFIConfirmHotelBossAccountBill(SettlementAccntBill settlementAccntBill);
//    
//}