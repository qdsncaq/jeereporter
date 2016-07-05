/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.dao;

import com.thinkgem.crm.location.entity.AllAreas;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.user.entity.RfCrmUserArea;

import java.util.List;

/**
 * 用户区域设置DAO接口
 * @author 李雪楠
 * @version 2016-03-23
 */
@MyBatisDao
public interface RfCrmUserAreaDao extends CrudDao<RfCrmUserArea> {

    List<AllAreas> findUserByArea(RfCrmUserArea rfCrmUserArea);

	TProvince queryProvince(String userId);
}