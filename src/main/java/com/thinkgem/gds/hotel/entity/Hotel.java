/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotel.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.crm.hotel.enums.HotelStatusEnum;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * modelEntity
 * @author tankai
 * @version 2016-03-11
 */
public class Hotel extends DataEntity<Hotel> {
	
	private static final long serialVersionUID = 1L;
	@ExcelField(title="酒店id", type=1, align=2, sort=10)
	private Long id;//
	private String hotelname;		// 酒店名称
	private String hotelcontactname;		// 联系人
	private Date regtime;		// 注册时间
	private String detailaddr;		// 详细地址
	private Date opentime;		// 开业时间
	private Date repairtime;		// 维修时间
	private String roomnum;		// 房间数
	private String introduction;		// 酒店介绍
	private String businesslicensefront;		// 许可证(前)
	private String businesslicenseback;		// 许可证(背)
	private String hotelpms;		// 酒店pms
	private String isvisible;		// 是否上线
	private String isonline;		// 是否在线
	private String idcardfront;		// 身份证(前)
	private String idcardback;		// 身份证(背)
	private String retentiontime;		// 保留时间
	private String defaultleavetime;		// 默认离店时间
	private String hotelphone;		// 联系电话
	private String hoteltype;		// 酒店类型
	private String discode;		// 区县编码
	private String qtphone;		// 前台电话
	private String citycode;		// 市行政编码
	private String provcode;		// 省行政编码
	private String pmstype;		// pas类型，眯客:mike、金天鹅、昆仑、等等
	private Date isonlinetime;		// 上下线时间戳
	private String hotelfax;		// 酒店传真
	private String state;		// 酒店状态4店长正在编辑中，（审核通过）5审核中
	
	private List<Long> ids;
	private String hotelUserId;
	private String switchOpen;
	private Integer roomcountbegin;
	private Integer roomcountend;
	private Date switchcreatetime; //分销开通时间
	private Date switchcreatetimeEnd; //分销查询开通结束时间
	private String switchopendesc;

	private BigDecimal longitude;
	private BigDecimal latitude;


//	crm查询字段
	private Integer pmsOnLine;		// 1-未上线2-已上线
	private Integer onLine;		// 0-未合作1-未上线2-已上线
	private Integer hotelStatus;		// 1-未审核2-已审核
	private String street;		// 街道
	private Integer roomTypeNum;		// 房型数
	private String available;		// 可用[1，可用；2，不可用]
	private String isHealthy;		// 是否健康[1，是；2，不是]
	private Integer washCooperate;		// 是否洗涤合作[1是0否]
	private Integer distributCooperate;		// 是否分销合作[1是0否]
	private Integer supplyCooperate;		// 是否供应链合作[1是0否]
	private String isPrivate; //是否公私海
	private String isPrivateName; //是否公私海
	private String salesName;	//销售名称
	private String salesMobile;	//销售手机号
	private String bossName;	//老板名称
	private String bossMobile;	//老板手机
	private String statusName;		//酒店审核状态
	private String provName;	// 省
	private String cityName;	// 市
	private String disName;		// 县/区
	private Date createTime;		// 添加时间
	private Date updateTime;		// 修改时间
	private String pmsUser;		//pms安装人
	private String pmsUserName;		//pms安装人名称
	private String psbUserName;		//pms安装人(对应金盾)
	private Date startTime;		//销售领用时间

	private String isSelfBuildHotel;	//是否自建酒店 T-是  F-不是
	private String isSelfBuildHotelName; //是否自建酒店
	private String startOnLineTime;//起始日期
	private String endOnlineTime;//结束日志
	private Date onLineTime;//酒店上线时间

	
	/////
	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
	public String getHotelUserId() {
		return hotelUserId;
	}

	public void setHotelUserId(String hotelUserId) {
		this.hotelUserId = hotelUserId;
	}
	////
	
	

	@ExcelField(title="是否开通分销", type=1, align=2, sort=120)
	public String getSwitchopendesc() {
		return switchopendesc;
	}


	public void setSwitchopendesc(String switchopendesc) {
		this.switchopendesc = switchopendesc;
	}

	@ExcelField(title="分销开通时间", type=1, align=2, sort=130)
	public Date getSwitchcreatetime() {
		return switchcreatetime;
	}

	public void setSwitchcreatetime(Date switchcreatetime) {
		this.switchcreatetime = switchcreatetime;
	}

