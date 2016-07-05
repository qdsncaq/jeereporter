/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.syslabel.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.syslabel.entity.RfCrmSysLabel;
import com.thinkgem.crm.syslabel.dao.RfCrmSysLabelDao;

/**
 * CRM系统配置Service
 * @author 段文昌
 * @version 2016-03-14
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmSysLabelService extends CrudService<RfCrmSysLabelDao, RfCrmSysLabel> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected RfCrmSysLabelDao dao;

	public RfCrmSysLabel get(String id) {
		return super.get(id);
	}

	/**
	 * 获取拜访类型
	 * @return
     */
	public List<RfCrmSysLabel> getVisitType(){
		RfCrmSysLabel sysLabel = new RfCrmSysLabel();
		sysLabel.setCodeLabel("visit_type");
		sysLabel.setAvailable("1");
		return this.findList(sysLabel);
	}

	/**
	 * 获取拜访方式
	 * @return
	 */
	public List<RfCrmSysLabel> getVisitWay(){
		RfCrmSysLabel sysLabel = new RfCrmSysLabel();
		sysLabel.setCodeLabel("visit_way");
		sysLabel.setAvailable("1");
		return this.findList(sysLabel);
	}

	/**
	 * 获取样品发放
	 * @return
	 */
	public List<RfCrmSysLabel> getSampleGrant(){
		RfCrmSysLabel sysLabel = new RfCrmSysLabel();
		sysLabel.setCodeLabel("sample_grant");
		sysLabel.setAvailable("1");
		return this.findList(sysLabel);
	}
	
	public List<RfCrmSysLabel> findList(RfCrmSysLabel rfCrmSysLabel) {
		return super.findList(rfCrmSysLabel);
	}
	
	public Page<RfCrmSysLabel> findPage(Page<RfCrmSysLabel> page, RfCrmSysLabel rfCrmSysLabel) {
		return super.findPage(page, rfCrmSysLabel);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmSysLabel rfCrmSysLabel) {
		super.save(rfCrmSysLabel);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmSysLabel rfCrmSysLabel) {
		super.delete(rfCrmSysLabel);
	}
	
}