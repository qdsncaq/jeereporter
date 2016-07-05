/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.service;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oms.entity.OmsMarketInfo;
import com.thinkgem.jeesite.modules.oms.dao.OmsMarketInfoDao;

/**
 * oms 商场模块Service
 * @author yaoxiang
 * @version 2016-03-28
 */
@Service
@DataSourceName("oms")
@Transactional(readOnly = true)
public class OmsMarketInfoService extends CrudService<OmsMarketInfoDao, OmsMarketInfo> {

	@Autowired
	private OmsMarketInfoDao marketInfoDao;
	
	/**
	 * 商铺选择 
	 * @return
	 */
	public List<Map<String,Object>> findMarketSelect(){
		return marketInfoDao.findMarketInfoSelect();
	}
	public OmsMarketInfo get(String id) {
		return super.get(id);
	}
	
	public List<OmsMarketInfo> findList(OmsMarketInfo omsMarketInfo) {
		return super.findList(omsMarketInfo);
	}
	
	public Page<OmsMarketInfo> findPage(Page<OmsMarketInfo> page, OmsMarketInfo omsMarketInfo) {
		return super.findPage(page, omsMarketInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(OmsMarketInfo omsMarketInfo) {
		super.save(omsMarketInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(OmsMarketInfo omsMarketInfo) {
		super.delete(omsMarketInfo);
	}
	
}