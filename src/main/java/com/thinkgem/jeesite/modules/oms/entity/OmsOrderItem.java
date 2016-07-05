/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

/**
 * oms订单详情Entity
 * @author jiajianhong
 * @version 2016-03-18
 */
public class OmsOrderItem extends DataEntity<OmsOrderItem> {
	
	private static final long serialVersionUID = 1L;
    private Long selfId;		// 自增id主键
	private String sAtime;		// 订单创建时间
	private String sMtime;		// 订单修改时间
	private String sCmpy;		// 酒店id
	private OmsOrder orderid;		// 订单id 父类
	private String skuid;		// 有赞物品id
	private String num;		// 物品数量
	private String title;		// 物品名称
	private String totoleFee;		// 商品总费用
	private String discount;		// 折扣
	private String payment;		// 支付费用
	
	private String invoiceTime; //预发货时间
	private Date invoiceDate;
	
	private String remarks; //修改备注
	
	private int yundanBegin;
	
	private int yundanEnd;
	
	private String yundanWuliu;
	
	private double weight;
	
	private String wuliuCmpy;

    private BigDecimal divisor = new BigDecimal(100);
	
	public OmsOrderItem() {
		super();
	}

	public OmsOrderItem(String id){
		super(id);
	}

    public Long getSelfId() {
        return selfId;
    }

    public void setSelfId(Long selfId) {
        this.selfId = selfId;
    }

    public OmsOrderItem(OmsOrder orderid){
		this.orderid = orderid;
	}

	@Length(min=0, max=40, message="订单创建时间长度必须介于 0 和 40 之间")
	public String getSAtime() {
		return sAtime;
	}

	public void setSAtime(String sAtime) {
		this.sAtime = sAtime;
	}
	
	@Length(min=0, max=40, message="订单修改时间长度必须介于 0 和 40 之间")
	public String getSMtime() {
		return sMtime;
	}

	public void setSMtime(String sMtime) {
		this.sMtime = sMtime;
	}
	
	@Length(min=0, max=40, message="酒店id长度必须介于 0 和 40 之间")
	public String getSCmpy() {
		return sCmpy;
	}

	public void setSCmpy(String sCmpy) {
		this.sCmpy = sCmpy;
	}
	
	public OmsOrder getOrderid() {
		return orderid;
	}

	public void setOrderid(OmsOrder orderid) {
		this.orderid = orderid;
	}
	
	@Length(min=0, max=40, message="有赞物品id长度必须介于 0 和 40 之间")
	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}
	
	@Length(min=0, max=11, message="物品数量长度必须介于 0 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=200, message="物品名称长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=11, message="商品总费用长度必须介于 0 和 11 之间")
	public String getTotoleFee() {

        BigDecimal bd = new BigDecimal(totoleFee);

        return bd.divide(divisor, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	public void setTotoleFee(String totoleFee) {
		this.totoleFee = totoleFee;
	}
	
	@Length(min=0, max=11, message="折扣长度必须介于 0 和 11 之间")
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	@Length(min=0, max=11, message="支付费用长度必须介于 0 和 11 之间")
	public String getPayment() {

        BigDecimal bd = new BigDecimal(payment);

        return bd.divide(divisor, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	public void setPayment(String payment) {
		this.payment = payment;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getYundanBegin() {
		return yundanBegin;
	}

	public void setYundanBegin(int yundanBegin) {
		this.yundanBegin = yundanBegin;
	}

	public int getYundanEnd() {
		return yundanEnd;
	}

	public void setYundanEnd(int yundanEnd) {
		this.yundanEnd = yundanEnd;
	}

	public String getYundanWuliu() {
		return yundanWuliu;
	}

	public void setYundanWuliu(String yundanWuliu) {
		this.yundanWuliu = yundanWuliu;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getWuliuCmpy() {
		return wuliuCmpy;
	}

	public void setWuliuCmpy(String wuliuCmpy) {
		this.wuliuCmpy = wuliuCmpy;
	}
	
	
	
}