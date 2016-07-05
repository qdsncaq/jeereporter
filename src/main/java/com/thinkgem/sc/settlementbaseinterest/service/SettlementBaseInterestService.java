/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.settlementbaseinterest.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.sc.settlementbaseinterest.entity.SettlementBaseInterest;
import com.thinkgem.sc.settlementbaseinterest.dao.SettlementBaseInterestDao;

/**
 * SettlementBaseInterestService
 * @author ft
 * @version 2016-06-14
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementBaseInterestService extends CrudService<SettlementBaseInterestDao, SettlementBaseInterest> {

	public SettlementBaseInterest get(String id) {
		return super.get(id);
	}
	
	public List<SettlementBaseInterest> findList(SettlementBaseInterest settlementBaseInterest) {
		return super.findList(settlementBaseInterest);
	}
	
	public Page<SettlementBaseInterest> findPage(Page<SettlementBaseInterest> page, SettlementBaseInterest settlementBaseInterest) {
		return super.findPage(page, settlementBaseInterest);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementBaseInterest settlementBaseInterest) {
		super.save(settlementBaseInterest);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementBaseInterest settlementBaseInterest) {
		super.delete(settlementBaseInterest);
	}
	
}