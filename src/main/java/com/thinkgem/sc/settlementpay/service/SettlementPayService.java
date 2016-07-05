/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.settlementpay.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.sc.settlementpay.entity.SettlementPay;
import com.thinkgem.sc.settlementpay.dao.SettlementPayDao;

/**
 * settlementpayService
 * @author lm
 * @version 2016-04-05
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementPayService extends CrudService<SettlementPayDao, SettlementPay> {

	public SettlementPay get(String id) {
		return super.get(id);
	}
	
	public List<SettlementPay> findList(SettlementPay settlementPay) {
		return super.findList(settlementPay);
	}
	
	public Page<SettlementPay> findPage(Page<SettlementPay> page, SettlementPay settlementPay) {
		return super.findPage(page, settlementPay);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementPay settlementPay) {
		super.save(settlementPay);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementPay settlementPay) {
		super.delete(settlementPay);
	}
	
}