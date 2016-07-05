/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomprice.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.roomprice.entity.Roomprice;

/**
 * 房型价格DAO接口
 * @author jiajianhong
 * @version 2016-04-01
 */
@MyBatisDao
public interface RoompriceDao extends CrudDao<Roomprice> {
	
}