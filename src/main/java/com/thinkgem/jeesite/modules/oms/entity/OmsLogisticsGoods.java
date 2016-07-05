/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品物流数据Entity
 * @author oms
 * @version 2016-05-14
 */
public class OmsLogisticsGoods extends DataEntity<OmsLogisticsGoods> {
	
	private static final long serialVersionUID = 1L;
	private String logisticsCode;		// 物流编码
	private String logisticsName;		// 物流名称
	private String warehouseCode;		// 仓库编码
	private String warehouseName;		// 仓库名称
	private String distinctCode;		// 区域编码
	private String distinctName;		// 区域名称
	private String commodityCode;		// 商品编码
	private String commodityName;		// 商品名称
	private String sFlag;		// 是否有效
	
	public OmsLogisticsGoods() {
		super();
	}

	public OmsLogisticsGoods(String id){
		super(id);
	}

	@Length(min=0, max=50, message="物流编码长度必须介于 0 和 50 之间")
	public String getLogisticsCode() {
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}
	
	@Length(min=0, max=100, message="物流名称长度必须介于 0 和 100 之间")
	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
	
	@Length(min=0, max=50, message="仓库编码长度必须介于 0 和 50 之间")
	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	
	@Length(min=0, max=100, message="仓库名称长度必须介于 0 和 100 之间")
	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	@Length(min=0, max=20, message="区域编码长度必须介于 0 和 20 之间")
	public String getDistinctCode() {
		return distinctCode;
	}

	public void setDistinctCode(String distinctCode) {
		this.distinctCode = distinctCode;
	}
	
	@Length(min=0, max=100, message="区域名称长度必须介于 0 和 100 之间")
	public String getDistinctName() {
		return distinctName;
	}

	public void setDistinctName(String distinctName) {
		this.distinctName = distinctName;
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
	
	@Length(min=0, max=11, message="是否有效长度必须介于 0 和 11 之间")
	public String getSFlag() {
		return sFlag;
	}

	public void setSFlag(String sFlag) {
		this.sFlag = sFlag;
	}
	
}