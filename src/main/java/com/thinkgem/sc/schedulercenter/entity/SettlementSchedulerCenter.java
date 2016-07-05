/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.schedulercenter.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * schedulercenterEntity
 * @author lm
 * @version 2016-03-23
 */
public class SettlementSchedulerCenter extends DataEntity<SettlementSchedulerCenter> {
	
	private static final long serialVersionUID = 1L;
	private String taskName;		// 任务名称
	private String currentMachine;		// 执行机器IP
	private Date tickTime;		// 上次执行时间
	private Long threshold;		// 阈值
	private Date beginTickTime;		// 开始 上次执行时间
	private Date endTickTime;		// 结束 上次执行时间
	private Long beginThreshold;		// 开始 阈值
	private Long endThreshold;		// 结束 阈值
	
	public SettlementSchedulerCenter() {
		super();
	}

	public SettlementSchedulerCenter(String id){
		super(id);
	}

	@Length(min=1, max=20, message="任务名称长度必须介于 1 和 20 之间")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	@Length(min=1, max=20, message="执行机器IP长度必须介于 1 和 20 之间")
	public String getCurrentMachine() {
		return currentMachine;
	}

	public void setCurrentMachine(String currentMachine) {
		this.currentMachine = currentMachine;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="上次执行时间不能为空")
	public Date getTickTime() {
		return tickTime;
	}

	public void setTickTime(Date tickTime) {
		this.tickTime = tickTime;
	}
	
	@NotNull(message="阈值不能为空")
	public Long getThreshold() {
		return threshold;
	}

	public void setThreshold(Long threshold) {
		this.threshold = threshold;
	}
	
	public Date getBeginTickTime() {
		return beginTickTime;
	}

	public void setBeginTickTime(Date beginTickTime) {
		this.beginTickTime = beginTickTime;
	}
	
	public Date getEndTickTime() {
		return endTickTime;
	}

	public void setEndTickTime(Date endTickTime) {
		this.endTickTime = endTickTime;
	}
		
	public Long getBeginThreshold() {
		return beginThreshold;
	}

	public void setBeginThreshold(Long beginThreshold) {
		this.beginThreshold = beginThreshold;
	}
	
	public Long getEndThreshold() {
		return endThreshold;
	}

	public void setEndThreshold(Long endThreshold) {
		this.endThreshold = endThreshold;
	}
		
}