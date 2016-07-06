///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.accntledger.entity;
//
//import org.hibernate.validator.constraints.Length;
//
//import javax.validation.constraints.NotNull;
//
//import java.util.Date;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.mk.settlement.enums.AccountRoleEnums;
//import com.mk.settlement.enums.TripAccntTypeEnums;
//import com.thinkgem.jeesite.common.persistence.DataEntity;
//import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
//
///**
// * accntledgerEntity
// * @author lm
// * @version 2016-03-29
// */
//public class SettlementAccntLedger extends DataEntity<SettlementAccntLedger> {
//	
//	private static final long serialVersionUID = 1L;
//	private String accountid;		// 账户ID
//	private String accountname;		// 账户名称
//	private String hotelid;		// 酒店ID
//	private String hotelname;		// 酒店名称
//	private String hotelpms;		// PMS编码
//	private String password;		// 账户密码
//	private Integer accountstate;		// 状态
//	private Date frozendate;		// 冻结起始时间
//	private Date unfrozendate;		// 冻结结束时间
//	private String balance;		// 账户余额
//	private Date createTime;		// 创建时间
//	private Date updateTime;		// 更新时间
//	private Integer accountrole;		// 账户角色
//	private String waitpayment;		// 存储中间金额
//	private String postpayBalance;		// 可用额度
//	private String postpayThreshold;		// 后付费额度
//	private Integer accounttype;		// 旅行社账户类型(1:储值账户/2:后付费账户)
//	private Date beginFrozendate;		// 开始 冻结起始时间
//	private Date endFrozendate;		// 结束 冻结起始时间
//	private Date beginUnfrozendate;		// 开始 冻结结束时间
//	private Date endUnfrozendate;		// 结束 冻结结束时间
//	private String beginBalance;		// 开始 账户余额
//	private String endBalance;		// 结束 账户余额
//	private Date beginCreateTime;		// 开始 创建时间
//	private Date endCreateTime;		// 结束 创建时间
//	private Date beginUpdateTime;		// 开始 更新时间
//	private Date endUpdateTime;		// 结束 更新时间
//	private String beginWaitpayment;		// 开始 存储中间金额
//	private String endWaitpayment;		// 结束 存储中间金额
//	private String beginPostpayBalance;		// 开始 可用额度
//	private String endPostpayBalance;		// 结束 可用额度
//	private String beginPostpayThreshold;		// 开始 后付费额度
//	private String endPostpayThreshold;		// 结束 后付费额度
//	
//	public SettlementAccntLedger() {
//		super();
//	}
//
//	public SettlementAccntLedger(String id){
//		super(id);
//	}
//
//	@Length(min=1, max=50, message="账户ID长度必须介于 1 和 50 之间")
//	@ExcelField(title="账户ID", type=1, align=2, sort=10)
//	public String getAccountid() {
//		return accountid;
//	}
//
//	public void setAccountid(String accountid) {
//		this.accountid = accountid;
//	}
//	
//	@Length(min=0, max=100, message="账户名称长度必须介于 0 和 100 之间")
//	@ExcelField(title="账户名称", type=1, align=2, sort=20)
//	public String getAccountname() {
//		return accountname;
//	}
//
//	public void setAccountname(String accountname) {
//		this.accountname = accountname;
//	}
//	
//	@Length(min=1, max=50, message="酒店ID长度必须介于 1 和 50 之间")
//	@ExcelField(title="酒店ID", type=1, align=2, sort=30)
//	public String getHotelid() {
//		return hotelid;
//	}
//
//	public void setHotelid(String hotelid) {
//		this.hotelid = hotelid;
//	}
//	
//	@Length(min=0, max=500, message="酒店名称长度必须介于 0 和 500 之间")
//	@ExcelField(title="酒店名称", type=1, align=2, sort=40)
//	public String getHotelname() {
//		return hotelname;
//	}
//
//	public void setHotelname(String hotelname) {
//		this.hotelname = hotelname;
//	}
//	
//	@Length(min=0, max=100, message="PMS编码长度必须介于 0 和 100 之间")
//	public String getHotelpms() {
//		return hotelpms;
//	}
//
//	public void setHotelpms(String hotelpms) {
//		this.hotelpms = hotelpms;
//	}
//	
//	@Length(min=0, max=100, message="账户密码长度必须介于 0 和 100 之间")
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	@NotNull(message="状态不能为空")
//	public Integer getAccountstate() {
//		return accountstate;
//	}
//
//	public void setAccountstate(Integer accountstate) {
//		this.accountstate = accountstate;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	public Date getFrozendate() {
//		return frozendate;
//	}
//
//	public void setFrozendate(Date frozendate) {
//		this.frozendate = frozendate;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	public Date getUnfrozendate() {
//		return unfrozendate;
//	}
//
//	public void setUnfrozendate(Date unfrozendate) {
//		this.unfrozendate = unfrozendate;
//	}
//	
//	@ExcelField(title="当前余额", type=1, align=2, sort=70)
//	public String getBalance() {
//		return balance;
//	}
//
//	public void setBalance(String balance) {
//		this.balance = balance;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@NotNull(message="创建时间不能为空")
//	@ExcelField(title="创建时间", type=1, align=2, sort=100)
//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@NotNull(message="更新时间不能为空")
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//	
//	public Integer getAccountrole() {
//		return accountrole;
//	}
//
//	@ExcelField(title="账户角色", type=1, align=2, sort=50)
//	public String getStrAccountrole() {
//		return accountrole != null ? AccountRoleEnums.getDesc(accountrole) : "";
//	}
//	
//	public void setAccountrole(Integer accountrole) {
//		this.accountrole = accountrole;
//	}
//	
//	public String getWaitpayment() {
//		return waitpayment;
//	}
//
//	public void setWaitpayment(String waitpayment) {
//		this.waitpayment = waitpayment;
//	}
//	
//	@ExcelField(title="可用额度", type=1, align=2, sort=80)
//	public String getPostpayBalance() {
//		return postpayBalance;
//	}
//
//	public void setPostpayBalance(String postpayBalance) {
//		this.postpayBalance = postpayBalance;
//	}
//	
//	@ExcelField(title="后付费额度", type=1, align=2, sort=90)
//	public String getPostpayThreshold() {
//		return postpayThreshold;
//	}
//
//	public void setPostpayThreshold(String postpayThreshold) {
//		this.postpayThreshold = postpayThreshold;
//	}
//	
//	public Integer getAccounttype() {
//		return accounttype;
//	}
//	
//	@ExcelField(title="旅行社账户类型", type=1, align=2, sort=60)
//	public String getStrAccounttype(){
//		return accounttype != null ? TripAccntTypeEnums.getDesc(accounttype) : "";
//	}
//
//	public void setAccounttype(Integer accounttype) {
//		this.accounttype = accounttype;
//	}
//	
//	public Date getBeginFrozendate() {
//		return beginFrozendate;
//	}
//
//	public void setBeginFrozendate(Date beginFrozendate) {
//		this.beginFrozendate = beginFrozendate;
//	}
//	
//	public Date getEndFrozendate() {
//		return endFrozendate;
//	}
//
//	public void setEndFrozendate(Date endFrozendate) {
//		this.endFrozendate = endFrozendate;
//	}
//		
//	public Date getBeginUnfrozendate() {
//		return beginUnfrozendate;
//	}
//
//	public void setBeginUnfrozendate(Date beginUnfrozendate) {
//		this.beginUnfrozendate = beginUnfrozendate;
//	}
//	
//	public Date getEndUnfrozendate() {
//		return endUnfrozendate;
//	}
//
//	public void setEndUnfrozendate(Date endUnfrozendate) {
//		this.endUnfrozendate = endUnfrozendate;
//	}
//		
//	public String getBeginBalance() {
//		return beginBalance;
//	}
//
//	public void setBeginBalance(String beginBalance) {
//		this.beginBalance = beginBalance;
//	}
//	
//	public String getEndBalance() {
//		return endBalance;
//	}
//
//	public void setEndBalance(String endBalance) {
//		this.endBalance = endBalance;
//	}
//		
//	public Date getBeginCreateTime() {
//		return beginCreateTime;
//	}
//
//	public void setBeginCreateTime(Date beginCreateTime) {
//		this.beginCreateTime = beginCreateTime;
//	}
//	
//	public Date getEndCreateTime() {
//		return endCreateTime;
//	}
//
//	public void setEndCreateTime(Date endCreateTime) {
//		this.endCreateTime = endCreateTime;
//	}
//		
//	public Date getBeginUpdateTime() {
//		return beginUpdateTime;
//	}
//
//	public void setBeginUpdateTime(Date beginUpdateTime) {
//		this.beginUpdateTime = beginUpdateTime;
//	}
//	
//	public Date getEndUpdateTime() {
//		return endUpdateTime;
//	}
//
//	public void setEndUpdateTime(Date endUpdateTime) {
//		this.endUpdateTime = endUpdateTime;
//	}
//		
//	public String getBeginWaitpayment() {
//		return beginWaitpayment;
//	}
//
//	public void setBeginWaitpayment(String beginWaitpayment) {
//		this.beginWaitpayment = beginWaitpayment;
//	}
//	
//	public String getEndWaitpayment() {
//		return endWaitpayment;
//	}
//
//	public void setEndWaitpayment(String endWaitpayment) {
//		this.endWaitpayment = endWaitpayment;
//	}
//		
//	public String getBeginPostpayBalance() {
//		return beginPostpayBalance;
//	}
//
//	public void setBeginPostpayBalance(String beginPostpayBalance) {
//		this.beginPostpayBalance = beginPostpayBalance;
//	}
//	
//	public String getEndPostpayBalance() {
//		return endPostpayBalance;
//	}
//
//	public void setEndPostpayBalance(String endPostpayBalance) {
//		this.endPostpayBalance = endPostpayBalance;
//	}
//		
//	public String getBeginPostpayThreshold() {
//		return beginPostpayThreshold;
//	}
//
//	public void setBeginPostpayThreshold(String beginPostpayThreshold) {
//		this.beginPostpayThreshold = beginPostpayThreshold;
//	}
//	
//	public String getEndPostpayThreshold() {
//		return endPostpayThreshold;
//	}
//
//	public void setEndPostpayThreshold(String endPostpayThreshold) {
//		this.endPostpayThreshold = endPostpayThreshold;
//	}
//		
//}