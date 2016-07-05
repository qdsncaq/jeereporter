/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * CRM/OMS登录信息统计Entity
 * @author 段文昌
 * @version 2016-04-05
 */
public class RfCrmUserDevic extends DataEntity<RfCrmUserDevic> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户ID
	private String loginName;		// 登录名称
	private String channelId;		// channelId
	private String loginDevic;		// 登录设备
	private String devicInfo;		// 设备信息
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String sysType;		// system type，1-crm, 2-oms
	private String sysTypeName;		// system type，1-crm, 2-oms

	private Date firstTime;	//首次访问时间
	private Date lastTime;	//末次访问时间
	private String hotelName;	//酒店名称
	private String hotelId;	//酒店ID

	private String provCode;	// 省编码
	private String provName;	// 省
	private String cityCode;	// 市编码
	private String cityName;	// 市
	private String disCode;		// 县/区编码
	private String disName;		// 县/区

	private Integer distributCooperate; //是否分销合作[1是0否]不能为空
	private String distributCooperateName; //是否分销合作
	private Integer washCooperate;		// 是否洗涤合作[1是0否]
	private String washCooperateName;		// 是否洗涤合作[1是0否]
	private Integer supplyCooperate;		// 是否供应链合作[1是0否]
	private String supplyCooperateName;		// 是否供应链合作[1是0否]
	private String salerName;	//销售名称
	private String salerMobile;	//销售手机


	public RfCrmUserDevic() {
		super();
	}

	public RfCrmUserDevic(String id){
		super(id);
	}

	@Length(min=0, max=100, message="用户ID长度必须介于 0 和 100 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ExcelField(title = "老板账号", align = 2, sort = 10)
	@Length(min=0, max=64, message="登录名称长度必须介于 0 和 64 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=64, message="channelId长度必须介于 0 和 64 之间")
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	@Length(min=0, max=64, message="登录设备长度必须介于 0 和 64 之间")
	public String getLoginDevic() {
		return loginDevic;
	}

	public void setLoginDevic(String loginDevic) {
		this.loginDevic = loginDevic;
	}
	
	@Length(min=0, max=512, message="设备信息长度必须介于 0 和 512 之间")
	public String getDevicInfo() {
		return devicInfo;
	}

	public void setDevicInfo(String devicInfo) {
		this.devicInfo = devicInfo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=10, message="system type，1-crm, 2-oms长度必须介于 0 和 10 之间")
	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

	public String getSysTypeName() {
		if("1".equals(this.sysType)){
			sysTypeName = "CRM";
		} else if("2".equals(this.sysType)){
			sysTypeName = "OMS";
		}
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	@ExcelField(title = "最早登录时间", dataFormat = "yyyy-MM-dd HH:mm:ss", align = 2, sort = 70)
	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	@ExcelField(title = "最近登录时间", dataFormat = "yyyy-MM-dd HH:mm:ss", align = 2, sort = 80)
	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	@ExcelField(title = "酒店名称", align = 2, sort = 30)
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	@ExcelField(title = "酒店ID", align = 2, sort = 20)
	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	@ExcelField(title = "省", align = 2, sort = 40)
	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@ExcelField(title = "市", align = 2, sort = 50)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDisCode() {
		return disCode;
	}

	public void setDisCode(String disCode) {
		this.disCode = disCode;
	}

	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	public Integer getDistributCooperate() {
		return distributCooperate;
	}

	public void setDistributCooperate(Integer distributCooperate) {
		this.distributCooperate = distributCooperate;
	}

	@ExcelField(title = "是否开通分销", align = 2, sort = 60)
	public String getDistributCooperateName() {
		if(1 == this.distributCooperate){
			distributCooperateName = "开通";
		} else if(0 == this.distributCooperate){
			distributCooperateName = "未开通";
		} else{
			distributCooperateName = "未开通";
		}
		return distributCooperateName;
	}

	public void setDistributCooperateName(String distributCooperateName) {
		this.distributCooperateName = distributCooperateName;
	}

	public Integer getWashCooperate() {
		return washCooperate;
	}

	public void setWashCooperate(Integer washCooperate) {
		this.washCooperate = washCooperate;
	}

	public String getWashCooperateName() {
		if(1 == this.washCooperate){
			washCooperateName = "开通";
		} else if(0 == this.washCooperate){
			washCooperateName = "未开通";
		} else{
			washCooperateName = "未开通";
		}
		return washCooperateName;
	}

	public void setWashCooperateName(String washCooperateName) {
		this.washCooperateName = washCooperateName;
	}

	public Integer getSupplyCooperate() {
		return supplyCooperate;
	}

	public void setSupplyCooperate(Integer supplyCooperate) {
		this.supplyCooperate = supplyCooperate;
	}

	public String getSupplyCooperateName() {
		if(1 == this.supplyCooperate){
			supplyCooperateName = "开通";
		} else if(0 == this.supplyCooperate){
			supplyCooperateName = "未开通";
		} else{
			supplyCooperateName = "未开通";
		}
		return supplyCooperateName;
	}

	public void setSupplyCooperateName(String supplyCooperateName) {
		this.supplyCooperateName = supplyCooperateName;
	}

	@ExcelField(title = "销售名称", align = 2, sort = 90)
	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
	@ExcelField(title = "销售手机", align = 2, sort = 100)
	public String getSalerMobile() {
		return salerMobile;
	}

	public void setSalerMobile(String salerMobile) {
		this.salerMobile = salerMobile;
	}
}