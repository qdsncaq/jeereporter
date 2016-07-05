/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * oms商品类型Entity
 * @author yaoxiang
 * @version 2016-03-29
 */
public class OmsCommodityColumn extends DataEntity<OmsCommodityColumn> {
	
	private static final long serialVersionUID = 1L;
	private Long promotionCid;		// 类型编码
	private String name;		// 名称
	private Long shopid;		// 商铺
	
	public OmsCommodityColumn() {
		super();
	}

	public OmsCommodityColumn(String id){
		super(id);
	}

	@NotNull(message="类型编码不能为空")
	public Long getPromotionCid() {
		return promotionCid;
	}

	public void setPromotionCid(Long promotionCid) {
		this.promotionCid = promotionCid;
	}
	
	@Length(min=0, max=50, message="名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getShopid() {
		return shopid;
	}

	public void setShopid(Long shopid) {
		this.shopid = shopid;
	}
	
}