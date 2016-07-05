package com.thinkgem.gds.order.entity;

import java.io.Serializable;
import java.util.Date;

public class QunarOrderLogParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3850997393320013832L;

	private String _id;

	private Long hotelid;

	private String hotelname;

	private Integer qunarStatus;

	private String orderno;

	private Integer ordertype;

	private Date createtime;
	// 查询参数
	private Date createtime1;
	// 查询参数
	private Date createtime2;

	public Date getCreatetime1() {
		return createtime1;
	}

	public void setCreatetime1(Date createtime1) {
		this.createtime1 = createtime1;
	}

	public Date getCreatetime2() {
		return createtime2;
	}

	public void setCreatetime2(Date createtime2) {
		this.createtime2 = createtime2;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public Integer getQunarStatus() {
		return qunarStatus;
	}

	public void setQunarStatus(Integer qunarStatus) {
		this.qunarStatus = qunarStatus;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
