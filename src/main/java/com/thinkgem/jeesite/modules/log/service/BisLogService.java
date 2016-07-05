/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.log.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.log.entity.BisLog;
import com.thinkgem.jeesite.modules.log.dao.BisLogDao;

/**
 * 日志Service
 * @author 王智威
 * @version 2016-04-13
 */
@Service
@Transactional(readOnly = true)
public class BisLogService extends CrudService<BisLogDao, BisLog> {

	public BisLog get(String id) {
		return super.get(id);
	}
	
	public List<BisLog> findList(BisLog bisLog) {
		return super.findList(bisLog);
	}
	
	public Page<BisLog> findPage(Page<BisLog> page, BisLog bisLog) {
		return super.findPage(page, bisLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BisLog bisLog) {
		super.save(bisLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BisLog bisLog) {
		super.delete(bisLog);
	}
	
}