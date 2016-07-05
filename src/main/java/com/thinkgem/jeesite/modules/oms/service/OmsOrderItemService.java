/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oms.dao.OmsOrderItemDao;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderItem;

/**
 * OMS订单Service
 * @author jiajianhong
 * @version 2016-03-17
 */
@Service
@DataSourceName("oms")
@Transactional(readOnly = true)
public class OmsOrderItemService extends CrudService<OmsOrderItemDao, OmsOrderItem> {

	  @Autowired
	private OmsOrderItemDao omsOrderItemDao;
  

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<OmsOrderItem> findPage(Page<OmsOrderItem> page, OmsOrderItem omsOrderItem) {
		return super.findPage(page, omsOrderItem);
	}
    
    /**
     * 修改预发货时间
     * @param orderItem
     * @return
     */
    @Transactional(readOnly = false)
    public int updateInvoiceTime(OmsOrderItem orderItem){
    	return omsOrderItemDao.updateInvoiceTime(orderItem);
 	   
    }
	
 
}