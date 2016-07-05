/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.notice.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 通知Entity
 * @author 李雪楠
 * @version 2016-05-27
 */
public class RfCrmNoticeReceiver extends DataEntity<RfCrmNoticeReceiver> {
	
	private static final long serialVersionUID = 1L;
	private Long noticeId;		// 通知ID
	private String receiveUserId;		// 通知接收人ID
	private String readStatus;		// 消息状态:1-未读,2-已读
	
	public RfCrmNoticeReceiver() {
		super();
	}

	public RfCrmNoticeReceiver(String id){
		super(id);
	}

	@NotNull(message="通知ID不能为空")
	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	
	@Length(min=1, max=50, message="通知接收人ID长度必须介于 1 和 50 之间")
	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	
	@Length(min=0, max=1, message="消息状态:1-未读,2-已读长度必须介于 0 和 1 之间")
	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	
}