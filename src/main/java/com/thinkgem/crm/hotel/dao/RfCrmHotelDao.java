/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.hotel.entity.RfCrmHotel;

import java.util.List;

/**
 * CRM酒店公私海信息DAO接口
 * @author 段文昌
 * @version 2016-03-17
 */
@MyBatisDao
public interface RfCrmHotelDao extends CrudDao<RfCrmHotel> {
    /**
     * 条件检索全部数据
     * @param rfCrmHotel
     * @return
     */
    public List<RfCrmHotel> findListAll(RfCrmHotel rfCrmHotel);
}