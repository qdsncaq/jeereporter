/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.location.service;

import java.util.List;

import com.thinkgem.crm.location.entity.AllAreas;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.dao.TProvinceDao;

/**
 * CRM地理位置信息Service
 * @author 段文昌
 * @version 2016-03-15
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class TProvinceService extends CrudService<TProvinceDao, TProvince> {

	@Autowired
	private TProvinceDao dao;

	public TProvince get(String id) {
		return super.get(id);
	}
	
	public List<TProvince> findList(TProvince tProvince) {
        tProvince.getPage().setOrderBy("proname asc");
		return super.findList(tProvince);
	}
	
	public Page<TProvince> findPage(Page<TProvince> page, TProvince tProvince) {
		return super.findPage(page, tProvince);
	}
	
	@Transactional(readOnly = false)
	public void save(TProvince tProvince) {
		super.save(tProvince);
	}
	
	@Transactional(readOnly = false)
	public void delete(TProvince tProvince) {
		super.delete(tProvince);
	}

	public TProvince getByCode(String code) {
		return dao.getByCode(code);
	}

    public List<AllAreas> findAllToAllAreas() {
        return dao.findAllToAllAreas();
    }
}