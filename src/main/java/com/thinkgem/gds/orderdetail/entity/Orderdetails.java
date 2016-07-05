/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.orderdetail.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * orderdetailEntity
 * @author tankai
 * @version 2016-03-14
 */
public class Orderdetails extends DataEntity<Orderdetails> {
	
	private static final long serialVersionUID = 1L;
	private Long orderid;		// 订单id
	private Date begintime;		// 预抵时间
	private Date endtime;		// 预离时间
	private String totalprice;		// 总价格
	private String status;		// 订单明细状态
	private String pmsroomorderno;		// 客单号
	private Long roomtypeid;		// 房型id
	private String roompms;		// pms系统中房间编码
	private Long roomid;		// 房间id
	private Date createtime;		// 创建时间
	private Date updatetime;		// 修改时间
	private String note;		// 备注
	private Long hotelid;		// 酒店id
	private Date checkintime;		// checkintime
	private Date checkouttime;		// checkouttime
	private String roomtypename;		// 房型名称
	private String roomno;		// 房间号
	
	public Orderdetails() {
		super();
	}

	public Orderdetails(String id){
		super(id);
	}

	@NotNull(message="订单id不能为空")
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="预抵时间不能为空")
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="预离时间不能为空")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	
	@Length(min=1, max=4, message="订单明细状态长度必须介于 1 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=100, message="客单号长度必须介于 0 和 100 之间")
	public String getPmsroomorderno() {
		return pmsroomorderno;
	}

	public void setPmsroomorderno(String pmsroomorderno) {
		this.pmsroomorderno = pmsroomorderno;
	}
	
	@NotNull(message="房型id不能为空")
	public Long getRoomtypeid() {
		return roomtypeid;
	}

	public void setRoomtypeid(Long roomtypeid) {
		this.roomtypeid = roomtypeid;
	}
	
	@Length(min=0, max=100, message="pms系统中房间编码长度必须介于 0 和 100 之间")
	public String getRoompms() {
		return roompms;
	}

	public void setRoompms(String roompms) {
		this.roompms = roompms;
	}
	
	public Long getRoomid() {
		return roomid;
	}

	public void setRoomid(Long roomid) {
		this.roomid = roomid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckintime() {
		return checkintime;
	}

	public void setCheckintime(Date checkintime) {
		this.checkintime = checkintime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckouttime() {
		return checkouttime;
	}

	public void setCheckouttime(Date checkouttime) {
		this.checkouttime = checkouttime;
	}
	
	@Length(min=0, max=50, message="房型名称长度必须介于 0 和 50 之间")
	public String getRoomtypename() {
		return roomtypename;
	}

	public void setRoomtypename(String roomtypename) {
		this.roomtypename = roomtypename;
	}
	
	@Length(min=0, max=50, message="房间号长度必须介于 0 和 50 之间")
	public String getRoomno() {
		return roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	
}