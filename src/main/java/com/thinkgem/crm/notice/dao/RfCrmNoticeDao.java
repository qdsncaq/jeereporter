/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.notice.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.notice.entity.RfCrmNotice;

/**
 * 公告DAO接口
 * @author 姜浩
 * @version 2016-05-27
 */
@MyBatisDao
public interface RfCrmNoticeDao extends CrudDao<RfCrmNotice> {
	
}