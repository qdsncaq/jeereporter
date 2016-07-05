/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.task.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * CRM任务发送Entity
 * @author 李雪楠
 * @version 2016-03-23
 */
public class RfCrmUserTask extends DataEntity<RfCrmUserTask> {
	
	private static final long serialVersionUID = 1L;
	private Long taskId;		// 发送人用户id
	private String senderUserId;		// 发送人用户id
	private String senderUserName;		// 发布人姓名
	private String name;		// 任务名称
	private String isComplete;		// 是否完成,1-是,2-否
	private String priority;		// 优先级,1-紧急,2-一般
	private String remark;		// 备注
	private Date createTime;		// 添加时间
	private Date updateTime;		// 修改时间
	private Date endTime;		// 截止时间
	private Date completeTime;		// 完成时间
	private Date startCreateTime;	// 查询起始时间
	private Date endCreateTime;		// 查询起始结束时间
	private String provCode;		// 省份
	private String cityCode;		// 城市
	
	public RfCrmUserTask() {
		super();
	}

	public RfCrmUserTask(String id){
		super(id);
	}

	@Length(min=1, max=64, message="发送人用户id长度必须介于 1 和 64 之间")
	public String getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}
	
	@Length(min=0, max=50, message="发布人姓名长度必须介于 0 和 50 之间")
	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}
	
	@Length(min=0, max=100, message="任务名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=2, message="是否完成,1-是,2-否长度必须介于 1 和 2 之间")
	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}
	
	@Length(min=1, max=2, message="优先级,1-紧急,2-一般长度必须介于 1 和 2 之间")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
}