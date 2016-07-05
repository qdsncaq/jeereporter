/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.invoice.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 发票票据管理Entity
 * @author aiqing.chu@fangbaba.com
 * @version 2016-05-06
 */
public class OmsReceipt extends DataEntity<OmsReceipt> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// CODE
	private BigDecimal money;		// 金额
	private String rtype;		// 票据类型
	private String rtypename;		// 类型名称
	private String title;		// 票据抬头
	private String identiid;		// 纳税人号
	private String content;		// 票据内容
	private String receiptcode;		// 票据号码
	private String receiptdate;		// 开票日期
	private Date atime;		// 添加时间
	private String postchajia;		// 票据邮寄费
	private Integer isopen;    // 发票状态
	
	public OmsReceipt() {
		super();
	}

	public OmsReceipt(String id){
		super(id);
	}
	
	@ExcelField(title="主键", type=1, align=3, sort=0)
	@Override
    public String getId() {
        return super.getId();
    }

    @ExcelField(title="票据ID", type=1, align=2, sort=10)
	@Length(min=0, max=50, message="CODE长度必须介于 0 和 50 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@ExcelField(title="金额(元)", type=1, align=3, sort=20, dataFormat="0.00")
	@Length(min=0, max=11, message="金额长度必须介于 0 和 11 之间")
	public BigDecimal getMoney() {
		return money.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	@Length(min=0, max=50, message="票据类型长度必须介于 0 和 50 之间")
	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	
	@ExcelField(title="票据类型", type=1, align=2, sort=30)
	@Length(min=0, max=50, message="类型名称长度必须介于 0 和 50 之间")
	public String getRtypename() {
		return rtypename;
	}

	public void setRtypename(String rtypename) {
		this.rtypename = rtypename;
	}
	
	@ExcelField(title="票据抬头", type=1, align=1, sort=40)
	@Length(min=0, max=200, message="票据抬头长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ExcelField(title="纳税人号", type=1, align=1, sort=50)
	@Length(min=0, max=200, message="纳税人号长度必须介于 0 和 200 之间")
	public String getIdentiid() {
		return identiid;
	}

	public void setIdentiid(String identiid) {
		this.identiid = identiid;
	}
	
	@ExcelField(title="发票内容", type=1, align=1, sort=60)
	@Length(min=0, max=2000, message="票据内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@ExcelField(title="发票号码", type=1, align=1, sort=70)
	@Length(min=0, max=2000, message="票据号码长度必须介于 0 和 2000 之间")
	public String getReceiptcode() {
		return receiptcode;
	}

	public void setReceiptcode(String receiptcode) {
		this.receiptcode = receiptcode;
	}
	
	@ExcelField(title="开票日期", type=1, align=2, sort=80)
	@Length(min=0, max=23, message="开票日期长度必须介于 0 和 23 之间")
	public String getReceiptdate() {
		return receiptdate;
	}

	public void setReceiptdate(String receiptdate) {
		this.receiptdate = receiptdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAtime() {
		return atime;
	}

	public void setAtime(Date atime) {
		this.atime = atime;
	}
	
	@Length(min=0, max=11, message="票据邮寄费长度必须介于 0 和 11 之间")
	public String getPostchajia() {
		return postchajia;
	}

	public void setPostchajia(String postchajia) {
		this.postchajia = postchajia;
	}

    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }
	
}