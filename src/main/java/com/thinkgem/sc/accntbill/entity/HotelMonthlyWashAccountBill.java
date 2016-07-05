package com.thinkgem.sc.accntbill.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class HotelMonthlyWashAccountBill extends SettlementAccntBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    @ExcelField(title="开始日期", type=1, align=2, sort=10)
    public Date getBeginDate() {
        return super.getBeginDate();
    }

    @Override
    @ExcelField(title="结束日期", type=1, align=2, sort=20)
    public Date getEndDate() {
        return super.getEndDate();
    }
	
	@Override
    @ExcelField(title="账户ID", type=1, align=2, sort=30)
    public String getAccountid() {
        return super.getAccountid();
    }

    @Override
    @ExcelField(title="酒店ID", type=1, align=2, sort=40)
    public String getHotelid() {
        return super.getHotelid();
    }

    @Override
    @ExcelField(title="酒店名称", type=1, align=2, sort=50)
    public String getHotelname() {
        return super.getHotelname();
    }


    @Override
    @ExcelField(title="订单金额", type=1, align=2, sort=60)
    public String getStrBalance() {
        return super.getStrBalance();
    }
    
    @Override
    @ExcelField(title="折扣率", type=1, align=2, sort=70)
    public String getStrDeposit() {
        return super.getStrDeposit();
    }
    
    @Override
    @ExcelField(title="折扣金额", type=1, align=2, sort=80)
    public String getStrDiscount() {
    	return super.getStrDiscount();
    }
    
    @Override
    @ExcelField(title="账单金额", type=1, align=2, sort=90)
    public String getStrScsum() {
    	return super.getStrScsum();
    }
    
}
