/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.visit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * CRM 采购采销拜访记录Entity 设置导出excel,重写导出规则
 * @author 段文昌
 * @version 2016-03-14
 */
public class RfCrmMarketingVisitRecord extends RfCrmVisitRecord implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long visitHotelId;		// 拜访酒店ID
	private String visitHotelName;		// 拜访酒店名称
	private String visitType;		// 拜访类型[1-上门拜访；2-电话拜访]
	private String visitTypeName;		// 拜访类型[1-上门拜访；2-电话拜访]
	private String visitWay;		// 拜访方式
	private String visitWayName;		// 拜访方式
	private String visitHotelIntention;		// 拜访酒店意向[1-合作意向]
	private String hotelContacts;		// 拜访酒店联系人？？？？
	private String hotelContactsName;	//联系人名称
	private String visitUser;		// 我司的拜访人
	private String visitUserName;		// 我司的拜访人
	private String visitDesc;		// 拜访记录
	private String visitStatus;		// 拜访状态[1-暂存；2-提交]
	private Date visitTime;		// 拜访时间
	private Date createTime;		// 添加时间
	private Date updateTime;		// 修改时间
	private String sampleGrant;		// 样品发放
	private String sampleGrantName;		// 样品发放
	private String purchaseIntention;		// 采购意向
	private String purchaseIntentionName;		// 采购意向
	private String isSign;			//是否签约
	private String isSignName;			//是否签约

	private String provCode;	// 省编码
	private String provName;	// 省
	private String cityCode;	// 市编码
	private String cityName;	// 市
	private String disCode;		// 县/区编码
	private String disName;		// 县/区

	private String yaju;	//牙具
	private String muyulu;	//沐浴露
	private String xiangzao; //香皂
	private String xifaye;	//洗发液
	private String shuzi;	//梳子
	private String shui;	//水
	private String zhi;		//纸
	private String lajidai;	//垃圾袋

	private String queryVisitType;	// 用于查询操作

	private String pics;	//组图
	private String[] picArray;	//解析后的图片

	private BigDecimal longitude;	//经度
	private BigDecimal latitude;	//纬度
	private String lbsAddress;	//lbs解析后地址

	public RfCrmMarketingVisitRecord() {
		super();
	}

	public RfCrmMarketingVisitRecord(String id){
		super(id);
	}
	@ExcelField(title = "酒店ID", align = 2, sort = 17)
	public Long getVisitHotelId() {
		return visitHotelId;
	}

	public void setVisitHotelId(Long visitHotelId) {
		this.visitHotelId = visitHotelId;
	}

	@ExcelField(title = "酒店名称", align = 2, sort = 20)
	public String getVisitHotelName() {
		return visitHotelName;
	}

	public void setVisitHotelName(String visitHotelName) {
		this.visitHotelName = visitHotelName;
	}

	@Length(min=0, max=10, message="拜访类型[1-上门拜访；2-电话拜访]长度必须介于 0 和 10 之间")
	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
