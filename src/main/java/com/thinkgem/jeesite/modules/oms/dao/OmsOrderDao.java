/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticImpObj;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrder;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderLogistic;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderLogisticExp;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderReport;

/**
 * OMS订单DAO接口
 * @author jiajianhong
 * @version 2016-03-17
 */
@MyBatisDao
public interface OmsOrderDao extends CrudDao<OmsOrder> {
	
	
	public List<OmsOrderReport> findReport(OmsOrderReport order);
	
	/**
	 * 导出的列表，不分页，先全部查，
	 * @param orderLogistic
	 * @return
	 */
	public List<OmsOrderLogistic> findLogisticsReport(OmsOrderLogistic orderLogistic);
	
	/**
	 * 获取分页的页面的列表
	 * @param orderLogistic 
	 * @return
	 */
	public List<OmsOrderLogistic> findLogisticsReportPage(OmsOrderLogistic orderLogistic);
	
	public int updateReceiver(OmsOrder order);
	
	public List<OmsOrder> findInvoiceOrders(String invoiceid);
	
	public void updateOrdersExpBatch(@Param("expBatch")String expBatch, @Param("ids")Set<Long> ids);

	/**
	 * 
	 * @param rowObj
	 */
	public void updateForImp(OmsLogisticImpObj rowObj);

	
	/**
	 * 物流那里导出的， 不分页的查询
	 * @param orderLogisticExp
	 * @return
	 */
	public List<OmsOrderLogisticExp> findLogisticsReportExp(
			OmsOrderLogisticExp orderLogisticExp);
}