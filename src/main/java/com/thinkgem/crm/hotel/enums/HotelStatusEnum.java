package com.thinkgem.crm.hotel.enums;

/**
 * Created by duanwc on 16/3/17.
 */
public enum HotelStatusEnum {

    WAIT_INSTALL(-1,"待安装"),
    WAIT_PERFECT(0,"待完善"),
    FIRST_AUDIT(3,"初次审核"),
    PASS_AUDIT(4,"审核通过"),
    MODIFY_AUDIT(5,"修改审核");

    private final Integer key;
    private final String value;

    private HotelStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 根据状态返回名称
     * @param status
     * @return
     */
    public static String getName(int status){
        String name = null;
        switch (status){
            case -1:{
                name = WAIT_INSTALL.value;
                break;
            }
            case 0:{
                name = WAIT_PERFECT.value;
                break;
            }
            case 3:{
                name = FIRST_AUDIT.value;
                break;
            }
            case 4:{
                name = PASS_AUDIT.value;
                break;
            }
            case 5:{
                name = MODIFY_AUDIT.value;
                break;
            }
            default:{
                name = "未知状态";
            }
        }
        return name;
    }
}
