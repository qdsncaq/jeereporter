/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomtypemapping.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 房型映射Entity
 * @author 姜贺
 * @version 2016-04-26
 */
public class RoomtypeMapping extends DataEntity<RoomtypeMapping> {
	
	private static final long serialVersionUID = 1L;
	private Long hotelid;		// 酒店id
	private String otaHotelid;		// ota酒店id
	private Long roomtypeid;		// 房型id
	private String otaRoomtypeid;		// ota房型id
	private String roomtypename;		// 房型名
	private String otaRoomtypename;		// ota房型名
	private String state;		// 状态
	private String bedtype;		// 床型
	private String channelid;		// 渠道
	private String createby;		// 创建人
	private Date createtime;		// 创建时间
	private String updateby;		// 修改人
	private Date updatetime;		// 修改时间
	
	public RoomtypeMapping() {
		super();
	}

	public RoomtypeMapping(String id){
		super(id);
	}

	@NotNull(message="酒店id不能为空")
	@ExcelField(title="酒店id", align=2, sort=25)
	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=50, message="ota酒店id长度必须介于 0 和 50 之间")
	//@ExcelField(title="ota酒店id", align=2, sort=30)
	public String getOtaHotelid() {
		return otaHotelid;
	}

	public void setOtaHotelid(String otaHotelid) {
		this.otaHotelid = otaHotelid;
	}
	
	public Long getRoomtypeid() {
		return roomtypeid;
	}

	public void setRoomtypeid(Long roomtypeid) {
		this.roomtypeid = roomtypeid;
	}
	
	@Length(min=0, max=50, message="ota房型id长度必须介于 0 和 50 之间")
	//@ExcelField(title="ota房型id", align=2, sort=35)
	public String getOtaRoomtypeid() {
		return otaRoomtypeid;
	}

	public void setOtaRoomtypeid(String otaRoomtypeid) {
		this.otaRoomtypeid = otaRoomtypeid;
	}
	@ExcelField(title="房型名字", align=2, sort=30)
	public String getRoomtypename() {
		return roomtypename;
	}

	public void setRoomtypename(String roomtypename) {
		this.roomtypename = roomtypename;
	}
	
	
	@Length(min=0, max=50, message="ota房型名长度必须介于 0 和 50 之间")
	@NotNull(message="ota房型名字不能为空")
	@ExcelField(title="ota房型名字", align=2, sort=35)
	public String getOtaRoomtypename() {
		return otaRoomtypename;
	}

	public void setOtaRoomtypename(String otaRoomtypename) {
		this.otaRoomtypename = otaRoomtypename;
	}
	
	@Length(min=0, max=10, message="状态长度必须介于 0 和 10 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@NotNull(message="床型不能为空")
	@ExcelField(title="床型", align=2, sort=50)
	public String getBedtype() {
		return bedtype;
	}

	public void setBedtype(String bedtype) {
		this.bedtype = bedtype;
	}
	
	@Length(min=0, max=2, message="渠道长度必须介于 0 和 2 之间")
	@NotNull(message="渠道id不能为空")
	@ExcelField(title="渠道id", align=2, sort=55)
	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	
	@Length(min=0, max=50, message="创建人长度必须介于 0 和 50 之间")
	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=50, message="修改人长度必须介于 0 和 50 之间")
	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}