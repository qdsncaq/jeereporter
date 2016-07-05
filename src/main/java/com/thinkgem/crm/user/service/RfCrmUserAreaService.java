/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.crm.location.entity.AllAreas;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.crm.user.dao.RfCrmUserAreaDao;
import com.thinkgem.crm.user.entity.RfCrmUserArea;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 用户区域设置Service
 * @author 李雪楠
 * @version 2016-03-23
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmUserAreaService extends CrudService<RfCrmUserAreaDao, RfCrmUserArea> {

	public RfCrmUserArea get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmUserArea> findList(RfCrmUserArea rfCrmUserArea) {
		return super.findList(rfCrmUserArea);
	}
	
	public Page<RfCrmUserArea> findPage(Page<RfCrmUserArea> page, RfCrmUserArea rfCrmUserArea) {
		return super.findPage(page, rfCrmUserArea);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmUserArea rfCrmUserArea) {
		if(rfCrmUserArea.getCreateTime() != null){
			rfCrmUserArea.setCreateTime(new Date());
		}
		rfCrmUserArea.setUpdateTime(new Date());
		super.save(rfCrmUserArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmUserArea rfCrmUserArea) {
		super.delete(rfCrmUserArea);
	}

    public List<AllAreas> findUserByArea(RfCrmUserArea rfCrmUserArea) {
        return dao.findUserByArea(rfCrmUserArea);
    }
}