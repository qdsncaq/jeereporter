/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.syslabel.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * CRM系统配置Entity
 * @author 段文昌
 * @version 2016-03-14
 */
public class RfCrmSysLabel extends DataEntity<RfCrmSysLabel> {
	
	private static final long serialVersionUID = 1L;
	private String codeLabel;		// 标签类型
	private String keyLabel;		// 标签key
	private String valueLabel;		// 标签value
	private String orderBy;		// 排序字段
	private String isShow;		// 默认显示[1，是；2，不是]
	private String type;		// type
	private String userId;		// 操作人
	private String available;		// 可用[1，可用；2，不可用]
	private Date createTime;		// 添加时间
	private Date updateTime;		// 修改时间
	
	public RfCrmSysLabel() {
		super();
	}

	public RfCrmSysLabel(String id){
		super(id);
	}

	@Length(min=1, max=50, message="标签类型长度必须介于 1 和 50 之间")
	public String getCodeLabel() {
		return codeLabel;
	}

	public void setCodeLabel(String codeLabel) {
		this.codeLabel = codeLabel;
	}
	
	@Length(min=1, max=50, message="标签key长度必须介于 1 和 50 之间")
	public String getKeyLabel() {
		return keyLabel;
	}

	public void setKeyLabel(String keyLabel) {
		this.keyLabel = keyLabel;
	}
	
	@Length(min=1, max=255, message="标签value长度必须介于 1 和 255 之间")
	public String getValueLabel() {
		return valueLabel;
	}

	public void setValueLabel(String valueLabel) {
		this.valueLabel = valueLabel;
	}
	
	@Length(min=0, max=4, message="排序字段长度必须介于 0 和 4 之间")
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	@Length(min=0, max=1, message="默认显示[1，是；2，不是]长度必须介于 0 和 1 之间")
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	@Length(min=0, max=10, message="type长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="操作人长度必须介于 0 和 100 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=1, message="可用[1，可用；2，不可用]长度必须介于 0 和 1 之间")
	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
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