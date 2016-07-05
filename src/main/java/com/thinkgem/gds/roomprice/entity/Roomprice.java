/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomprice.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 房型价格Entity
 * @author jiajianhong
 * @version 2016-04-01
 */
public class Roomprice extends DataEntity<Roomprice> {
	
	private static final long serialVersionUID = 1L;
	private Date createtime;		// 创建时间
	private Long orderdetailid;		// 订单详细ID
	private Date actiondate;		// 日期
	private String type;		// 类型
	private BigDecimal price;		// 价格
	private Date updatetime;		// 修改时间
	private Long orderid;		// 订单ID
	
	public Roomprice() {
		super();
	}

	public Roomprice(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@NotNull(message="订单详细ID不能为空")
	public Long getOrderdetailid() {
		return orderdetailid;
	}

	public void setOrderdetailid(Long orderdetailid) {
		this.orderdetailid = orderdetailid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActiondate() {
		return actiondate;
	}

	public void setActiondate(Date actiondate) {
		this.actiondate = actiondate;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@NotNull(message="订单ID不能为空")
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
}