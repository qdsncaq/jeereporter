/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.datacenter.entity;

import com.thinkgem.gds.roomprice.entity.Roomprice;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单详情Entity
 * @author LYN
 * @version 2016-03-29
 */
public class OtaOrderdetail extends DataEntity<OtaOrderdetail> {
	
	private static final long serialVersionUID = 1L;
	private Long orderid;		// 订单id
	private Long roomtypeid;		// 房型id
	private String booknum;		// 预定房间数
	private Date createtime;		// 创建时间
	private Date updatetime;		// 修改时间
	private String roomtypename;
    private BigDecimal totalOTAPrice = new BigDecimal(0);
    private BigDecimal totalClearingPrice = new BigDecimal(0);;
    private List<Roomprice> roompriceList;
	
	
	public OtaOrderdetail() {
		super();
	}

	public OtaOrderdetail(String id){
		super(id);
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public Long getRoomtypeid() {
		return roomtypeid;
	}

	public void setRoomtypeid(Long roomtypeid) {
		this.roomtypeid = roomtypeid;
	}
	
	@Length(min=0, max=11, message="预定房间数长度必须介于 0 和 11 之间")
	public String getBooknum() {
		return booknum;
	}

	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	public String getRoomtypename() {
		return roomtypename;
	}

	public void setRoomtypename(String roomtypename) {
		this.roomtypename = roomtypename;
	}

    public List<Roomprice> getRoompriceList() {
        return roompriceList;
    }

    public void setRoompriceList(List<Roomprice> roompriceList) {
        this.roompriceList = roompriceList;
    }

    public BigDecimal getTotalClearingPrice() {
        return totalClearingPrice;
    }

    public void setTotalClearingPrice(BigDecimal totalClearingPrice) {
        this.totalClearingPrice = totalClearingPrice;
    }

    public BigDecimal getTotalOTAPrice() {
        return totalOTAPrice;
    }

    public void setTotalOTAPrice(BigDecimal totalOTAPrice) {
        this.totalOTAPrice = totalOTAPrice;
    }
}