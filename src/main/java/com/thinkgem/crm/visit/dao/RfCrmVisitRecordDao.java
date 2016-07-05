/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.visit.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.visit.entity.RfCrmVisitRecord;

/**
 * CRM拜访记录DAO接口
 * @author 段文昌
 * @version 2016-03-14
 */
@MyBatisDao
public interface RfCrmVisitRecordDao extends CrudDao<RfCrmVisitRecord> {
	
}