/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.task.entity.sub;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * BMS用户表Entity
 * @author 李雪楠
 * @version 2016-03-23
 */
public class RfCrmUserTaskReceive extends DataEntity<RfCrmUserTaskReceive> {
	
	private static final long serialVersionUID = 1L;
	private String receiveUserId;		// 接收人用户id
	private Long taskId;		// 任务Id
	private String isComplete;		// 是否完成,1-是,2-否
	private String remark;		// 备注
	private Date createTime;		// 添加时间
	private Date updateTime;		// 修改时间
	
	public RfCrmUserTaskReceive() {
		super();
	}

	public RfCrmUserTaskReceive(String id){
		super(id);
	}

	@Length(min=1, max=64, message="接收人用户id长度必须介于 1 和 64 之间")
	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	
	@NotNull(message="任务Id不能为空")
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	@Length(min=1, max=2, message="是否完成,1-是,2-否长度必须介于 1 和 2 之间")
	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
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
	
}