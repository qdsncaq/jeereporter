/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.base.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.sc.base.entity.SettlementBaseGoodsrate;

/**
 * 采购商品提成比例维护DAO接口
 * @author aiqing.chu@fangbaba.com
 * @version 2016-07-01
 */
@MyBatisDao
public interface SettlementBaseGoodsrateDao extends CrudDao<SettlementBaseGoodsrate> {
	
}