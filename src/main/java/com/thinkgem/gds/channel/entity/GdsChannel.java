/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.channel.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 渠道管理Entity
 * @author tankai
 * @version 2016-03-18
 */
public class GdsChannel extends DataEntity<GdsChannel> {
	
	private static final long serialVersionUID = 1L;
	private String channelid;		// 渠道id
	private String name;		// 渠道名称
	private String state;		// 是否启用1启用 2未启用，预留扩展审核状态
	private String isVisible;		// 是否可见，F不可见，T可见
	private Date createtime;		// 创建时间
	private String createuser;		// 创建人
	private Date updatetime;		// 更新时间
	private String updateuser;		// 更新人
	
	public GdsChannel() {
		super();
	}

	public GdsChannel(String id){
		super(id);
	}

	@Length(min=1, max=10, message="渠道id长度必须介于 1 和 10 之间")
	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	
	@Length(min=1, max=200, message="渠道名称长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=10, message="是否启用1启用 2未启用，预留扩展审核状态长度必须介于 1 和 10 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=1, message="是否可见，F不可见，T可见长度必须介于 0 和 1 之间")
	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=20, message="创建人长度必须介于 0 和 20 之间")
	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Length(min=0, max=20, message="更新人长度必须介于 0 和 20 之间")
	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	
}