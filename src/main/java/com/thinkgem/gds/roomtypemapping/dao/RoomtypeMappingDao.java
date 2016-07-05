/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomtypemapping.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.gds.roomtypemapping.entity.RoomtypeMapping;

/**
 * 房型映射DAO接口
 * @author 姜贺
 * @version 2016-04-20
 */
@MyBatisDao
public interface RoomtypeMappingDao extends CrudDao<RoomtypeMapping> {
	 public List<Long> findRoomtypeByName(@Param(value = "hotelid") Long hotelid,@Param(value = "name") String roomtypename);
}