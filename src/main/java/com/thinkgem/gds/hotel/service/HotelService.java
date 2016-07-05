/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotel.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.hotel.entity.Hotel;
import com.thinkgem.gds.hotel.dao.HotelDao;

/**
 * modelService
 * @author tankai
 * @version 2016-03-11
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class HotelService extends CrudService<HotelDao, Hotel> {
   
	public Hotel get(String id) {
		return super.get(id);
	}
	
	public List<Hotel> findList(Hotel hotel) {
		return super.findList(hotel);
	}
	
	public Page<Hotel> findPage(Page<Hotel> page, Hotel hotel) {
		return super.findPage(page, hotel);
	}
	
	@Transactional(readOnly = false)
	public void save(Hotel hotel) {
		super.save(hotel);
	}
	
	@Transactional(readOnly = false)
	public void delete(Hotel hotel) {
		super.delete(hotel);
	}
	//批量退回公海
	@Transactional(readOnly = false)
	public int returnPublicByUser(String isPrivate,Date updateTime,Date returnTime,String hotelUserId,Long hotelId){
		return dao.returnPublicByUser(isPrivate, updateTime, returnTime, hotelUserId,hotelId);
	}
	/**
	 * 公海酒店批量分配销售
	 * @param sellId  公海酒店分配的销售
	 * @param updateTime
	 * @param returnTime
	 * @param hotelId  酒店id
	 * @return
	 */
	@Transactional(readOnly = false)
	public int assignHotelToSeller(String sellId,Date updateTime,Date returnTime,Long hotelId){
		return dao.assignHotelToSeller(sellId, updateTime, returnTime, hotelId);
	}
	/**
	 * 私海酒店批量转移
	 * @param sellId 转给那个销售
	 * @param updateTime
	 * @param returnTime
	 * @param hotelUserId 酒店当前销售
	 * @return
	 */
	@Transactional(readOnly = false)
	public int transferHotelToSeller(String sellId,Date updateTime,Date returnTime,String hotelUserId,Long hotelId){
		return dao.transferHotelToSeller(sellId, updateTime, returnTime, hotelUserId,hotelId);
	}

}