/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.message.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.message.entity.LMessageLog;
import com.thinkgem.gds.message.dao.LMessageLogDao;

/**
 * 短信Service
 * @author jianghe
 * @version 2016-03-17
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class LMessageLogService extends CrudService<LMessageLogDao, LMessageLog> {

	public LMessageLog get(String id) {
		return super.get(id);
	}
	
	public List<LMessageLog> findList(LMessageLog lMessageLog) {
		return super.findList(lMessageLog);
	}
	
	public Page<LMessageLog> findPage(Page<LMessageLog> page, LMessageLog lMessageLog) {
		return super.findPage(page, lMessageLog);
	}
	
	@Transactional(readOnly = false)
	public void save(LMessageLog lMessageLog) {
		super.save(lMessageLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(LMessageLog lMessageLog) {
		super.delete(lMessageLog);
	}
	
}