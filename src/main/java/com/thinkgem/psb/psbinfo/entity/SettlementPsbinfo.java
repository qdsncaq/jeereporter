/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbinfo.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * psbinfoEntity
 * @author lm
 * @version 2016-04-06
 */
public class SettlementPsbinfo extends DataEntity<SettlementPsbinfo> {
	
	private static final long serialVersionUID = 1L;
	private String psbname;		// psb名称
	private Date begintime;		// 合作开始日期
	private Date endtime;		// 合作结束日期
	private BigDecimal fee;		// psb对应年费
	private Date createtime;		// 创建时间
	private Date updatetime;		// 更新时间
	private Date beginBegintime;		// 开始 合作开始日期
	private Date endBegintime;		// 结束 合作开始日期
	private Date beginEndtime;		// 开始 合作结束日期
	private Date endEndtime;		// 结束 合作结束日期
	private String beginFee;		// 开始 psb对应年费
	private String endFee;		// 结束 psb对应年费
	private Integer delflag; //添加是否删除标识 默认0:不删除, 1:删除

	public SettlementPsbinfo() {
		super();
	}

	public SettlementPsbinfo(String id){
		super(id);
	}
	
	@ExcelField(title="PSB厂家编码", type=1, align=2, sort=10)
	public String getId(){
		return id;
	}

	@Length(min=1, max=255, message="psb名称长度必须介于 1 和 255 之间")
	@ExcelField(title="PSB厂家名称", type=1, align=2, sort=20)
	public String getPsbname() {
		return psbname;
	}

	public void setPsbname(String psbname) {
		this.psbname = psbname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="合作开始日期", type=1, align=2, sort=30)
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="合作结束日期", type=1, align=2, sort=40)
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	public BigDecimal getFee() {
		return fee;
	}
	
	@ExcelField(title="年费", type=1, align=2, sort=50)
	public String getStrFee(){
		return fee + "";
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	@ExcelField(title="记录创建时间", type=1, align=2, sort=60)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="更新时间不能为空")
	@ExcelField(title="记录更新时间", type=1, align=2, sort=70)
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
		
	public Date getBeginEndtime() {
		return beginEndtime;
	}

	public void setBeginEndtime(Date beginEndtime) {
		this.beginEndtime = beginEndtime;
	}
	
	public Date getEndEndtime() {
		return endEndtime;
	}

	public void setEndEndtime(Date endEndtime) {
		this.endEndtime = endEndtime;
	}
		
	public String getBeginFee() {
		return beginFee;
	}

	public void setBeginFee(String beginFee) {
		this.beginFee = beginFee;
	}
	
	public String getEndFee() {
		return endFee;
	}

	public void setEndFee(String endFee) {
		this.endFee = endFee;
	}
	
	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
		
}