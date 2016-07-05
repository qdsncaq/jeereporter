/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.distributprice.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.distributprice.entity.RackDistributePrice;

/**
 * 酒店分销价DAO接口
 * @author zhaochuanbin
 * @version 2016-03-23
 */
@MyBatisDao
public interface RackDistributePriceDao extends CrudDao<RackDistributePrice> {
    RackDistributePrice getbyhotelandroomtype(@Param(value = "hotelid")Long hotelid,@Param(value="roomtypeid")Long roomtypeid);
}