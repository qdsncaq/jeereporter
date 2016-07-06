///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.accntdetail.entity;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.validator.constraints.Length;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.mk.settlement.enums.AccountRoleEnums;
//import com.mk.settlement.enums.AccountStatusEnums;
//import com.mk.settlement.enums.BizTypeEnums;
//import com.mk.settlement.enums.FeeTypeEnums;
//import com.thinkgem.jeesite.common.persistence.DataEntity;
//import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
//
///**
// * accntdetailEntity
// * @author lm
// * @version 2016-03-18
// */
//public class SettlementAccntDetail1 extends DataEntity<SettlementAccntDetail1> {
//	
//	private static final long serialVersionUID = 1L;
//	private String accountid;		// 账户ID
//	private String accountname;		// 账户名称
//	private String orderid;		// 订单ID
//	private BigDecimal orderprice;		// 原始金额
//	private String strOrderprice;		// 原始金额
//	private String hotelid;		// 酒店ID
//	private String hotelname;		// 酒店名称
//	private Integer biztype;		// 业务类型
//	private BigDecimal ordertotal;		// 结算金额
//	private String strOrdertotal;		// 结算金额
//	private Date ordertime;		// 下单时间
//	private Integer roomnums;		// 房间数
//	private Integer days;		// 天数
//	private Date createTime;		// 创建时间
//	private Date updateTime;		// 更新时间
//	private Integer accountrole;		// 账户角色
//	private String hotelpms;		// PMS编码
//	private Integer status;		// 状态
//	private Integer orderstatus;		// 订单状态
//	private BigDecimal freetotal;		// 充值优惠金额
//	private String strFreetotal;		// 充值优惠金额
//	private Date leftDate;		// 离店时间
//	private String tripname;		// 旅行社名称
//	private Integer feetype;		// 费用类型
//	private Long scruleid;		// 规则ID
//	private String snapshotrate;		// 比率
//	private String scruledesc;		// 规则描述
//	private Integer accountyear;		// 年
//	private Integer accountmonth;		// 月
//	private Integer accountweek;		// 周
//	private Date begindate;		// 账期起始时间
//	private Date enddate;		// 账期截止时间
//	private Long no;		// bill表id(或者码表中的账期序号)
//	private String beginOrderprice;		// 开始 原始金额
//	private String endOrderprice;		// 结束 原始金额
//	private String beginOrdertotal;		// 开始 结算金额
//	private String endOrdertotal;		// 结束 结算金额
//	private Date beginOrdertime;		// 开始 下单时间
//	private Date endOrdertime;		// 结束 下单时间
//	private Integer beginRoomnums;		// 开始 房间数
//	private Integer endRoomnums;		// 结束 房间数
//	private Integer beginDays;		// 开始 天数
//	private Integer endDays;		// 结束 天数
//	private Date beginCreateTime;		// 开始 创建时间
//	private Date endCreateTime;		// 结束 创建时间
//	private Date beginUpdateTime;		// 开始 更新时间
//	private Date endUpdateTime;		// 结束 更新时间
//	private String beginFreetotal;		// 开始 充值优惠金额
//	private String endFreetotal;		// 结束 充值优惠金额
//	private Date beginLeftDate;		// 开始 离店时间
//	private Date endLeftDate;		// 结束 离店时间
//	private String beginSnapshotrate;		// 开始 比率
//	private String endSnapshotrate;		// 结束 比率
//	private Date beginBegindate;		// 开始 账期起始时间
//	private Date endBegindate;		// 结束 账期起始时间
//	private Date beginEnddate;		// 开始 账期截止时间
//	private Date endEnddate;		// 结束 账期截止时间
//	private String remarks; //调整说明
//	private Long no1;		// 分销业务销售月账单更新/分销业务销售周账单更新no
//	
//    private String bizorderid;
//    
//    private String thirdno;
//    
//    private String orderno;
//    
//    private Date callbackTime;    //支付时间
//    private Date beginCallbackTime;       // 开始 支付时间
//    private Date endCallbackTime;     // 结束 支付时间
//	
//	/*@ExcelField(title="调整说明", type=1, align=2, sort=200)*/
//	public String getRemarks() {
//		return remarks;
//	}
//
//	public void setRemarks(String remarks) {
//		this.remarks = remarks;
//	}
//
//	public SettlementAccntDetail1() {
//		super();
//	}
//
//	public SettlementAccntDetail1(String id){
//		super(id);
//	}
//
//	@Length(min=0, max=50, message="账户ID长度必须介于 0 和 50 之间")
//	@ExcelField(title="账户ID", type=1, align=2, sort=10)
//	public String getAccountid() {
//		return accountid;
//	}
//
//	public void setAccountid(String accountid) {
//		this.accountid = accountid;
//	}
//	
//	@Length(min=0, max=100, message="账户名称长度必须介于 0 和 100 之间")
//	@ExcelField(title="账户名称", type=1, align=2, sort=20)
//	public String getAccountname() {
//		return accountname;
//	}
//
//	public void setAccountname(String accountname) {
//		this.accountname = accountname;
//	}
//	
//	@NotNull(message="订单ID不能为空")
//	public String getOrderid() {
//		return orderid;
//	}
//	
//	@ExcelField(title="订单ID", type=1, align=2, sort=30)
//	public String getStrOrderid() {
//		return orderid.toString();
//	}
//
//	public void setOrderid(String orderid) {
//		this.orderid = orderid;
//	}
//	
//	public BigDecimal getOrderprice() {
//		return orderprice;
//	}
//	
//	@ExcelField(title="原始金额", type=1, align=2, sort=110)
//	public String getStrOrderprice() {
//		return strOrderprice;
//	}
//	
//	public void setStrOrderprice(String strOrderprice) {
//		this.strOrderprice = strOrderprice;
//	}
//
//	public void setOrderprice(BigDecimal orderprice) {
//		this.orderprice = orderprice;
//	}
//	
//	@Length(min=0, max=50, message="酒店ID长度必须介于 0 和 50 之间")
//	@ExcelField(title="酒店ID", type=1, align=2, sort=40)
//	public String getHotelid() {
//		return hotelid;
//	}
//
//	public void setHotelid(String hotelid) {
//		this.hotelid = hotelid;
//	}
//	
//	@Length(min=0, max=500, message="酒店名称长度必须介于 0 和 500 之间")
//	@ExcelField(title="酒店名称", type=1, align=2, sort=50)
//	public String getHotelname() {
//		return hotelname;
//	}
//
//	public void setHotelname(String hotelname) {
//		this.hotelname = hotelname;
//	}
//	
//	@NotNull(message="业务类型不能为空")
//	public Integer getBiztype() {
//		return biztype;
//	}
//	
//	@ExcelField(title="业务类型", type=1, align=2, sort=80)
//	public String getStrBiztype(){
//		return biztype != null ? BizTypeEnums.getDesc(biztype) : "";
//	}
//
//	public void setBiztype(Integer biztype) {
//		this.biztype = biztype;
//	}
//	
//	public BigDecimal getOrdertotal() {
//		return ordertotal;
//	}
//
//	@ExcelField(title="结算金额", type=1, align=2, sort=120)
//	public String getStrOrdertotal() {
//		return ordertotal+"";
//	}
//	
//	public void setStrOrdertotal(String strOrdertotal) {
//		this.strOrdertotal = strOrdertotal;
//	}
//	
//	public void setOrdertotal(BigDecimal ordertotal) {
//		this.ordertotal = ordertotal;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ExcelField(title="下单时间", type=1, align=2, sort=140)
//	public Date getOrdertime() {
//		return ordertime;
//	}
//
//	public void setOrdertime(Date ordertime) {
//		this.ordertime = ordertime;
//	}
//	
//	public Integer getRoomnums() {
//		return roomnums;
//	}
//
//    @ExcelField(title="房间数", type=1, align=2, sort=170)
//    public String getStrRoomnums() {
//        return roomnums != null ? roomnums + "" : "";
//    }
//
//	public void setRoomnums(Integer roomnums) {
//		this.roomnums = roomnums;
//	}
//	
//	public Integer getDays() {
//		return days;
//	}
//
//    @ExcelField(title="天数", type=1, align=2, sort=180)
//    public String getStrDays() {
//        return days != null ? days + "" : "";
//    }
//
//	public void setDays(Integer days) {
//		this.days = days;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@NotNull(message="创建时间不能为空")
//    @ExcelField(title="创建时间", type=1, align=2, sort=150)
//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@NotNull(message="更新时间不能为空")
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//	
//	public Integer getAccountrole() {
//		return accountrole;
//	}
//	
//	@ExcelField(title="账户角色", type=1, align=2, sort=90)
//	public String getAccountRole(){
//		return accountrole != null ? AccountRoleEnums.getDesc(accountrole) : "";
//	}
//
//	public void setAccountrole(Integer accountrole) {
//		this.accountrole = accountrole;
//	}
//	
//	@Length(min=0, max=100, message="PMS编码长度必须介于 0 和 100 之间")
//	@ExcelField(title="酒店PMS编码", type=1, align=2, sort=60)
//	public String getHotelpms() {
//		return hotelpms;
//	}
//
//	public void setHotelpms(String hotelpms) {
//		this.hotelpms = hotelpms;
//	}
//	
//	@NotNull(message="状态不能为空")
//	public Integer getStatus() {
//		return status;
//	}
//	
//	@ExcelField(title="财务状态", type=1, align=2, sort=190)
//	public String getStrStatus(){
//		return status != null ? AccountStatusEnums.getDesc(status) : "";
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//	
//	public Integer getOrderstatus() {
//		return orderstatus;
//	}
//
//	public void setOrderstatus(Integer orderstatus) {
//		this.orderstatus = orderstatus;
//	}
//	
//	public BigDecimal getFreetotal() {
//		return freetotal;
//	}
//	
//	public void setStrFreetotal(String strFreetotal) {
//		this.strFreetotal = strFreetotal;
//	}
//
//	public void setFreetotal(BigDecimal freetotal) {
//		this.freetotal = freetotal;
//	}
//	
//	@ExcelField(title="订单成功率系数", type=1, align=2, sort=130)
//	public String getStrFreetotal() {
//		return strFreetotal;
//	}
//
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ExcelField(title="离店时间", type=1, align=2, sort=160)
//	public Date getLeftDate() {
//		return leftDate;
//	}
//
//	public void setLeftDate(Date leftDate) {
//		this.leftDate = leftDate;
//	}
//	
//	@Length(min=0, max=100, message="旅行社名称长度必须介于 0 和 100 之间")
//	@ExcelField(title="旅行社名称", type=1, align=2, sort=70)
//	public String getTripname() {
//		return tripname;
//	}
//
//	public void setTripname(String tripname) {
//		this.tripname = tripname;
//	}
//	
//	public Integer getFeetype() {
//		return feetype;
//	}
//	
//	@ExcelField(title="费用类型", type=1, align=2, sort=100)
//	public String getStrFeetype(){
//		return feetype != null ? FeeTypeEnums.getDesc(feetype) : "";
//	}
//
//	public void setFeetype(Integer feetype) {
//		this.feetype = feetype;
//	}
//	
//	public Long getScruleid() {
//		return scruleid;
//	}
//
//	public void setScruleid(Long scruleid) {
//		this.scruleid = scruleid;
//	}
//	
//	public String getSnapshotrate() {
//		return snapshotrate;
//	}
//
//	public void setSnapshotrate(String snapshotrate) {
//		this.snapshotrate = snapshotrate;
//	}
//	
//	@Length(min=0, max=500, message="规则描述长度必须介于 0 和 500 之间")
//	public String getScruledesc() {
//		return scruledesc;
//	}
//
//	public void setScruledesc(String scruledesc) {
//		this.scruledesc = scruledesc;
//	}
//	
//	public Integer getAccountyear() {
//		return accountyear;
//	}
//
//	public void setAccountyear(Integer accountyear) {
//		this.accountyear = accountyear;
//	}
//	
//	public Integer getAccountmonth() {
//		return accountmonth;
//	}
//
//	public void setAccountmonth(Integer accountmonth) {
//		this.accountmonth = accountmonth;
//	}
//	
//	public Integer getAccountweek() {
//		return accountweek;
//	}
//
//	public void setAccountweek(Integer accountweek) {
//		this.accountweek = accountweek;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	public Date getBegindate() {
//		return begindate;
//	}
//
//	public void setBegindate(Date begindate) {
//		this.begindate = begindate;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	public Date getEnddate() {
//		return enddate;
//	}
//
//	public void setEnddate(Date enddate) {
//		this.enddate = enddate;
//	}
//	
//	public Long getNo() {
//		return no;
//	}
//
//	public void setNo(Long no) {
//		this.no = no;
//	}
//	
//	public String getBeginOrderprice() {
//		return beginOrderprice;
//	}
//
//	public void setBeginOrderprice(String beginOrderprice) {
//		this.beginOrderprice = beginOrderprice;
//	}
//	
//	public String getEndOrderprice() {
//		return endOrderprice;
//	}
//
//	public void setEndOrderprice(String endOrderprice) {
//		this.endOrderprice = endOrderprice;
//	}
//		
//	public String getBeginOrdertotal() {
//		return beginOrdertotal;
//	}
//
//	public void setBeginOrdertotal(String beginOrdertotal) {
//		this.beginOrdertotal = beginOrdertotal;
//	}
//	
//	public String getEndOrdertotal() {
//		return endOrdertotal;
//	}
//
//	public void setEndOrdertotal(String endOrdertotal) {
//		this.endOrdertotal = endOrdertotal;
//	}
//		
//	public Date getBeginOrdertime() {
//		return beginOrdertime;
//	}
//
//	public void setBeginOrdertime(Date beginOrdertime) {
//		this.beginOrdertime = beginOrdertime;
//	}
//	
//	public Date getEndOrdertime() {
//		return endOrdertime;
//	}
//
//	public void setEndOrdertime(Date endOrdertime) {
//		this.endOrdertime = endOrdertime;
//	}
//		
//	public Integer getBeginRoomnums() {
//		return beginRoomnums;
//	}
//
//	public void setBeginRoomnums(Integer beginRoomnums) {
//		this.beginRoomnums = beginRoomnums;
//	}
//	
//	public Integer getEndRoomnums() {
//		return endRoomnums;
//	}
//
//	public void setEndRoomnums(Integer endRoomnums) {
//		this.endRoomnums = endRoomnums;
//	}
//		
//	public Integer getBeginDays() {
//		return beginDays;
//	}
//
//	public void setBeginDays(Integer beginDays) {
//		this.beginDays = beginDays;
//	}
//	
//	public Integer getEndDays() {
//		return endDays;
//	}
//
//	public void setEndDays(Integer endDays) {
//		this.endDays = endDays;
//	}
//		
//	public Date getBeginCreateTime() {
//		return beginCreateTime;
//	}
//
//	public void setBeginCreateTime(Date beginCreateTime) {
//		this.beginCreateTime = beginCreateTime;
//	}
//	
//	public Date getEndCreateTime() {
//		return endCreateTime;
//	}
//
//	public void setEndCreateTime(Date endCreateTime) {
//		this.endCreateTime = endCreateTime;
//	}
//		
//	public Date getBeginUpdateTime() {
//		return beginUpdateTime;
//	}
//
//	public void setBeginUpdateTime(Date beginUpdateTime) {
//		this.beginUpdateTime = beginUpdateTime;
//	}
//	
//	public Date getEndUpdateTime() {
//		return endUpdateTime;
//	}
//
//	public void setEndUpdateTime(Date endUpdateTime) {
//		this.endUpdateTime = endUpdateTime;
//	}
//		
//	public String getBeginFreetotal() {
//		return beginFreetotal;
//	}
//
//	public void setBeginFreetotal(String beginFreetotal) {
//		this.beginFreetotal = beginFreetotal;
//	}
//	
//	public String getEndFreetotal() {
//		return endFreetotal;
//	}
//
//	public void setEndFreetotal(String endFreetotal) {
//		this.endFreetotal = endFreetotal;
//	}
//		
//	public Date getBeginLeftDate() {
//		return beginLeftDate;
//	}
//
//	public void setBeginLeftDate(Date beginLeftDate) {
//		this.beginLeftDate = beginLeftDate;
//	}
//	
//	public Date getEndLeftDate() {
//		return endLeftDate;
//	}
//
//	public void setEndLeftDate(Date endLeftDate) {
//		this.endLeftDate = endLeftDate;
//	}
//		
//	public String getBeginSnapshotrate() {
//		return beginSnapshotrate;
//	}
//
//	public void setBeginSnapshotrate(String beginSnapshotrate) {
//		this.beginSnapshotrate = beginSnapshotrate;
//	}
//	
//	public String getEndSnapshotrate() {
//		return endSnapshotrate;
//	}
//
//	public void setEndSnapshotrate(String endSnapshotrate) {
//		this.endSnapshotrate = endSnapshotrate;
//	}
//		
//	public Date getBeginBegindate() {
//		return beginBegindate;
//	}
//
//	public void setBeginBegindate(Date beginBegindate) {
//		this.beginBegindate = beginBegindate;
//	}
//	
//	public Date getEndBegindate() {
//		return endBegindate;
//	}
//
//	public void setEndBegindate(Date endBegindate) {
//		this.endBegindate = endBegindate;
//	}
//		
//	public Date getBeginEnddate() {
//		return beginEnddate;
//	}
//
//	public void setBeginEnddate(Date beginEnddate) {
//		this.beginEnddate = beginEnddate;
//	}
//	
//	public Date getEndEnddate() {
//		return endEnddate;
//	}
//
//	public void setEndEnddate(Date endEnddate) {
//		this.endEnddate = endEnddate;
//	}
//	
//    @ExcelField(title="商户订单号", type=1, align=2, sort=210)
//    public String getBizorderid() {
//        return bizorderid;
//    }
//
//    public void setBizorderid(String bizorderid) {
//        this.bizorderid = bizorderid;
//    }
//
//    @ExcelField(title="微信/支付宝交易号", type=1, align=2, sort=220)
//    public String getThirdno() {
//        return thirdno;
//    }
//
//    public void setThirdno(String thirdno) {
//        this.thirdno = thirdno;
//    }
//
//    @ExcelField(title="支付时间", type=1, align=2, sort=230)
//    public Date getCallbackTime() {
//    	if(callbackTime == null){
//    		return ordertime;
//    	} else{
//    		return callbackTime;
//    	}
//    }
//
//    public void setCallbackTime(Date callbackTime) {
//        this.callbackTime = callbackTime;
//    }
//
//    public Date getBeginCallbackTime() {
//        return beginCallbackTime;
//    }
//
//    public void setBeginCallbackTime(Date beginCallbackTime) {
//        this.beginCallbackTime = beginCallbackTime;
//    }
//
//    public Date getEndCallbackTime() {
//        return endCallbackTime;
//    }
//
//    public void setEndCallbackTime(Date endCallbackTime) {
//        this.endCallbackTime = endCallbackTime;
//    }
//
//    @ExcelField(title="第三方单号", type=1, align=2, sort=35)
//    public String getOrderno() {
//        return orderno;
//    }
//
//    public void setOrderno(String orderno) {
//        this.orderno = orderno;
//    }
//
//	public Long getNo1() {
//		return no1;
//	}
//
//	public void setNo1(Long no1) {
//		this.no1 = no1;
//	}
//
//}
