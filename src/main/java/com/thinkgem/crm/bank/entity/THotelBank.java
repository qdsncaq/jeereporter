/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.bank.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 酒店银行账号Entity
 * @author 李雪楠
 * @version 2016-05-17
 */
public class THotelBank extends DataEntity<THotelBank> {
	
	private static final long serialVersionUID = 1L;
	private Long hotelid;		// 酒店Id
	private String hotelname; //酒店名称
	private String bank;		// 开户行
	private String bankbranch;		// 支行
	private String transfertype;		// 转帐类型，1：同城（针对上海市），2:异地
	private String name;		// 用名
	private String account;		// 卡号
	private Date createtime;		// createtime
	private Date updatetime;		// 更新时间
	
	public THotelBank() {
		super();
	}

	public THotelBank(String id){
		super(id);
	}

	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=100, message="开户行长度必须介于 0 和 100 之间")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@Length(min=0, max=100, message="支行长度必须介于 0 和 100 之间")
	public String getBankbranch() {
		return bankbranch;
	}

	public void setBankbranch(String bankbranch) {
		this.bankbranch = bankbranch;
	}
	
	@Length(min=0, max=50, message="转帐类型，1：同城（针对上海市），2:异地长度必须介于 0 和 50 之间")
	public String getTransfertype() {
		return transfertype;
	}

	public void setTransfertype(String transfertype) {
		this.transfertype = transfertype;
	}
	
	@Length(min=0, max=50, message="用名长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="卡号长度必须介于 0 和 50 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
}