package com.thinkgem.crm.mongo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 酒店上下线信息统计Entity,应用于mongodb
 * @author 段文昌
 * @version 2016-04-21
 */
public class OnOffLineDetailMongo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;		// 自增ID
	private Long hotelId;		// 酒店ID
    private String pms;             // pms编码
	private String operateId;		// 操作人ID
	private String operateName;		// 操作人名称
	private Date onOffTime;//明细操作时间
	private Date createTime;		// 上下线时间
	private Date updateTime;		// 更新时间
	private String remarks;		//文字说明
	private String remarkType;		//文字说明
	private String type;		// 上下线类型，1-上线，2-下线
	private String typeName;		// 上下线类型，1-上线，2-下线
	private String delFlag;		// 0-删除,1-正常

	public OnOffLineDetailMongo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getPms() {
		return pms;
	}

	public void setPms(String pms) {
		this.pms = pms;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public Date getOnOffTime() {
		return onOffTime;
	}

	public void setOnOffTime(Date onOffTime) {
		this.onOffTime = onOffTime;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarkType() {
		return remarkType;
	}

	public void setRemarkType(String remarkType) {
		this.remarkType = remarkType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		if("1".equals(type)){
			typeName = "已上线";
		} else if("2".equals(type)){
			typeName = "已下线";
		} else if("3".equals(type)){
			typeName = "未上线";
		}
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "OnOffLineDetailMongo{" +
				"id=" + id +
				", hotelId=" + hotelId +
				", pms='" + pms + '\'' +
				", operateId='" + operateId + '\'' +
				", operateName='" + operateName + '\'' +
				", onOffTime=" + onOffTime +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", remarks='" + remarks + '\'' +
				", remarkType=" + remarkType +
				", type=" + type +
				", typeName='" + typeName + '\'' +
				", delFlag='" + delFlag + '\'' +
				'}';
	}
}