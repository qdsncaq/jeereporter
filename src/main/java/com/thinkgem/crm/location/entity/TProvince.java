/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.location.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * CRM地理位置信息Entity
 * @author 段文昌
 * @version 2016-03-15
 */
public class TProvince extends DataEntity<TProvince> {
	
	private static final long serialVersionUID = 1L;
	private Long proid;		// proid
	private String code;		// code
	private String proname;		// proname
	private Integer prosort;		// prosort
	private String proremark;		// proremark
	private String latitude;		// latitude
	private String longitude;		// longitude
	private String nation;// 是否全国
	
	public TProvince() {
		super();
	}

	public TProvince(String id){
		super(id);
	}

	@NotNull(message="proid不能为空")
	public Long getProid() {
		return proid;
	}

	public void setProid(Long proid) {
		this.proid = proid;
	}
	
	@Length(min=0, max=50, message="code长度必须介于 0 和 50 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=50, message="proname长度必须介于 1 和 50 之间")
	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}
	
	public Integer getProsort() {
		return prosort;
	}

	public void setProsort(Integer prosort) {
		this.prosort = prosort;
	}
	
	@Length(min=0, max=50, message="proremark长度必须介于 0 和 50 之间")
	public String getProremark() {
		return proremark;
	}

	public void setProremark(String proremark) {
		this.proremark = proremark;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
}