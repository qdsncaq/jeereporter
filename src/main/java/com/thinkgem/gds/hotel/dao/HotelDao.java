/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotel.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.gds.hotel.entity.Hotel;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * modelDAO接口
 * @author tankai
 * @version 2016-03-11
 */
@MyBatisDao
public interface HotelDao extends CrudDao<Hotel> {
	//批量退回公海
	int returnPublicByUser(@Param("isPrivate")String isPrivate,@Param("updateTime")Date updateTime,@Param("returnTime")Date returnTime,@Param("hotelUserId")String hotelUserId,@Param("hotelId")Long hotelId);
	//公海酒店批量分配销售
	int assignHotelToSeller(@Param("sellId")String sellId,@Param("updateTime")Date updateTime,@Param("returnTime")Date returnTime,@Param("hotelId")Long hotelId);
	//私海酒店批量转移
	int transferHotelToSeller(@Param("sellId")String sellId,@Param("updateTime")Date updateTime,@Param("returnTime")Date returnTime,@Param("hotelUserId")String hotelUserId,@Param("hotelId")Long hotelId);
}