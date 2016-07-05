/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticImpObj;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderItem;

/**
 * oms订单详情DAO接口
 * @author jiajianhong
 * @version 2016-03-18
 */
@MyBatisDao
public interface OmsOrderItemDao extends CrudDao<OmsOrderItem> {
	
	/**
	 * 修改发货时间
	 * @param item
	 * @return
	 */
	public int updateInvoiceTime(OmsOrderItem item);


	/**
	 * 
	 * @param itemId 主键 
	 * @param yundanBegin 运单号开始 
	 * @param yundanEnd 运单号结束
	 */
	public void updateYundan(@Param("itemId")long id, @Param("yundanBegin")int yundanBegin, @Param("yundanEnd")int yundanEnd);
	
}