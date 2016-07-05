/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticsDirect;
import com.thinkgem.jeesite.modules.oms.dao.OmsLogisticsDirectDao;

/**
 * 直送基础数据Service
 * @author oms
 * @version 2016-05-14
 */
@Service
@DataSourceName("oms")
@Transactional(readOnly = true)
public class OmsLogisticsDirectService extends CrudService<OmsLogisticsDirectDao, OmsLogisticsDirect> {

	public OmsLogisticsDirect get(String id) {
		return super.get(id);
	}
	
	public List<OmsLogisticsDirect> findList(OmsLogisticsDirect omsLogisticsDirect) {
		return super.findList(omsLogisticsDirect);
	}
	
	public Page<OmsLogisticsDirect> findPage(Page<OmsLogisticsDirect> page, OmsLogisticsDirect omsLogisticsDirect) {
		return super.findPage(page, omsLogisticsDirect);
	}
	
	@Transactional(readOnly = false)
	public void save(OmsLogisticsDirect omsLogisticsDirect) {
		super.save(omsLogisticsDirect);
	}
	
	@Transactional(readOnly = false)
	public void delete(OmsLogisticsDirect omsLogisticsDirect) {
		super.delete(omsLogisticsDirect);
	}
	
}