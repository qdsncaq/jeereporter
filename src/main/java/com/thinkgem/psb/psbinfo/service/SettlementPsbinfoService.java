/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.psb.psbinfo.dao.SettlementPsbinfoDao;
import com.thinkgem.psb.psbinfo.entity.SettlementPsbinfo;

/**
 * psbinfoService
 * @author lm
 * @version 2016-04-06
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementPsbinfoService extends CrudService<SettlementPsbinfoDao, SettlementPsbinfo> {
	
	@Autowired
	private SettlementPsbinfoDao settlementPsbinfoDao;

	public SettlementPsbinfo get(String id) {
		return super.get(id);
	}
	
	public List<SettlementPsbinfo> findList(SettlementPsbinfo settlementPsbinfo) {
		return super.findList(settlementPsbinfo);
	}
	
	public Page<SettlementPsbinfo> findPage(Page<SettlementPsbinfo> page, SettlementPsbinfo settlementPsbinfo) {
		return super.findPage(page, settlementPsbinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementPsbinfo settlementPsbinfo) {
		super.save(settlementPsbinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementPsbinfo settlementPsbinfo) {
		super.delete(settlementPsbinfo);
	}

	@Transactional(readOnly = false)
	public void update(SettlementPsbinfo settlementPsbinfo) {
		settlementPsbinfo.setDelflag(2); //删除
		settlementPsbinfoDao.update(settlementPsbinfo);
	}

	/**
	 * 查询所有psb厂家 
	 * 	未删除
	 * 	去重
	 * @return
	 */
	public List<SettlementPsbinfo> findAllPsb() {
		return settlementPsbinfoDao.findAllPsb();
	}
	
}