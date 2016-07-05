/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.visit.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.visit.entity.RfCrmVisitRecord;
import com.thinkgem.crm.visit.dao.RfCrmVisitRecordDao;

/**
 * CRM拜访记录Service
 * @author 段文昌
 * @version 2016-03-14
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmVisitRecordService extends CrudService<RfCrmVisitRecordDao, RfCrmVisitRecord> {

	public RfCrmVisitRecord get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmVisitRecord> findList(RfCrmVisitRecord rfCrmVisitRecord) {
		return super.findList(rfCrmVisitRecord);
	}
	
	public Page<RfCrmVisitRecord> findPage(Page<RfCrmVisitRecord> page, RfCrmVisitRecord rfCrmVisitRecord) {
		page.setOrderBy(" a.visit_time desc,h.prov_code desc,a.visit_user desc,a.visit_hotel_id desc");
		return super.findPage(page, rfCrmVisitRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmVisitRecord rfCrmVisitRecord) {
		super.save(rfCrmVisitRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmVisitRecord rfCrmVisitRecord) {
		super.delete(rfCrmVisitRecord);
	}
	
}