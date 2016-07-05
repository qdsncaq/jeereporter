/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotelchannel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.hotelchannel.entity.HotelChannelSetting;

/**
 * 酒店渠道分销开关DAO接口
 * @author zhaochuanbin
 * @version 2016-03-16
 */
@MyBatisDao
public interface HotelChannelSettingDao extends CrudDao<HotelChannelSetting> {
	
}