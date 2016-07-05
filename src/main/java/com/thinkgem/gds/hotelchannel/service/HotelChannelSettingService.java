/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotelchannel.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.hotelchannel.entity.HotelChannelSetting;
import com.thinkgem.gds.hotelchannel.dao.HotelChannelSettingDao;

/**
 * 酒店渠道分销开关Service
 * @author zhaochuanbin
 * @version 2016-03-16
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class HotelChannelSettingService extends CrudService<HotelChannelSettingDao, HotelChannelSetting> {

	public HotelChannelSetting get(String id) {
		return super.get(id);
	}
	
	public List<HotelChannelSetting> findList(HotelChannelSetting hotelChannelSetting) {
		return super.findList(hotelChannelSetting);
	}
	
	public Page<HotelChannelSetting> findPage(Page<HotelChannelSetting> page, HotelChannelSetting hotelChannelSetting) {
		return super.findPage(page, hotelChannelSetting);
	}
	
	@Transactional(readOnly = false)
	public void save(HotelChannelSetting hotelChannelSetting) {
		super.save(hotelChannelSetting);
	}
	
	@Transactional(readOnly = false)
	public void delete(HotelChannelSetting hotelChannelSetting) {
		super.delete(hotelChannelSetting);
	}
	
}