	public Date getSwitchcreatetimeEnd() {
		return switchcreatetimeEnd;
	}

	public void setSwitchcreatetimeEnd(Date switchcreatetimeEnd) {
		this.switchcreatetimeEnd = switchcreatetimeEnd;
	}

	public Integer getRoomcountbegin() {
		return roomcountbegin;
	}

	public void setRoomcountbegin(Integer roomcountbegin) {
		this.roomcountbegin = roomcountbegin;
	}

	public Integer getRoomcountend() {
		return roomcountend;
	}

	public void setRoomcountend(Integer roomcountend) {
		this.roomcountend = roomcountend;
	}

	public Hotel() {
		super();
	}

	public Hotel(String id){
		super(id);
	}

	@Length(min=0, max=50, message="酒店名称长度必须介于 0 和 50 之间")
	@ExcelField(title="酒店名称", type=1, align=2, sort=20)
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	@Length(min=0, max=25, message="联系人长度必须介于 0 和 25 之间")
	@ExcelField(title="联系人", type=1, align=2, sort=90)
	public String getHotelcontactname() {
		return hotelcontactname;
	}

	public void setHotelcontactname(String hotelcontactname) {
		this.hotelcontactname = hotelcontactname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="注册时间", type=1, align=2, sort=190)
	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	
	@Length(min=0, max=100, message="详细地址长度必须介于 0 和 100 之间")
	@ExcelField(title="详细地址", type=1, align=2, sort=170)
	public String getDetailaddr() {
		return detailaddr;
	}

	public void setDetailaddr(String detailaddr) {
		this.detailaddr = detailaddr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开业时间", type=1, align=2, sort=180)
	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="维修时间", type=1, align=2, sort=200)
	public Date getRepairtime() {
		return repairtime;
	}

	public void setRepairtime(Date repairtime) {
		this.repairtime = repairtime;
	}
	
	@Length(min=0, max=11, message="房间数长度必须介于 0 和 11 之间")
	@ExcelField(title="房间数", type=1, align=2, sort=40)
	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@Length(min=0, max=100, message="许可证(前)长度必须介于 0 和 100 之间")
	public String getBusinesslicensefront() {
		return businesslicensefront;
	}

	public void setBusinesslicensefront(String businesslicensefront) {
		this.businesslicensefront = businesslicensefront;
	}
	
	@Length(min=0, max=100, message="许可证(背)长度必须介于 0 和 100 之间")
	public String getBusinesslicenseback() {
		return businesslicenseback;
	}

	public void setBusinesslicenseback(String businesslicenseback) {
		this.businesslicenseback = businesslicenseback;
	}
	
	@Length(min=0, max=50, message="酒店pms长度必须介于 0 和 50 之间")
	@ExcelField(title = "pms", align = 2, sort = 210)
	public String getHotelpms() {
		return hotelpms;
	}

	public void setHotelpms(String hotelpms) {
		this.hotelpms = hotelpms;
	}
	
	@Length(min=0, max=1, message="是否上线长度必须介于 0 和 1 之间")
	public String getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(String isvisible) {
		this.isvisible = isvisible;
	}
	
