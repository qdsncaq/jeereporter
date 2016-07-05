/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbrulesrelation.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * psbrulesrelationEntity
 * @author lm
 * @version 2016-04-08
 */
public class SettlementPsbrulesrelation extends DataEntity<SettlementPsbrulesrelation> {
	
	private static final long serialVersionUID = 1L;
	private String psbid;		// PSB编码
	private String procode;		// 省编码
	private String proname;		// 省名称
	private String citycode;		// 市编码
	private String cityname;		// 市名称
	private String discode;		// 区编码
	private String disname;		// 区名称
	private String hotelid;		// 酒店ID
	private String hotelpms;		// PMS编码
	private String hotelname;		// 酒店名称
	private String cityrules;		// 城市对应规则
	private String hotelrules;		// 酒店对应规则
	private Date begintime;		// 生效时间
	private Date endtime;		// 失效时间
	private Date createtime;		// 创建时间
	private Date updatetime;		// 更新时间
	private Date beginBegintime;		// 开始 生效时间
	private Date endBegintime;		// 结束 生效时间
	private Date beginEndtime;		// 开始 失效时间
	private Date endEndtime;		// 结束 失效时间
	private Date beginCreatetime;		// 开始 创建时间
	private Date endCreatetime;		// 结束 创建时间
	private Date beginUpdatetime;		// 开始 更新时间
	private Date endUpdatetime;		// 结束 更新时间
	private Integer areatype;		// 区分区域规则1 和 酒店规则3 区域规则2
	private String disrules;		// 区域规则
	
	private String psbname;			// psbname
	
	private String ruledesc; 
	
	private Integer delflag;

	public SettlementPsbrulesrelation() {
		super();
	}

	public SettlementPsbrulesrelation(String id){
		super(id);
	}

	@Length(min=1, max=11, message="PSB编码长度必须介于 1 和 11 之间")
	@ExcelField(title="PSB编码", type=1, align=2, sort=40)
	public String getPsbid() {
		return psbid;
	}

	public void setPsbid(String psbid) {
		this.psbid = psbid;
	}
	
	@ExcelField(title="编号", type=1, align=2, sort=10)
	public String getId(){
		return id;
	}
	
	@Length(min=1, max=200, message="省编码长度必须介于 1 和 200 之间")
	public String getProcode() {
		return procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}
	
	@Length(min=1, max=200, message="省名称长度必须介于 1 和 200 之间")
	@ExcelField(title="省名称", type=1, align=1, sort=50)
	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}
	
	@Length(min=1, max=200, message="市编码长度必须介于 1 和 200 之间")
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	
	@Length(min=1, max=200, message="市名称长度必须介于 1 和 200 之间")
	@ExcelField(title="市名称", type=1, align=1, sort=60)
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	@Length(min=0, max=200, message="区编码长度必须介于 0 和 200 之间")
	@ExcelField(title="区名称", type=1, align=1, sort=61)
	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}
	
	@Length(min=0, max=50, message="区名称长度必须介于 0 和 50 之间")
	public String getDisname() {
		return disname;
	}

	public void setDisname(String disname) {
		this.disname = disname;
	}
	
	@Length(min=0, max=50, message="酒店ID长度必须介于 0 和 50 之间")
	@ExcelField(title="酒店ID", type=1, align=1, sort=20)
	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=100, message="PMS编码长度必须介于 0 和 100 之间")
	public String getHotelpms() {
		return hotelpms;
	}

	public void setHotelpms(String hotelpms) {
		this.hotelpms = hotelpms;
	}
	
	@Length(min=0, max=500, message="酒店名称长度必须介于 0 和 500 之间")
	@ExcelField(title="酒店名称", type=1, align=1, sort=30)
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	@Length(min=0, max=200, message="城市对应规则长度必须介于 0 和 200 之间")
	@ExcelField(title="城市对应规则", type=1, align=2, sort=75)
	public String getCityrules() {
		return cityrules;
	}

	public void setCityrules(String cityrules) {
		this.cityrules = cityrules;
	}
	
	@Length(min=0, max=200, message="酒店对应规则长度必须介于 0 和 200 之间")
	@ExcelField(title="酒店对应规则", type=1, align=2, sort=70)
	public String getHotelrules() {
		return hotelrules;
	}

	public void setHotelrules(String hotelrules) {
		this.hotelrules = hotelrules;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="生效时间不能为空")
	@ExcelField(title="生效时间", type=1, align=2, sort=80)
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="失效时间不能为空")
	@ExcelField(title="失效时间", type=1, align=2, sort=90)
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="创建时间", type=1, align=2, sort=100)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="更新时间", type=1, align=2, sort=110)
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
	
	public Integer getAreatype() {
		return areatype;
	}

	public void setAreatype(Integer areatype) {
		this.areatype = areatype;
	}

	@ExcelField(title="区域对应规则", type=1, align=2, sort=72)
	public String getDisrules() {
		return disrules;
	}

	public void setDisrules(String disrules) {
		this.disrules = disrules;
	}

	@ExcelField(title="PSB名称", type=1, align=2, sort=45)
	public String getPsbname() {
		return psbname;
	}

	public void setPsbname(String psbname) {
		this.psbname = psbname;
	}

	@ExcelField(title="规则描述", type=1, align=1, sort=77)
	public String getRuledesc() {
		return ruledesc;
	}

	public void setRuledesc(String ruledesc) {
		this.ruledesc = ruledesc;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}

}