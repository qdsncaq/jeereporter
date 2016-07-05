/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntwithdraw.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mk.settlement.enums.AccountRoleEnums;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.sc.enums.WithdrawStatusEnums;

/**
 * accntwithdrawEntity
 * @author lm
 * @version 2016-03-18
 */
public class SettlementAccountWithdrawcash extends DataEntity<SettlementAccountWithdrawcash> {
	
	private static final long serialVersionUID = 1L;
	private String accountid;		// 账户ID
	private String accountname;		// 账户名称
	private String accountphone;		// 联系电话
	private Integer accountrole;		// 账户角色
	private String hotelid;		// 酒店ID
	private String hotelpms;		// 酒店PMS编码
	private String hotelname;		// 酒店名称
	private BigDecimal cash;		// 账户提取金额
	private String strCash;		// 账户提取金额
	private BigDecimal prebalance;		// 账户当前余额
	private String strPrebalance;		// 账户当前余额
	private BigDecimal balance;		// 提现后账户余额
	private String strBalance;		// 提现后账户余额
	private Integer status;		// 账户申请状态
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String procode;		// 省编码
	private String proname;		// 省名称
	private String citycode;		// 市编码
	private String cityname;		// 市名称
	private String discode;		// 区编码
	private String disname;		// 区名称
	private String name;		// 用名
	private String account;		// 卡号
	private String bank;		// 开户行
	private String bankbranch;		// 支行
	private BigDecimal beginCash;		// 开始 账户提取金额
	private BigDecimal endCash;		// 结束 账户提取金额
	private BigDecimal beginPrebalance;		// 开始 账户当前余额
	private BigDecimal endPrebalance;		// 结束 账户当前余额
	private BigDecimal beginBalance;		// 开始 提现后账户余额
	private BigDecimal endBalance;		// 结束 提现后账户余额
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间
	private Date beginUpdateTime;		// 开始 更新时间
	private Date endUpdateTime;		// 结束 更新时间
	private String strRole;

    private String empNo;           // 员工编号
    
    private List<Integer> accountroles;        // 账户角色
    
    private List<Integer> statuses;

	public SettlementAccountWithdrawcash() {
		super();
	}

	public SettlementAccountWithdrawcash(String id){
		super(id);
	}
	
	@ExcelField(title="编号", type=1, align=2, sort=5)
	public String getId(){
		return id;
	}

	@Length(min=1, max=50, message="账户ID长度必须介于 1 和 50 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Length(min=0, max=100, message="账户名称长度必须介于 0 和 100 之间")
	@ExcelField(title="姓名", type=1, align=2, sort=60)
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	@Length(min=0, max=400, message="联系电话长度必须介于 0 和 400 之间")
	@ExcelField(title="联系电话", type=1, align=2, sort=70)
	public String getAccountphone() {
		return accountphone;
	}

	public void setAccountphone(String accountphone) {
		this.accountphone = accountphone;
	}
	
	@Length(min=0, max=2, message="账户角色长度必须介于 0 和 2 之间")
	public Integer getAccountrole() {
		return accountrole;
	}
	
	@ExcelField(title="账户角色", type=1, align=2, sort=190)
	public String getAccountRole(){
		return accountrole != null ? AccountRoleEnums.getDesc(accountrole) : "";
	}

	public void setAccountrole(Integer accountrole) {
		this.accountrole = accountrole;
	}
	
	@Length(min=0, max=50, message="酒店ID长度必须介于 0 和 50 之间")
	@ExcelField(title="酒店ID", type=1, align=2, sort=40)
	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=100, message="酒店PMS编码长度必须介于 0 和 100 之间")
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
	
	public BigDecimal getCash() {
		return cash;
	}
	
	@ExcelField(title="提取金额", type=1, align=2, sort=90)
	public String getStrCash(){
		return strCash;
	}
	
