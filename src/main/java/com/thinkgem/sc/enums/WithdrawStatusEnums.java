package com.thinkgem.sc.enums;

public enum WithdrawStatusEnums {
    ACCSUBMIT(1, "提交申请"),
    CHECK(2, "财务确认"),
    PLAYMONEY(3, "财务打款"),
    CANCEL(4, "财务驳回"),
    OPERATOR_CONFIRM(10, "运营确认"),
    OPERATOR_CANCEL(11, "运营驳回"),
    HR_CONFIRM(12, "人事确认"),
    HR_CANCEL(13, "人事驳回"),
    ;

    private int index;
    
    private String desc;

    private WithdrawStatusEnums(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }
    
    @Override
    public String toString() {
        return this.index + "_" + this.desc;
    }

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }
    
    public static String getDesc(int index) {
        
        for(WithdrawStatusEnums e : values()){
            
            if(e.index == index) {
                
                return e.desc;
            }
        }
        
        return null;
    }
}
