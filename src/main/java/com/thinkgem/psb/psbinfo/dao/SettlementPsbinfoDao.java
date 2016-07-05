/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbinfo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.psb.psbinfo.entity.SettlementPsbinfo;

/**
 * psbinfoDAO接口
 * @author lm
 * @version 2016-04-06
 */
@MyBatisDao
public interface SettlementPsbinfoDao extends CrudDao<SettlementPsbinfo> {

	List<SettlementPsbinfo> findAllPsb();
	
}