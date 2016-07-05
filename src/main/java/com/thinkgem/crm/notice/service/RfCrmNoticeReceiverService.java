/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.notice.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.notice.entity.RfCrmNoticeReceiver;
import com.thinkgem.crm.notice.dao.RfCrmNoticeReceiverDao;

/**
 * 通知Service
 * @author 李雪楠
 * @version 2016-05-27
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmNoticeReceiverService extends CrudService<RfCrmNoticeReceiverDao, RfCrmNoticeReceiver> {

	public RfCrmNoticeReceiver get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmNoticeReceiver> findList(RfCrmNoticeReceiver rfCrmNoticeReceiver) {
		return super.findList(rfCrmNoticeReceiver);
	}
	
	public Page<RfCrmNoticeReceiver> findPage(Page<RfCrmNoticeReceiver> page, RfCrmNoticeReceiver rfCrmNoticeReceiver) {
		return super.findPage(page, rfCrmNoticeReceiver);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmNoticeReceiver rfCrmNoticeReceiver) {
		super.save(rfCrmNoticeReceiver);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmNoticeReceiver rfCrmNoticeReceiver) {
		super.delete(rfCrmNoticeReceiver);
	}
	
}