/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.bank.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.bank.entity.THotelBank;

/**
 * 酒店银行账号DAO接口
 * @author 李雪楠
 * @version 2016-05-17
 */
@MyBatisDao
public interface THotelBankDao extends CrudDao<THotelBank> {
	
}