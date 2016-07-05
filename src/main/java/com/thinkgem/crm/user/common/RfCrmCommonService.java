/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.common;

import java.util.List;

import com.thinkgem.crm.user.entity.RfCrmUserRelation;
import com.thinkgem.crm.user.service.RfCrmUserRelationService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;

@Service
public class RfCrmCommonService{
	 
	/** 省服务 */
	@Autowired
	private TProvinceService provinceService;
	
	/** 市服务 */
	@Autowired
	private TCityService cityService;
	
	/** 县区服务 */
	@Autowired
	private TDistrictService districtService;

    @Autowired
    private RfCrmUserRelationService rfCrmUserRelationService;
	
	/**
	 * 获取省份+城市
	 * @param provCode
	 * @param model
	 */
	public void getProvAndCity(String provCode, Model model) {
		//省
		List<TProvince> provinceList = provinceService.findList(new TProvince());
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(provCode)){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(provCode);
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
			List<TCity> cityList = cityService.getByParent(city);
			model.addAttribute("cityList", cityList);
		}

        // 获取区总的数据
        RfCrmUserRelation rfCrmUserRelationQuery = new RfCrmUserRelation();
        rfCrmUserRelationQuery.setLevel(DictUtils.getDictValue("区总", "area_level", "区总"));
        List<RfCrmUserRelation> qzList = rfCrmUserRelationService.findList(rfCrmUserRelationQuery);
        model.addAttribute("qzList", qzList);

        // 获取区总的数据
        rfCrmUserRelationQuery.setLevel(DictUtils.getDictValue("区经", "area_level", "区经"));
        List<RfCrmUserRelation> qjList = rfCrmUserRelationService.findList(rfCrmUserRelationQuery);
        model.addAttribute("qjList", qjList);
	}
}