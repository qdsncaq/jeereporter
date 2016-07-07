package com.thinkgem.jeesite.modules.rpt.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 锌层测厚班报表Entity.
 * 
 * @author xiaohong
 *
 */
public class ZinccoatingWorkTeamReport extends DataEntity<ZinccoatingWorkTeamReport> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8577779633531787114L;
	
	private Date logtime;
	private Integer loggroup;
	private String loggroupname;
	private String prodcode;
	private String gencode;
	private Integer workmode;
	private String workmodename;
	private Double bandwidth;
	private Double bandthickness;
	private Double linespeed;
	private Double totallen;
	private Double totalsteel;    // 过钢量(吨)
	private Double zincratetarget;    // 目标上锌量（克）
	private Double zincrateavgfront;    // 正面平米上锌量平均值（克）
	private Double zincrateavgreverse;    // 反面平米上锌量平均值（克）
	private Double zinctotalinc;    // 上锌总增总（千克）
	private Double zinctotalincfront;    // 正面上锌增重（千克）
	private Double zinctotalincreverse;    // 反面上锌增重（千克）
	private Date beginLogtime;		// 开始 记录时间
	private Date endLogtime;		// 结束 记录时间
	
	/** getters and setters */
	
	public Date getLogtime() {
		return logtime;
	}
	
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	
	public Integer getLoggroup() {
		return loggroup;
	}
	
	public void setLoggroup(Integer loggroup) {
		this.loggroup = loggroup;
	}
	
	public String getLoggroupname() {
		return loggroupname;
	}
	
	public void setLoggroupname(String loggroupname) {
		this.loggroupname = loggroupname;
	}
	
	public String getProdcode() {
		return prodcode;
	}
	
	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}
	
	public String getGencode() {
		return gencode;
	}
	
	public void setGencode(String gencode) {
		this.gencode = gencode;
	}
	
	public Integer getWorkmode() {
		return workmode;
	}
	
	public void setWorkmode(Integer workmode) {
		this.workmode = workmode;
	}
	
	public String getWorkmodename() {
		return workmodename;
	}
	
	public void setWorkmodename(String workmodename) {
		this.workmodename = workmodename;
	}
	
	public Double getBandwidth() {
		return bandwidth;
	}
	
	public void setBandwidth(Double bandwidth) {
		this.bandwidth = bandwidth;
	}
	
	public Double getBandthickness() {
		return bandthickness;
	}
	
	public void setBandthickness(Double bandthickness) {
		this.bandthickness = bandthickness;
	}
	
	public Double getLinespeed() {
		return linespeed;
	}
	
	public void setLinespeed(Double linespeed) {
		this.linespeed = linespeed;
	}
	
	public Double getTotallen() {
		return totallen;
	}
	
	public void setTotallen(Double totallen) {
		this.totallen = totallen;
	}
	
	public Double getTotalsteel() {
		return totalsteel;
	}
	
	public void setTotalsteel(Double totalsteel) {
		this.totalsteel = totalsteel;
	}
	
	public Double getZincratetarget() {
		return zincratetarget;
	}
	
	public void setZincratetarget(Double zincratetarget) {
		this.zincratetarget = zincratetarget;
	}
	
	public Double getZincrateavgfront() {
		return zincrateavgfront;
	}
	
	public void setZincrateavgfront(Double zincrateavgfront) {
		this.zincrateavgfront = zincrateavgfront;
	}
	
	public Double getZincrateavgreverse() {
		return zincrateavgreverse;
	}
	
	public void setZincrateavgreverse(Double zincrateavgreverse) {
		this.zincrateavgreverse = zincrateavgreverse;
	}
	
	public Double getZinctotalinc() {
		return zinctotalinc;
	}
	
	public void setZinctotalinc(Double zinctotalinc) {
		this.zinctotalinc = zinctotalinc;
	}
	
	public Double getZinctotalincfront() {
		return zinctotalincfront;
	}
	
	public void setZinctotalincfront(Double zinctotalincfront) {
		this.zinctotalincfront = zinctotalincfront;
	}
	
	public Double getZinctotalincreverse() {
		return zinctotalincreverse;
	}
	
	public void setZinctotalincreverse(Double zinctotalincreverse) {
		this.zinctotalincreverse = zinctotalincreverse;
	}
	
	public Date getBeginLogtime() {
		return beginLogtime;
	}

	public void setBeginLogtime(Date beginLogtime) {
		this.beginLogtime = beginLogtime;
	}
	
	public Date getEndLogtime() {
		return endLogtime;
	}

	public void setEndLogtime(Date endLogtime) {
		this.endLogtime = endLogtime;
	}

}
