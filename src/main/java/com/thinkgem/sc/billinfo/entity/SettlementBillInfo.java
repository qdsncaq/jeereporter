/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.billinfo.entity;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * billinfoEntity
 * @author lm
 * @version 2016-03-28
 */
public class SettlementBillInfo extends DataEntity<SettlementBillInfo> {
	
	private static final long serialVersionUID = 1L;
	private Long billid;		// 账单ID
	private BigDecimal billscsum;		// 账单结算金额
	private BigDecimal settlesum;		// 收款金额
	private Date settletime;		// 收款日期
	private Integer status;		// 账单收款状态(1, 部分收款， 2， 已收款， 3， 超额收款)
	private Date createtime;		// 创建时间
	private Date updatetime;		// 更新时间
	private BigDecimal beginBillscsum;		// 开始 账单结算金额
	private BigDecimal endBillscsum;		// 结束 账单结算金额
	private BigDecimal beginSettlesum;		// 开始 收款金额
	private BigDecimal endSettlesum;		// 结束 收款金额
	private Date beginSettletime;		// 开始 收款日期
	private Date endSettletime;		// 结束 收款日期
	private Date beginCreatetime;		// 开始 创建时间
	private Date endCreatetime;		// 结束 创建时间
	private Date beginUpdatetime;		// 开始 更新时间
	private Date endUpdatetime;		// 结束 更新时间
	
	public SettlementBillInfo() {
		super();
	}

	public SettlementBillInfo(String id){
		super(id);
	}

	@NotNull(message="账单ID不能为空")
	public Long getBillid() {
		return billid;
	}

	public void setBillid(Long billid) {
		this.billid = billid;
	}
	
	public BigDecimal getBillscsum() {
		return billscsum;
	}

	public void setBillscsum(BigDecimal billscsum) {
		this.billscsum = billscsum;
	}
	
	public BigDecimal getSettlesum() {
		return settlesum;
	}

	public void setSettlesum(BigDecimal settlesum) {
		this.settlesum = settlesum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSettletime() {
		return settletime;
	}

	public void setSettletime(Date settletime) {
		this.settletime = settletime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
	public BigDecimal getBeginBillscsum() {
		return beginBillscsum;
	}

	public void setBeginBillscsum(BigDecimal beginBillscsum) {
		this.beginBillscsum = beginBillscsum;
	}
	
	public BigDecimal getEndBillscsum() {
		return endBillscsum;
	}

	public void setEndBillscsum(BigDecimal endBillscsum) {
		this.endBillscsum = endBillscsum;
	}
		
	public BigDecimal getBeginSettlesum() {
		return beginSettlesum;
	}

	public void setBeginSettlesum(BigDecimal beginSettlesum) {
		this.beginSettlesum = beginSettlesum;
	}
	
	public BigDecimal getEndSettlesum() {
		return endSettlesum;
	}

	public void setEndSettlesum(BigDecimal endSettlesum) {
		this.endSettlesum = endSettlesum;
	}
		
	public Date getBeginSettletime() {
		return beginSettletime;
	}

	public void setBeginSettletime(Date beginSettletime) {
		this.beginSettletime = beginSettletime;
	}
	
	public Date getEndSettletime() {
		return endSettletime;
	}

	public void setEndSettletime(Date endSettletime) {
		this.endSettletime = endSettletime;
	}
		
	public Date getBeginCreatetime() {
		return beginCreatetime;
	}

	public void setBeginCreatetime(Date beginCreatetime) {
		this.beginCreatetime = beginCreatetime;
	}
	
	public Date getEndCreatetime() {
		return endCreatetime;
	}

	public void setEndCreatetime(Date endCreatetime) {
		this.endCreatetime = endCreatetime;
	}
		
	public Date getBeginUpdatetime() {
		return beginUpdatetime;
	}

	public void setBeginUpdatetime(Date beginUpdatetime) {
		this.beginUpdatetime = beginUpdatetime;
	}
	
	public Date getEndUpdatetime() {
		return endUpdatetime;
	}

	public void setEndUpdatetime(Date endUpdatetime) {
		this.endUpdatetime = endUpdatetime;
	}
		
}