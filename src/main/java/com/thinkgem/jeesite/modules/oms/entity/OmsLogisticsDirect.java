/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直送基础数据Entity
 * @author oms
 * @version 2016-05-14
 */
public class OmsLogisticsDirect extends DataEntity<OmsLogisticsDirect> {
	
	private static final long serialVersionUID = 1L;
	private String vendorCode;		// 供应商
	private String vendorName;		// 供应商
	private String commodityCode;		// 商品编码
	private String commodityName;		// 商品名称
	private String distinctCode;		// 区域
	private String distinctName;		// 区域
	private String sFlag;		// 是否有效
	
	public OmsLogisticsDirect() {
		super();
	}

	public OmsLogisticsDirect(String id){
		super(id);
	}

	@Length(min=0, max=50, message="供应商长度必须介于 0 和 50 之间")
	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	
	@Length(min=0, max=100, message="供应商长度必须介于 0 和 100 之间")
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	@Length(min=0, max=50, message="商品编码长度必须介于 0 和 50 之间")
	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	
	@Length(min=0, max=200, message="商品名称长度必须介于 0 和 200 之间")
	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	
	@Length(min=0, max=50, message="区域长度必须介于 0 和 50 之间")
	public String getDistinctCode() {
		return distinctCode;
	}

	public void setDistinctCode(String distinctCode) {
		this.distinctCode = distinctCode;
	}
	
	@Length(min=0, max=100, message="区域长度必须介于 0 和 100 之间")
	public String getDistinctName() {
		return distinctName;
	}

	public void setDistinctName(String distinctName) {
		this.distinctName = distinctName;
	}
	
	@Length(min=0, max=11, message="是否有效长度必须介于 0 和 11 之间")
	public String getSFlag() {
		return sFlag;
	}

	public void setSFlag(String sFlag) {
		this.sFlag = sFlag;
	}
	
}