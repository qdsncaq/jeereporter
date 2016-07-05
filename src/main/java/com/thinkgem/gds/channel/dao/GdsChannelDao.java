/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.channel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.channel.entity.GdsChannel;

/**
 * 渠道管理DAO接口
 * @author tankai
 * @version 2016-03-18
 */
@MyBatisDao
public interface GdsChannelDao extends CrudDao<GdsChannel> {
	
}