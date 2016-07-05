/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * oms 商场模块Entity
 * @author yaoxiang
 * @version 2016-03-28
 */
public class OmsMarketInfo extends DataEntity<OmsMarketInfo> {
	
	private static final long serialVersionUID = 1L;
	private String appid;		// appid
	private String appsecret;		// appsecret
	private String name;		// 商铺名称
	private String status;		// 是否启用
	private String url;		// 访问地址
	private String atime;		// 创建时间
	private String mtime;		// 修改时间
	
	public OmsMarketInfo() {
		super();
	}

	public OmsMarketInfo(String id){
		super(id);
	}

	@Length(min=0, max=50, message="appid长度必须介于 0 和 50 之间")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Length(min=0, max=100, message="appsecret长度必须介于 0 和 100 之间")
	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	
	@Length(min=0, max=100, message="商铺名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="是否启用长度必须介于 0 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=500, message="访问地址长度必须介于 0 和 500 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=45, message="创建时间长度必须介于 0 和 45 之间")
	public String getAtime() {
		return atime;
	}

	public void setAtime(String atime) {
		this.atime = atime;
	}
	
	@Length(min=0, max=45, message="修改时间长度必须介于 0 和 45 之间")
	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	
}