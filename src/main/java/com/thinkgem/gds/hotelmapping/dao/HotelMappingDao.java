/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotelmapping.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.hotelmapping.entity.HotelMapping;

/**
 * 酒店映射DAO接口
 * @author zhangyajun
 * @version 2016-04-15
 */
@MyBatisDao
public interface HotelMappingDao extends CrudDao<HotelMapping> {
	
}