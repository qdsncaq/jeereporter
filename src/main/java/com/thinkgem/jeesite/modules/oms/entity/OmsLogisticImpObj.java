package com.thinkgem.jeesite.modules.oms.entity;

/**
 * 订单id --- oms_order.ID 匹配 订单号 --- oms_order.YZ_ORDERID eg.
 * E20160325151215009869520 匹配 我方运单号 --- 100001开始自增 匹配 物流运单号 --- 添加字段， 导入 直配送厂家
 * --- 需要做匹配 配送公司 --- 签收状态(出库、签收、拒收、退货) 添加字段， 导入
 */
public class OmsLogisticImpObj {
	private Long orderId; // 订单id
	
	private Long itemId;

	private String yzOrderid; // 有赞订单id

	private String yunDanSelf;

	private String yunDanWuliu;

	private String vendorName;

	/**
	 * 配送公司
	 */
	private String peiSongCmpy;

	private String qianShouStatus;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getYzOrderid() {
		return yzOrderid;
	}

	public void setYzOrderid(String yzOrderid) {
		this.yzOrderid = yzOrderid;
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

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPeiSongCmpy() {
		return peiSongCmpy;
	}

	public void setPeiSongCmpy(String peiSongCmpy) {
		this.peiSongCmpy = peiSongCmpy;
	}

	public String getQianShouStatus() {
		return qianShouStatus;
	}

	public void setQianShouStatus(String qianShouStatus) {
		this.qianShouStatus = qianShouStatus;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
}
