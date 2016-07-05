/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.location.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * CRM地理位置信息Entity
 * @author 段文昌
 * @version 2016-03-15
 */
public class TDistrict extends DataEntity<TDistrict> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// code
	private String disname;		// disname
	private Integer cityid;		// cityid
	private Integer dissort;		// dissort
	private String latitude;		// latitude
	private String longitude;		// longitude
	
	public TDistrict() {
		super();
	}

	public TDistrict(String id){
		super(id);
	}

	@Length(min=1, max=50, message="code长度必须介于 1 和 50 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=50, message="disname长度必须介于 1 和 50 之间")
	public String getDisname() {
		return disname;
	}

	public void setDisname(String disname) {
		this.disname = disname;
	}
	
	@NotNull(message="cityid不能为空")
	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	
	public Integer getDissort() {
		return dissort;
	}

	public void setDissort(Integer dissort) {
		this.dissort = dissort;
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
	
}