/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntinterests.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * accntInterestsEntity
 * @author ld
 * @version 2016-06-17
 */
public class SettlementAccntInterest extends DataEntity<SettlementAccntInterest> {
	
	private static final long serialVersionUID = 1L;
	private Long ledgerid;		// 总账主键id
	private String accountid;		// 账户id
	private String accountname;		// 账户名称
	private String hotelid;		// 酒店id
	private String hotelname;		// 酒店名称
	private String hotelpms;		// 酒店PMS编码
	private String pvalue;		// 计息金额
	private Date begininterestdate;		// 开始计息日期
	private Date endinterestdate;		// 截止计息日期
	private String days;		// 计息天数
	private String interestrate;		// 利率
	private String ratetype;		// 利率类型
	private String isrepeat;		// 是否复利
	private String interests;		// 利息
	private String balanceupdated;		// 余额是否已更新
	private String status;		// 状态
	private Date createtime;		// createtime
	private Date updatetime;		// updatetime
	
	public SettlementAccntInterest() {
		super();
	}

	public SettlementAccntInterest(String id){
		super(id);
	}

	@NotNull(message="总账主键id不能为空")
	public Long getLedgerid() {
		return ledgerid;
	}

	public void setLedgerid(Long ledgerid) {
		this.ledgerid = ledgerid;
	}
	
	@Length(min=1, max=50, message="账户id长度必须介于 1 和 50 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Length(min=0, max=100, message="账户名称长度必须介于 0 和 100 之间")
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	@Length(min=1, max=50, message="酒店id长度必须介于 1 和 50 之间")
	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=500, message="酒店名称长度必须介于 0 和 500 之间")
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	@Length(min=0, max=100, message="酒店PMS编码长度必须介于 0 和 100 之间")
	public String getHotelpms() {
		return hotelpms;
	}

	public void setHotelpms(String hotelpms) {
		this.hotelpms = hotelpms;
	}
	
	public String getPvalue() {
		return pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="开始计息日期不能为空")
	public Date getBegininterestdate() {
		return begininterestdate;
	}

	public void setBegininterestdate(Date begininterestdate) {
		this.begininterestdate = begininterestdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="截止计息日期不能为空")
	public Date getEndinterestdate() {
		return endinterestdate;
	}

	public void setEndinterestdate(Date endinterestdate) {
		this.endinterestdate = endinterestdate;
	}
	
	@Length(min=1, max=8, message="计息天数长度必须介于 1 和 8 之间")
	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}
	
	public String getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}
	
	@Length(min=1, max=1, message="利率类型长度必须介于 1 和 1 之间")
	public String getRatetype() {
		return ratetype;
	}

	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}
	
	@Length(min=0, max=1, message="是否复利长度必须介于 0 和 1 之间")
	public String getIsrepeat() {
		return isrepeat;
	}

	public void setIsrepeat(String isrepeat) {
		this.isrepeat = isrepeat;
	}
	
	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	@Length(min=1, max=1, message="余额是否已更新长度必须介于 1 和 1 之间")
	public String getBalanceupdated() {
		return balanceupdated;
	}

	public void setBalanceupdated(String balanceupdated) {
		this.balanceupdated = balanceupdated;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="createtime不能为空")
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
	
}