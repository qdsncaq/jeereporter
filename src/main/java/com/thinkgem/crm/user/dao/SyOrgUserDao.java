/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.crm.location.entity.AllAreas;
import org.apache.ibatis.annotations.Param;

import com.thinkgem.crm.user.entity.SyOrgUser;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * BMS用户表DAO接口
 * @author 李雪楠
 * @version 2016-03-23
 */
@MyBatisDao
public interface SyOrgUserDao extends CrudDao<SyOrgUser> {
	/**
	 * 查询没有配置销售区域的剩余销售用户
	 */
	public List<SyOrgUser> findListNotExistUserArea(SyOrgUser syOrgUser) ;

    List<AllAreas> findUsersByCode(List<String> codes);
	
//	public List<SyOrgUser> getSellersByProvCode(@Param("provCode")Integer provCode);
	public List<Map<String,String>> getSellersByProvCode(@Param("provCode")Integer provCode);
	 
}