/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbhoteloperlog.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * psbhoteloperlogEntity
 * @author lm
 * @version 2016-04-11
 */
public class THotelOperateLog extends DataEntity<THotelOperateLog> {
	
	private static final long serialVersionUID = 1L;
	private Long hotelid;		// 酒店ID
	private String hotelname;		// 酒店名称
	private Long usercode;		// 用户ID
	private String username;		// 用户姓名
	private Date checktime;		// 审核时间
	private Integer checktype;		// 审核类型（1，初次审核；2，更新审核）
	private String checktypename;		// 审核类型名称
	private Date beginChecktime;		// 开始 审核时间
	private Date endChecktime;		// 结束 审核时间
	
	@ModelAttribute("tHotelOperateLog")
	public THotelOperateLog getTHotelOperateLog() {
		return new THotelOperateLog();
	}
	
	public THotelOperateLog() {
		super();
	}

	public THotelOperateLog(String id){
		super(id);
	}

	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=100, message="酒店名称长度必须介于 0 和 100 之间")
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	public Long getUsercode() {
		return usercode;
	}

	public void setUsercode(Long usercode) {
		this.usercode = usercode;
	}
	
	@Length(min=0, max=50, message="用户姓名长度必须介于 0 和 50 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getChecktime() {
		return checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}
	
	public Integer getChecktype() {
		return checktype;
	}

	public void setChecktype(Integer checktype) {
		this.checktype = checktype;
	}
	
	@Length(min=0, max=50, message="审核类型名称长度必须介于 0 和 50 之间")
	public String getChecktypename() {
		return checktypename;
	}

	public void setChecktypename(String checktypename) {
		this.checktypename = checktypename;
	}
	
	public Date getBeginChecktime() {
		return beginChecktime;
	}

	public void setBeginChecktime(Date beginChecktime) {
		this.beginChecktime = beginChecktime;
	}
	
	public Date getEndChecktime() {
		return endChecktime;
	}

	public void setEndChecktime(Date endChecktime) {
		this.endChecktime = endChecktime;
	}
		
}