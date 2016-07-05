/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntemobalance.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.sc.accntemobalance.dao.SettlementAccntEomBalanceDao;
import com.thinkgem.sc.accntemobalance.entity.SettlementAccntEomBalance;

/**
 * settlementAccntEmobalanceService
 * @author lm
 * @version 2016-06-04
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementAccntEomBalanceService extends CrudService<SettlementAccntEomBalanceDao, SettlementAccntEomBalance> {

	public SettlementAccntEomBalance get(String id) {
		return super.get(id);
	}
	
	public List<SettlementAccntEomBalance> findList(SettlementAccntEomBalance settlementAccntEomBalance) {
		return super.findList(settlementAccntEomBalance);
	}
	
	public Page<SettlementAccntEomBalance> findPage(Page<SettlementAccntEomBalance> page, SettlementAccntEomBalance settlementAccntEomBalance) {
		return super.findPage(page, settlementAccntEomBalance);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementAccntEomBalance settlementAccntEomBalance) {
		super.save(settlementAccntEomBalance);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementAccntEomBalance settlementAccntEomBalance) {
		super.delete(settlementAccntEomBalance);
	}
	
}