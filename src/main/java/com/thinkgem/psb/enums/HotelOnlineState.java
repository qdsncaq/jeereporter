package com.thinkgem.psb.enums;

public enum HotelOnlineState {
	ONLINE(1, "在线"),
	OFFLINE(2, "离线"),;

    private int index;
    
    private String desc;

    private HotelOnlineState(int index, String desc) {
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
    
    public static String getDesc(Integer index) {
        if(index!=null){
        	for(HotelOnlineState e : values()){
                
                if(e.index == index) {
                    
                    return e.desc;
                }
            }
        }
        return null;
    }
}
