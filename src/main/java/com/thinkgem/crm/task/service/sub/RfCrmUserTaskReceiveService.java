/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.task.service.sub;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.task.entity.sub.RfCrmUserTaskReceive;
import com.thinkgem.crm.task.dao.sub.RfCrmUserTaskReceiveDao;

/**
 * BMS用户表Service
 * @author 李雪楠
 * @version 2016-03-23
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmUserTaskReceiveService extends CrudService<RfCrmUserTaskReceiveDao, RfCrmUserTaskReceive> {

	public RfCrmUserTaskReceive get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmUserTaskReceive> findList(RfCrmUserTaskReceive rfCrmUserTaskReceive) {
		return super.findList(rfCrmUserTaskReceive);
	}
	
	public Page<RfCrmUserTaskReceive> findPage(Page<RfCrmUserTaskReceive> page, RfCrmUserTaskReceive rfCrmUserTaskReceive) {
		return super.findPage(page, rfCrmUserTaskReceive);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmUserTaskReceive rfCrmUserTaskReceive) {
		super.save(rfCrmUserTaskReceive);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmUserTaskReceive rfCrmUserTaskReceive) {
		super.delete(rfCrmUserTaskReceive);
	}
	
}