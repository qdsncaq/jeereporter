/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.onoffdetail.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.psb.common.DateUtils;

/**
 * onoffdetailEntity
 * @author lm
 * @version 2016-05-11
 */
public class OnOffLineDetail extends DataEntity<OnOffLineDetail> {
	
	private static final long serialVersionUID = 1L;
	private Long hotelId;		// 酒店ID
	private String pms;		// 酒店pms码
	private String operateId;		// 操作人ID
	private String operateName;		// 操作人名称
	private Date onOffTime;		// 上下线时间
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private String remarkType;		// 数据类型
	private String type;		// 上下线,1-已上线，2-已下线，3-未上线
	private Date beginOnOffTime;		// 开始 上下线时间
	private Date endOnOffTime;		// 结束 上下线时间
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间
	
	private String hotelname;
	private String procode;
	private String proname;
	private String citycode;
	private String cityname;
	private String discode;
	private String disname;
	
	private String remarks;
	
	public OnOffLineDetail() {
		super();
	}

	public OnOffLineDetail(String id){
		super(id);
	}

	@ExcelField(title="酒店ID", type=1, align=2, sort=10)
	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	
	@Length(min=0, max=100, message="酒店pms码长度必须介于 0 和 100 之间")
	@ExcelField(title="酒店PMS", type=1, align=2, sort=20)
	public String getPms() {
		return pms;
	}

	public void setPms(String pms) {
		this.pms = pms;
	}
	
	@Length(min=0, max=64, message="操作人ID长度必须介于 0 和 64 之间")
	@ExcelField(title="操作人ID", type=1, align=2, sort=70)
	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
	
	@Length(min=0, max=32, message="操作人名称长度必须介于 0 和 32 之间")
	@ExcelField(title="操作人名称", type=1, align=2, sort=80)
	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOnOffTime() {
		return onOffTime;
	}
	
	@ExcelField(title="上下线时间", type=1, align=2, sort=90)
	public String getStrOnOffTime() {
		return DateUtils.getStringFromDate(onOffTime, DateUtils.FORMAT_DATETIME);
	}

	public void setOnOffTime(Date onOffTime) {
		this.onOffTime = onOffTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	
	@ExcelField(title="创建时间", type=1, align=2, sort=100)
	public String getStrCreateTime() {
		return DateUtils.getStringFromDate(createTime, DateUtils.FORMAT_DATETIME);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}
	
	@ExcelField(title="更新时间", type=1, align=2, sort=110)
	public String getStrUpdateTime() {
		return DateUtils.getStringFromDate(updateTime, DateUtils.FORMAT_DATETIME);
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@ExcelField(title="备注信息", type=1, align=2, sort=120)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Length(min=0, max=10, message="数据类型长度必须介于 0 和 10 之间")
	public String getRemarkType() {
		return remarkType;
	}

	public void setRemarkType(String remarkType) {
		this.remarkType = remarkType;
	}
	
	@Length(min=0, max=10, message="上下线,1-已上线，2-已下线，3-未上线长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}
	
	@ExcelField(title="当前状态", type=1, align=2, sort=120)
	public String getStrType() {
		if(type.equalsIgnoreCase("1")){
			return "上线";
		}
		return "下线";
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Date getBeginOnOffTime() {
		return beginOnOffTime;
	}

	public void setBeginOnOffTime(Date beginOnOffTime) {
		this.beginOnOffTime = beginOnOffTime;
	}
	
	public Date getEndOnOffTime() {
		return endOnOffTime;
	}

	public void setEndOnOffTime(Date endOnOffTime) {
		this.endOnOffTime = endOnOffTime;
	}
		
	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getProcode() {
		return procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}

	@ExcelField(title="省名称", type=1, align=2, sort=40)
	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	@ExcelField(title="市名称", type=1, align=2, sort=50)
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}

	@ExcelField(title="区名称", type=1, align=2, sort=60)
	public String getDisname() {
		return disname;
	}

	public void setDisname(String disname) {
		this.disname = disname;
	}

	@ExcelField(title="酒店名称", type=1, align=2, sort=30)
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
		
}