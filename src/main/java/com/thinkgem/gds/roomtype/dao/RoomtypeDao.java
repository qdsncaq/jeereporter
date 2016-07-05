/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomtype.dao;

import com.thinkgem.gds.roomtype.entity.RoomtypePrices;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.roomtype.entity.Roomtype;

import java.util.List;

/**
 * 房型DAO接口
 * @author jiajianhong
 * @version 2016-05-24
 */
@MyBatisDao
public interface RoomtypeDao extends CrudDao<Roomtype> {

    List<RoomtypePrices> findDailyRate(Roomtype roomtype);

    List<RoomtypePrices> findRackRate(Roomtype roomtype);

    List<RoomtypePrices> findWeekRate(Roomtype roomtype);
}