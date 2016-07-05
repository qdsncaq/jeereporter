/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotelmapping.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.supcan.annotation.treelist.cols.SupCol;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 酒店映射Entity
 * @author zhangyajun
 * @version 2016-04-15
 */
public class HotelMapping extends DataEntity<HotelMapping> {
	
	private static final long serialVersionUID = 1L;
	
	private Long hotelid;		// 酒店id
	private String otaHotelid;		// ota的hotelid
	private String hotelname;		// 酒店名字
	private String hoteladdr;		// 酒店地址
	private String channelid;		// 渠道id(去哪儿等)
	private String hotelphone;		// 酒店电话
	private String state;		// 酒店状态
	private String createby;		// 创建人
	private Date createtime;		// 创建时间
	private String updateby;		// 修改人
	private Date updatetime;		// 修改时间
	
	public HotelMapping() {
		super();
	}
	
	
	public HotelMapping(String id){
		super(id);
	}
	@JsonIgnore
	@NotNull(message="酒店id不能为空")
	@ExcelField(title="酒店id", align=2, sort=25)
	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=100, message="ota的hotelid长度必须介于 0 和 100 之间")
	@JsonIgnore
	@ExcelField(title="ota酒店id", align=2, sort=30)
	public String getOtaHotelid() {
		return otaHotelid;
	}

	public void setOtaHotelid(String otaHotelid) {
		this.otaHotelid = otaHotelid;
	}
	
	@Length(min=0, max=50, message="酒店名字长度必须介于 0 和 50 之间")
	@JsonIgnore
	@NotNull(message="ota酒店名字不能为空")
	@ExcelField(title="ota酒店名字", align=2, sort=35)
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	@Length(min=0, max=100, message="酒店地址长度必须介于 0 和 100 之间")
	@JsonIgnore
	//@NotNull(message="ota酒店地址不能为空")
	@ExcelField(title="ota酒店地址", align=2, sort=40)
	public String getHoteladdr() {
		return hoteladdr;
	}

	public void setHoteladdr(String hoteladdr) {
		this.hoteladdr = hoteladdr;
	}
	
	@Length(min=0, max=2, message="渠道id(去哪儿等)长度必须介于 0 和 2 之间")
	@JsonIgnore
	@NotNull(message="渠道id不能为空")
	@ExcelField(title="渠道id", align=2, sort=45)
	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	
	@Length(min=0, max=20, message="酒店电话长度必须介于 0 和 20 之间")
	@JsonIgnore
	@ExcelField(title="酒店电话", align=2, sort=50)
	public String getHotelphone() {
		return hotelphone;
	}

	public void setHotelphone(String hotelphone) {
		this.hotelphone = hotelphone;
	}
	
	@Length(min=0, max=2, message="酒店状态长度必须介于 0 和 2 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

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