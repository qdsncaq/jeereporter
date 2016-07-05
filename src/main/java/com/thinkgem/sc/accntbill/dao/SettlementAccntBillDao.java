/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntbill.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.sc.accntbill.entity.SettlementAccntBill;

/**
 * accntbillDAO接口
 * @author lm
 * @version 2016-03-21
 */
@MyBatisDao
public interface SettlementAccntBillDao extends CrudDao<SettlementAccntBill> {

	/**
     * 调整金额 计算离店时间
     * @param leftDate
     * @param accountid
     * @param accountrole
     * @param biztype
     * @return
     */
	SettlementAccntBill selectAccBillByConditions(@Param(value = "leftDate")Date leftDate,
			@Param(value = "accountid")String accountid, @Param(value = "accountrole")Integer accountrole, @Param(value = "biztype")Integer biztype);

	
}