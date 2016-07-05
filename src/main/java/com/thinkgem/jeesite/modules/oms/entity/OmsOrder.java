/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * OMS订单Entity
 * @author jiajianhong
 * @version 2016-03-17
 */
public class OmsOrder extends DataEntity<OmsOrder> {
	
	private static final long serialVersionUID = 1L;
	private Long orderId;		// 订单id
	private String sAtime;		// 创建时间
    private Date startTime;
    private String startTimeStr;
    private Date endTime;
    private String endTimeStr;
	private String sMtime;		// 修改时间
    private Date modifyTime;
	private String sCmpy;		// 酒店id
    private String hotelName;
	private String status;		// 订单状态
	private String yzOrderid;		// 有赞订单id
	private Long userid;		// 创建订单用户ID  索引
	private String tradeMemo;		// 交易备注
	private String receiverCity;		// 收货城市 
	private String receiverDistrict;		// 区县
	private String receiverState;		// 收货状态
	private String receiverAddress;		// 收货地址
	private String receiverZip;		// 收货邮编
	private String receiverMobile;		// 联系电话
	private String postFee;		// 邮寄费用
	private String totoleFee;		// 总费用
	private String refundedFee;		// 退还金额
	private String discount;		// 折扣
	private String payment;		// 支付费用
	private String title;		// 标题
	private String marketid;		// 销售id
    private String marketName;      // 销售名称
	private String payStatus;		// 支付状态
	private String payMemo;		// 支付备注
    private String num;         // 商品数量
    private String kind;        // 物品品类
    private String productName; // 商品名称
    private String productTotalFee; // 商品总费用
    private Long shopid ;//店铺id
    private String  receiverName ; //收货人姓名
    private String marketMoble;  //销售电话
    private String buyerMessage ;  //买家留言
    private String buyerNick;//买家昵称
    
    private String consignTime; //发货时间 
    private String signTime; //签收时间
    private String remarks; //修改备注
    private String invoiceTime; //预发货时间
    private Date invoiceDate;
    
    // 显示用
    private String shopName ;//店铺名称
    private String hmsuserid;//hms 用户id
    private String userName;//hms 用户名
    private String hmsid;//hms 酒店id
    
    //查询用  防止页面查询时输入 封装报错
    private String searchId;
    
    private Date startPayTime;
    private String startPayTimeStr; 
    private Date endPayTime;
    private String endPayTimeStr;
    
    //修改时间
    private String startUpdateTime; 
    private String endUpdateTime;
    
    //发货日期
    private String startConsignTime; 
    private String endConsignTime;
    
    //预计发货时间
    private String startInvoiceTime;
    private String endInvoiceTime;
    
    private Long receiptid;
    
    private String weight;
    
    private String yunDanSelf;
    
    private String yunDanWuliu;
    
    private String qianShouStatus;

    private String expBatch;
    
    
    private String receiptTypeName;
    
    private String receiptCode;
    
    private String receiptDate;

	private List<OmsOrderItem> omsOrderItemList = Lists.newArrayList();		// 订单详情子表列表

    private BigDecimal divisor = new BigDecimal(100);
	
	public OmsOrder() {
		super();
	}

	public OmsOrder(String id){
		super(id);
	}

	@NotNull(message="订单id不能为空")
	@ExcelField(title="订单id", align=2, sort=10)
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
    @ExcelField(title="创建时间", align=2, sort=30)
	public String getSAtime() {
		return sAtime;
	}

	public void setSAtime(String sAtime) {
		this.sAtime = sAtime;
	}
	
    //@ExcelField(title="修改时间", align=2, sort=100)
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
	
    @ExcelField(title="订单状态", align=2, sort=30, dictType="orderStatus")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
    @ExcelField(title="有赞订单号", align=2, sort=20)
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
	
    @ExcelField(title="收货城市", align=2, sort=130)
	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	
	@ExcelField(title="收货区县", align=2, sort=140)
	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	
	 @ExcelField(title="收货省份", align=2, sort=120)
	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	
    @ExcelField(title="收货地址", align=2, sort=150)
	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
	@ExcelField(title="邮编", align=2, sort=100)
	public String getReceiverZip() {
		return receiverZip;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}
	
