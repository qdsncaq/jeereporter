package com.thinkgem.jeesite.modules.settlement.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountLedger implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8210313811277917423L;

    private Long id;

    private String accountid;

    private Integer accountstate;

    private Date frozendate;

    private Date unfrozendate;

    private BigDecimal balance;

    private Date createTime;

    private Date updateTime;
    
    private Integer accountrole;
    
    private BigDecimal waitpayment;
    
    private BigDecimal postpayBalance;
    
    private BigDecimal postpayThreshold;
    
    private String hotelid;

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public void setPostpayThreshold(BigDecimal postpayThreshold) {
        this.postpayThreshold = postpayThreshold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public Integer getAccountstate() {
        return accountstate;
    }

    public void setAccountstate(Integer accountstate) {
        this.accountstate = accountstate;
    }

    public Date getFrozendate() {
        return frozendate;
    }

    public void setFrozendate(Date frozendate) {
        this.frozendate = frozendate;
    }

    public Date getUnfrozendate() {
        return unfrozendate;
    }

    public void setUnfrozendate(Date unfrozendate) {
        this.unfrozendate = unfrozendate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public Integer getAccountrole() {
        return accountrole;
    }

    public void setAccountrole(Integer accountrole) {
        this.accountrole = accountrole;
    }

    public BigDecimal getWaitpayment() {
        return waitpayment;
    }

    public void setWaitpayment(BigDecimal waitpayment) {
        this.waitpayment = waitpayment;
    }
    
    public BigDecimal getPostpayBalance() {
        return postpayBalance;
    }

    public void setPostpayBalance(BigDecimal postpayBalance) {
        this.postpayBalance = postpayBalance;
    }

    public BigDecimal getPostpayThreshold() {
        return postpayThreshold;
    }
}