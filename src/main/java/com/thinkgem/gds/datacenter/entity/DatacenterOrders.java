/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.datacenter.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 分销订单查询Entity
 * 
 * @author LYN
 * @version 2016-03-15
 */
public class DatacenterOrders extends DataEntity<DatacenterOrders> {

	private static final long serialVersionUID = 1L;
	private Long orderid;
	private String hotelid; // 酒店id
	private String hotelname; // 酒店名称
	private String hotelpms; // 酒店对应的pms
	private Integer channelid; // 渠道id
	private String paytype; // 支付类型
	private Date begintime; // 预抵时间
	private Date begintime1;  //预抵时间  (查询参数)
	private Date endtime; // 预离时间
	private Date endtime1; // 预离时间    (查询参数)
	private String status; // 订单状态
	private String teamname; // 团队名称
	private String totalprice; // 总价格
	private String contacts; // 联系人
	private String contactsphone; // 联系电话
	private String paystatus; // 支付类型
	private String orderno; // 第三方id
	private String pmsorderid; // pms订单号
	private String grouporderid; // PMS批量订单ID
	private String ordertype; // 订单类型
	private String cancelreason; // 取消原因
	private Date createtime; // 创建时间
	private Date createtime1; // 创建时间   (查询参数)
	private Date updatetime; // 修改时间
	private String note; // 备注
	private String channelname; // 分销商名称
	private String salerid; // 销售ID
	private String settlestatus; // 结算状态1,
	private String cancelreasondesc; // 取消理由

	private String provcode;
	private String citycode;
	private String discode;
	private String provname;
	private String cityname;
	private String disname;
	private String salername;
	private String salerphone;
	private String bossname;
	private String bossphone;
	private Integer totalroom;

	private String hotelphone;
	private Integer refuseorder;
	private Integer pmsbosscomfirmorder;
	
	/**
	 * 是否已设置一键满房
	 */
	private Boolean fullflag;

    /**
     * 是否自建酒店
     */
    private String hotelSelfBuild;

    /**
     * 订单优惠金额
     */
    private BigDecimal discount;

	@ExcelField(title = "到店无房", type = 1, align = 2, sort = 18, dictType = "RefuseOrderEnum")
	public Integer getRefuseorder() {
		return refuseorder;
	}

	public void setRefuseorder(Integer refuseorder) {
		this.refuseorder = refuseorder;
	}

	public Boolean getFullflag() {
		return fullflag;
	}

	public void setFullflag(Boolean fullflag) {
		this.fullflag = fullflag;
	}

	public Integer getPmsbosscomfirmorder() {
		return pmsbosscomfirmorder;
	}

	public void setPmsbosscomfirmorder(Integer pmsbosscomfirmorder) {
		this.pmsbosscomfirmorder = pmsbosscomfirmorder;
	}

	@ExcelField(title = "销售", type = 1, align = 2, sort = 17)
	public String getSalername() {
		return salername;
	}

	public void setSalername(String salername) {
		this.salername = salername;
	}

	@ExcelField(title = "销售电话", type = 1, align = 2, sort = 17)
	public String getSalerphone() {
		return salerphone;
	}

	public void setSalerphone(String salerphone) {
		this.salerphone = salerphone;
	}

