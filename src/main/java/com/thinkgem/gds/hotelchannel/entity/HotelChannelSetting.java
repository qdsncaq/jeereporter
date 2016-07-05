/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotelchannel.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 酒店渠道分销开关Entity
 * @author zhaochuanbin
 * @version 2016-03-16
 */
public class HotelChannelSetting extends DataEntity<HotelChannelSetting> {
	
	private static final long serialVersionUID = 1L;
	private Long hotelid;		// 酒店id
	private String hotelname;
	private String channelid;		// 渠道：1、旅行社，2、去哪儿，3、去啊，4、艺龙，5、同程旅游，6、爱学贷，7、爱康网，8、天下房仓，9、众荟，10、携程
	private String state;		// 状态，1开通，2未开通
	private Date createtime;		// 创建时间
	private String createuser;		// 创建人
	private Date updatetime;		// 更新时间
	private String updateuser;		// 更新人
	
	
	public HotelChannelSetting() {
		super();
	}

	public HotelChannelSetting(String id){
		super(id);
	}

	@NotNull(message="酒店id不能为空")
	@ExcelField(title="酒店id", align=2, sort=1)
	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=1, max=10, message="渠道：1、旅行社，2、去哪儿，3、去啊，4、艺龙，5、同程旅游，6、爱学贷，7、爱康网，8、天下房仓，9、众荟，10、携程长度必须介于 1 和 10 之间")
	@ExcelField(title="渠道", align=2, sort=2)
	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	
	@Length(min=1, max=10, message="状态，1开通，2未开通长度必须介于 1 和 10 之间")
	@ExcelField(title="操作", align=2, sort=3)
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

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	
	
}