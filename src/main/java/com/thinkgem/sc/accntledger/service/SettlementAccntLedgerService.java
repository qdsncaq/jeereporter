/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntledger.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.sc.accntledger.entity.SettlementAccntLedger;
import com.thinkgem.sc.accntledger.dao.SettlementAccntLedgerDao;

/**
 * accntledgerService
 * @author lm
 * @version 2016-03-29
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementAccntLedgerService extends CrudService<SettlementAccntLedgerDao, SettlementAccntLedger> {

	public SettlementAccntLedger get(String id) {
		return super.get(id);
	}
	
	public List<SettlementAccntLedger> findList(SettlementAccntLedger settlementAccntLedger) {
		return super.findList(settlementAccntLedger);
	}
	
	public Page<SettlementAccntLedger> findPage(Page<SettlementAccntLedger> page, SettlementAccntLedger settlementAccntLedger) {
		return super.findPage(page, settlementAccntLedger);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementAccntLedger settlementAccntLedger) {
		super.save(settlementAccntLedger);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementAccntLedger settlementAccntLedger) {
		super.delete(settlementAccntLedger);
	}
	
}