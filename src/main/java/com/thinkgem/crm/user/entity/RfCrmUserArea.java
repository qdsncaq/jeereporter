/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 用户区域设置Entity
 * @author 李雪楠
 * @version 2016-03-23
 */
public class RfCrmUserArea extends DataEntity<RfCrmUserArea> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// 机构编码
	private String userName;		// 机构编码
	private String userState;		// 机构编码
	private String provCode;		// 省编码
	private String provName;		// 省编码
	private String cityCode;		// 市编码
	private String cityName;		// 市编码
	private String disCode;		// 区编码
	private String disName;		// 区编码
	private String available = "1";		// 可用[1，可用；2，不可用]
	private String availableName ;		// 可用[1，可用；2，不可用]
	private Date createTime;		// 添加时间
	private Date updateTime;		// 修改时间
	
	public RfCrmUserArea() {
		super();
	}

	public RfCrmUserArea(String id){
		super(id);
	}

	@Length(min=1, max=40, message="机构编码长度必须介于 1 和 40 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=10, message="省编码长度必须介于 0 和 10 之间")
	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}
	
	@Length(min=0, max=10, message="市编码长度必须介于 0 和 10 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Length(min=0, max=10, message="区编码长度必须介于 0 和 10 之间")
	public String getDisCode() {
		return disCode;
	}

	public void setDisCode(String disCode) {
		this.disCode = disCode;
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
	@ExcelField(title = "用户名称", align = 2, sort = 10)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@ExcelField(title = "省份", align = 2, sort = 20)
	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}
	@ExcelField(title = "城市", align = 2, sort = 25)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	public String getAvailableName() {
		if("1".equals(this.getAvailable())){
			availableName = "可用";
		}else{
			availableName = "不可用";
		}
		return availableName;
	}

	public void setAvailableName(String availableName) {
		this.availableName = availableName;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}
	
	
}