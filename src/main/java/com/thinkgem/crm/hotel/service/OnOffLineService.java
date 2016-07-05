/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.crm.hotel.entity.HotelRate;
import com.thinkgem.crm.user.dao.RfCrmUserAreaDao;
import com.thinkgem.crm.utils.HttpClientUtils;
import com.thinkgem.jeesite.common.annotation.DataSourceName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.hotel.entity.OnOffLine;
import com.thinkgem.crm.hotel.dao.OnOffLineDao;
import com.thinkgem.crm.location.entity.TProvince;

/**
 * 酒店上下线信息统计Service
 * @author 段文昌
 * @version 2016-04-21
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class OnOffLineService extends CrudService<OnOffLineDao, OnOffLine> {
	
	@Autowired
	private RfCrmUserAreaDao rfCrmUserAreaDao;
	
	@Value("${congqipms.service.host}")
	private String pmsServiceHost;

	public OnOffLine get(String id) {
		return super.get(id);
	}
	
	public List<OnOffLine> findList(OnOffLine onOffLine) {
		return super.findList(onOffLine);
	}
	
	public Page<OnOffLine> findPage(Page<OnOffLine> page, OnOffLine onOffLine) {
		page.setOrderBy(" firstTime desc");
		return super.findPage(page, onOffLine);
	}

	public Page<OnOffLine> findDetailsPage(Page<OnOffLine> page, OnOffLine onOffLine) {
		page.setOrderBy(" onOffTime");
		onOffLine.setPage(page);
		page.setList(dao.findDetailsList(onOffLine));
		return page;
	}

	public Page<HotelRate> findHotelRateList(Page<HotelRate> page, HotelRate hotelRate) {
		String httpUrl = pmsServiceHost + "/sy/base/view/KF_PMSC_EFFECTIVEHOTEL_EXT.query.do";
		Map<String,String> paramMap = new HashMap<String,String>();
		String entityStr = null;
		try {
			paramMap.put("HOTEL_ID", hotelRate.getHOTEL_ID());
			paramMap.put("data", "{\"_PAGE_\":{\"NOWPAGE\":"+page.getPageNo()+"}}");
			//searchWhere条件存在sql注入,congqipms解决
			String searchWhere = "";
			if(hotelRate.getStartDate() != null){
				searchWhere += " and TIME >="+hotelRate.getStartDate().getTime() +" ";
			}
			if(hotelRate.getEndDate() != null){
				searchWhere += " and TIME <="+hotelRate.getEndDate().getTime()+" ";
			}
			paramMap.put("_searchWhere", searchWhere);
			entityStr = HttpClientUtils.post(httpUrl, paramMap);
			JSONObject resJson = JSON.parseObject(entityStr);
			JSONObject _page = resJson.getJSONObject("_PAGE_");

			page.setPageNo(_page.getIntValue("NOWPAGE"));
			page.setPageSize(_page.getIntValue("SHOWNUM"));
			page.setCount(_page.getLong("ALLNUM"));

			List<HotelRate> list = JSON.parseArray(resJson.getString("_DATA_"),HotelRate.class);
			page.setList(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(OnOffLine onOffLine) {
		super.save(onOffLine);
	}
	
	@Transactional(readOnly = false)
	public void delete(OnOffLine onOffLine) {
		super.delete(onOffLine);
	}
	
	public TProvince queryProvince(String userId){
		return rfCrmUserAreaDao.queryProvince(userId);
	}
}