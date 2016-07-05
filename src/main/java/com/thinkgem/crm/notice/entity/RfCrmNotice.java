/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.notice.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公告Entity
 * @author 姜浩
 * @version 2016-05-27
 */
public class RfCrmNotice extends DataEntity<RfCrmNotice> {
	
	private static final long serialVersionUID = 1L;
	private String noticeType;		// 通知类型
	private String noticeTypeName;	// 通知类型
	private String noticeTitle;		// 通知标题
	private String noticeContent;		// 通知内容
	private String noticeTop;		// 通知是否置顶 1-是 0-否
	private String noticeRead;		// 通知是否必读 1-是 0-否
	private String noticeStatus;		// 通知状态：10-已发送 20-暂存
	private Date noticeTime;		// 通知时间
	private Date noticeStartTime;		// 通知时间
	private Date noticeEndTime;		// 通知时间
	private String noticeAttachment;		// 通知附件路径
	
	public RfCrmNotice() {
		super();
	}

	public RfCrmNotice(String id){
		super(id);
	}

	@Length(min=1, max=500, message="通知类型长度必须介于 1 和 500 之间")
	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	
	@Length(min=1, max=500, message="通知标题长度必须介于 1 和 500 之间")
	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	@Length(min=1, max=4000, message="通知内容长度必须介于 1 和 4000 之间")
	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	
	@Length(min=1, max=2, message="通知是否置顶 1-是 0-否长度必须介于 1 和 2 之间")
	public String getNoticeTop() {
		return noticeTop;
	}

	public void setNoticeTop(String noticeTop) {
		this.noticeTop = noticeTop;
	}
	
	@Length(min=1, max=2, message="通知是否必读 1-是 0-否长度必须介于 1 和 2 之间")
	public String getNoticeRead() {
		return noticeRead;
	}

	public void setNoticeRead(String noticeRead) {
		this.noticeRead = noticeRead;
	}
	
	@Length(min=0, max=2, message="通知状态：10-已发送 20-暂存长度必须介于 0 和 2 之间")
	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	
	@Length(min=0, max=255, message="通知附件路径长度必须介于 0 和 255 之间")
	public String getNoticeAttachment() {
		return noticeAttachment;
	}

	public void setNoticeAttachment(String noticeAttachment) {
		this.noticeAttachment = noticeAttachment;
	}

	public String getNoticeTypeName() {
		return noticeTypeName;
	}

	public void setNoticeTypeName(String noticeTypeName) {
		this.noticeTypeName = noticeTypeName;
	}

	public Date getNoticeStartTime() {
		return noticeStartTime;
	}

	public void setNoticeStartTime(Date noticeStartTime) {
		this.noticeStartTime = noticeStartTime;
	}

	public Date getNoticeEndTime() {
		return noticeEndTime;
	}

	public void setNoticeEndTime(Date noticeEndTime) {
		this.noticeEndTime = noticeEndTime;
	}

	
}