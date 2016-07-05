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
import com.thinkgem.jeesite.modules.oms.entity.OmsCommodityColumn;
import com.thinkgem.jeesite.modules.oms.dao.OmsCommodityColumnDao;

/**
 * oms商品类型Service
 * @author yaoxiang
 * @version 2016-03-29
 */
@Service
@DataSourceName("oms")
@Transactional(readOnly = true)
public class OmsCommodityColumnService extends CrudService<OmsCommodityColumnDao, OmsCommodityColumn> {

	public OmsCommodityColumn get(String id) {
		return super.get(id);
	}
	
	public List<OmsCommodityColumn> findList(OmsCommodityColumn omsCommodityColumn) {
		return super.findList(omsCommodityColumn);
	}
	
	public Page<OmsCommodityColumn> findPage(Page<OmsCommodityColumn> page, OmsCommodityColumn omsCommodityColumn) {
		return super.findPage(page, omsCommodityColumn);
	}
	
	@Transactional(readOnly = false)
	public void save(OmsCommodityColumn omsCommodityColumn) {
		super.save(omsCommodityColumn);
	}
	
	@Transactional(readOnly = false)
	public void delete(OmsCommodityColumn omsCommodityColumn) {
		super.delete(omsCommodityColumn);
	}
	
}