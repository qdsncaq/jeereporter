/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.onoffdetail.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.psb.onoffdetail.entity.OnOffLineDetail;

/**
 * onoffdetailDAO接口
 * @author lm
 * @version 2016-05-11
 */
@MyBatisDao
public interface OnOffLineDetailDao extends CrudDao<OnOffLineDetail> {
	
	/**
	 * 根据酒店id 起始结束时间查询酒店本段时间内的上下线记录
	 * @param hotelid
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	List<OnOffLineDetail> selectHotelOfflineByConditions(@Param(value = "hotelid")String hotelid,
			@Param(value = "begintime")Date begintime, @Param(value = "endtime")Date endtime);

	List<OnOffLineDetail> selectHotelOfflineByConditionsTime(@Param(value = "hotelid")String hotelid,
			@Param(value = "begintime")Date begintime, @Param(value = "endtime")Date endtime);

}