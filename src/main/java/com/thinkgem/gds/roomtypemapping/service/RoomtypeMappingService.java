///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.gds.roomtypemapping.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.fangbaba.gds.face.service.IHotelChangePushService;
//import com.thinkgem.gds.roomtypemapping.dao.RoomtypeMappingDao;
//import com.thinkgem.gds.roomtypemapping.entity.RoomtypeMapping;
//import com.thinkgem.jeesite.common.annotation.DataSourceName;
//import com.thinkgem.jeesite.common.persistence.Page;
//import com.thinkgem.jeesite.common.service.CrudService;
//
///**
// * 房型映射Service
// * @author 姜贺
// * @version 2016-04-20
// */
//@Service
//@DataSourceName("gds")
//@Transactional(readOnly = true)
//public class RoomtypeMappingService extends CrudService<RoomtypeMappingDao, RoomtypeMapping> {
//
//	@Autowired
//	private RoomtypeMappingDao roomtypeMappingDao;
//	@Autowired
//	private IHotelChangePushService iHotelChangePushService;
//	public RoomtypeMapping get(String id) {
//		return super.get(id);
//	}
//	
//	public List<RoomtypeMapping> findList(RoomtypeMapping roomtypeMapping) {
//		return super.findList(roomtypeMapping);
//	}
//	
//	public Page<RoomtypeMapping> findPage(Page<RoomtypeMapping> page, RoomtypeMapping roomtypeMapping) {
//		return super.findPage(page, roomtypeMapping);
//	}
//	
//	@Transactional(readOnly = false)
//	public void save(RoomtypeMapping roomtypeMapping) {
//		super.save(roomtypeMapping);
//		
//	}
//	
//	@Transactional(readOnly = false)
//	public void delete(RoomtypeMapping roomtypeMapping) {
//		super.delete(roomtypeMapping);
//		iHotelChangePushService.sendHotelChange(roomtypeMapping.getHotelid());
//	}
//	 public List<Long> findRoomtypeByName(Long hotelid,String roomtypename){
//		return roomtypeMappingDao.findRoomtypeByName(hotelid,roomtypename);
//	 }
//}