/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 物流单号Entity
 * @author oms
 * @version 2016-05-20
 */
public class OmsOrderItemSplit extends DataEntity<OmsOrderItemSplit> {
	
	private static final long serialVersionUID = 1L;
	private Long orderId;
	private Long itemId;		// 订单商品的ID
	private Integer yundanCode;		// 我们自己的单号
	private String wuliuCode;		// 物流单号
	private String qianshouStatus;		// 签收状态
	private String memo;		// 备注
	private String descstr;		// 说明
	private String wuliuCmpy;
	
	public OmsOrderItemSplit() {
		super();
	}

	public OmsOrderItemSplit(String id){
		super(id);
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	@Length(min=0, max=11, message="我们自己的单号长度必须介于 0 和 11 之间")
	public Integer getYundanCode() {
		return yundanCode;
	}

	public void setYundanCode(Integer yundanCode) {
		this.yundanCode = yundanCode;
	}
	
	@Length(min=0, max=50, message="物流单号长度必须介于 0 和 50 之间")
	public String getWuliuCode() {
		return wuliuCode;
	}

	public void setWuliuCode(String wuliuCode) {
		this.wuliuCode = wuliuCode;
	}
	
	@Length(min=0, max=50, message="签收状态长度必须介于 0 和 50 之间")
	public String getQianshouStatus() {
		return qianshouStatus;
	}

	public void setQianshouStatus(String qianshouStatus) {
		this.qianshouStatus = qianshouStatus;
	}
	
	@Length(min=0, max=50, message="备注长度必须介于 0 和 50 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	@Length(min=0, max=200, message="说明长度必须介于 0 和 200 之间")
	public String getDescstr() {
		return descstr;
	}

	public void setDescstr(String descstr) {
		this.descstr = descstr;
	}

	public String getWuliuCmpy() {
		return wuliuCmpy;
	}

	public void setWuliuCmpy(String wuliuCmpy) {
		this.wuliuCmpy = wuliuCmpy;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}