    @ExcelField(title="联系电话", align=2, sort=90)
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
	
	@ExcelField(title="订单金额", align=2, sort=70)
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
	
    @ExcelField(title="支付状态", align=2, sort=50, dictType="oms_payStatus")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
    @ExcelField(title="支付时间", align=2, sort=60)
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

    public List<OmsOrderItem> getOmsOrderItemList() {
        return omsOrderItemList;
    }

    public void setOmsOrderItemList(List<OmsOrderItem> omsOrderItemList) {
        this.omsOrderItemList = omsOrderItemList;
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

    @ExcelField(title="酒店名称", align=2, sort=160)
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @ExcelField(title="销售名称", align=2, sort=200)
    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

   // @ExcelField(title="成交数量", align=2, sort=75)
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    //@ExcelField(title="成交品类", align=2, sort=5)
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    //@ExcelField(title="商品名称", align=2, sort=2)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

   // @ExcelField(title="成交金额", align=2, sort=80)
    public String getProductTotalFee() {

        return productTotalFee;
    }

    public void setProductTotalFee(String productTotalFee) {

        BigDecimal bd = new BigDecimal(productTotalFee);

        this.productTotalFee = bd.divide(divisor, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

	public Long getShopid() {
		return shopid;
	}

	public void setShopid(Long shopid) {
		this.shopid = shopid;
	}

	@ExcelField(title="收货人", align=2, sort=90)
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	 @ExcelField(title="销售电话", align=2, sort=210)
	public String getMarketMoble() {
		return marketMoble;
	}

	public void setMarketMoble(String marketMoble) {
		this.marketMoble = marketMoble;
	}

	 @ExcelField(title="用户留言", align=2, sort=110)
	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	@ExcelField(title="商品名称", align=2, sort=220)
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	 @ExcelField(title="用户id", align=2, sort=180)
	public String getHmsuserid() {
		return hmsuserid;
	}

	public void setHmsuserid(String hmsuserid) {
		this.hmsuserid = hmsuserid;
	}

	 @ExcelField(title="用户名称", align=2, sort=190)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ExcelField(title="酒店id", align=2, sort=160)
	public String getHmsid() {
		return hmsid;
	}

	public void setHmsid(String hmsid) {
		this.hmsid = hmsid;
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
			  return  DateUtils.formatDateTime(startPayTime);
	        }
		return startPayTimeStr;
	}

	

	public String getEndPayTimeStr() {
		  if (null != endPayTime) {
			  return  DateUtils.formatDateTime(endPayTime);
	        }
		
		
		return endPayTimeStr;
	}
    
    
	@ExcelField(title="发货时间", align=2, sort=240)
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

	@ExcelField(title="预发货时间", align=2, sort=260)
	public String getInvoiceTime() {
		return invoiceTime;
	}

	public void setInvoiceTime(String invoiceTime) {
		if(invoiceTime!=null&&!"".equals(invoiceTime)){
			 this.invoiceDate=DateUtils.parseDate(invoiceTime);
		}
		this.invoiceTime = invoiceTime;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		if(invoiceDate!=null){
			 this.invoiceTime=DateUtils.formatDate(invoiceDate);
		}
		this.invoiceDate = invoiceDate;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
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

    public Long getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(Long receiptid) {
        this.receiptid = receiptid;
    }
    
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getYunDanSelf() {
		return yunDanSelf;
	}

	public void setYunDanSelf(String yunDanSelf) {
		this.yunDanSelf = yunDanSelf;
	}

	public String getYunDanWuliu() {
		return yunDanWuliu;
	}

	public void setYunDanWuliu(String yunDanWuliu) {
		this.yunDanWuliu = yunDanWuliu;
	}

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

	public String getReceiptTypeName() {
		return receiptTypeName;
	}

	public void setReceiptTypeName(String receiptTypeName) {
		this.receiptTypeName = receiptTypeName;
	}

	public String getReceiptCode() {
		return receiptCode;
	}

	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
}