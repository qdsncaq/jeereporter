/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbbill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.psb.psbbill.entity.SettlementPsbhotelbill;
import com.thinkgem.psb.psbhotelbillinit.entity.SettlementPsbhotelbillinit;

/**
 * psbbillDAO接口
 * @author lm
 * @version 2016-04-21
 */
@MyBatisDao
public interface SettlementPsbhotelbillDao extends CrudDao<SettlementPsbhotelbill> {

	/**
	 * 确认生成账单,批量插入
	 * @param genbills
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	int batchInsert(@Param(value = "hotelbillList")List<SettlementPsbhotelbill> hotelbillList);

	/**
	 * 根据酒店id 去重 开始时间-结束时间 未结算
	 * @param genhotelbill
	 * @return
	 */
	SettlementPsbhotelbill getPeriodBillByhotelid(SettlementPsbhotelbill genhotelbill);

	// 批量 修改账单状态为: 已结算
	int settlebill(@Param(value = "ids")String[] arrids);

	// 批量 修改账单状态为: 本期不结算
	int nobill(@Param(value = "ids")String[] arrids);
	
}