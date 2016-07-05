/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.bank.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.bank.entity.THotelBank;
import com.thinkgem.crm.bank.dao.THotelBankDao;

/**
 * 酒店银行账号Service
 * @author 李雪楠
 * @version 2016-05-17
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class THotelBankService extends CrudService<THotelBankDao, THotelBank> {

	public THotelBank get(String id) {
		return super.get(id);
	}
	
	public List<THotelBank> findList(THotelBank tHotelBank) {
		return super.findList(tHotelBank);
	}
	
	public Page<THotelBank> findPage(Page<THotelBank> page, THotelBank tHotelBank) {
		return super.findPage(page, tHotelBank);
	}
	
	@Transactional(readOnly = false)
	public void save(THotelBank tHotelBank) {
		super.save(tHotelBank);
	}
	
	@Transactional(readOnly = false)
	public void delete(THotelBank tHotelBank) {
		super.delete(tHotelBank);
	}
	
}