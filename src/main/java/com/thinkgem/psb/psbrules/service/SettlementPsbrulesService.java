/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbrules.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.psb.psbrules.entity.SettlementPsbrules;
import com.thinkgem.psb.psbrules.dao.SettlementPsbrulesDao;

/**
 * psbrulesService
 * @author lm
 * @version 2016-04-06
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementPsbrulesService extends CrudService<SettlementPsbrulesDao, SettlementPsbrules> {
	
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SettlementPsbrulesDao settlementPsbrulesDao;

	public SettlementPsbrules get(String id) {
		return super.get(id);
	}
	
	public List<SettlementPsbrules> findList(SettlementPsbrules settlementPsbrules) {
		return super.findList(settlementPsbrules);
	}
	
	public Page<SettlementPsbrules> findPage(Page<SettlementPsbrules> page, SettlementPsbrules settlementPsbrules) {
		return super.findPage(page, settlementPsbrules);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementPsbrules settlementPsbrules) {
		super.save(settlementPsbrules);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementPsbrules settlementPsbrules) {
		super.delete(settlementPsbrules);
	}

	@Transactional(readOnly = false)
	public void update(SettlementPsbrules settlementPsbrules) {
		int updateCounts = settlementPsbrulesDao.update(settlementPsbrules);
		log.info("更新{}条记录.", updateCounts);
	}

	public List<SettlementPsbrules> findRulesByPsbid(Integer integer) {
		return settlementPsbrulesDao.findRulesByPsbid(integer);
	}

	public List<SettlementPsbrules> findAllList() {
		return settlementPsbrulesDao.findAllList(null);
	}

	public List<SettlementPsbrules> findRulesByPsbids(String[] ids) {
		return settlementPsbrulesDao.selectRulesByids(ids);
	}
	
}