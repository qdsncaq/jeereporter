/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntbill.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mk.settlement.enums.BizTypeEnums;
import com.mk.settlement.enums.SettlementStatusEnums;
import com.mk.settlement.enums.SettlementTypeEnums;
import com.mk.settlement.enums.TripAccntTypeEnums;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * accntbillEntity
 * @author lm
 * @version 2016-03-21
 */
public class SettlementAccntBill extends DataEntity<SettlementAccntBill> {
	
	private static final long serialVersionUID = 1L;
	private Integer accountyear;		// 年
	private Integer accountmonth;		// 月
	private Integer accountweek;		// 周
	private String accountid;		// 账户ID
	private Integer accountstate;		// 财务状态
	private Integer accountrole;		// 账户角色
	private BigDecimal balance;		// 账单总额
	private String strBalance;		// 账单总额
	private BigDecimal scsum;		// 结算总额
	private String strScsum;		// 结算总额
	private BigDecimal deposit;		// 储蓄账户扣除金额
	private String strDeposit;		// 储蓄账户扣除金额
	private BigDecimal overdraft;		// 后付费账户扣除金额
	private String strOverdraft;		// 后付费账户扣除金额
	private BigDecimal discount;		// 折扣
	private String strDiscount;		// 折扣
	private BigDecimal adjustvalue;		// 调整金额
	private String strAdjustvalue;		// 调整金额
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String hotelid;		// 酒店ID
	private String hotelname;		// 酒店名称
	private String hotelpms;		// PMS编码
	private Date beginDate;		// 账单起始日期
	private Date endDate;		// 账单结束日期
	private Integer sctype;		// 结算方式
	private Integer paytype;		// 付费方式
	private Integer billstate;		// 账单状态
	private Integer biztype;		// 业务类型
	private Integer scstate;		// 财务状态
	private String beginbalance;		// 原始账单金额
	private Integer beginAccountyear;		// 开始 年
	private Integer endAccountyear;		// 结束 年
	private Integer beginAccountmonth;		// 开始 月
	private Integer endAccountmonth;		// 结束 月
	private Integer beginAccountweek;		// 开始 周
	private Integer endAccountweek;		// 结束 周
	private String beginBalance;		// 开始 账单总额
	private String endBalance;		// 结束 账单总额
	private String beginScsum;		// 开始 结算总额
	private String endScsum;		// 结束 结算总额
	private String beginDeposit;		// 开始 储蓄账户扣除金额
	private String endDeposit;		// 结束 储蓄账户扣除金额
	private String beginOverdraft;		// 开始 后付费账户扣除金额
	private String endOverdraft;		// 结束 后付费账户扣除金额
	private String beginDiscount;		// 开始 折扣
	private String endDiscount;		// 结束 折扣
	private String beginAdjustvalue;		// 开始 调整金额
	private String endAdjustvalue;		// 结束 调整金额
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间
	private Date beginUpdateTime;		// 开始 更新时间
	private Date endUpdateTime;		// 结束 更新时间
    private Date beginBeginDate;        // 开始 账单起始日期
    private Date endBeginDate;      // 结束 账单起始日期
    private Date beginEndDate;      // 开始 账单结束日期
    private Date endEndDate;        // 结束 账单结束日期
	private String beginBeginbalance;		// 开始 原始账单金额
	private String endBeginbalance;		// 结束 原始账单金额
	private String strbiztype;     // 业务类型名称
	
	private List<Integer> roles;
	
	public SettlementAccntBill() {
		super();
	}

	public SettlementAccntBill(String id){
		super(id);
	}
	
	@ExcelField(title="账单号", type=1, align=2, sort=5)
	public String getId(){
		return id;
	}

	public Integer getAccountyear() {
		return accountyear;
	}

	public void setAccountyear(Integer accountyear) {
		this.accountyear = accountyear;
	}
	
	public Integer getAccountmonth() {
		return accountmonth;
	}

	public void setAccountmonth(Integer accountmonth) {
		this.accountmonth = accountmonth;
	}
	
	public Integer getAccountweek() {
		return accountweek;
	}

	public void setAccountweek(Integer accountweek) {
		this.accountweek = accountweek;
	}
	
	@Length(min=1, max=50, message="账户ID长度必须介于 1 和 50 之间")
	@ExcelField(title="账户ID", type=1, align=2, sort=30)
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@NotNull(message="财务状态不能为空")
	public Integer getAccountstate() {
		return accountstate;
	}

	public void setAccountstate(Integer accountstate) {
		this.accountstate = accountstate;
	}
	
