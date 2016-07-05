/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbhoteloperlog.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.psb.psbhoteloperlog.entity.THotelOperateLog;

/**
 * psbhoteloperlogDAO接口
 * @author lm
 * @version 2016-04-11
 */
@MyBatisDao
public interface THotelOperateLogDao extends CrudDao<THotelOperateLog> {

	/**
	 * 根据酒店id 和 时间 查询酒店上线下线天数 以及最后一次上线时间 最后一次下线时间
	 * @param hotelid
	 * @param monthFirstDay
	 * @param monthLastDay
	 * @return
	 */
	List<THotelOperateLog> gethotellog(@Param(value = "hotelid")String hotelid, @Param(value = "begin")Date begin,
			@Param(value = "end")Date end);
	
}