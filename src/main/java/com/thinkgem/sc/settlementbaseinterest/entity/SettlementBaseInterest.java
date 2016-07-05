/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.settlementbaseinterest.entity;

import org.codehaus.groovy.tools.StringHelper;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mk.settlement.enums.AccountRoleEnums;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * SettlementBaseInterestEntity
 * @author ft
 * @version 2016-06-14
 */
public class SettlementBaseInterest extends DataEntity<SettlementBaseInterest> {
	
	private static final long serialVersionUID = 1L;
	private String hotelid;		// 酒店ID
	private String hotelname;		// 酒店名称
	private String hotelpms;		// hotelpms
	private Integer accountrole;		// 账户角色
	private String interestrate;		// 利率（小数点后6位）
	private Integer interestratetype;		// 1日 2周  3月   4季度  5年
	private Integer status;		          // 开关 （1开    2关）
	private Integer compoundinterest;		// 是否复利（1：是    2：不是）
	private Date begintime;		// 开始计息日期
	private Date updatetime;		// updatetime
	private Date beginBegintime;		// 开始 开始计息日期
	private Date endBegintime;		// 结束 开始计息日期
	private Date beginUpdatetime;		// 开始 updatetime
	private Date endUpdatetime;		// 结束 updatetime
	
	public SettlementBaseInterest() {
		super();
	}

	public SettlementBaseInterest(String id){
		super(id);
	}

	@Length(min=0, max=50, message="酒店ID长度必须介于 0 和 50 之间")
	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=100, message="酒店名称长度必须介于 0 和 100 之间")
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	@Length(min=0, max=100, message="hotelpms长度必须介于 0 和 100 之间")
	public String getHotelpms() {
		return hotelpms;
	}

	public void setHotelpms(String hotelpms) {
		this.hotelpms = hotelpms;
	}
	
	public Integer getAccountrole() {
		return accountrole;
	}

	public String getStrAccountrole() {
		if(accountrole!=null){
			return AccountRoleEnums.getDesc(accountrole);
		}
		return null;
	}
	
	public void setAccountrole(Integer accountrole) {
		this.accountrole = accountrole;
	}
	
	public String getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}
	
	public Integer getInterestratetype() {
		return interestratetype;
	}

	public void setInterestratetype(Integer interestratetype) {
		this.interestratetype = interestratetype;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getCompoundinterest() {
		return compoundinterest;
	}

	public void setCompoundinterest(Integer compoundinterest) {
		this.compoundinterest = compoundinterest;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public Date getBeginBegintime() {
		return beginBegintime;
	}

	public void setBeginBegintime(Date beginBegintime) {
		this.beginBegintime = beginBegintime;
	}
	
	public Date getEndBegintime() {
		return endBegintime;
	}

	public void setEndBegintime(Date endBegintime) {
		this.endBegintime = endBegintime;
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