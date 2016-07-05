/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.base.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.sc.base.entity.SettlementBonusProportion;
import com.thinkgem.sc.base.dao.SettlementBonusProportionDao;

/**
 * 区域经理奖金分配比例Service
 * @author aiqing.chu@fangbaba.com
 * @version 2016-06-06
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementBonusProportionService extends CrudService<SettlementBonusProportionDao, SettlementBonusProportion> {

	public SettlementBonusProportion get(String id) {
		return super.get(id);
	}
	
	public List<SettlementBonusProportion> findList(SettlementBonusProportion settlementBonusProportion) {
		return super.findList(settlementBonusProportion);
	}
	
	public Page<SettlementBonusProportion> findPage(Page<SettlementBonusProportion> page, SettlementBonusProportion settlementBonusProportion) {
		return super.findPage(page, settlementBonusProportion);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementBonusProportion settlementBonusProportion) {
		super.save(settlementBonusProportion);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementBonusProportion settlementBonusProportion) {
		super.delete(settlementBonusProportion);
	}
	
}