/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.notice.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.notice.entity.RfCrmNotice;
import com.thinkgem.crm.notice.entity.RfCrmNoticeReceiver;
import com.thinkgem.crm.notice.dao.RfCrmNoticeDao;
import com.thinkgem.crm.user.entity.SyOrgUser;
import com.thinkgem.crm.user.service.SyOrgUserService;

/**
 * 公告Service
 * @author 姜浩
 * @version 2016-05-27
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmNoticeService extends CrudService<RfCrmNoticeDao, RfCrmNotice> {

	@Autowired
	private SyOrgUserService syOrgUserService;
	
	@Autowired
	private RfCrmNoticeReceiverService rfCrmNoticeReceiverService;
	
	public RfCrmNotice get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmNotice> findList(RfCrmNotice rfCrmNotice) {
		return super.findList(rfCrmNotice);
	}
	
	public Page<RfCrmNotice> findPage(Page<RfCrmNotice> page, RfCrmNotice rfCrmNotice) {
		return super.findPage(page, rfCrmNotice);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmNotice rfCrmNotice) {
		super.save(rfCrmNotice);
		
		//1.保存接收人数据
		SyOrgUser syOrgUser = new SyOrgUser();
		syOrgUser.setUserState("1");
		syOrgUser.setsFlag("1");
		List<SyOrgUser> users = syOrgUserService.findList(syOrgUser);
		for (SyOrgUser user : users) {
			RfCrmNoticeReceiver receiver = new RfCrmNoticeReceiver();
			receiver.setNoticeId(Long.parseLong(rfCrmNotice.getId()));
			receiver.setReadStatus("1");//1表示未读 2表示已读
			receiver.setReceiveUserId(user.getUserCode());
			rfCrmNoticeReceiverService.save(receiver);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmNotice rfCrmNotice) {
		super.delete(rfCrmNotice);
	}
	
}