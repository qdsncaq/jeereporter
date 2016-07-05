/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.schedulercenter.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.sc.schedulercenter.entity.SettlementSchedulerCenter;

/**
 * schedulercenterDAO接口
 * @author lm
 * @version 2016-03-23
 */
@MyBatisDao
public interface SettlementSchedulerCenterDao extends CrudDao<SettlementSchedulerCenter> {
	
}