/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticsDirect;

/**
 * 直送基础数据DAO接口
 * @author oms
 * @version 2016-05-14
 */
@MyBatisDao
public interface OmsLogisticsDirectDao extends CrudDao<OmsLogisticsDirect> {
	
}