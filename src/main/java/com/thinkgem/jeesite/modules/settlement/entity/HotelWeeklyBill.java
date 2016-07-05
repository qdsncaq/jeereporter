package com.thinkgem.jeesite.modules.settlement.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.persistence.Page;


public class HotelWeeklyBill extends DataEntity<HotelWeeklyBill> {

    /**
     * 
     */
    private static final long serialVersionUID = 2048036447793297940L;
    

    private Integer accountyear;

    private Integer accountmonth;
    
    private Integer accountweek;

    private String accountid;

    private Integer accountstate;

    private Integer accountrole;

    //账单总额
    private BigDecimal balance;

    //调整金额
    private BigDecimal adjustvalue;

    private Date createTime;

    private Date updateTime;
    
    private String hotelid;
    
    private String hotelname;
    
    private String hotelpms;
    
    //账单起始日期
    //@DateTimeFormat(pattern="yyyy-MM-dd") 
    private Date beginDate;
    
    //账单结束日期
    //@DateTimeFormat(pattern="yyyy-MM-dd") 
    private Date endDate;
    
    //结算方式(周/月 等)
    private Integer sctype;
    
    //付款方式(储值/后付费)
    private Integer paytype;
    
    //账单状态
    private Integer billstate;
    
    //结算总额
    private BigDecimal scsum;
    
    //储值付费金额
    private BigDecimal deposit;
    
    //后付费金额
    private BigDecimal overdraft;
    
    //折扣金额
    private BigDecimal discount;
    
    //业务类型
    private Integer biztype;
    
    //财务状态(已结算/未结算)
    private Integer scstate;
    
    private BigDecimal beginbalance;
    
    private Integer feeType;

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Integer getScstate() {
        return scstate;
    }

    public void setScstate(Integer scstate) {
        this.scstate = scstate;
    }

    public Integer getBiztype() {
        return biztype;
    }

    public void setBiztype(Integer biztype) {
        this.biztype = biztype;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSctype() {
        return sctype;
    }

    public void setSctype(Integer sctype) {
        this.sctype = sctype;
    }

    public Integer getPaytype() {
        return paytype;
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

    public BigDecimal getScsum() {
        return scsum;
    }

    public void setScsum(BigDecimal scsum) {
        this.scsum = scsum;
    }

    public BigDecimal getDeposit() {
        return deposit;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

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

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAdjustvalue() {
        return adjustvalue;
    }

    public void setAdjustvalue(BigDecimal adjustvalue) {
        this.adjustvalue = adjustvalue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getHotelpms() {
        return hotelpms;
    }

    public void setHotelpms(String hotelpms) {
        this.hotelpms = hotelpms;
    }

    public BigDecimal getBeginbalance() {
        return beginbalance;
    }

    public void setBeginbalance(BigDecimal beginbalance) {
        this.beginbalance = beginbalance;
    }
    
    @Override
    @JsonProperty
    @JsonIgnore(false)
    public Page<HotelWeeklyBill> getPage() {
        return super.getPage();
    }

    @Override
    @JsonProperty()
    @JsonIgnore(false)
    public Page<HotelWeeklyBill> setPage(Page<HotelWeeklyBill> page) {
        return super.setPage(page);
    }

    @Override
    @JsonIgnore
    public boolean getIsNewRecord() {
        return super.getIsNewRecord();
    }

    @Override
    @JsonIgnore
    public void setIsNewRecord(boolean isNewRecord) {
        super.setIsNewRecord(isNewRecord);
    }

}
