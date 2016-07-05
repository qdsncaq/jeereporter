/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbhotelbillinit.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.supcan.annotation.treelist.cols.SupCol;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.psb.enums.HotelOnlineState;

/**
 * psbhotelbillEntity
 * @author lm
 * @version 2016-04-12
 */
public class SettlementPsbhotelbillinit extends DataEntity<SettlementPsbhotelbillinit> {
	
	private static final long serialVersionUID = 1L;
	private Integer psbid;		// PSB编码
	private String psbname;		// PSB名称
	private String hotelid;		// 酒店ID
	private String hotelpms;		// PMS编码
	private String hotelname;		// 酒店名称
	private String procode;		// 省编码
	private String proname;		// 省名称
	private String citycode;		// 市编码
	private String cityname;		// 市名称
	private String discode;		// 区编码
	private String disname;		// 区名称
	private Integer roomnums;		// 酒店房间数
	private Integer onlinestate;		// 当前酒店HMS在线状态(1,在线/2,离线)
	private String areamanager;		// 区域工程师
	private String operator;		// 装机工程师
	private Date begintime;		// 结算开始日期
	private Date endtime;		// 结算截止日期(+360天 日期不变)
	private Date realendtime;		// 实际截止日期(计算下线天数推迟)
	private Date onlinetime;		// 上线时间
	private Date offlinetime;		// 下线时间
	private BigDecimal fee;		// 本年年费
	private String feetypeids;		// 对应科目(多个)
	private Integer onlinedays;		// HMS有效天数（账期已统计天数）
	private BigDecimal amount;		// 结算金额(预付给psb金额)
	private BigDecimal recoveramount;		// 应收金额
	private Date createtime;		// 创建时间
	private Date updatetime;		// 更新时间
	private Integer beginRoomnums;		// 开始 酒店房间数
	private Integer endRoomnums;		// 结束 酒店房间数
	private Date beginBegintime;		// 开始 结算开始日期
	private Date endBegintime;		// 结束 结算开始日期
	private Date beginEndtime;		// 开始 结算截止日期(+360天 日期不变)
	private Date endEndtime;		// 结束 结算截止日期(+360天 日期不变)
	private Date beginRealendtime;		// 开始 实际截止日期(计算下线天数推迟)
	private Date endRealendtime;		// 结束 实际截止日期(计算下线天数推迟)
	private Date beginOnlinetime;		// 开始 上线时间
	private Date endOnlinetime;		// 结束 上线时间
	private Date beginOfflinetime;		// 开始 下线时间
	private Date endOfflinetime;		// 结束 下线时间
	private String beginFee;		// 开始 本年年费
	private String endFee;		// 结束 本年年费
	private Integer beginOnlinedays;		// 开始 HMS有效天数（账期已统计天数）
	private Integer endOnlinedays;		// 结束 HMS有效天数（账期已统计天数）
	private String beginAmount;		// 开始 结算金额(预付给psb金额)
	private String endAmount;		// 结束 结算金额(预付给psb金额)
	private String beginRecoveramount;		// 开始 应收金额
	private String endRecoveramount;		// 结束 应收金额
	private Date beginCreatetime;		// 开始 创建时间
	private Date endCreatetime;		// 结束 创建时间
	private Date beginUpdatetime;		// 开始 更新时间
	private Date endUpdatetime;		// 结束 更新时间
	private Integer status;			// 状态
	
	private Integer billtype;		// 账单状态1:新上线酒店 
	private String billtypedesc; 	// 账单状态描述
	
	public SettlementPsbhotelbillinit() {
		super();
	}
	
	@ExcelField(title="ID", type=1, align=2, sort=1)
	@SupCol(isUnique="true", isHide="true")
	public String getId() {
		return id;
	}

	public SettlementPsbhotelbillinit(String id){
		super(id);
	}

	@NotNull(message="PSB编码不能为空")
	public Integer getPsbid() {
		return psbid;
	}

	public void setPsbid(Integer psbid) {
		this.psbid = psbid;
	}
	
	@Length(min=0, max=255, message="PSB名称长度必须介于 0 和 255 之间")
	public String getPsbname() {
		return psbname;
	}

	public void setPsbname(String psbname) {
		this.psbname = psbname;
	}
	
