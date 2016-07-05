package com.thinkgem.crm.hotel.enums;

public enum RfCrmHotelPublicLogRemarkEnum {
	LEADER("1", "领导分配"),
	ONESELF("2", "主动认领"), 
	ONESELFRETURN("3", "主动退回"),
	SYSTEMRETURN("4", "系统退回"),
	BOSSCONFIRM("5", "老板确认"),
	TRANSFER("6", "私海转移"),
	ASSIGN("7", "公海分配销售");
	
	private String value;
	private String text;
	
	private RfCrmHotelPublicLogRemarkEnum(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
