/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomtype.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 房型Entity
 * @author jiajianhong
 * @version 2016-05-24
 */
public class Roomtype extends DataEntity<Roomtype> {
	
	private static final long serialVersionUID = 1L;
	private Long id1;		// id_1
	private Long hotelid;		// 酒店id
	private String name;		// 房型名字
	private String roomtypepms;		// 房型pms
	private String roomnum;		// 房型下房间数
	private String cost;		// 门市价
	private String type;		// 房型类型：1、普通房型，2、特价房型
	private Date updatetime;		// updatetime
	
	public Roomtype() {
		super();
	}

	public Roomtype(String id){
		super(id);
	}

	public Long getId1() {
		return id1;
	}

	public void setId1(Long id1) {
		this.id1 = id1;
	}
	
	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=50, message="房型名字长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=50, message="房型pms长度必须介于 1 和 50 之间")
	public String getRoomtypepms() {
		return roomtypepms;
	}

	public void setRoomtypepms(String roomtypepms) {
		this.roomtypepms = roomtypepms;
	}
	
	@Length(min=1, max=11, message="房型下房间数长度必须介于 1 和 11 之间")
	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	@Length(min=1, max=10, message="房型类型：1、普通房型，2、特价房型长度必须介于 1 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="updatetime不能为空")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
}