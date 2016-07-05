/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticImpObj;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderItemSplit;

/**
 * 物流单号DAO接口
 * @author oms
 * @version 2016-05-20
 */
@MyBatisDao
public interface OmsOrderItemSplitDao extends CrudDao<OmsOrderItemSplit> {

	/**
	 * 运单号的最大值
	 * @return
	 */
	Integer findMaxYundan();
	
	
	/**
	 * 
	 * @param rowObj
	 */
	public int updateForImp(OmsLogisticImpObj rowObj); 
}