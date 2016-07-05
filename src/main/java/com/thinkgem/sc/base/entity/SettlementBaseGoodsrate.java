/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.base.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 采购商品提成比例维护Entity
 * @author aiqing.chu@fangbaba.com
 * @version 2016-07-01
 */
public class SettlementBaseGoodsrate extends DataEntity<SettlementBaseGoodsrate> {
	
	private static final long serialVersionUID = 1L;
	private String classname;		// 品类名称
	private String gcode;		// 商品编码
	private String gname;		// 商品名称
	private Double ratepercent;		// 提成比例%
	private Double beginRatepercent;		// 开始 提成比例%
	private Double endRatepercent;		// 结束 提成比例%
	
	public SettlementBaseGoodsrate() {
		super();
	}

	public SettlementBaseGoodsrate(String id){
		super(id);
	}

	@Length(min=1, max=50, message="品类名称长度必须介于 1 和 50 之间")
	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	@Length(min=1, max=50, message="商品编码长度必须介于 1 和 50 之间")
	public String getGcode() {
		return gcode;
	}

	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	
	@Length(min=0, max=500, message="商品名称长度必须介于 0 和 500 之间")
	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}
	
	@NotNull(message="提成比例不能为空")
	public Double getRatepercent() {
		return ratepercent;
	}

	public void setRatepercent(Double ratepercent) {
		this.ratepercent = ratepercent;
	}
	
	public Double getBeginRatepercent() {
		return beginRatepercent;
	}

	public void setBeginRatepercent(Double beginRatepercent) {
		this.beginRatepercent = beginRatepercent;
	}
	
	public Double getEndRatepercent() {
		return endRatepercent;
	}

	public void setEndRatepercent(Double endRatepercent) {
		this.endRatepercent = endRatepercent;
	}
		
}