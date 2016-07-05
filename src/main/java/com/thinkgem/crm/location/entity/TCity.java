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
public class TCity extends DataEntity<TCity> {
	
	private static final long serialVersionUID = 1L;
	private Long cityid;		// cityid
	private String code;		// code
	private String cityname;		// cityname
	private Integer proid;		// proid
	private Integer citysort;		// citysort
	private String latitude;		// latitude
	private String longitude;		// longitude
	private String simplename;		// 城市简称
	private String ishotcity;		// 是否热门城市 T-是，F-否
	private String range;		// 城市半径长度（KM）
	private String isselect;		// 是否查询城市 F-是，F-否
	private String querycityname;		// 查询城市名称
	private Integer level;		// 城市等级
	
	public TCity() {
		super();
	}

	public TCity(String id){
		super(id);
	}

	@NotNull(message="cityid不能为空")
	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}
	
	@Length(min=1, max=50, message="code长度必须介于 1 和 50 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=50, message="cityname长度必须介于 1 和 50 之间")
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	@NotNull(message="proid不能为空")
	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}
	
	public Integer getCitysort() {
		return citysort;
	}

	public void setCitysort(Integer citysort) {
		this.citysort = citysort;
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
	
	@Length(min=0, max=50, message="城市简称长度必须介于 0 和 50 之间")
	public String getSimplename() {
		return simplename;
	}

	public void setSimplename(String simplename) {
		this.simplename = simplename;
	}
	
	@Length(min=0, max=10, message="是否热门城市 T-是，F-否长度必须介于 0 和 10 之间")
	public String getIshotcity() {
		return ishotcity;
	}

	public void setIshotcity(String ishotcity) {
		this.ishotcity = ishotcity;
	}
	
	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
	
	@Length(min=0, max=10, message="是否查询城市 F-是，F-否长度必须介于 0 和 10 之间")
	public String getIsselect() {
		return isselect;
	}

	public void setIsselect(String isselect) {
		this.isselect = isselect;
	}
	
	@Length(min=0, max=50, message="查询城市名称长度必须介于 0 和 50 之间")
	public String getQuerycityname() {
		return querycityname;
	}

	public void setQuerycityname(String querycityname) {
		this.querycityname = querycityname;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}