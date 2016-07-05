/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.orderdetail.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.orderdetail.entity.Orderdetails;

/**
 * orderdetailDAO接口
 * @author tankai
 * @version 2016-03-14
 */
@MyBatisDao
public interface OrderdetailsDao extends CrudDao<Orderdetails> {
	
}