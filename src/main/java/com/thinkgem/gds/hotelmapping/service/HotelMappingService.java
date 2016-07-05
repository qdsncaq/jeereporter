/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotelmapping.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.hotelmapping.entity.HotelMapping;
import com.thinkgem.gds.hotelmapping.dao.HotelMappingDao;

/**
 * 酒店映射Service
 * @author zhangyajun
 * @version 2016-04-15
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class HotelMappingService extends CrudService<HotelMappingDao, HotelMapping> {

	public HotelMapping get(String id) {
		return super.get(id);
	}
	
	public List<HotelMapping> findList(HotelMapping hotelMapping) {
		return super.findList(hotelMapping);
	}
	
	public Page<HotelMapping> findPage(Page<HotelMapping> page, HotelMapping hotelMapping) {
		return super.findPage(page, hotelMapping);
	}
	
	@Transactional(readOnly = false)
	public void save(HotelMapping hotelMapping) {
		super.save(hotelMapping);
	}
	
	@Transactional(readOnly = false)
	public void delete(HotelMapping hotelMapping) {
		super.delete(hotelMapping);
	}
	
}