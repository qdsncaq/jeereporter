/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.message.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.message.entity.LMessageLog;

/**
 * 短信DAO接口
 * @author jianghe
 * @version 2016-03-17
 */
@MyBatisDao
public interface LMessageLogDao extends CrudDao<LMessageLog> {
	
}