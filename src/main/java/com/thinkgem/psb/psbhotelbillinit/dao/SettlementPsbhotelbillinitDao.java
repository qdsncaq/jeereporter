/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbhotelbillinit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.psb.psbhotelbillinit.entity.SettlementPsbhotelbillinit;

/**
 * psbhotelbillDAO接口
 * @author lm
 * @version 2016-04-12
 */
@MyBatisDao
public interface SettlementPsbhotelbillinitDao extends CrudDao<SettlementPsbhotelbillinit> {

	// 酒店账单初始化, 查询所有有上线记录的酒店,生成一条账单明细
	List<Map<String, Object>> getAllFirstOnHotel(SettlementPsbhotelbillinit billinit);

	/**
	 * 根据酒店id查询酒店房间数
	 * @param hotelid
	 * @return
	 */
	Integer selectroomNums(@Param(value = "hotelid")String hotelid);

	/**
	 * 查询规则
	 * @param procode
	 * @param citycode
	 * @param discode
	 * @param hotelid
	 * @return
	 */
	Map<String, Object> selectrules(@Param(value = "procode")String procode, @Param(value = "citycode")String citycode,
			@Param(value = "discode")String discode, @Param(value = "hotelid")String hotelid);

	/**
	 * 批量插入到账单初始化表中
	 * 		返回 插入记录条数
	 * @param billinits
	 * @return
	 */
	int batchInsert(@Param(value = "billinits")List<SettlementPsbhotelbillinit> billinits);

	SettlementPsbhotelbillinit getByHotelid(@Param(value = "hotelid")String hotelid);

}