	@ExcelField(title = "省", type = 1, align = 2, sort = 3)
	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}

	@ExcelField(title = "市", type = 1, align = 2, sort = 4)
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	@ExcelField(title = "县", type = 1, align = 2, sort = 5)
	public String getDisname() {
		return disname;
	}

	public void setDisname(String disname) {
		this.disname = disname;
	}

	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}

	public DatacenterOrders() {
		super();
	}

	public DatacenterOrders(String id) {
		super(id);
	}

	@ExcelField(title = "订单ID", type = 1, align = 2, sort = 1)
	public String getOrderid() {
		return id;
	}

	@NotNull(message = "酒店id不能为空")
	@ExcelField(title = "酒店id", type = 1, align = 2, sort = 1)
	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}

	@Length(min = 0, max = 100, message = "酒店名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "酒店名称", type = 1, align = 2, sort = 2)
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	@Length(min = 0, max = 100, message = "酒店对应的pms长度必须介于 0 和 100 之间")
	public String getHotelpms() {
		return hotelpms;
	}

	public void setHotelpms(String hotelpms) {
		this.hotelpms = hotelpms;
	}

	@NotNull(message = "渠道id不能为空")
	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	@Length(min = 1, max = 4, message = "支付类型长度必须介于 1 和 4 之间")
	@ExcelField(title = "支付类型", type = 1, align = 2, sort = 6, dictType = "PayTypeEnum")
	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "预抵时间不能为空")
	@ExcelField(title = "预抵时间", type = 1, align = 2, sort = 12)
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "预离时间不能为空")
	@ExcelField(title = "预离时间", type = 1, align = 2, sort = 13)
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	@Length(min = 1, max = 4, message = "订单状态长度必须介于 1 和 4 之间")
	@ExcelField(title = "订单状态", type = 1, align = 2, sort = 11, dictType = "OrderStatusEnum")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min = 0, max = 50, message = "团队名称长度必须介于 0 和 50 之间")
	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	@ExcelField(title = "总价格", type = 1, align = 2, sort = 9)
	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	@Length(min = 0, max = 25, message = "联系人长度必须介于 0 和 25 之间")
	@ExcelField(title = "联系人", type = 1, align = 2, sort = 14)
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Length(min = 0, max = 25, message = "联系电话长度必须介于 0 和 25 之间")
	@ExcelField(title = "联系电话", type = 1, align = 2, sort = 15)
	public String getContactsphone() {
		return contactsphone;
	}

	public void setContactsphone(String contactsphone) {
		this.contactsphone = contactsphone;
	}

	@Length(min = 0, max = 4, message = "支付类型长度必须介于 0 和 4 之间")
	@ExcelField(title="支付状态", type=1, align=2,sort=7,dictType="OrderPayStatusEnum")
	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

	
	@Length(min = 0, max = 100, message = "第三方id长度必须介于 0 和 100 之间")
	@ExcelField(title = "第三方单号", type = 1, align = 2, sort = 4)
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Length(min = 0, max = 100, message = "pms订单号长度必须介于 0 和 100 之间")
	public String getPmsorderid() {
		return pmsorderid;
	}

	public void setPmsorderid(String pmsorderid) {
		this.pmsorderid = pmsorderid;
	}

	@Length(min = 0, max = 100, message = "PMS批量订单ID长度必须介于 0 和 100 之间")
	public String getGrouporderid() {
		return grouporderid;
	}

	public void setGrouporderid(String grouporderid) {
		this.grouporderid = grouporderid;
	}

	@Length(min = 0, max = 4, message = "订单类型长度必须介于 0 和 4 之间")
	@ExcelField(title = "订单类型", type = 1, align = 2, sort = 8, dictType = "OrderTypeEnum")
	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	@Length(min = 0, max = 4, message = "取消原因长度必须介于 0 和 4 之间")
	public String getCancelreason() {
		return cancelreason;
	}

	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "创建时间不能为空")
	@ExcelField(title = "预订时间", type = 1, align = 2, sort = 10)
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Length(min = 0, max = 100, message = "分销商名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "渠道名称", type = 1, align = 2, sort = 9)
	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	@Length(min = 0, max = 100, message = "销售ID长度必须介于 0 和 100 之间")
	@ExcelField(title = "销售id", type = 1, align = 2, sort = 16)
	public String getSalerid() {
		return salerid;
	}

	public void setSalerid(String salerid) {
		this.salerid = salerid;
	}

	@Length(min = 0, max = 4, message = "结算状态1,长度必须介于 0 和 4 之间")
	public String getSettlestatus() {
		return settlestatus;
	}

	public void setSettlestatus(String settlestatus) {
		this.settlestatus = settlestatus;
	}

	@Length(min = 0, max = 100, message = "取消理由长度必须介于 0 和 100 之间")
	@ExcelField(title = "取消原因", type = 1, align = 2, sort = 8)
	public String getCancelreasondesc() {
		return cancelreasondesc;
	}

	public void setCancelreasondesc(String cancelreasondesc) {
		this.cancelreasondesc = cancelreasondesc;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public String getBossname() {
		return bossname;
	}

	public void setBossname(String bossname) {
		this.bossname = bossname;
	}

	public String getBossphone() {
		return bossphone;
	}

	public void setBossphone(String bossphone) {
		this.bossphone = bossphone;
	}

	public Integer getTotalroom() {
		return totalroom;
	}

	public void setTotalroom(Integer totalroom) {
		this.totalroom = totalroom;
	}

	public String getHotelphone() {
		return hotelphone;
	}

	public void setHotelphone(String hotelphone) {
		this.hotelphone = hotelphone;
	}

	/**
	 * @return the begintime1
	 */
	public Date getBegintime1() {
		return begintime1;
	}

	/**
	 * @param begintime1 the begintime1 to set
	 */
	public void setBegintime1(Date begintime1) {
		this.begintime1 = begintime1;
	}

	/**
	 * @return the endtime1
	 */
	public Date getEndtime1() {
		return endtime1;
	}

	/**
	 * @param endtime1 the endtime1 to set
	 */
	public void setEndtime1(Date endtime1) {
		this.endtime1 = endtime1;
	}

	/**
	 * @return the createtime1
	 */
	public Date getCreatetime1() {
		return createtime1;
	}

	/**
	 * @param createtime1 the createtime1 to set
	 */
	public void setCreatetime1(Date createtime1) {
		this.createtime1 = createtime1;
	}

    public String getHotelSelfBuild() {
        return hotelSelfBuild;
    }

    public void setHotelSelfBuild(String hotelSelfBuild) {
        this.hotelSelfBuild = hotelSelfBuild;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}