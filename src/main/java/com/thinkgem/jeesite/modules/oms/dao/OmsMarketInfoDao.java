/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oms.entity.OmsMarketInfo;

/**
 * oms 商场模块DAO接口
 * @author yaoxiang
 * @version 2016-03-28
 */
@MyBatisDao
public interface OmsMarketInfoDao extends CrudDao<OmsMarketInfo> {
	
	/**
	 * 商铺选择 
	 * @return
	 */
	public List<Map<String,Object>> findMarketInfoSelect();
	
	
	
}