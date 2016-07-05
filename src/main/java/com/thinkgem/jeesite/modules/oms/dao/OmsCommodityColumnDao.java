/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oms.entity.OmsCommodityColumn;

/**
 * oms商品类型DAO接口
 * @author yaoxiang
 * @version 2016-03-29
 */
@MyBatisDao
public interface OmsCommodityColumnDao extends CrudDao<OmsCommodityColumn> {
	
}