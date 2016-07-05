/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.entity;

import com.thinkgem.crm.hotel.enums.HotelStatusEnum;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * CRM酒店公私海信息Entity
 * @author 段文昌
 * @version 2016-03-17
 */
public class RfCrmHotel extends DataEntity<RfCrmHotel> {
	
	private static final long serialVersionUID = 1L;

	@ExcelField(title = "酒店编号", type=1, align = 2, sort = 10)
	private Long id;				//酒店编号
	private String hotelName;		// 酒店名称
	private String hotelTel;		// 酒店电话
	private String hotelPhone;		// 酒店老板电话
	private String hotelBossName;		// 酒店老板名称
	private Integer pmsOnLine;		// 1-未上线2-已上线
	private Integer onLine;		// 0-未合作1-未上线2-已上线
	private Integer hotelStatus;		// 1-未审核2-已审核
	private String pms;		// 和PMS对应的字段
	private String detailAddr;		// 酒店地址
	private String provCode;		// 酒店省行政编码
	private String cityCode;		// 酒店市行政编码
	private String disCode;		// 酒店区县行政编码
	private String street;		// 街道
	private String longitude;		// 酒店坐标
	private String latitude;		// 酒店坐标
	private Integer roomNum;		// 房间数
	private Integer roomTypeNum;		// 房型数
	private String available;		// 可用[1，可用；2，不可用]
	private String isHealthy;		// 是否健康[1，是；2，不是]
	private Date openTime;		// 开业时间
	private Date repairTime;		// 最后装修时间
	private Date createTime;		// 添加时间
	private Date updateTime;		// 修改时间
	private Integer washCooperate;		// 是否洗涤合作[1是0否]
	private Integer distributCooperate;		// 是否分销合作[1是0否]
	private Integer supplyCooperate;		// 是否供应链合作[1是0否]


	private String isPrivate; //是否公私海
	private String isPrivateName; //是否公私海
	private String salesName;	//销售名称
	private String salesMobile;	//销售手机号
	private String bossName;	//老板名称
	private String bossMobile;	//老板手机
	private Integer status;		//酒店审核状态
	private String statusName;		//酒店审核状态
	private String hotelContactName;	//酒店联系人名称
	private String provName;	// 省
	private String cityName;	// 市
	private String disName;		// 县/区


//	public String getId(){
//		return this.id;
//	}

	public RfCrmHotel() {
		super();
	}

	public RfCrmHotel(String id){
		super(id);
	}

	@Length(min=0, max=50, message="酒店名称长度必须介于 0 和 50 之间")
	@ExcelField(title = "酒店名称", align = 2, sort = 20)
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	@Length(min=0, max=20, message="酒店电话长度必须介于 0 和 20 之间")
	public String getHotelTel() {
		return hotelTel;
	}

	public void setHotelTel(String hotelTel) {
		this.hotelTel = hotelTel;
	}
	
	@Length(min=0, max=400, message="酒店老板电话长度必须介于 0 和 400 之间")
	public String getHotelPhone() {
		return hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}
	
	@Length(min=0, max=40, message="酒店老板名称长度必须介于 0 和 40 之间")
	public String getHotelBossName() {
		return hotelBossName;
	}

	public void setHotelBossName(String hotelBossName) {
		this.hotelBossName = hotelBossName;
	}
	
	public Integer getPmsOnLine() {
		return pmsOnLine;
	}

	public void setPmsOnLine(Integer pmsOnLine) {
		this.pmsOnLine = pmsOnLine;
	}
	
	public Integer getOnLine() {
		return onLine;
	}

	public void setOnLine(Integer onLine) {
		this.onLine = onLine;
	}
	
	public Integer getHotelStatus() {
		return hotelStatus;
	}

	public void setHotelStatus(Integer hotelStatus) {
		this.hotelStatus = hotelStatus;
	}
	
	@Length(min=0, max=50, message="和PMS对应的字段长度必须介于 0 和 50 之间")
	@ExcelField(title = "pms", align = 2, sort = 170)
	public String getPms() {
		return pms;
	}

	public void setPms(String pms) {
		this.pms = pms;
	}
	
	@Length(min=0, max=100, message="酒店地址长度必须介于 0 和 100 之间")
	@ExcelField(title = "酒店地址", align = 2, sort = 120)
	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	
	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}
	
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getDisCode() {
		return disCode;
	}

	public void setDisCode(String disCode) {
		this.disCode = disCode;
	}
	
	@Length(min=0, max=255, message="街道长度必须介于 0 和 255 之间")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@ExcelField(title = "房间数", align = 2, sort = 30)
	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	
	public Integer getRoomTypeNum() {
		return roomTypeNum;
	}

	public void setRoomTypeNum(Integer roomTypeNum) {
		this.roomTypeNum = roomTypeNum;
	}
	
	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}
	
	public String getIsHealthy() {
		return isHealthy;
	}

	public void setIsHealthy(String isHealthy) {
		this.isHealthy = isHealthy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "开业时间", align = 2, sort = 130)
	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "最近装修", align = 2, sort = 150)
	public Date getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "注册时间", align = 2, sort = 140)
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
	
	@NotNull(message="是否洗涤合作[1是0否]不能为空")
	public Integer getWashCooperate() {
		return washCooperate;
	}

	public void setWashCooperate(Integer washCooperate) {
		this.washCooperate = washCooperate;
	}
	
	@NotNull(message="是否分销合作[1是0否]不能为空")
	public Integer getDistributCooperate() {
		return distributCooperate;
	}

	public void setDistributCooperate(Integer distributCooperate) {
		this.distributCooperate = distributCooperate;
	}
	
	@NotNull(message="是否供应链合作[1是0否]不能为空")
	public Integer getSupplyCooperate() {
		return supplyCooperate;
	}

	public void setSupplyCooperate(Integer supplyCooperate) {
		this.supplyCooperate = supplyCooperate;
	}

	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	@ExcelField(title = "销售", align = 2, sort = 40)
	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	@ExcelField(title = "销售手机", align = 2, sort = 50)
	public String getSalesMobile() {
		return salesMobile;
	}

	public void setSalesMobile(String salesMobile) {
		this.salesMobile = salesMobile;
	}
	@ExcelField(title = "老板", align = 2, sort = 60)
	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	@ExcelField(title = "老板手机", align = 2, sort = 70)
	public String getBossMobile() {
		return bossMobile;
	}

	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@ExcelField(title = "联系人", align = 2, sort = 80)
	public String getHotelContactName() {
		return hotelContactName;
	}

	public void setHotelContactName(String hotelContactName) {
		this.hotelContactName = hotelContactName;
	}
	@ExcelField(title = "省份", align = 2, sort = 90)
	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}
	@ExcelField(title = "城市", align = 2, sort = 100)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@ExcelField(title = "区域", align = 2, sort = 110)
	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}
	@ExcelField(title = "公私海", align = 2, sort = 25)
	public String getIsPrivateName() {
		if("1".equals(this.isPrivate)){
			this.isPrivateName = "私海";
		} else if("2".equals(this.isPrivate)){
			this.isPrivateName = "公海";
		}
		return isPrivateName;
	}

	public void setIsPrivateName(String isPrivateName) {
		this.isPrivateName = isPrivateName;
	}
	@ExcelField(title = "审核状态", align = 2, sort = 180)
	public String getStatusName() {
		this.statusName = HotelStatusEnum.getName(this.status);
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}