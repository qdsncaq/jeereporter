package com.thinkgem.sc.accntbill.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class HotelWeeklyAccountBill extends SettlementAccntBill {

    /**
     * 
     */
    private static final long serialVersionUID = -2369161220197980500L;

    @Override
    @ExcelField(title="账户ID", type=1, align=1, sort=10)
    public String getAccountid() {
        return super.getAccountid();
    }

    @Override
    @ExcelField(title="酒店ID", type=1, align=1, sort=20)
    public String getHotelid() {
        return super.getHotelid();
    }

    @Override
    @ExcelField(title="酒店名称", type=1, align=1, sort=30)
    public String getHotelname() {
        return super.getHotelname();
    }

    @Override
    @ExcelField(title="开始日期", type=1, align=2, sort=40)
    public Date getBeginDate() {
        return super.getBeginDate();
    }

    @Override
    @ExcelField(title="结束日期", type=1, align=2, sort=50)
    public Date getEndDate() {
        return super.getEndDate();
    }
    
    @Override
    @ExcelField(title="类型", type=1, align=2, sort=50)
    public String getStrbiztype() {
        return super.getStrbiztype();
    }

    @Override
    @ExcelField(title="金额", type=1, align=3, sort=60)
    public String getStrBalance() {
        return super.getStrBalance();
    }
    
}
