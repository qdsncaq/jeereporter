/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.onoffdetail.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TDistrict;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.psb.onoffdetail.entity.OnOffLineDetail;
import com.thinkgem.psb.onoffdetail.service.OnOffLineDetailService;

/**
 * onoffdetailController
 * @author lm
 * @version 2016-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/onoffdetail/onOffLineDetail")
public class OnOffLineDetailController extends BaseController {

	@Autowired
	private OnOffLineDetailService onOffLineDetailService;
	
	/** 省服务 */
	@Autowired
	private TProvinceService provinceService;
	
	/** 市服务 */
	@Autowired
	private TCityService tCityService;
	
	/** 县区服务 */
	@Autowired
	private TDistrictService districtService;
	
	@ModelAttribute
	public OnOffLineDetail get(@RequestParam(required=false) String id) {
		OnOffLineDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = onOffLineDetailService.get(id);
		}
		if (entity == null){
			entity = new OnOffLineDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("onoffdetail:onOffLineDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(OnOffLineDetail onOffLineDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OnOffLineDetail> page = onOffLineDetailService.findPage(new Page<OnOffLineDetail>(request, response), onOffLineDetail); 
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
				 
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(onOffLineDetail.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(onOffLineDetail.getProcode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
//          List<TCity> cityList = tCityService.getByParent(city);
			Page<TCity> pc=new Page<TCity>();
			pc.setOrderBy(" a.CityName ASC ");
			List<TCity> cityList = tCityService.findPage(pc,city).getList();
			model.addAttribute("cityList", cityList);
		}
//		区县 (暂时去掉)
		if(StringUtils.isNotBlank(onOffLineDetail.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(onOffLineDetail.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		
		model.addAttribute("page", page);
		return "psb/onoffdetail/onOffLineDetailList";
	}
	
	/**
	 * 导出
	 * @param onOffLineDetail
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("onoffdetail:onOffLineDetail:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(OnOffLineDetail onOffLineDetail, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
            String fileName = "酒店上下线记录列表.xlsx";
            List<OnOffLineDetail> list = onOffLineDetailService.findList(onOffLineDetail);
            new ExportExcel("酒店上下线记录", OnOffLineDetail.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "酒店上下线记录excel导出失败！失败信息："+e.getMessage());
        }
		return "redirect:"+Global.getAdminPath()+"/onoffdetail/onOffLineDetail/?repage";
	}

	@RequiresPermissions("onoffdetail:onOffLineDetail:view")
	@RequestMapping(value = "form")
	public String form(OnOffLineDetail onOffLineDetail, Model model) {
		model.addAttribute("onOffLineDetail", onOffLineDetail);
		return "psb/onoffdetail/onOffLineDetailForm";
	}

	@RequiresPermissions("onoffdetail:onOffLineDetail:edit")
	@RequestMapping(value = "save")
	public String save(OnOffLineDetail onOffLineDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, onOffLineDetail)){
			return form(onOffLineDetail, model);
		}
		onOffLineDetailService.save(onOffLineDetail);
		addMessage(redirectAttributes, "保存onoffdetail成功");
		return "redirect:"+Global.getAdminPath()+"/onoffdetail/onOffLineDetail/?repage";
	}
	
	@RequiresPermissions("onoffdetail:onOffLineDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(OnOffLineDetail onOffLineDetail, RedirectAttributes redirectAttributes) {
		onOffLineDetailService.delete(onOffLineDetail);
		addMessage(redirectAttributes, "删除onoffdetail成功");
		return "redirect:"+Global.getAdminPath()+"/onoffdetail/onOffLineDetail/?repage";
	}

}