/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.settlementpay.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mk.settlement.enums.BizTypeEnums;
import com.mk.settlement.enums.PayStatusEnums;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * settlementpayEntity
 * @author lm
 * @version 2016-04-05
 */
public class SettlementPay extends DataEntity<SettlementPay> {
	
	private static final long serialVersionUID = 1L;
	private String accountid;		// 账户ID
	private String accountname;		// 账户名称
	private String hotelid;		// 酒店ID
	private String hotelpms;		// PMS编码
	private String hotelname;		// 酒店名称
	private String orderid;		// 订单ID
	private Long sysOrderid;		// 第三方交易号
	private Integer biztype;		// 业务类型
	private String thirdno;		// 商户订单号
	private String price;		// 支付金额
	private String sum;		// 支付金额
	private Integer paychannel;		// 支付类型
	private Integer paytype;		// 类型
	private String buyerid;		// 第三方账号ID
	private String buyermail;		// 第三方账号
	private String banktype;		// 付款银行
	private Integer paystatus;		// 支付状态
	private String errCode;		// 错误编码
	private String errCodeDes;		// 错误描述
	private String tradeStatus;		// trade_status
	private Date payTime;		// 支付时间
	private Date callbackTime;		// 支付完成时间
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String beginPrice;		// 开始 支付金额
	private String endPrice;		// 结束 支付金额
	private String beginSum;		// 开始 支付金额
	private String endSum;		// 结束 支付金额
	private Date beginPayTime;		// 开始 支付时间
	private Date endPayTime;		// 结束 支付时间
	private Date beginCallbackTime;		// 开始 支付完成时间
	private Date endCallbackTime;		// 结束 支付完成时间
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间
	private Date beginUpdateTime;		// 开始 更新时间
	private Date endUpdateTime;		// 结束 更新时间
	
	public SettlementPay() {
		super();
	}

	public SettlementPay(String id){
		super(id);
	}
	
	@ExcelField(title="系统编号", type=1, align=2, sort=10)
	public String getId(){
		return id;
	}

	@Length(min=0, max=50, message="账户ID长度必须介于 0 和 50 之间")
	@ExcelField(title="账户ID", type=1, align=2, sort=20)
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Length(min=0, max=100, message="账户名称长度必须介于 0 和 100 之间")
	@ExcelField(title="账户名称", type=1, align=2, sort=30)
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	@Length(min=0, max=50, message="酒店ID长度必须介于 0 和 50 之间")
	@ExcelField(title="酒店ID", type=1, align=2, sort=40)
	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=100, message="PMS编码长度必须介于 0 和 100 之间")
	public String getHotelpms() {
		return hotelpms;
	}

	public void setHotelpms(String hotelpms) {
		this.hotelpms = hotelpms;
	}
	
	@Length(min=0, max=500, message="酒店名称长度必须介于 0 和 500 之间")
	@ExcelField(title="酒店名称", type=1, align=2, sort=50)
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	@Length(min=0, max=64, message="订单ID长度必须介于 0 和 64 之间")
	@ExcelField(title="第三方支付单号", type=1, align=2, sort=60)
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	@ExcelField(title="业务单号", type=1, align=2, sort=70)
	public Long getSysOrderid() {
		return sysOrderid;
	}

	public void setSysOrderid(Long sysOrderid) {
		this.sysOrderid = sysOrderid;
	}
	
	public Integer getBiztype() {
		return biztype;
	}

	@ExcelField(title="业务类型", type=1, align=2, sort=90)
	public String getStrBiztype(){
		return biztype != null ? BizTypeEnums.getDesc(biztype) : "";
	}
	
	public void setBiztype(Integer biztype) {
		this.biztype = biztype;
	}
	
	@Length(min=0, max=64, message="商户订单号长度必须介于 0 和 64 之间")
	@ExcelField(title="商户订单号", type=1, align=2, sort=75)
	public String getThirdno() {
		return thirdno;
	}

	public void setThirdno(String thirdno) {
		this.thirdno = thirdno;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@ExcelField(title="支付金额", type=1, align=2, sort=80)
	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	
	public Integer getPaychannel() {
		return paychannel;
	}

	@ExcelField(title="支付类型", type=1, align=2, sort=100)
	public String getStrPaychannel(){
		return paychannel == 1 ? "支付宝" : "微信";
	}
	
	public void setPaychannel(Integer paychannel) {
		this.paychannel = paychannel;
	}
	
	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	
	@Length(min=0, max=128, message="第三方账号ID长度必须介于 0 和 128 之间")
	public String getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	
	@Length(min=0, max=100, message="第三方账号长度必须介于 0 和 100 之间")
	public String getBuyermail() {
		return buyermail;
	}

	public void setBuyermail(String buyermail) {
		this.buyermail = buyermail;
	}
	
	@Length(min=0, max=16, message="付款银行长度必须介于 0 和 16 之间")
	public String getBanktype() {
		return banktype;
	}

	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}
	
	public Integer getPaystatus() {
		return paystatus;
	}
	
	@ExcelField(title="支付状态", type=1, align=2, sort=110)
	public String getStrPaystatus(){
		return paystatus != null ? PayStatusEnums.getDesc(paystatus) : "";
	}

	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}
	
	@Length(min=0, max=32, message="错误编码长度必须介于 0 和 32 之间")
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	@Length(min=0, max=128, message="错误描述长度必须介于 0 和 128 之间")
	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	
	@Length(min=0, max=100, message="trade_status长度必须介于 0 和 100 之间")
	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// @ExcelField(title="支付时间", type=1, align=2, sort=120)
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="支付完成时间", type=1, align=2, sort=140)
	public Date getCallbackTime() {
		return callbackTime;
	}

	public void setCallbackTime(Date callbackTime) {
		this.callbackTime = callbackTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="创建时间", type=1, align=2, sort=130)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getBeginPrice() {
		return beginPrice;
	}

	public void setBeginPrice(String beginPrice) {
		this.beginPrice = beginPrice;
	}
	
	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}
		
	public String getBeginSum() {
		return beginSum;
	}

	public void setBeginSum(String beginSum) {
		this.beginSum = beginSum;
	}
	
	public String getEndSum() {
		return endSum;
	}

	public void setEndSum(String endSum) {
		this.endSum = endSum;
	}
		
	public Date getBeginPayTime() {
		return beginPayTime;
	}

	public void setBeginPayTime(Date beginPayTime) {
		this.beginPayTime = beginPayTime;
	}
	
	public Date getEndPayTime() {
		return endPayTime;
	}

	public void setEndPayTime(Date endPayTime) {
		this.endPayTime = endPayTime;
	}
		
	public Date getBeginCallbackTime() {
		return beginCallbackTime;
	}

	public void setBeginCallbackTime(Date beginCallbackTime) {
		this.beginCallbackTime = beginCallbackTime;
	}
	
	public Date getEndCallbackTime() {
		return endCallbackTime;
	}

	public void setEndCallbackTime(Date endCallbackTime) {
		this.endCallbackTime = endCallbackTime;
	}
		
	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
		
	public Date getBeginUpdateTime() {
		return beginUpdateTime;
	}

	public void setBeginUpdateTime(Date beginUpdateTime) {
		this.beginUpdateTime = beginUpdateTime;
	}
	
	public Date getEndUpdateTime() {
		return endUpdateTime;
	}

	public void setEndUpdateTime(Date endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}
		
}