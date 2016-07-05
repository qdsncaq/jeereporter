/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.log.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.log.entity.BisLog;

/**
 * 日志DAO接口
 * @author 王智威
 * @version 2016-04-13
 */
@MyBatisDao
public interface BisLogDao extends CrudDao<BisLog> {
	
}