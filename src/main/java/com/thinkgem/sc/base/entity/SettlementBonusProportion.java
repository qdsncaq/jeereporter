/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.base.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 区域经理奖金分配比例Entity
 * @author aiqing.chu@fangbaba.com
 * @version 2016-06-06
 */
public class SettlementBonusProportion extends DataEntity<SettlementBonusProportion> {
	
	private static final long serialVersionUID = 1L;
	private String province;		// 省级编码
	private String city;		// 市级编码
	private String district;		// 区级编码
	private String proname;		// 省份名称
	private String role;		// 角色
	private String proportion;		// 分配比例
	private Date createtime;		// 创建时间
	private Date updatetime;		// 修改时间
	private String operator;		// 操作人
	
	public SettlementBonusProportion() {
		super();
	}

	public SettlementBonusProportion(String id){
		super(id);
	}

	@Length(min=0, max=20, message="省级编码长度必须介于 0 和 20 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=20, message="市级编码长度必须介于 0 和 20 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=20, message="区级编码长度必须介于 0 和 20 之间")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	@Length(min=0, max=30, message="省份名称长度必须介于 0 和 30 之间")
	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}
	
	@Length(min=0, max=11, message="角色长度必须介于 0 和 11 之间")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Length(min=0, max=20, message="操作人长度必须介于 0 和 20 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}