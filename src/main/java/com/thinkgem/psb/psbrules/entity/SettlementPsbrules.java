/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbrules.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * psbrulesEntity
 * @author lm
 * @version 2016-04-06
 */
public class SettlementPsbrules extends DataEntity<SettlementPsbrules> {
	
	private static final long serialVersionUID = 1L;
	private Integer psbid;		// psb厂家编号
	private String rulename;		// 科目名称
	private String fee10;		// 科目费用(&lt;10间房)
	private String fee20;		// 科目费用(&gt;=10间房)
	private String remarks;		// 描述说明
	private Date createtime;		// 创建时间
	private Date updatetime;		// 更新时间
	private Integer delflag;		// 是否有效标识
	private Date beginCreatetime;		// 开始 创建时间
	private Date endCreatetime;		// 结束 创建时间
	private Date beginUpdatetime;		// 开始 更新时间
	private Date endUpdatetime;		// 结束 更新时间
	private String psbname;			// psbname
	
	public SettlementPsbrules() {
		super();
	}

	public SettlementPsbrules(String id){
		super(id);
	}
	
	@ExcelField(title="科目编码", type=1, align=2, sort=10)
	public String getId(){
		return id;
	}

	@NotNull(message="psb厂家编号不能为空")
	public Integer getPsbid() {
		return psbid;
	}

	@ExcelField(title="PSB厂家编码", type=1, align=2, sort=20)
	public String getStrPsbid() {
		return psbid+"";
	}
	
	public void setPsbid(Integer psbid) {
		this.psbid = psbid;
	}
	
	@Length(min=1, max=255, message="科目名称长度必须介于 1 和 255 之间")
	@ExcelField(title="科目名称", type=1, align=2, sort=30)
	public String getRulename() {
		return rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}
	
	@Length(min=1, max=255, message="科目费用(&lt;10间房)长度必须介于 1 和 255 之间")
	@ExcelField(title="科目费用(<10间房)", type=1, align=2, sort=40)
	public String getFee10() {
		return fee10;
	}

	public void setFee10(String fee10) {
		this.fee10 = fee10;
	}
	
	@Length(min=1, max=255, message="科目费用(&gt;=10间房)长度必须介于 1 和 255 之间")
	@ExcelField(title="科目费用(>=10间房)", type=1, align=2, sort=50)
	public String getFee20() {
		return fee20;
	}

	public void setFee20(String fee20) {
		this.fee20 = fee20;
	}
	
	@ExcelField(title="描述说明", type=1, align=2, sort=60)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="创建时间", type=1, align=2, sort=70)
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
	
	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
	
	public Date getBeginCreatetime() {
		return beginCreatetime;
	}

	public void setBeginCreatetime(Date beginCreatetime) {
		this.beginCreatetime = beginCreatetime;
	}
	
	public Date getEndCreatetime() {
		return endCreatetime;
	}

	public void setEndCreatetime(Date endCreatetime) {
		this.endCreatetime = endCreatetime;
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

	@ExcelField(title="PSB厂家名称", type=1, align=2, sort=25)
	public String getPsbname() {
		return psbname;
	}

	public void setPsbname(String psbname) {
		this.psbname = psbname;
	}
		
}