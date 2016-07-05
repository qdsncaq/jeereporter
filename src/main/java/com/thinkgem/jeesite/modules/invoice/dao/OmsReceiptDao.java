/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.invoice.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.invoice.entity.OmsReceipt;

/**
 * 发票票据管理DAO接口
 * @author aiqing.chu@fangbaba.com
 * @version 2016-05-06
 */
@MyBatisDao
public interface OmsReceiptDao extends CrudDao<OmsReceipt> {
	
}