	public void setStrCash(String strCash) {
		this.strCash = strCash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	
	public BigDecimal getPrebalance() {
		return prebalance;
	}
	
	@ExcelField(title="可提现金额", type=1, align=2, sort=80)
	public String getStrPrebalance() {
		return strPrebalance;
	}
	
	public void setStrPrebalance(String strPrebalance) {
		this.strPrebalance = strPrebalance;
	}

	public void setPrebalance(BigDecimal prebalance) {
		this.prebalance = prebalance;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	@ExcelField(title="提现后余额", type=1, align=2, sort=100)
	public String getStrBalance() {
		return strBalance;
	}

	public void setStrBalance(String strBalance) {
		this.strBalance = strBalance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	@Length(min=1, max=2, message="账户申请状态长度必须介于 1 和 2 之间")
	public Integer getStatus() {
		return status;
	}
	
	@ExcelField(title="提现状态", type=1, align=2, sort=160)
	public String getStrStatus(){
		return status != null ? WithdrawStatusEnums.getDesc(status) : "";
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="提现申请时间不能为空")
	@ExcelField(title="申请时间", type=1, align=2, sort=110)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="更新时间不能为空")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=50, message="省编码长度必须介于 0 和 50 之间")
	public String getProcode() {
		return procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}
	
	@Length(min=0, max=50, message="省名称长度必须介于 0 和 50 之间")
	@ExcelField(title="省份", type=1, align=2, sort=10)
	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}
	
	@Length(min=0, max=50, message="市编码长度必须介于 0 和 50 之间")
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	
	@Length(min=0, max=50, message="市名称长度必须介于 0 和 50 之间")
	@ExcelField(title="城市", type=1, align=2, sort=20)
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	@Length(min=0, max=50, message="区编码长度必须介于 0 和 50 之间")
	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}
	
	@Length(min=0, max=50, message="区名称长度必须介于 0 和 50 之间")
	@ExcelField(title="区域", type=1, align=2, sort=30)
	public String getDisname() {
		return disname;
	}

	public void setDisname(String disname) {
		this.disname = disname;
	}
	
	@Length(min=1, max=50, message="用名长度必须介于 1 和 50 之间")
	@ExcelField(title="户名", type=1, align=2, sort=120)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=50, message="卡号长度必须介于 1 和 50 之间")
	@ExcelField(title="账号", type=1, align=2, sort=130)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Length(min=1, max=100, message="开户行长度必须介于 1 和 100 之间")
	@ExcelField(title="开户行", type=1, align=2, sort=140)
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@Length(min=0, max=100, message="支行长度必须介于 0 和 100 之间")
	@ExcelField(title="开户支行", type=1, align=2, sort=150)
	public String getBankbranch() {
		return bankbranch;
	}

	public void setBankbranch(String bankbranch) {
		this.bankbranch = bankbranch;
	}
	
	public BigDecimal getBeginCash() {
		return beginCash;
	}

	public void setBeginCash(BigDecimal beginCash) {
		this.beginCash = beginCash;
	}
	
	public BigDecimal getEndCash() {
		return endCash;
	}

	public void setEndCash(BigDecimal endCash) {
		this.endCash = endCash;
	}
		
	public BigDecimal getBeginPrebalance() {
		return beginPrebalance;
	}

	public void setBeginPrebalance(BigDecimal beginPrebalance) {
		this.beginPrebalance = beginPrebalance;
	}
	
	public BigDecimal getEndPrebalance() {
		return endPrebalance;
	}

	public void setEndPrebalance(BigDecimal endPrebalance) {
		this.endPrebalance = endPrebalance;
	}
		
	public BigDecimal getBeginBalance() {
		return beginBalance;
	}

	public void setBeginBalance(BigDecimal beginBalance) {
		this.beginBalance = beginBalance;
	}
	
	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
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
	
	public String getStrRole() {
		return strRole;
	}

	public void setStrRole(String strRole) {
		this.strRole = strRole;
	}

    @ExcelField(title="员工编号", type=1, align=2, sort=115)
    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public List<Integer> getAccountroles() {
        return accountroles;
    }

    public void setAccountroles(List<Integer> accountroles) {
        this.accountroles = accountroles;
    }

    public List<Integer> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Integer> statuses) {
        this.statuses = statuses;
    }
}