	@Length(min=1, max=50, message="酒店ID长度必须介于 1 和 50 之间")
	@ExcelField(title="酒店编号", type=1, align=2, sort=5)
	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	
	@Length(min=0, max=100, message="PMS编码长度必须介于 0 和 100 之间")
	public String getHotelpms() {
		return hotelpms;
	}

	public void setHotelpms(String hotelpms) {
		this.hotelpms = hotelpms;
	}
	
	@Length(min=0, max=500, message="酒店名称长度必须介于 0 和 500 之间")
	@ExcelField(title="PMS酒店名", type=1, align=2, sort=6)
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	@Length(min=0, max=200, message="省编码长度必须介于 0 和 200 之间")
	public String getProcode() {
		return procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}
	
	@Length(min=0, max=200, message="省名称长度必须介于 0 和 200 之间")
	@ExcelField(title="省份", type=1, align=2, sort=2)
	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}
	
	@Length(min=0, max=200, message="市编码长度必须介于 0 和 200 之间")
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	
	@Length(min=0, max=200, message="市名称长度必须介于 0 和 200 之间")
	@ExcelField(title="城市", type=1, align=2, sort=3)
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	@Length(min=0, max=200, message="区编码长度必须介于 0 和 200 之间")
	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}
	
	@Length(min=0, max=200, message="区名称长度必须介于 0 和 200 之间")
	@ExcelField(title="区域", type=1, align=2, sort=4)
	public String getDisname() {
		return disname;
	}

	public void setDisname(String disname) {
		this.disname = disname;
	}
	
	@ExcelField(title="酒店房间数", type=1, align=2, sort=7)
	public Integer getRoomnums() {
		return roomnums;
	}

	public void setRoomnums(Integer roomnums) {
		this.roomnums = roomnums;
	}
	
	public Integer getOnlinestate() {
		return onlinestate;
	}
	
	@ExcelField(title="HMS状态", type=1, align=2, sort=8)
	public String getStrOnlinestate() {
		return  HotelOnlineState.getDesc(onlinestate);
	}

	public void setOnlinestate(Integer onlinestate) {
		this.onlinestate = onlinestate;
	}
	
	@Length(min=0, max=255, message="区域工程师长度必须介于 0 和 255 之间")
	@ExcelField(title="区域经理", type=1, align=2, sort=9)
	public String getAreamanager() {
		return areamanager;
	}

	public void setAreamanager(String areamanager) {
		this.areamanager = areamanager;
	}
	
	@Length(min=0, max=255, message="装机工程师长度必须介于 0 和 255 之间")
	@ExcelField(title="装机工程师", type=1, align=2, sort=10)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="结算起始日期", type=1, align=2, sort=11)
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="结算截止日期", type=1, align=2, sort=12)
	public Date getRealendtime() {
		return realendtime;
	}

	public void setRealendtime(Date realendtime) {
		this.realendtime = realendtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="上线时间", type=1, align=2, sort=13)
	public Date getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(Date onlinetime) {
		this.onlinetime = onlinetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="下线时间", type=1, align=2, sort=14)
	public Date getOfflinetime() {
		return offlinetime;
	}

	public void setOfflinetime(Date offlinetime) {
		this.offlinetime = offlinetime;
	}
	
	@ExcelField(title="HMS有效天数", type=1, align=2, sort=15)
	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	@Length(min=0, max=50, message="对应科目(多个)长度必须介于 0 和 50 之间")
	public String getFeetypeids() {
		return feetypeids;
	}

	public void setFeetypeids(String feetypeids) {
		this.feetypeids = feetypeids;
	}
	
	public Integer getOnlinedays() {
		return onlinedays;
	}

	public void setOnlinedays(Integer onlinedays) {
		this.onlinedays = onlinedays;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@ExcelField(title="结算金额", type=1, align=2, sort=17)
	public BigDecimal getRecoveramount() {
		return recoveramount;
	}

	public void setRecoveramount(BigDecimal recoveramount) {
		this.recoveramount = recoveramount;
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
	@NotNull(message="更新时间不能为空")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public Integer getBeginRoomnums() {
		return beginRoomnums;
	}

	public void setBeginRoomnums(Integer beginRoomnums) {
		this.beginRoomnums = beginRoomnums;
	}
	
	public Integer getEndRoomnums() {
		return endRoomnums;
	}

	public void setEndRoomnums(Integer endRoomnums) {
		this.endRoomnums = endRoomnums;
	}
		
	public Date getBeginBegintime() {
		return beginBegintime;
	}

	public void setBeginBegintime(Date beginBegintime) {
		this.beginBegintime = beginBegintime;
	}
	
	public Date getEndBegintime() {
		return endBegintime;
	}

	public void setEndBegintime(Date endBegintime) {
		this.endBegintime = endBegintime;
	}
		
	public Date getBeginEndtime() {
		return beginEndtime;
	}

	public void setBeginEndtime(Date beginEndtime) {
		this.beginEndtime = beginEndtime;
	}
	
	public Date getEndEndtime() {
		return endEndtime;
	}

	public void setEndEndtime(Date endEndtime) {
		this.endEndtime = endEndtime;
	}
		
	public Date getBeginRealendtime() {
		return beginRealendtime;
	}

	public void setBeginRealendtime(Date beginRealendtime) {
		this.beginRealendtime = beginRealendtime;
	}
	
	public Date getEndRealendtime() {
		return endRealendtime;
	}

	public void setEndRealendtime(Date endRealendtime) {
		this.endRealendtime = endRealendtime;
	}
		
	public Date getBeginOnlinetime() {
		return beginOnlinetime;
	}

	public void setBeginOnlinetime(Date beginOnlinetime) {
		this.beginOnlinetime = beginOnlinetime;
	}
	
	public Date getEndOnlinetime() {
		return endOnlinetime;
	}

	public void setEndOnlinetime(Date endOnlinetime) {
		this.endOnlinetime = endOnlinetime;
	}
		
	public Date getBeginOfflinetime() {
		return beginOfflinetime;
	}

	public void setBeginOfflinetime(Date beginOfflinetime) {
		this.beginOfflinetime = beginOfflinetime;
	}
	
	public Date getEndOfflinetime() {
		return endOfflinetime;
	}

	public void setEndOfflinetime(Date endOfflinetime) {
		this.endOfflinetime = endOfflinetime;
	}
		
	public String getBeginFee() {
		return beginFee;
	}

	public void setBeginFee(String beginFee) {
		this.beginFee = beginFee;
	}
	
	public String getEndFee() {
		return endFee;
	}

	public void setEndFee(String endFee) {
		this.endFee = endFee;
	}
		
	public Integer getBeginOnlinedays() {
		return beginOnlinedays;
	}

	public void setBeginOnlinedays(Integer beginOnlinedays) {
		this.beginOnlinedays = beginOnlinedays;
	}
	
	public Integer getEndOnlinedays() {
		return endOnlinedays;
	}

	public void setEndOnlinedays(Integer endOnlinedays) {
		this.endOnlinedays = endOnlinedays;
	}
		
	public String getBeginAmount() {
		return beginAmount;
	}

	public void setBeginAmount(String beginAmount) {
		this.beginAmount = beginAmount;
	}
	
	public String getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(String endAmount) {
		this.endAmount = endAmount;
	}
		
	public String getBeginRecoveramount() {
		return beginRecoveramount;
	}

	public void setBeginRecoveramount(String beginRecoveramount) {
		this.beginRecoveramount = beginRecoveramount;
	}
	
	public String getEndRecoveramount() {
		return endRecoveramount;
	}

	public void setEndRecoveramount(String endRecoveramount) {
		this.endRecoveramount = endRecoveramount;
	}
		
	public Date getBeginCreatetime() {
		return beginCreatetime;
	}

	public void setBeginCreatetime(Date beginCreatetime) {
		this.beginCreatetime = beginCreatetime;
	}
	
	public Date getEndCreatetime() {
		return endCreatetime;
	}

	public void setEndCreatetime(Date endCreatetime) {
		this.endCreatetime = endCreatetime;
	}
		
	public Date getBeginUpdatetime() {
		return beginUpdatetime;
	}

	public void setBeginUpdatetime(Date beginUpdatetime) {
		this.beginUpdatetime = beginUpdatetime;
	}
	
	public Date getEndUpdatetime() {
		return endUpdatetime;
	}

	public void setEndUpdatetime(Date endUpdatetime) {
		this.endUpdatetime = endUpdatetime;
	}
		
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBilltype() {
		return billtype;
	}

	public void setBilltype(Integer billtype) {
		this.billtype = billtype;
	}

	public String getBilltypedesc() {
		return billtypedesc;
	}

	public void setBilltypedesc(String billtypedesc) {
		this.billtypedesc = billtypedesc;
	}
	
	
}