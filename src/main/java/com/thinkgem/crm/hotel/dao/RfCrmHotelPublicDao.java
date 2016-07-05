/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.hotel.entity.RfCrmHotelPublic;
import org.springframework.transaction.annotation.Transactional;

/**
 * CRM酒店公私海信息DAO接口
 * @author 段文昌
 * @version 2016-03-17
 */
@MyBatisDao
public interface RfCrmHotelPublicDao extends CrudDao<RfCrmHotelPublic> {
    /**
     * 退还公海(根据酒店id)
     * @param rfCrmHotelPublic
     */
    public void returnPublic(RfCrmHotelPublic rfCrmHotelPublic);
    /**
     * 退还公海(根据人)
     * @param rfCrmHotelPublic
     */
    public void returnPublicByUser(RfCrmHotelPublic rfCrmHotelPublic);
}