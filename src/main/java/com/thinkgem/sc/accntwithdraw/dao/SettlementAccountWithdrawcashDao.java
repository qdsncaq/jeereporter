///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.accntwithdraw.dao;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Param;
//
//import com.thinkgem.jeesite.common.persistence.CrudDao;
//import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
//import com.thinkgem.sc.accntwithdraw.entity.SettlementAccountWithdrawcash;
//
///**
// * accntwithdrawDAO接口
// * @author lm
// * @version 2016-03-16
// */
//@MyBatisDao
//public interface SettlementAccountWithdrawcashDao extends CrudDao<SettlementAccountWithdrawcash> {
//
//	List<SettlementAccountWithdrawcash> selectWithdrawcashByids(@Param(value = "ids")List<Long> lids);
//
//	// 审核通过修改状态
//	int updateDoneAccntDetailStatus(@Param(value = "accountid")String accountid, @Param(value = "hotelid")String hotelid,
//			@Param(value = "withdrawcashid")String withdrawcashid, @Param(value = "status")Integer status);
//	
//	// 打款修改状态
//	int updatePaydone(@Param(value = "ids")List<Long> lids);
//
//	// 驳回修改状态
//	int updateNoAccntDetailStatus(@Param(value = "accountid")String accountid, @Param(value = "hotelid")String hotelid,
//			@Param(value = "withdrawcashid")String withdrawcashid, @Param(value = "status")Integer status);
//	
//	int updateByPrimaryKeySelective(SettlementAccountWithdrawcash record);
//	
//}