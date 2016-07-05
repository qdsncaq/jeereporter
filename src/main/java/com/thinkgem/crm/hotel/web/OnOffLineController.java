/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.crm.hotel.entity.HotelRate;
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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.crm.hotel.entity.OnOffLine;
import com.thinkgem.crm.hotel.service.OnOffLineService;

import java.util.ArrayList;
import java.util.List;

/**
 * 酒店上下线信息统计Controller
 * @author 段文昌
 * @version 2016-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/hotel/onOffLine")
public class OnOffLineController extends BaseController {

	@Autowired
	private OnOffLineService onOffLineService;

	/** 省服务 */
	@Autowired
	private TProvinceService provinceService;
	/** 市服务 */
	@Autowired
	private TCityService cityService;
	/** 县区服务 */
	@Autowired
	private TDistrictService districtService;
	
	private static final String NOTNATION = "0";
	
	
	@ModelAttribute
	public OnOffLine get(@RequestParam(required=false) String id) {
		OnOffLine entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = onOffLineService.get(id);
		}
		if (entity == null){
			entity = new OnOffLine();
		}
		return entity;
	}
	
	@RequiresPermissions("hotel:onOffLine:view")
	@RequestMapping(value = {"list", ""})
	public String list(OnOffLine onOffLine, HttpServletRequest request, HttpServletResponse response, Model model) {
		onOffLine.setPsbType(201);
		if(logger.isDebugEnabled()){
			logger.debug(onOffLine.toString());
		}
		
		//查询当前用户的配置信息，确定用户可见数据范围
		User user = UserUtils.getUser();
		if(user==null){
			logger.error("用户信息为空");
			return "crm/hotel/onOffLineList";
		}
		//省
		List<TProvince> provinceList = null;
		//查询销售对应的省
		TProvince defaultProvince = onOffLineService.queryProvince(user.getLoginName());
		//需要按照省进行限制的用户对省进行赋值
		if(defaultProvince!=null && defaultProvince.getNation().equals(NOTNATION)){
			onOffLine.setProvCode(defaultProvince.getCode());
			provinceList = new ArrayList<TProvince>();
			provinceList.add(defaultProvince);
		}else{
			provinceList = provinceService.findList(new TProvince());
		}
		model.addAttribute("provinceList",provinceList);
		
//		if(StringUtils.isBlank(onOffLine.getProvCode())){
//			//查询当前用户的配置信息，确定用户可见数据范围
//			User user = UserUtils.getUser();
//			if(user==null){
//				logger.error("用户信息为空");
//				return "crm/hotel/onOffLineList";
//			}
//			//查询销售对应的省
//			TProvince province = onOffLineService.queryProvince(user.getLoginName());
//			//需要按照省进行限制的用户对省进行赋值
//			if(province!=null && province.getNation().equals(NOTNATION)){
//				onOffLine.setProvCode(province.getCode());
//				provinceList = new ArrayList<TProvince>();
//				provinceList.add(province);
//				model.addAttribute("provinceList",provinceList);
//			}else{
//				provinceList = provinceService.findList(new TProvince());
//			}
//		}else{
//			provinceList = provinceService.findList(new TProvince());
//		}
		model.addAttribute("provinceList",provinceList);
		Page<OnOffLine> page = onOffLineService.findPage(new Page<OnOffLine>(request, response), onOffLine); 
		model.addAttribute("page", page);
		//市
		if(StringUtils.isNotBlank(onOffLine.getProvCode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(onOffLine.getProvCode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
			List<TCity> cityList = cityService.getByParent(city);
			model.addAttribute("cityList", cityList);
		}
//		 区县 (暂时去掉)
		if(StringUtils.isNotBlank(onOffLine.getCityCode())){
			//查询城市ID(前台变相可传)
			TCity city = cityService.getByCode(onOffLine.getCityCode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		return "crm/hotel/onOffLineList";
	}

	@RequiresPermissions("hotel:onOffLine:view")
	@RequestMapping(value = "details")
	public String details(OnOffLine onOffLine, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OnOffLine> page = onOffLineService.findDetailsPage(new Page<OnOffLine>(request, response), onOffLine);
		model.addAttribute("page", page);
		return "crm/hotel/onOffLineDetails";
	}

	@RequiresPermissions("hotel:onOffLine:view")
	@RequestMapping(value = "form")
	public String form(OnOffLine onOffLine, Model model) {
		model.addAttribute("onOffLine", onOffLine);
		return "crm/hotel/onOffLineForm";
	}

	@RequiresPermissions("hotel:onOffLine:edit")
	@RequestMapping(value = "save")
	public String save(OnOffLine onOffLine, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, onOffLine)){
			return form(onOffLine, model);
		}
		onOffLineService.save(onOffLine);
		addMessage(redirectAttributes, "保存信息成功");
		return "redirect:"+Global.getAdminPath()+"/crm/hotel/onOffLine/?repage";
	}
	
	@RequiresPermissions("hotel:onOffLine:edit")
	@RequestMapping(value = "delete")
	public String delete(OnOffLine onOffLine, RedirectAttributes redirectAttributes) {
		onOffLineService.delete(onOffLine);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:"+Global.getAdminPath()+"/crm/hotel/onOffLine/?repage";
	}

	@RequiresPermissions("hotel:onOffLine:edit")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(OnOffLine onOffLine, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			//以下是导出excel文件
			onOffLine.setOrderBy(" firstTime desc");
			List<OnOffLine> list = onOffLineService.findList(onOffLine);
			// 如果是分销
			fileName = "酒店上下线数据" + fileName;
			new ExportExcel("酒店上下线记录", OnOffLine.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出酒店上下线记录！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/crm/hotel/onOffLine/list?repage";
	}

	@RequiresPermissions("hotel:onOffLine:edit")
	@RequestMapping(value = "hotelRateList")
	public String hotelRateList(HotelRate hotelRate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HotelRate> page = onOffLineService.findHotelRateList(new Page<HotelRate>(request, response), hotelRate);
		model.addAttribute("page", page);
		model.addAttribute("hotelRate", hotelRate);
		return "crm/hotel/hotelRateList";
	}

}