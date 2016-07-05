/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.location.dao;

import com.thinkgem.crm.location.entity.AllAreas;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.location.entity.TDistrict;

import java.util.List;

/**
 * CRM地理位置信息DAO接口
 * @author 段文昌
 * @version 2016-03-15
 */
@MyBatisDao
public interface TDistrictDao extends CrudDao<TDistrict> {

    List<AllAreas> findAllToAllAreas(AllAreas allAreas);
}