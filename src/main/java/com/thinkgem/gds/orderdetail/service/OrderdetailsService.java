/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.orderdetail.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.orderdetail.entity.Orderdetails;
import com.thinkgem.gds.orderdetail.dao.OrderdetailsDao;

/**
 * orderdetailService
 * @author tankai
 * @version 2016-03-14
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class OrderdetailsService extends CrudService<OrderdetailsDao, Orderdetails> {

	public Orderdetails get(String id) {
		return super.get(id);
	}
	
	public List<Orderdetails> findList(Orderdetails orderdetails) {
		return super.findList(orderdetails);
	}
	
	public Page<Orderdetails> findPage(Page<Orderdetails> page, Orderdetails orderdetails) {
		return super.findPage(page, orderdetails);
	}
	
	@Transactional(readOnly = false)
	public void save(Orderdetails orderdetails) {
		super.save(orderdetails);
	}
	
	@Transactional(readOnly = false)
	public void delete(Orderdetails orderdetails) {
		super.delete(orderdetails);
	}
	
}