package com.thinkgem.crm.mongo.entity;

import com.mongodb.util.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 酒店上下线信息统计Entity,应用于mongodb
 * @author 段文昌
 * @version 2016-04-21
 */
public class HotelAuditLogMongo implements Serializable {

	/** 初次审核 */
	public static final Integer AUDIT_FIRST = 100;
	/** 提交审核 */
	public static final Integer AUDIT_SUBMIT = 101;
	/** 审核驳回 */
	public static final Integer AUDIT_REJECT = 200;
	/** 审核通过 */
	public static final Integer AUDIT_PASS = 999;

	private static final long serialVersionUID = 1L;
	private String id;		// ID
	private Long hotelId;		// 酒店ID
    private String pms;             // pms编码
	private String operateId;		// 操作人ID
	private String operateName;		// 操作人名称
	private Date operateTime;		// 操作时间
	private String remarks;		//文字说明
	private Integer type;		// 记录类型，100-提交,200-驳回,999-通过
	private String typeName;		// 记录类型，100-提交,200-驳回,999-通过
	private Object before;             // 之前  json对象
	private Object after;             // 之后  json对象
	private Object different;             // 不同  json对象
	private List<String> differents = new ArrayList<String>();	//不同,字段描述

	public HotelAuditLogMongo() {
		super();
	}

	public HotelAuditLogMongo(Long hotelId,String pms,String operateId,String operateName,String remarks,Integer type,String jsonBefore,String jsonAfter,String jsonDifferent) {
		this.hotelId = hotelId;
		this.pms = pms;
		this.operateId = operateId;
		this.operateName = operateName;
		this.remarks = remarks;
		this.type = type;
		this.before = JSON.parse(jsonBefore);
		this.after = JSON.parse(jsonAfter);
		this.different = JSON.parse(jsonDifferent);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		if(AUDIT_FIRST.equals(type)){
			typeName = "初次审核";
		} if(AUDIT_SUBMIT.equals(type)){
			typeName = "提交审核";
		} else if(AUDIT_REJECT.equals(type)){
			typeName = "驳回";
		} else if(AUDIT_PASS.equals(type)){
			typeName = "通过";
		}
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public Object getBefore() {
		return before;
	}

	public void setBefore(Object before) {
		this.before = before;
	}

	public Object getAfter() {
		return after;
	}

	public void setAfter(Object after) {
		this.after = after;
	}

	public Object getDifferent() {
		return different;
	}

	public void setDifferent(Object different) {
		this.different = different;
	}

	public List<String> getDifferents() {
		return differents;
	}

	public void setDifferents(List<String> differents) {
		this.differents = differents;
	}

	@Override
	public String toString() {
		return "HotelAuditLogMongo{" +
				"id='" + id + '\'' +
				", hotelId=" + hotelId +
				", pms='" + pms + '\'' +
				", operateId='" + operateId + '\'' +
				", operateName='" + operateName + '\'' +
				", operateTime=" + operateTime +
				", remarks='" + remarks + '\'' +
				", type=" + type +
				", typeName='" + typeName + '\'' +
				", before=" + before +
				", after=" + after +
				", different=" + different +
				'}';
	}
}