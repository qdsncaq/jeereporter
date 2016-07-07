/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.rpt.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.rpt.entity.ZinccoatingthicknessLog;
import com.thinkgem.jeesite.modules.rpt.dao.ZinccoatingthicknessLogDao;

/**
 * 锌层测厚日志Service
 * @author aiqing.chu
 * @version 2016-07-07
 */
@Service
@DataSourceName("office")
@Transactional(readOnly = true)
public class ZinccoatingthicknessLogService extends CrudService<ZinccoatingthicknessLogDao, ZinccoatingthicknessLog> {

	public ZinccoatingthicknessLog get(String id) {
		return super.get(id);
	}
	
	public List<ZinccoatingthicknessLog> findList(ZinccoatingthicknessLog zinccoatingthicknessLog) {
		return super.findList(zinccoatingthicknessLog);
	}
	
	public Page<ZinccoatingthicknessLog> findPage(Page<ZinccoatingthicknessLog> page, ZinccoatingthicknessLog zinccoatingthicknessLog) {
		return super.findPage(page, zinccoatingthicknessLog);
	}
	
	@Transactional(readOnly = false)
	public void save(ZinccoatingthicknessLog zinccoatingthicknessLog) {
		super.save(zinccoatingthicknessLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZinccoatingthicknessLog zinccoatingthicknessLog) {
		super.delete(zinccoatingthicknessLog);
	}
	
}