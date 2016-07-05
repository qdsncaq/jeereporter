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
import com.thinkgem.sc.base.entity.SettlementBaseGoodsrate;
import com.thinkgem.sc.base.dao.SettlementBaseGoodsrateDao;

/**
 * 采购商品提成比例维护Service
 * @author aiqing.chu@fangbaba.com
 * @version 2016-07-01
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementBaseGoodsrateService extends CrudService<SettlementBaseGoodsrateDao, SettlementBaseGoodsrate> {

	public SettlementBaseGoodsrate get(String id) {
		return super.get(id);
	}
	
	public List<SettlementBaseGoodsrate> findList(SettlementBaseGoodsrate settlementBaseGoodsrate) {
		return super.findList(settlementBaseGoodsrate);
	}
	
	public Page<SettlementBaseGoodsrate> findPage(Page<SettlementBaseGoodsrate> page, SettlementBaseGoodsrate settlementBaseGoodsrate) {
		return super.findPage(page, settlementBaseGoodsrate);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementBaseGoodsrate settlementBaseGoodsrate) {
		super.save(settlementBaseGoodsrate);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementBaseGoodsrate settlementBaseGoodsrate) {
		super.delete(settlementBaseGoodsrate);
	}
	
}