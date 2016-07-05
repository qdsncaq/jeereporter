/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.hotel.entity.RfCrmHotelPublic;
import com.thinkgem.crm.hotel.dao.RfCrmHotelPublicDao;

/**
 * CRM酒店公私海信息Service
 * @author 段文昌
 * @version 2016-03-17
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmHotelPublicService extends CrudService<RfCrmHotelPublicDao, RfCrmHotelPublic> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected RfCrmHotelPublicDao dao;

	public RfCrmHotelPublic get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmHotelPublic> findList(RfCrmHotelPublic rfCrmHotelPublic) {
		return super.findList(rfCrmHotelPublic);
	}
	
	public Page<RfCrmHotelPublic> findPage(Page<RfCrmHotelPublic> page, RfCrmHotelPublic rfCrmHotelPublic) {
		return super.findPage(page, rfCrmHotelPublic);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmHotelPublic rfCrmHotelPublic) {
		super.save(rfCrmHotelPublic);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmHotelPublic rfCrmHotelPublic) {
		super.delete(rfCrmHotelPublic);
	}

	/**
	 * 退还公海(根据酒店id)
	 * @param rfCrmHotelPublic
	 */
	@Transactional(readOnly = false)
	public void returnPublic(RfCrmHotelPublic rfCrmHotelPublic) {
		rfCrmHotelPublic.setUpdateTime(new Date());
		rfCrmHotelPublic.setReturnTime(new Date());
		rfCrmHotelPublic.setHotelUserId(null);
		rfCrmHotelPublic.setIsPrivate("2");
		dao.returnPublic(rfCrmHotelPublic);
	}
	
	/**
	 * 退还公海(根据人)
	 * @param rfCrmHotelPublic
	 */
	@Transactional(readOnly = false)
	public void returnPublicByUser(RfCrmHotelPublic rfCrmHotelPublic) {
		rfCrmHotelPublic.setUpdateTime(new Date());
		rfCrmHotelPublic.setReturnTime(new Date());
		rfCrmHotelPublic.setIsPrivate("2");
		dao.returnPublicByUser(rfCrmHotelPublic);
	}
}