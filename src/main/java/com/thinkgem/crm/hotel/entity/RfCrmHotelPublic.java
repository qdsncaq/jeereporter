/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * CRM酒店公私海信息Entity
 * @author 段文昌
 * @version 2016-03-17
 */
public class RfCrmHotelPublic extends DataEntity<RfCrmHotelPublic> {
	
	private static final long serialVersionUID = 1L;
	private Long hotelId;		// 酒店ID
	private String hotelUserId;		// 私海负责人ID
	private String isPrivate;		// 是否私海[1，是；2，不是]
	private Date startTime;		// 周期开始时间
	private Date endTime;		// 周期结束时间
	private String available;		// 可用[1，可用；2，不可用]
	private Date createTime;		// 添加时间
	private Date updateTime;		// 修改时间
	private Date returnTime;		// 私海退还公海时间
	private String isConfirm;		// 是否确认[1，已确认；2，未确认]
	private String confirmUserId;		// 确认人ID
	
	public RfCrmHotelPublic() {
		super();
	}

	public RfCrmHotelPublic(String id){
		super(id);
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	
	@Length(min=0, max=100, message="私海负责人ID长度必须介于 0 和 100 之间")
	public String getHotelUserId() {
		return hotelUserId;
	}

	public void setHotelUserId(String hotelUserId) {
		this.hotelUserId = hotelUserId;
	}
	
	@Length(min=0, max=1, message="是否私海[1，是；2，不是]长度必须介于 0 和 1 之间")
	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=1, message="可用[1，可用；2，不可用]长度必须介于 0 和 1 之间")
	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	
	@Length(min=0, max=2, message="是否确认[1，已确认；2，未确认]长度必须介于 0 和 2 之间")
	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}
	
	@Length(min=0, max=100, message="确认人ID长度必须介于 0 和 100 之间")
	public String getConfirmUserId() {
		return confirmUserId;
	}

	public void setConfirmUserId(String confirmUserId) {
		this.confirmUserId = confirmUserId;
	}
	
}