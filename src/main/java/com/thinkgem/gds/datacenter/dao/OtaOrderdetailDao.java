/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.datacenter.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.datacenter.entity.OtaOrderdetail;

/**
 * 订单详情DAO接口
 * @author LYN
 * @version 2016-03-29
 */
@MyBatisDao
public interface OtaOrderdetailDao extends CrudDao<OtaOrderdetail> {
	
}