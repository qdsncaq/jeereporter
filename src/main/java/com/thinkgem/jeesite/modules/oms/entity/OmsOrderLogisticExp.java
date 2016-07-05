package com.thinkgem.jeesite.modules.oms.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;


/**
 * 物流那里导出的 导出有自己的  字段及 顺序，
 * @author zongsi
 *
 */
public class OmsOrderLogisticExp extends DataEntity<OmsOrderLogisticExp> {

	private static final long serialVersionUID = 1L;
	private Long orderId; // 订单id
	private String sAtime; // 创建时间
	private Date startTime;
	private String startTimeStr;
	private Date endTime;
	private String endTimeStr;
	private String sMtime; // 修改时间
	private Date modifyTime;
	private String sCmpy; // 酒店id
	private String hotelName;
	private String status; // 订单状态
	private String yzOrderid; // 有赞订单id
	private Long userid; // 创建订单用户ID 索引
	private String tradeMemo; // 交易备注
	private String receiverCity; // 收货城市
	private String receiverDistrict; // 区县
	private String receiverState; // 收货状态
	private String receiverAddress; // 收货地址
	private String receiverZip; // 收货邮编
	private String receiverMobile; // 联系电话
	private String postFee; // 邮寄费用
	private String totoleFee; // 总费用
	private String refundedFee; // 退还金额
	private String discount; // 折扣
	private String payment; // 支付费用
	private String title; // 标题
	private String marketid; // 销售id
	private String marketName; // 销售名称
	private String payStatus; // 支付状态
	private String payMemo; // 支付备注
	private Integer num; // 商品数量
	private String kind; // 物品品类
	private String productName; // 商品名称
	private String productTotalFee; // 商品总费用
	private Long shopid;// 店铺id
	private String receiverName; // 收货人姓名
	private String marketMoble; // 销售电话
	private String buyerMessage; // 买家留言

	private String buyerNick;// 买家昵称

	private String consignTime; // 发货时间
	private String signTime; // 签收时间
	private String remarks; // 修改备注
	private String invoiceTime; // 预发货时间
	private Date invoiceDate;

	// 显示用
	private String shopName;// 店铺名称
	private String hmsuserid;// hms 用户id
	private String userName;// hms 用户名
	private String hmsid;// hms 酒店id

	private String sku;// SKU编码
	private String comType;// 商品类别
	private String comFee;// 商品单价
	private String comtotoleFee;// 商品金额
	private String comName;// 商品名称
	// private String yzUserName;//买家会员名

	// 查询用 防止页面查询时输入 封装报错
	private String searchId;

	private Date startPayTime;
	private String startPayTimeStr;
	private Date endPayTime;
	private String endPayTimeStr;

	private Set<String> statusIn; // 状态 用作查询

	private String startUpdateTime;
	private String endUpdateTime;

	// 发货日期
	private String startConsignTime;
	private String endConsignTime;

	// 预计发货时间
	private String startInvoiceTime;
	private String endInvoiceTime;

	// 运单号
	private String controlNo;

	private String weight;

	private Integer yundanCode;
	
	private String wuliuCode;

	private String qianShouStatus;

	private String expBatch;

	private String outerId;
	
	/** 直配送供应商名称  */
	private String vendorName;
	
	/**
	 * 配送公司
	 */
	private String peiSongCmpy;
	
	
	/**
	 * 仓库
	 */
	private String wareHouseName;
	
	/**
	 * 两列其实是一样的，操蛋的需求
	 */
	private String tiHuoCangku;

	private Long itemId;
	
	/**
	 * 按天查询导出的记录
	 */
	private String expBatchDay;
	
	/**
	 * 用于物流 状态的查询
	 */
	private Integer logisticsStatus;
	
	
	private BigDecimal divisor = new BigDecimal(100);

	public OmsOrderLogisticExp() {
		super();
	}

	public OmsOrderLogisticExp(String id) {
		super(id);
	}

	@ExcelField(title = "订单id", align = 2, sort = 10)
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getSAtime() {
		return sAtime;
	}

	public void setSAtime(String sAtime) {
		this.sAtime = sAtime;
	}

	public String getSMtime() {
		return sMtime;
	}

	public void setSMtime(String sMtime) {
		this.sMtime = sMtime;
	}

	public String getSCmpy() {
		return sCmpy;
	}

