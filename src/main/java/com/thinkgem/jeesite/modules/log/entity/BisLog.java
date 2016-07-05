/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.log.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 日志Entity
 * @author 王智威
 * @version 2016-04-13
 */
public class BisLog extends DataEntity<BisLog> {
	
	private static final long serialVersionUID = 1L;
	private String Id;		// 主键
	private String system;		// 系统
	private String bussinessid;		// 业务ID
	private String bussinesstype;		// 业务类型
	private String content;		// 内容
	private String operator;		// 操作人
	private Date createtime;		// 创建时间
	
	public BisLog() {
		super();
	}

	public BisLog(String id){
		super(id);
	}

	@Length(min=0, max=50, message="主键长度必须介于 0 和 50 之间")
	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}
	
	@Length(min=0, max=20, message="系统长度必须介于 0 和 20 之间")
	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}
	
	@Length(min=0, max=20, message="业务ID长度必须介于 0 和 20 之间")
	public String getBussinessid() {
		return bussinessid;
	}

	public void setBussinessid(String bussinessid) {
		this.bussinessid = bussinessid;
	}
	
	@Length(min=0, max=255, message="业务类型长度必须介于 0 和 255 之间")
	public String getBussinesstype() {
		return bussinesstype;
	}

	public void setBussinesstype(String bussinesstype) {
		this.bussinesstype = bussinesstype;
	}
	
	@Length(min=0, max=100, message="内容长度必须介于 0 和 100 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="操作人长度必须介于 0 和 255 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}