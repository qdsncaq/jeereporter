/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.task.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.task.entity.RfCrmUserTask;

/**
 * CRM任务发送DAO接口
 * @author 李雪楠
 * @version 2016-03-23
 */
@MyBatisDao
public interface RfCrmUserTaskDao extends CrudDao<RfCrmUserTask> {
	
}