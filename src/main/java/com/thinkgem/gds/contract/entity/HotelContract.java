/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.contract.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 合同Entity
 * @author zhaochuanbin
 * @version 2016-03-15
 */
public class HotelContract extends DataEntity<HotelContract> {
	
	private static final long serialVersionUID = 1L;
	private Long hotelid;		// 酒店id
	private String hotelpms;		// 酒店pms
	private String author;		// 合同编写人
	private String status;		// 1:正常,0:无效
	private String type;		// 1:分销，2:洗涤
	private String content;		// 合同内容
	private Date createtime;		// 合同创建时间
	private String version;		// 版本号
	
	public HotelContract() {
		super();
	}

	public HotelContract(String id){
		super(id);
	}

	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=50, message="酒店pms长度必须介于 0 和 50 之间")
	public String getHotelpms() {
		return hotelpms;
	}

	public void setHotelpms(String hotelpms) {
		this.hotelpms = hotelpms;
	}
	
	@Length(min=0, max=50, message="合同编写人长度必须介于 0 和 50 之间")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Length(min=0, max=2, message="1:正常,0:无效长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="1:分销，2:洗涤长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=5, message="版本号长度必须介于 0 和 5 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}