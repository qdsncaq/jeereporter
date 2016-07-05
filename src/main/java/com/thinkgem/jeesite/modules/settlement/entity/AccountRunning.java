package com.thinkgem.jeesite.modules.settlement.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.persistence.Page;

public class AccountRunning extends DataEntity<AccountRunning> {

    /**
     * 
     */
    private static final long serialVersionUID = -7751446174792706143L;

    private String accountid;
    
    private Long orderid;

    private Long hotelid;
    
    private String hotelname;

    private Integer biztype;
    
    private String strBiztype;

    private BigDecimal ordertotal;

    private Date ordertime;

    private Integer roomnums;

    private Integer days;

    private Date createTime;

    private Date updateTime;
    
    private Integer accountrole;
    
    private String strAccountrole;
    
    private String hotelpms;
    
    private Integer status;
    
    private Integer orderstatus;
    
    private BigDecimal freetotal;
    
    private Long driverid;
    
    private Long washfactoryid;

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public Integer getBiztype() {
        return biztype;
    }
    
    public void setBiztype(Integer biztype) {
        this.biztype = biztype;
    }

    public String getStrBiztype() {
        return strBiztype;
    }

    public void setStrBiztype(String strBiztype) {
        this.strBiztype = strBiztype;
    }

    public BigDecimal getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(BigDecimal ordertotal) {
        this.ordertotal = ordertotal;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getRoomnums() {
        return roomnums;
    }

    public void setRoomnums(Integer roomnums) {
        this.roomnums = roomnums;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
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

    public String getStrAccountrole() {
        return strAccountrole;
    }

    public void setStrAccountrole(String strAccountrole) {
        this.strAccountrole = strAccountrole;
    }

    public String getHotelpms() {
        return hotelpms;
    }

    public void setHotelpms(String hotelpms) {
        this.hotelpms = hotelpms;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public BigDecimal getFreetotal() {
        return freetotal;
    }

    public void setFreetotal(BigDecimal freetotal) {
        this.freetotal = freetotal;
    }

    public Long getDriverid() {
        return driverid;
    }

    public void setDriverid(Long driverid) {
        this.driverid = driverid;
    }

    public Long getWashfactoryid() {
        return washfactoryid;
    }

    public void setWashfactoryid(Long washfactoryid) {
        this.washfactoryid = washfactoryid;
    }

    @Override
    @JsonProperty
    @JsonIgnore(false)
    public Page<AccountRunning> getPage() {
        return super.getPage();
    }

    @Override
    @JsonProperty()
    @JsonIgnore(false)
    public Page<AccountRunning> setPage(Page<AccountRunning> page) {
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
