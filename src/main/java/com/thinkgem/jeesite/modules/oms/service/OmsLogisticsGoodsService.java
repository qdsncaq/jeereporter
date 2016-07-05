/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticsGoods;
import com.thinkgem.jeesite.modules.oms.dao.OmsLogisticsGoodsDao;

/**
 * 商品物流数据Service
 * @author oms
 * @version 2016-05-14
 */
@Service
@DataSourceName("oms")
@Transactional(readOnly = true)
public class OmsLogisticsGoodsService extends CrudService<OmsLogisticsGoodsDao, OmsLogisticsGoods> {

	public OmsLogisticsGoods get(String id) {
		return super.get(id);
	}
	
	public List<OmsLogisticsGoods> findList(OmsLogisticsGoods omsLogisticsGoods) {
		return super.findList(omsLogisticsGoods);
	}
	
	public Page<OmsLogisticsGoods> findPage(Page<OmsLogisticsGoods> page, OmsLogisticsGoods omsLogisticsGoods) {
		return super.findPage(page, omsLogisticsGoods);
	}
	
	@Transactional(readOnly = false)
	public void save(OmsLogisticsGoods omsLogisticsGoods) {
		super.save(omsLogisticsGoods);
	}
	
	@Transactional(readOnly = false)
	public void delete(OmsLogisticsGoods omsLogisticsGoods) {
		super.delete(omsLogisticsGoods);
	}
	
}