/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 酒店历史信息统计次数等统计Entity
 * @author 段文昌
 * @version 2016-04-21
 */
public class HotelRate extends DataEntity<HotelRate> {

	private static final long serialVersionUID = 1L;
	private String TIME;		// 日期
	private String ROOMNUM;		// 可售房间数
    private String RATEROOMNUM;             // 出租房间数
	private String CHECKROOMNUM;		// 入住房间数
	private String rate;		// 出租率
	private String onlinecount;		// 在线时长
	private String handleChecknum;		// 班里入住次数
	private String HOTEL_ID;		//pms编码
	private String ID;	//ID
	private Date startDate;	//搜索开始日期
	private Date endDate;	//搜索结束日期

	public String getTIME() {
		return TIME;
	}

	public void setTIME(String TIME) {
		this.TIME = TIME;
	}

	public String getROOMNUM() {
		return ROOMNUM;
	}

	public void setROOMNUM(String ROOMNUM) {
		this.ROOMNUM = ROOMNUM;
	}

	public String getRATEROOMNUM() {
		return RATEROOMNUM;
	}

	public void setRATEROOMNUM(String RATEROOMNUM) {
		this.RATEROOMNUM = RATEROOMNUM;
	}

	public String getCHECKROOMNUM() {
		return CHECKROOMNUM;
	}

	public void setCHECKROOMNUM(String CHECKROOMNUM) {
		this.CHECKROOMNUM = CHECKROOMNUM;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getOnlinecount() {
		return onlinecount;
	}

	public void setOnlinecount(String onlinecount) {
		this.onlinecount = onlinecount;
	}

	public String getHandleChecknum() {
		return handleChecknum;
	}

	public void setHandleChecknum(String handleChecknum) {
		this.handleChecknum = handleChecknum;
	}

	public String getHOTEL_ID() {
		return HOTEL_ID;
	}

	public void setHOTEL_ID(String HOTEL_ID) {
		this.HOTEL_ID = HOTEL_ID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "HotelRate{" +
				"TIME='" + TIME + '\'' +
				", ROOMNUM='" + ROOMNUM + '\'' +
				", RATEROOMNUM='" + RATEROOMNUM + '\'' +
				", CHECKROOMNUM='" + CHECKROOMNUM + '\'' +
				", rate='" + rate + '\'' +
				", onlinecount='" + onlinecount + '\'' +
				", handleChecknum='" + handleChecknum + '\'' +
				", HOTEL_ID='" + HOTEL_ID + '\'' +
				", ID='" + ID + '\'' +
				'}';
	}
}