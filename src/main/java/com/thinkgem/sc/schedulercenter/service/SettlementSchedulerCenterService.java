/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.schedulercenter.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.sc.schedulercenter.entity.SettlementSchedulerCenter;
import com.thinkgem.sc.schedulercenter.dao.SettlementSchedulerCenterDao;

/**
 * schedulercenterService
 * @author lm
 * @version 2016-03-23
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementSchedulerCenterService extends CrudService<SettlementSchedulerCenterDao, SettlementSchedulerCenter> {

	public SettlementSchedulerCenter get(String id) {
		return super.get(id);
	}
	
	public List<SettlementSchedulerCenter> findList(SettlementSchedulerCenter settlementSchedulerCenter) {
		return super.findList(settlementSchedulerCenter);
	}
	
	public Page<SettlementSchedulerCenter> findPage(Page<SettlementSchedulerCenter> page, SettlementSchedulerCenter settlementSchedulerCenter) {
		return super.findPage(page, settlementSchedulerCenter);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementSchedulerCenter settlementSchedulerCenter) {
		super.save(settlementSchedulerCenter);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementSchedulerCenter settlementSchedulerCenter) {
		super.delete(settlementSchedulerCenter);
	}
	
}