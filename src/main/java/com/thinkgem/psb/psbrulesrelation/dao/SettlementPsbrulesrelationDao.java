/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbrulesrelation.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.psb.psbrulesrelation.entity.SettlementPsbrulesrelation;

/**
 * psbrulesrelationDAO接口
 * @author lm
 * @version 2016-04-08
 */
@MyBatisDao
public interface SettlementPsbrulesrelationDao extends CrudDao<SettlementPsbrulesrelation> {

	/**
	 * 根据省 区 查询规则
	 * @param hotelid
	 * @param citycode 
	 * @return
	 */
	Map<String, Object> getRulesByhotelid(@Param(value = "procode")String procode, @Param(value = "citycode")String citycode);
	
	@Override
	public int update(SettlementPsbrulesrelation relation);

	/**
	 * 查询酒店规则是否已经存在
	 * @param hotelid
	 * @return
	 */
	SettlementPsbrulesrelation getByHotelid(@Param(value = "hotelid")String hotelid);

	/**
	 * 查询区域规则是否已经存在
	 * @param procode
	 * @param citycode
	 * @param discode
	 * @return
	 */
	SettlementPsbrulesrelation getByDiscode(@Param(value = "procode")String procode, @Param(value = "citycode")String citycode,
			@Param(value = "discode")String discode);

	/**
	 * 查询城市规则是否存在
	 * @param citycode
	 * @param procode
	 * @return
	 */
	SettlementPsbrulesrelation getByCitycode(@Param(value = "citycode")String citycode, @Param(value = "procode")String procode);

	/**
	 * 批量删除
	 * @param arrids
	 * @return
	 */
	int deleteids(@Param(value = "ids")String[] arrids);
	
}