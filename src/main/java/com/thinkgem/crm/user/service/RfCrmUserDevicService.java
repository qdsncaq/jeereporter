/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.user.entity.RfCrmUserDevic;
import com.thinkgem.crm.user.dao.RfCrmUserDevicDao;

/**
 * CRM/OMS登录信息统计Service
 * @author 段文昌
 * @version 2016-04-05
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmUserDevicService extends CrudService<RfCrmUserDevicDao, RfCrmUserDevic> {

	public RfCrmUserDevic get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmUserDevic> findList(RfCrmUserDevic rfCrmUserDevic) {
		return super.findList(rfCrmUserDevic);
	}
	
	public Page<RfCrmUserDevic> findPage(Page<RfCrmUserDevic> page, RfCrmUserDevic rfCrmUserDevic) {
		return super.findPage(page, rfCrmUserDevic);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmUserDevic rfCrmUserDevic) {
		super.save(rfCrmUserDevic);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmUserDevic rfCrmUserDevic) {
		super.delete(rfCrmUserDevic);
	}

	/**
	 * 统计登录情况列表
	 * @param entity
	 * @return
	 */
	public List<RfCrmUserDevic> findOmsLoginList(RfCrmUserDevic entity){
		List<RfCrmUserDevic> list = dao.findOmsLoginList(entity);
		return list;
	}

	/**
	 * 分页查询登录情况
	 * @param page
	 * @param entity
     * @return
     */
	public Page<RfCrmUserDevic> findOmsLoginPage(Page<RfCrmUserDevic> page, RfCrmUserDevic entity) {
		entity.setPage(page);
		page.setOrderBy(" d.provcode, d.citycode,a.lastTime desc ");
		page.setList(dao.findOmsLoginList(entity));
		return page;
	}
	
}