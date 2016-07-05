/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.invoice.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.invoice.entity.OmsReceipt;
import com.thinkgem.jeesite.modules.invoice.dao.OmsReceiptDao;

/**
 * 发票票据管理Service
 * @author aiqing.chu@fangbaba.com
 * @version 2016-05-06
 */
@Service
@DataSourceName("oms")
@Transactional(readOnly = true)
public class OmsReceiptService extends CrudService<OmsReceiptDao, OmsReceipt> {

	public OmsReceipt get(String id) {
		return super.get(id);
	}
	
	public List<OmsReceipt> findList(OmsReceipt omsReceipt) {
		return super.findList(omsReceipt);
	}
	
	public Page<OmsReceipt> findPage(Page<OmsReceipt> page, OmsReceipt omsReceipt) {
		return super.findPage(page, omsReceipt);
	}
	
	@Transactional(readOnly = false)
	public void save(OmsReceipt omsReceipt) {
		super.save(omsReceipt);
	}
	
	@Transactional(readOnly = false)
	public void delete(OmsReceipt omsReceipt) {
		super.delete(omsReceipt);
	}
	
}