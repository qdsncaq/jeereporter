/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntinterests.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.sc.accntinterests.entity.SettlementAccntInterest;
import com.thinkgem.sc.accntinterests.dao.SettlementAccntInterestDao;

/**
 * accntInterestsService
 * @author ld
 * @version 2016-06-17
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementAccntInterestService extends CrudService<SettlementAccntInterestDao, SettlementAccntInterest> {

	public SettlementAccntInterest get(String id) {
		return super.get(id);
	}
	
	public List<SettlementAccntInterest> findList(SettlementAccntInterest settlementAccntInterest) {
		return super.findList(settlementAccntInterest);
	}
	
	public Page<SettlementAccntInterest> findPage(Page<SettlementAccntInterest> page, SettlementAccntInterest settlementAccntInterest) {
		return super.findPage(page, settlementAccntInterest);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementAccntInterest settlementAccntInterest) {
		super.save(settlementAccntInterest);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementAccntInterest settlementAccntInterest) {
		super.delete(settlementAccntInterest);
	}
	
}