//	@ExcelField(title = "拜访类型", align = 2, sort = 40)
	public String getVisitTypeName() {
		return visitTypeName;
	}

	public void setVisitTypeName(String visitTypeName) {
		this.visitTypeName = visitTypeName;
	}

	@Length(min=0, max=10, message="拜访方式长度必须介于 0 和 10 之间")
	public String getVisitWay() {
		return visitWay;
	}

	public void setVisitWay(String visitWay) {
		this.visitWay = visitWay;
	}
	@ExcelField(title = "拜访方式", align = 2, sort = 25)
	public String getVisitWayName() {
		return visitWayName;
	}

	public void setVisitWayName(String visitWayName) {
		this.visitWayName = visitWayName;
	}

	@Length(min=0, max=10, message="拜访酒店意向[1-合作意向]长度必须介于 0 和 10 之间")
	public String getVisitHotelIntention() {
		return visitHotelIntention;
	}

	public void setVisitHotelIntention(String visitHotelIntention) {
		this.visitHotelIntention = visitHotelIntention;
	}

	@ExcelField(title = "联系人", align = 2, sort = 30)
	@Length(min=0, max=255, message="拜访酒店联系人,长度必须介于 0 和 255 之间")
	public String getHotelContacts() {
		return hotelContacts;
	}

	public void setHotelContacts(String hotelContacts) {
		this.hotelContacts = hotelContacts;
	}

	public String getHotelContactsName() {
		return hotelContactsName;
	}

	public void setHotelContactsName(String hotelContactsName) {
		this.hotelContactsName = hotelContactsName;
	}

	@Length(min=0, max=255, message="我司的拜访人长度必须介于 0 和 255 之间")
	public String getVisitUser() {
		return visitUser;
	}

	public void setVisitUser(String visitUser) {
		this.visitUser = visitUser;
	}
	@ExcelField(title = "销售", align = 2, sort = 50)
	public String getVisitUserName() {
		return visitUserName;
	}

	public void setVisitUserName(String visitUserName) {
		this.visitUserName = visitUserName;
	}

	@ExcelField(title = "拜访反馈", align = 2, sort = 80)
	@Length(min=0, max=255, message="拜访记录长度必须介于 0 和 255 之间")
	public String getVisitDesc() {
		return visitDesc;
	}

	public void setVisitDesc(String visitDesc) {
		this.visitDesc = visitDesc;
	}

	@Length(min=0, max=255, message="拜访状态[1-暂存；2-提交]长度必须介于 0 和 255 之间")
	public String getVisitStatus() {
		return visitStatus;
	}

	public void setVisitStatus(String visitStatus) {
		this.visitStatus = visitStatus;
	}

	@ExcelField(title = "拜访日期", align = 2, sort = 10)
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "创建时间", align = 2, dataFormat = "yyyy-MM-dd HH:mm:ss", sort = 140)
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

	@Length(min=0, max=10, message="样品发放长度必须介于 0 和 10 之间")
	public String getSampleGrant() {
		return sampleGrant;
	}

	public void setSampleGrant(String sampleGrant) {
		this.sampleGrant = sampleGrant;
	}

	@ExcelField(title = "样品发放", align = 2, sort = 70)
	public String getSampleGrantName() {
		return sampleGrantName;
	}

	public void setSampleGrantName(String sampleGrantName) {
		this.sampleGrantName = sampleGrantName;
	}

	@Length(min=0, max=10, message="采购意向长度必须介于 0 和 10 之间")
	public String getPurchaseIntention() {
		return purchaseIntention;
	}

	public void setPurchaseIntention(String purchaseIntention) {
		this.purchaseIntention = purchaseIntention;
	}


	@ExcelField(title = "采购意向", align = 2, sort = 60)
	public String getPurchaseIntentionName() {
		if("T".equals(this.purchaseIntention)){
			this.purchaseIntentionName = "有意向";
		} else if("F".equals(this.purchaseIntention)){
			this.purchaseIntentionName = "无意向";
		}
		return purchaseIntentionName;
	}

	public void setPurchaseIntentionName(String purchaseIntentionName) {
		this.purchaseIntentionName = purchaseIntentionName;
	}

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	@ExcelField(title = "省", align = 2, sort = 13)
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

	@ExcelField(title = "市", align = 2, sort = 15)
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

	@ExcelField(title = "区/县", align = 2, sort = 16)
	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	@ExcelField(title = "牙具", align = 2, sort = 90)
	public String getYaju() {
		return yaju;
	}

	public void setYaju(String yaju) {
		this.yaju = yaju;
	}
	@ExcelField(title = "沐浴露", align = 2, sort = 100)
	public String getMuyulu() {
		return muyulu;
	}

	public void setMuyulu(String muyulu) {
		this.muyulu = muyulu;
	}
	@ExcelField(title = "香皂", align = 2, sort = 110)
	public String getXiangzao() {
		return xiangzao;
	}

	public void setXiangzao(String xiangzao) {
		this.xiangzao = xiangzao;
	}
	@ExcelField(title = "洗发液", align = 2, sort = 120)
	public String getXifaye() {
		return xifaye;
	}

	public void setXifaye(String xifaye) {
		this.xifaye = xifaye;
	}
	@ExcelField(title = "梳子", align = 2, sort = 130)
	public String getShuzi() {
		return shuzi;
	}

	public void setShuzi(String shuzi) {
		this.shuzi = shuzi;
	}

	public String getIsSign() {
		return isSign;
	}

	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}

	@ExcelField(title = "是否签约", align = 2, sort = 75)
	public String getIsSignName() {
		if("T".equals(this.isSign)){
			this.isSignName = "是";
		} else if("F".equals(this.isSign)){
			this.isSignName = "否";
		}
		return isSignName;
	}

	public void setIsSignName(String isSignName) {
		this.isSignName = isSignName;
	}

	public String getQueryVisitType() {
		return queryVisitType;
	}

	public void setQueryVisitType(String queryVisitType) {
		this.queryVisitType = queryVisitType;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String[] getPicArray() {
		if(null != this.pics && !"".equals(this.pics)){
			picArray = this.pics.split(",");
		}
		return picArray;
	}

	public void setPicArray(String[] picArray) {
		this.picArray = picArray;
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
	@ExcelField(title = "拜访地址", align = 2, sort = 150)
	public String getLbsAddress() {
		return lbsAddress;
	}

	public void setLbsAddress(String lbsAddress) {
		this.lbsAddress = lbsAddress;
	}

	@ExcelField(title = "水", align = 2, sort = 132)
	public String getShui() {
		return shui;
	}

	public void setShui(String shui) {
		this.shui = shui;
	}
	@ExcelField(title = "纸", align = 2, sort = 134)
	public String getZhi() {
		return zhi;
	}

	public void setZhi(String zhi) {
		this.zhi = zhi;
	}
	@ExcelField(title = "垃圾袋", align = 2, sort = 136)
	public String getLajidai() {
		return lajidai;
	}

	public void setLajidai(String lajidai) {
		this.lajidai = lajidai;
	}
}