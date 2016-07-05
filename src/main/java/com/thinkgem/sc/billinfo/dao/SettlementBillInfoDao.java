/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.billinfo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.sc.billinfo.entity.SettlementBillInfo;

/**
 * billinfoDAO接口
 * @author lm
 * @version 2016-03-28
 */
@MyBatisDao
public interface SettlementBillInfoDao extends CrudDao<SettlementBillInfo> {
	
	int insert(SettlementBillInfo settlementBillInfo);
	
}