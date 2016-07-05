/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.task.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.task.entity.RfCrmUserTask;
import com.thinkgem.crm.task.dao.RfCrmUserTaskDao;

/**
 * CRM任务发送Service
 * @author 李雪楠
 * @version 2016-03-23
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmUserTaskService extends CrudService<RfCrmUserTaskDao, RfCrmUserTask> {

	public RfCrmUserTask get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmUserTask> findList(RfCrmUserTask rfCrmUserTask) {
		return super.findList(rfCrmUserTask);
	}
	
	public Page<RfCrmUserTask> findPage(Page<RfCrmUserTask> page, RfCrmUserTask rfCrmUserTask) {
		page.setOrderBy(" a.create_time desc");
		return super.findPage(page, rfCrmUserTask);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmUserTask rfCrmUserTask) {
		super.save(rfCrmUserTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmUserTask rfCrmUserTask) {
		super.delete(rfCrmUserTask);
	}
	
}