	public void setSCmpy(String sCmpy) {
		this.sCmpy = sCmpy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ExcelField(title = "订单号", align = 2, sort = 20)
	public String getYzOrderid() {
		return yzOrderid;
	}

	public void setYzOrderid(String yzOrderid) {
		this.yzOrderid = yzOrderid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getTradeMemo() {
		return tradeMemo;
	}

	public void setTradeMemo(String tradeMemo) {
		this.tradeMemo = tradeMemo;
	}

	@ExcelField(title = "收货城市", align = 2, sort = 120)
	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	@ExcelField(title = "收货区县", align = 2, sort = 130)
	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	@ExcelField(title = "收货省份", align = 2, sort = 110)
	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	@ExcelField(title = "收件人地址", align = 2, sort = 140)
	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverZip() {
		return receiverZip;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

	@ExcelField(title = "收件人电话", align = 2, sort = 90)
	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getPostFee() {

		BigDecimal bd = new BigDecimal(postFee);

		return bd.divide(divisor, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}

	public String getTotoleFee() {

		BigDecimal bd = new BigDecimal(totoleFee);

		return bd.divide(divisor, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	public void setTotoleFee(String totoleFee) {
		this.totoleFee = totoleFee;
	}

	public String getRefundedFee() {

		BigDecimal bd = new BigDecimal(refundedFee);

		return bd.divide(divisor, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	public void setRefundedFee(String refundedFee) {
		this.refundedFee = refundedFee;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getPayment() {

		BigDecimal bd = new BigDecimal(payment);

		return bd.divide(divisor, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMarketid() {
		return marketid;
	}

	public void setMarketid(String marketid) {
		this.marketid = marketid;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayMemo() {
		return payMemo;
	}

	public void setPayMemo(String payMemo) {
		this.payMemo = payMemo;
	}

	public Date getModifyTime() {

		modifyTime = DateUtils.parseDate(sMtime);

		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		if (null != startTime) {
			this.startTimeStr = DateUtils.formatDateTime(startTime);
		}
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		if (null != endTime) {
			this.endTimeStr = DateUtils.formatDateTime(endTime);
		}
		this.endTime = endTime;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getsAtime() {
		return sAtime;
	}

	public void setsAtime(String sAtime) {
		this.sAtime = sAtime;
	}

	public String getsMtime() {
		return sMtime;
	}

	public void setsMtime(String sMtime) {
		this.sMtime = sMtime;
	}

	public String getsCmpy() {
		return sCmpy;
	}

	public void setsCmpy(String sCmpy) {
		this.sCmpy = sCmpy;
	}

	@ExcelField(title = "收件公司/个人", align = 2, sort = 80)
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	@ExcelField(title = "销售名称", align = 2, sort = 100)
	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	@ExcelField(title = "订购数量", align = 2, sort = 50)
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductTotalFee() {

		return productTotalFee;
	}

	public void setProductTotalFee(String productTotalFee) {

//		BigDecimal bd = new BigDecimal(productTotalFee);
//
//		this.productTotalFee = bd.divide(divisor, 2, BigDecimal.ROUND_HALF_UP)
//				.toPlainString();
		
		this.productTotalFee = productTotalFee;
	}

	public Long getShopid() {
		return shopid;
	}

	public void setShopid(Long shopid) {
		this.shopid = shopid;
	}

	@ExcelField(title = "收件人", align = 2, sort = 70)
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getMarketMoble() {
		return marketMoble;
	}

	public void setMarketMoble(String marketMoble) {
		this.marketMoble = marketMoble;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	@ExcelField(title = "销售区域", align = 2, sort = 170)
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getHmsuserid() {
		return hmsuserid;
	}

	public void setHmsuserid(String hmsuserid) {
		this.hmsuserid = hmsuserid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHmsid() {
		return hmsid;
	}

	public void setHmsid(String hmsid) {
		this.hmsid = hmsid;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}

	public String getComFee() {
		String str = "";

		if (null != comtotoleFee && null != this.num) {
			BigDecimal bd = new BigDecimal(comtotoleFee);
			BigDecimal b2 = new BigDecimal(this.num);
			str = bd.divide(b2, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
		} 
		
		return str;
	}

	public void setComFee(String comFee) {
		this.comFee = comFee;
	}

	public String getComtotoleFee() {
		return comtotoleFee;
	}

	public void setComtotoleFee(String comtotoleFee) {
		
		if (null == comtotoleFee) {
			return;
		}
		
		BigDecimal bd = new BigDecimal(comtotoleFee);
		this.comtotoleFee = bd.divide(divisor, 2, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
		
	}

	@ExcelField(title = "商品名称", align = 2, sort = 40)
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public Date getStartPayTime() {
		return startPayTime;
	}

	public void setStartPayTime(Date startPayTime) {
		this.startPayTime = startPayTime;
	}

	public Date getEndPayTime() {
		return endPayTime;
	}

	public void setEndPayTime(Date endPayTime) {
		this.endPayTime = endPayTime;
	}

	public String getStartPayTimeStr() {
		if (null != startPayTime) {
			return DateUtils.formatDateTime(startPayTime);
		}
		return startPayTimeStr;
	}

	public String getEndPayTimeStr() {
		if (null != endPayTime) {
			return DateUtils.formatDateTime(endPayTime);
		}

		return endPayTimeStr;
	}

	public String getConsignTime() {
		return consignTime;
	}

	public void setConsignTime(String consignTime) {
		this.consignTime = consignTime;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getInvoiceTime() {
		return invoiceTime;
	}

	public void setInvoiceTime(String invoiceTime) {
		this.invoiceTime = invoiceTime;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		if (invoiceDate != null) {
			this.invoiceTime = DateUtils.formatDate(invoiceDate);
		}
		this.invoiceDate = invoiceDate;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public Set<String> getStatusIn() {
		if (status != null && status != "") {
			Set<String> set = null;
			String[] strs = status.split(",");
			for (String string : strs) {
				if (set == null) {
					set = new HashSet<String>();
				}
				if (string != null && string != "") {
					set.add(string);
				}
			}
			return set;
		}
		return null;

	}

	public void setStatusIn(Set<String> statusIn) {
		this.statusIn = statusIn;
	}

	public String getStartUpdateTime() {
		return startUpdateTime;
	}

	public void setStartUpdateTime(String startUpdateTime) {
		this.startUpdateTime = startUpdateTime;
	}

	public String getEndUpdateTime() {
		return endUpdateTime;
	}

	public void setEndUpdateTime(String endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}

	public String getControlNo() {
		return controlNo;
	}

	public void setControlNo(String controlNo) {
		this.controlNo = controlNo;
	}

	public String getStartConsignTime() {
		return startConsignTime;
	}

	public void setStartConsignTime(String startConsignTime) {
		this.startConsignTime = startConsignTime;
	}

	public String getEndConsignTime() {
		return endConsignTime;
	}

	public void setEndConsignTime(String endConsignTime) {
		this.endConsignTime = endConsignTime;
	}

	public String getStartInvoiceTime() {
		return startInvoiceTime;
	}

	public void setStartInvoiceTime(String startInvoiceTime) {
		this.startInvoiceTime = startInvoiceTime;
	}

	public String getEndInvoiceTime() {
		return endInvoiceTime;
	}

	public void setEndInvoiceTime(String endInvoiceTime) {
		this.endInvoiceTime = endInvoiceTime;
	}

	@ExcelField(title = "重量（kg）", align = 2, sort = 60)
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@ExcelField(title = "签收状态", align = 2, sort = 230)
	public String getQianShouStatus() {
		return qianShouStatus;
	}

	public void setQianShouStatus(String qianShouStatus) {
		this.qianShouStatus = qianShouStatus;
	}

	public String getExpBatch() {
		return expBatch;
	}

	public void setExpBatch(String expBatch) {
		this.expBatch = expBatch;
	}

	@ExcelField(title = "商品编号", align = 2, sort = 30)
	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	@ExcelField(title = "直配送厂家", align = 2, sort = 180)
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@ExcelField(title = "配送公司", align = 2, sort = 190)
	public String getPeiSongCmpy() {
		return peiSongCmpy;
	}

	public void setPeiSongCmpy(String peiSongCmpy) {
		this.peiSongCmpy = peiSongCmpy;
	}

	@ExcelField(title = "发件地", align = 2, sort = 160)
	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@ExcelField(title = "提货仓库", align = 2, sort = 190)
	public String getTiHuoCangku() {
		return tiHuoCangku;
	}

	public void setTiHuoCangku(String tiHuoCangku) {
		this.tiHuoCangku = tiHuoCangku;
	}

	public String getExpBatchDay() {
		return expBatchDay;
	}

	public void setExpBatchDay(String expBatchDay) {
		this.expBatchDay = expBatchDay;
	}

	public Integer getLogisticsStatus() {
		return logisticsStatus;
	}

	public void setLogisticsStatus(Integer logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}

	@ExcelField(title = "我方运单号", align = 2, sort = 210)
	public Integer getYundanCode() {
		return yundanCode;
	}

	public void setYundanCode(Integer yundanCode) {
		this.yundanCode = yundanCode;
	}

	@ExcelField(title = "物流运单号", align = 2, sort = 220)
	public String getWuliuCode() {
		return wuliuCode;
	}

	public void setWuliuCode(String wuliuCode) {
		this.wuliuCode = wuliuCode;
	}

}