	public Integer getAccountrole() {
		return accountrole;
	}

	public void setAccountrole(Integer accountrole) {
		this.accountrole = accountrole;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	@ExcelField(title="订单金额", type=1, align=2, sort=90)
	public String getStrBalance() {
		return strBalance;
	}
	
	public void setStrBalance(String strBalance) {
		this.strBalance = strBalance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public BigDecimal getScsum() {
		return scsum;
	}
	
	@ExcelField(title="结算金额", type=1, align=2, sort=80)
	public String getStrScsum() {
		return strScsum;
	}
	
	public void setStrScsum(String strScsum) {
		this.strScsum = strScsum;
	}

	public void setScsum(BigDecimal scsum) {
		this.scsum = scsum;
	}
	
	public BigDecimal getDeposit() {
		return deposit;
	}
	
	@ExcelField(title="充值卡抵扣金额", type=1, align=2, sort=120)
	public String getStrDeposit() {
		return strDeposit;
	}
	
	public void setStrDeposit(String strDeposit) {
		this.strDeposit = strDeposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	
	public BigDecimal getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(BigDecimal overdraft) {
		this.overdraft = overdraft;
	}
	
	public String getStrOverdraft() {
		return strOverdraft;
	}

	public void setStrOverdraft(String strOverdraft) {
		this.strOverdraft = strOverdraft;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}
	
	@ExcelField(title="折扣金额", type=1, align=2, sort=100)
	public String getStrDiscount() {
		return strDiscount;
	}
	
	public void setStrDiscount(String strDiscount) {
		this.strDiscount = strDiscount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	public BigDecimal getAdjustvalue() {
		return adjustvalue;
	}
	
	@ExcelField(title="调整金额", type=1, align=2, sort=110)
	public String getStrAdjustvalue() {
		return strAdjustvalue;
	}
	
	public void setStrAdjustvalue(String strAdjustvalue) {
		this.strAdjustvalue = strAdjustvalue;
	}

	public void setAdjustvalue(BigDecimal adjustvalue) {
		this.adjustvalue = adjustvalue;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
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
	
	@Length(min=0, max=50, message="酒店ID长度必须介于 0 和 50 之间")
	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=500, message="酒店名称长度必须介于 0 和 500 之间")
	@ExcelField(title="客户名称", type=1, align=2, sort=40)
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	@Length(min=0, max=100, message="PMS编码长度必须介于 0 和 100 之间")
	public String getHotelpms() {
		return hotelpms;
	}

	public void setHotelpms(String hotelpms) {
		this.hotelpms = hotelpms;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开始日期", type=1, align=2, sort=10)
	public Date getBeginDate() {
		return beginDate;
	}
	
	public String getStrBeginDate(){
		return beginDate + "";
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="结束日期", type=1, align=2, sort=20)
	public Date getEndDate() {
		return endDate;
	}
	
	public String getStrEndDate() {
		return endDate + "";
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Integer getSctype() {
		return sctype;
	}
	
	@ExcelField(title="结算方式", type=1, align=2, sort=50)
	public String getStrSctype() {
		return sctype != null ? SettlementTypeEnums.getDesc(sctype) : "";
	}

	public void setSctype(Integer sctype) {
		this.sctype = sctype;
	}
	
	public Integer getPaytype() {
		return paytype;
	}
	
	@ExcelField(title="付款方式", type=1, align=2, sort=60)
	public String getStrPaytype() {
		return paytype != null ? TripAccntTypeEnums.getDesc(paytype) : "";
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	
	public Integer getBillstate() {
		return billstate;
	}

	public void setBillstate(Integer billstate) {
		this.billstate = billstate;
	}
	
	public Integer getBiztype() {
		return biztype;
	}

	public void setBiztype(Integer biztype) {
		this.biztype = biztype;
	}
	
	public Integer getScstate() {
		return scstate;
	}

	@ExcelField(title="财务状态", type=1, align=2, sort=70)
	public String getStrScstate() {
		return scstate != null ? SettlementStatusEnums.getDesc(scstate) : "";
	}
	
	public void setScstate(Integer scstate) {
		this.scstate = scstate;
	}
	
	public String getBeginbalance() {
		return beginbalance;
	}

	public void setBeginbalance(String beginbalance) {
		this.beginbalance = beginbalance;
	}
	
	public Integer getBeginAccountyear() {
		return beginAccountyear;
	}

	public void setBeginAccountyear(Integer beginAccountyear) {
		this.beginAccountyear = beginAccountyear;
	}
	
	public Integer getEndAccountyear() {
		return endAccountyear;
	}

	public void setEndAccountyear(Integer endAccountyear) {
		this.endAccountyear = endAccountyear;
	}
		
	public Integer getBeginAccountmonth() {
		return beginAccountmonth;
	}

	public void setBeginAccountmonth(Integer beginAccountmonth) {
		this.beginAccountmonth = beginAccountmonth;
	}
	
	public Integer getEndAccountmonth() {
		return endAccountmonth;
	}

	public void setEndAccountmonth(Integer endAccountmonth) {
		this.endAccountmonth = endAccountmonth;
	}
		
	public Integer getBeginAccountweek() {
		return beginAccountweek;
	}

	public void setBeginAccountweek(Integer beginAccountweek) {
		this.beginAccountweek = beginAccountweek;
	}
	
	public Integer getEndAccountweek() {
		return endAccountweek;
	}

	public void setEndAccountweek(Integer endAccountweek) {
		this.endAccountweek = endAccountweek;
	}
		
	public String getBeginBalance() {
		return beginBalance;
	}

	public void setBeginBalance(String beginBalance) {
		this.beginBalance = beginBalance;
	}
	
	public String getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(String endBalance) {
		this.endBalance = endBalance;
	}
		
	public String getBeginScsum() {
		return beginScsum;
	}

	public void setBeginScsum(String beginScsum) {
		this.beginScsum = beginScsum;
	}
	
	public String getEndScsum() {
		return endScsum;
	}

	public void setEndScsum(String endScsum) {
		this.endScsum = endScsum;
	}
		
	public String getBeginDeposit() {
		return beginDeposit;
	}

	public void setBeginDeposit(String beginDeposit) {
		this.beginDeposit = beginDeposit;
	}
	
	public String getEndDeposit() {
		return endDeposit;
	}

	public void setEndDeposit(String endDeposit) {
		this.endDeposit = endDeposit;
	}
		
	public String getBeginOverdraft() {
		return beginOverdraft;
	}

	public void setBeginOverdraft(String beginOverdraft) {
		this.beginOverdraft = beginOverdraft;
	}
	
	public String getEndOverdraft() {
		return endOverdraft;
	}

	public void setEndOverdraft(String endOverdraft) {
		this.endOverdraft = endOverdraft;
	}
		
	public String getBeginDiscount() {
		return beginDiscount;
	}

	public void setBeginDiscount(String beginDiscount) {
		this.beginDiscount = beginDiscount;
	}
	
	public String getEndDiscount() {
		return endDiscount;
	}

	public void setEndDiscount(String endDiscount) {
		this.endDiscount = endDiscount;
	}
		
	public String getBeginAdjustvalue() {
		return beginAdjustvalue;
	}

	public void setBeginAdjustvalue(String beginAdjustvalue) {
		this.beginAdjustvalue = beginAdjustvalue;
	}
	
	public String getEndAdjustvalue() {
		return endAdjustvalue;
	}

	public void setEndAdjustvalue(String endAdjustvalue) {
		this.endAdjustvalue = endAdjustvalue;
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

    public Date getBeginBeginDate() {
        return beginBeginDate;
    }

    public void setBeginBeginDate(Date beginBeginDate) {
        this.beginBeginDate = beginBeginDate;
    }

    public Date getEndBeginDate() {
        return endBeginDate;
    }

    public void setEndBeginDate(Date endBeginDate) {
        this.endBeginDate = endBeginDate;
    }
        
    public Date getBeginEndDate() {
        return beginEndDate;
    }

    public void setBeginEndDate(Date beginEndDate) {
        this.beginEndDate = beginEndDate;
    }
    
    public Date getEndEndDate() {
        return endEndDate;
    }

    public void setEndEndDate(Date endEndDate) {
        this.endEndDate = endEndDate;
    }

	public String getBeginBeginbalance() {
		return beginBeginbalance;
	}

	public void setBeginBeginbalance(String beginBeginbalance) {
		this.beginBeginbalance = beginBeginbalance;
	}
	
	public String getEndBeginbalance() {
		return endBeginbalance;
	}

	public void setEndBeginbalance(String endBeginbalance) {
		this.endBeginbalance = endBeginbalance;
	}

    public String getStrbiztype() {
        strbiztype = BizTypeEnums.getDesc(biztype) == null ? "" : BizTypeEnums.getDesc(biztype);
        return strbiztype;
    }

    public void setStrbiztype(String strbiztype) {
        this.strbiztype = strbiztype;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }
    
}