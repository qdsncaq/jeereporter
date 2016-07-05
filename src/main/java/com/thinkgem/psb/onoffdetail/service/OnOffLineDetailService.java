/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.onoffdetail.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.psb.onoffdetail.entity.OnOffLineDetail;
import com.thinkgem.psb.onoffdetail.dao.OnOffLineDetailDao;

/**
 * onoffdetailService
 * @author lm
 * @version 2016-05-11
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class OnOffLineDetailService extends CrudService<OnOffLineDetailDao, OnOffLineDetail> {

	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	public OnOffLineDetail get(String id) {
		return super.get(id);
	}
	
	public List<OnOffLineDetail> findList(OnOffLineDetail onOffLineDetail) {
		return super.findList(onOffLineDetail);
	}
	
	public Page<OnOffLineDetail> findPage(Page<OnOffLineDetail> page, OnOffLineDetail onOffLineDetail) {
		String delFlag = onOffLineDetail.getDelFlag();
		log.info("酒店上下线查询是否删除标识delFlag:{}.", delFlag);
		return super.findPage(page, onOffLineDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(OnOffLineDetail onOffLineDetail) {
		super.save(onOffLineDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(OnOffLineDetail onOffLineDetail) {
		super.delete(onOffLineDetail);
	}
	
}