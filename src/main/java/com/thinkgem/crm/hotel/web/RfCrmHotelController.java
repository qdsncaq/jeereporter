/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TDistrict;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.crm.hotel.entity.RfCrmHotel;
import com.thinkgem.crm.hotel.service.RfCrmHotelService;

import java.util.List;

/**
 * CRM酒店公私海信息Controller
 * @author 段文昌
 * @version 2016-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/hotel/rfCrmHotel")
public class RfCrmHotelController extends BaseController {

	@Autowired
	private RfCrmHotelService rfCrmHotelService;

	/** 省服务 */
	@Autowired
	private TProvinceService provinceService;
	/** 市服务 */
	@Autowired
	private TCityService cityService;
	/** 县区服务 */
	@Autowired
	private TDistrictService districtService;
	
	@ModelAttribute
	public RfCrmHotel get(@RequestParam(required=false) String id) {
		RfCrmHotel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmHotelService.get(id);
		}
		if (entity == null){
			entity = new RfCrmHotel();
		}
		return entity;
	}
	
	@RequiresPermissions("hotel:rfCrmHotel:view")
	@RequestMapping(value = {"list", ""})
	public String list(RfCrmHotel rfCrmHotel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmHotel> page = rfCrmHotelService.findPage(new Page<RfCrmHotel>(request, response), rfCrmHotel); 
		model.addAttribute("page", page);

		//省
		List<TProvince> provinceList = provinceService.findList(new TProvince());
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(rfCrmHotel.getProvCode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(rfCrmHotel.getProvCode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
			List<TCity> cityList = cityService.getByParent(city);
			model.addAttribute("cityList", cityList);
		}
		// 区县 (暂时去掉)
		if(StringUtils.isNotBlank(rfCrmHotel.getCityCode())){
			//查询城市ID(前台变相可传)
			TCity city = cityService.getByCode(rfCrmHotel.getCityCode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		return "crm/hotel/rfCrmHotelList";
	}

	@RequiresPermissions("hotel:rfCrmHotel:view")
	@RequestMapping(value = "form")
	public String form(RfCrmHotel rfCrmHotel, Model model) {
		model.addAttribute("rfCrmHotel", rfCrmHotel);
		return "crm/hotel/rfCrmHotelForm";
	}

	@RequiresPermissions("hotel:rfCrmHotel:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmHotel rfCrmHotel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rfCrmHotel)){
			return form(rfCrmHotel, model);
		}
		rfCrmHotelService.save(rfCrmHotel);
		addMessage(redirectAttributes, "保存酒店成功");
		return "redirect:"+Global.getAdminPath()+"/crm/hotel/rfCrmHotel/?repage";
	}
	
	@RequiresPermissions("hotel:rfCrmHotel:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmHotel rfCrmHotel, RedirectAttributes redirectAttributes) {
		rfCrmHotelService.delete(rfCrmHotel);
		addMessage(redirectAttributes, "删除酒店成功");
		return "redirect:"+Global.getAdminPath()+"/crm/hotel/rfCrmHotel/?repage";
	}

	/**
	 * 导出酒店数据
	 * @param rfCrmHotel 酒店记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("hotel:rfCrmHotel:edit")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(RfCrmHotel rfCrmHotel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "酒店数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			List<RfCrmHotel> list = rfCrmHotelService.findList(rfCrmHotel);

			new ExportExcel("酒店记录", RfCrmHotel.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出酒店记录失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/crm/hotel/rfCrmHotel/list?repage";
	}


	/**
	 * 导出酒店数据
	 * @param rfCrmHotel 酒店记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("hotel:rfCrmHotel:edit")
	@RequestMapping(value = "exportAll", method= RequestMethod.POST)
	public String exportFileAll(RfCrmHotel rfCrmHotel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "酒店数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			List<RfCrmHotel> list = rfCrmHotelService.findListAll(rfCrmHotel);

			new ExportExcel("酒店记录", RfCrmHotel.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出酒店记录失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/crm/hotel/rfCrmHotel/list?repage";
	}

}