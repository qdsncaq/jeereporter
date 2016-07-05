/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.user.entity.RfCrmUserDevic;

import java.util.List;

/**
 * CRM/OMS登录信息统计DAO接口
 * @author 段文昌
 * @version 2016-04-05
 */
@MyBatisDao
public interface RfCrmUserDevicDao extends CrudDao<RfCrmUserDevic> {

    /**
     * 统计登录情况列表
     * @param entity
     * @return
     */
    List<RfCrmUserDevic> findOmsLoginList(RfCrmUserDevic entity);
}