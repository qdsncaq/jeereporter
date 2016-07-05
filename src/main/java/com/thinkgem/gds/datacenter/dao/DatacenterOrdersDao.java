/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.datacenter.dao;

import com.thinkgem.gds.datacenter.entity.DatacenterOrders;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 分销订单查询DAO接口
 * @author LYN
 * @version 2016-03-15
 */
@MyBatisDao
public interface DatacenterOrdersDao extends CrudDao<DatacenterOrders> {
	
}