/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.dao;

import java.util.List;

import com.thinkgem.crm.hotel.entity.EHotel;
import com.thinkgem.crm.hotel.entity.TRoomType;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * CRM酒店信息DAO接口
 * @author 李雪楠
 * @version 2016-03-28
 */
@MyBatisDao
public interface EHotelDao extends CrudDao<EHotel> {

    List<EHotel> findSelfBuildHotelPage(EHotel eHotel);

    EHotel getSelfBuildHotel(EHotel eHotel);
    List<TRoomType> findTHotelTypeByHotelId(TRoomType tHotelType);

    EHotel getCheckHotel(EHotel eHotel);
}