/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbhoteloperlog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.psb.psbhoteloperlog.entity.THotelOperateLog;
import com.thinkgem.psb.psbhoteloperlog.dao.THotelOperateLogDao;

/**
 * psbhoteloperlogService
 * @author lm
 * @version 2016-04-11
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class THotelOperateLogService extends CrudService<THotelOperateLogDao, THotelOperateLog> {

	public THotelOperateLog get(String id) {
		return super.get(id);
	}
	
	public List<THotelOperateLog> findList(THotelOperateLog tHotelOperateLog) {
		return super.findList(tHotelOperateLog);
	}
	
	public Page<THotelOperateLog> findPage(Page<THotelOperateLog> page, THotelOperateLog tHotelOperateLog) {
		return super.findPage(page, tHotelOperateLog);
	}
	
	@Transactional(readOnly = false)
	public void save(THotelOperateLog tHotelOperateLog) {
		super.save(tHotelOperateLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(THotelOperateLog tHotelOperateLog) {
		super.delete(tHotelOperateLog);
	}
	
}