	@Length(min=0, max=1, message="是否在线长度必须介于 0 和 1 之间")
	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}
	
	@Length(min=0, max=255, message="身份证(前)长度必须介于 0 和 255 之间")
	public String getIdcardfront() {
		return idcardfront;
	}

	public void setIdcardfront(String idcardfront) {
		this.idcardfront = idcardfront;
	}
	
	@Length(min=0, max=255, message="身份证(背)长度必须介于 0 和 255 之间")
	public String getIdcardback() {
		return idcardback;
	}

	public void setIdcardback(String idcardback) {
		this.idcardback = idcardback;
	}
	
	@Length(min=0, max=6, message="保留时间长度必须介于 0 和 6 之间")
	public String getRetentiontime() {
		return retentiontime;
	}

	public void setRetentiontime(String retentiontime) {
		this.retentiontime = retentiontime;
	}
	
	@Length(min=0, max=6, message="默认离店时间长度必须介于 0 和 6 之间")
	public String getDefaultleavetime() {
		return defaultleavetime;
	}

	public void setDefaultleavetime(String defaultleavetime) {
		this.defaultleavetime = defaultleavetime;
	}
	
	@Length(min=0, max=400, message="联系电话长度必须介于 0 和 400 之间")
	@ExcelField(title="联系电话", type=1, align=2, sort=100)
	public String getHotelphone() {
		return hotelphone;
	}

	public void setHotelphone(String hotelphone) {
		this.hotelphone = hotelphone;
	}
	
	@Length(min=0, max=2, message="酒店类型长度必须介于 0 和 2 之间")
	public String getHoteltype() {
		return hoteltype;
	}

	public void setHoteltype(String hoteltype) {
		this.hoteltype = hoteltype;
	}
	
	@Length(min=0, max=10, message="区县编码长度必须介于 0 和 10 之间")
	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}
	
	@Length(min=0, max=20, message="前台电话长度必须介于 0 和 20 之间")
	@ExcelField(title="前台电话", type=1, align=2, sort=110)
	public String getQtphone() {
		return qtphone;
	}

	public void setQtphone(String qtphone) {
		this.qtphone = qtphone;
	}
	
	@Length(min=0, max=10, message="市行政编码长度必须介于 0 和 10 之间")
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	
	@Length(min=0, max=10, message="省行政编码长度必须介于 0 和 10 之间")
	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}
	
	@Length(min=0, max=10, message="pas类型，眯客:mike、金天鹅、昆仑、等等长度必须介于 0 和 10 之间")
	public String getPmstype() {
		return pmstype;
	}

	public void setPmstype(String pmstype) {
		this.pmstype = pmstype;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIsonlinetime() {
		return isonlinetime;
	}

	public void setIsonlinetime(Date isonlinetime) {
		this.isonlinetime = isonlinetime;
	}
	
	@Length(min=0, max=100, message="酒店传真长度必须介于 0 和 100 之间")
	public String getHotelfax() {
		return hotelfax;
	}

	public void setHotelfax(String hotelfax) {
		this.hotelfax = hotelfax;
	}
	
	@Length(min=0, max=4, message="酒店状态4店长正在编辑中，（审核通过）5审核中长度必须介于 0 和 4 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getSwitchOpen() {
		return switchOpen;
	}

	public void setSwitchOpen(String switchOpen) {
		this.switchOpen = switchOpen;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public Integer getWashCooperate() {
		return washCooperate;
	}

	public void setWashCooperate(Integer washCooperate) {
		this.washCooperate = washCooperate;
	}

	public Integer getDistributCooperate() {
		return distributCooperate;
	}

	public void setDistributCooperate(Integer distributCooperate) {
		this.distributCooperate = distributCooperate;
	}

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

	@ExcelField(title = "公私海", align = 2, sort = 30)
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
	@ExcelField(title = "销售", align = 2, sort = 50)
	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	@ExcelField(title = "销售手机", align = 2, sort = 60)
	public String getSalesMobile() {
		return salesMobile;
	}

	public void setSalesMobile(String salesMobile) {
		this.salesMobile = salesMobile;
	}
	@ExcelField(title = "老板", align = 2, sort = 70)
	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	@ExcelField(title = "老板手机", align = 2, sort = 80)
	public String getBossMobile() {
		return bossMobile;
	}

	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}

	@ExcelField(title = "审核状态", align = 2, sort = 220)
	public String getStatusName() {
		if(this.state == null){ //空值时处理,避免Integer.valueOf产生异常
			this.state = "-999"; //未知状态,任意小鱼-10正整数
		}
		this.statusName = HotelStatusEnum.getName(Integer.valueOf(this.state));
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@ExcelField(title = "省份", align = 2, sort = 140)
	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}
	@ExcelField(title = "城市", align = 2, sort = 150)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@ExcelField(title = "区域", align = 2, sort = 160)
	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@ExcelField(title = "pms安装人手机", align = 2, sort = 240)
	public String getPmsUser() {
		return pmsUser;
	}

	public void setPmsUser(String pmsUser) {
		this.pmsUser = pmsUser;
	}
	@ExcelField(title = "pms安装人名称", align = 2, sort = 230)
	public String getPmsUserName() {
		if(null == pmsUserName || "".equals(pmsUserName)){
			pmsUserName = this.psbUserName;
		}
		return pmsUserName;
	}

	public void setPmsUserName(String pmsUserName) {
		this.pmsUserName = pmsUserName;
	}

	public String getPsbUserName() {
		return psbUserName;
	}

	public void setPsbUserName(String psbUserName) {
		this.psbUserName = psbUserName;
	}
	@ExcelField(title = "认领时间", align = 2, dataFormat = "yyyy-MM-dd HH:mm:ss", sort = 65)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getIsSelfBuildHotel() {
		return isSelfBuildHotel;
	}

	public void setIsSelfBuildHotel(String isSelfBuildHotel) {
		this.isSelfBuildHotel = isSelfBuildHotel;
	}


	@ExcelField(title = "是否自建", align = 2, sort = 260)
	public String getIsSelfBuildHotelName() {
		if("T".equals(this.isSelfBuildHotel)){
			isSelfBuildHotelName = "是";
		} else if("F".equals(this.isSelfBuildHotel)){
			isSelfBuildHotelName = "否";
		}
		return isSelfBuildHotelName;
	}

	public void setIsSelfBuildHotelName(String isSelfBuildHotelName) {
		this.isSelfBuildHotelName = isSelfBuildHotelName;
	}

	@Override
	public String toString() {
		return "Hotel{" +
				"id=" + id +
				", hotelname='" + hotelname + '\'' +
				", hotelcontactname='" + hotelcontactname + '\'' +
				", regtime=" + regtime +
				", detailaddr='" + detailaddr + '\'' +
				", opentime=" + opentime +
				", repairtime=" + repairtime +
				", roomnum='" + roomnum + '\'' +
				", introduction='" + introduction + '\'' +
				", businesslicensefront='" + businesslicensefront + '\'' +
				", businesslicenseback='" + businesslicenseback + '\'' +
				", hotelpms='" + hotelpms + '\'' +
				", isvisible='" + isvisible + '\'' +
				", isonline='" + isonline + '\'' +
				", idcardfront='" + idcardfront + '\'' +
				", idcardback='" + idcardback + '\'' +
				", retentiontime='" + retentiontime + '\'' +
				", defaultleavetime='" + defaultleavetime + '\'' +
				", hotelphone='" + hotelphone + '\'' +
				", hoteltype='" + hoteltype + '\'' +
				", discode='" + discode + '\'' +
				", qtphone='" + qtphone + '\'' +
				", citycode='" + citycode + '\'' +
				", provcode='" + provcode + '\'' +
				", pmstype='" + pmstype + '\'' +
				", isonlinetime=" + isonlinetime +
				", hotelfax='" + hotelfax + '\'' +
				", state='" + state + '\'' +
				", switchOpen='" + switchOpen + '\'' +
				", roomcountbegin=" + roomcountbegin +
				", roomcountend=" + roomcountend +
				", switchcreatetime=" + switchcreatetime +
				", switchopendesc='" + switchopendesc + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", pmsOnLine=" + pmsOnLine +
				", onLine=" + onLine +
				", hotelStatus=" + hotelStatus +
				", street='" + street + '\'' +
				", roomTypeNum=" + roomTypeNum +
				", available='" + available + '\'' +
				", isHealthy='" + isHealthy + '\'' +
				", washCooperate=" + washCooperate +
				", distributCooperate=" + distributCooperate +
				", supplyCooperate=" + supplyCooperate +
				", isPrivate='" + isPrivate + '\'' +
				", isPrivateName='" + isPrivateName + '\'' +
				", salesName='" + salesName + '\'' +
				", salesMobile='" + salesMobile + '\'' +
				", bossName='" + bossName + '\'' +
				", bossMobile='" + bossMobile + '\'' +
				", statusName='" + statusName + '\'' +
				", provName='" + provName + '\'' +
				", cityName='" + cityName + '\'' +
				", disName='" + disName + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", onLineTime=" + onLineTime +
				", startOnLineTime=" + startOnLineTime +
				", endOnlineTime=" + endOnlineTime +
				'}';
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="上线时间", type=1, align=2, sort=191)
	public Date getOnLineTime() {
		return onLineTime;
	}
	public void setOnLineTime(Date onLineTime) {
		this.onLineTime = onLineTime;
	}
	public String getStartOnLineTime() {
		return startOnLineTime;
	}
	public void setStartOnLineTime(String startOnLineTime) {
		this.startOnLineTime = startOnLineTime;
	}
	public String getEndOnlineTime() {
		return endOnlineTime;
	}
	public void setEndOnlineTime(String endOnlineTime) {
		this.endOnlineTime = endOnlineTime;
	}
	
}