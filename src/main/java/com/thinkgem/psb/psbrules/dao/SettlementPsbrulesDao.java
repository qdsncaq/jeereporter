/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbrules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.psb.psbrules.entity.SettlementPsbrules;

/**
 * psbrulesDAO接口
 * @author lm
 * @version 2016-04-06
 */
@MyBatisDao
public interface SettlementPsbrulesDao extends CrudDao<SettlementPsbrules> {

	List<SettlementPsbrules> findRulesByPsbid(@Param(value = "psbid")Integer psbid);

	/**
	 * 出账单的时候 根据规则ids 查询对应规则
	 * @param ids
	 * @return
	 */
	List<SettlementPsbrules> selectRulesByids(@Param(value = "ids")String[] ids);
	
}