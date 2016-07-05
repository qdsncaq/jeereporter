/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.hotel.entity.OnOffLine;

import java.util.List;

/**
 * 酒店上下线信息统计DAO接口
 * @author 段文昌
 * @version 2016-04-21
 */
@MyBatisDao
public interface OnOffLineDao extends CrudDao<OnOffLine> {
    /**
     * 查询上下线明细
     * @param entity
     * @return
     */
    public List<OnOffLine> findDetailsList(OnOffLine entity);
}