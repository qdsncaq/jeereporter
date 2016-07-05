package com.thinkgem.jeesite.modules.oms.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticsDirect;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticsGoods;
import com.thinkgem.jeesite.modules.oms.service.OmsLogisticsDirectService;
import com.thinkgem.jeesite.modules.oms.service.OmsLogisticsGoodsService;

/**
 * 物流相关的 工具类 
 * @author ananyuan
 *
 */
public class LogisticUtils {
	
	public static final String LOGISTICS_DIRECT_CACHE = "LOGISTICS_DIRECT_CACHE";
	
	public static final String LOGISTICS_GOODS_CACHE = "LOGISTICS_GOODS_CACHE";
	
	private static final String DISTRICT_ALL = "全国";
	
	
	private static OmsLogisticsDirectService logisticsDirectService = SpringContextHolder.getBean(OmsLogisticsDirectService.class);
	
	private static OmsLogisticsGoodsService logisticsGoodsService = SpringContextHolder.getBean(OmsLogisticsGoodsService.class);
	
	
	/**
	 * 
	 * @param goodsCode 商品编码
	 * @param district 区域
	 * @return 
	 */
	public static OmsLogisticsDirect getLogisticsDirect(String goodsCode, String district) {
		OmsLogisticsDirect direct = (OmsLogisticsDirect)CacheUtils.get(LOGISTICS_DIRECT_CACHE, goodsCode + "_" + district);
		if (direct ==  null) {
			OmsLogisticsDirect omsLogisticsDirect = new OmsLogisticsDirect();
			omsLogisticsDirect.setCommodityCode(goodsCode);
			omsLogisticsDirect.setDistinctCode(district);
			
			List<OmsLogisticsDirect> list = logisticsDirectService.findList(omsLogisticsDirect);
			if (list.size() > 0) {
				direct = list.get(0);
			}
			
			CacheUtils.put(LOGISTICS_DIRECT_CACHE, goodsCode + "_" + district, direct);
		}
		
		
		if (null == direct) { //没查到指定省份的， 查全国的
			OmsLogisticsDirect directAll = (OmsLogisticsDirect)CacheUtils.get(LOGISTICS_DIRECT_CACHE, goodsCode + "_" + DISTRICT_ALL);
			
			if (null != directAll) {
				return directAll;
			}
			
			OmsLogisticsDirect omsLogisticsDirect = new OmsLogisticsDirect();
			omsLogisticsDirect.setCommodityCode(goodsCode);
			omsLogisticsDirect.setDistinctCode(DISTRICT_ALL); 
			
			List<OmsLogisticsDirect> list = logisticsDirectService.findList(omsLogisticsDirect);
			if (list.size() > 0) {
				direct = list.get(0);
			}
			
			CacheUtils.put(LOGISTICS_DIRECT_CACHE, goodsCode + "_" + DISTRICT_ALL, direct);			
		}
		
		return direct;
	}
	
	
	/**
	 * 
	 * @param goodsCode 商品编码
	 * @param district 区域
	 * @return 
	 */
	public static OmsLogisticsGoods getLogisticsGoods(String goodsCode, String district) {
		OmsLogisticsGoods good = (OmsLogisticsGoods)CacheUtils.get(LOGISTICS_GOODS_CACHE, goodsCode + "_" + district);
		if (good ==  null){
			OmsLogisticsGoods omsLogisticsGood = new OmsLogisticsGoods();
			omsLogisticsGood.setCommodityCode(goodsCode);
			omsLogisticsGood.setDistinctCode(district);
			
			List<OmsLogisticsGoods> list = logisticsGoodsService.findList(omsLogisticsGood);
			if (list.size() > 0) {
				good = list.get(0);
			}
			
			CacheUtils.put(LOGISTICS_GOODS_CACHE, goodsCode + "_" + district, good);
		}
		
		return good;
	}
}
