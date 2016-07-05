/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.hotel.entity.RfCrmHotel;
import com.thinkgem.crm.hotel.dao.RfCrmHotelDao;

/**
 * CRM酒店公私海信息Service
 * @author 段文昌
 * @version 2016-03-17
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmHotelService extends CrudService<RfCrmHotelDao, RfCrmHotel> {

	public RfCrmHotel get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmHotel> findList(RfCrmHotel rfCrmHotel) {
		return super.findList(rfCrmHotel);
	}
	
	public Page<RfCrmHotel> findPage(Page<RfCrmHotel> page, RfCrmHotel rfCrmHotel) {
		return super.findPage(page, rfCrmHotel);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmHotel rfCrmHotel) {
		super.save(rfCrmHotel);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmHotel rfCrmHotel) {
		super.delete(rfCrmHotel);
	}

	/**
	 * 条件检索全部数据
	 * @param rfCrmHotel
	 * @return
	 */
	public List<RfCrmHotel> findListAll(RfCrmHotel rfCrmHotel){
		return dao.findListAll(rfCrmHotel);
	}
	
}