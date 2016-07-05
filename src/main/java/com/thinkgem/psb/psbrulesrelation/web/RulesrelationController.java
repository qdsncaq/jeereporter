/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbrulesrelation.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.thinkgem.psb.common.DateUtils;
import com.thinkgem.psb.psbinfo.entity.SettlementPsbinfo;
import com.thinkgem.psb.psbinfo.service.SettlementPsbinfoService;
import com.thinkgem.psb.psbrules.entity.SettlementPsbrules;
import com.thinkgem.psb.psbrules.service.SettlementPsbrulesService;
import com.thinkgem.psb.psbrulesrelation.entity.SettlementPsbrulesrelation;
import com.thinkgem.psb.psbrulesrelation.service.SettlementPsbrulesrelationService;

/**
 * psbrulesrelationController
 * @author lm
 * @version 2016-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/psbrulesrelation/settlementPsbrulesrelation")
public class RulesrelationController extends BaseController {
	
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SettlementPsbrulesrelationService settlementPsbrulesrelationService;
	
	@Autowired
	private SettlementPsbinfoService settlementPsbinfoService;
	
	/** 省服务 */
	@Autowired
	private TProvinceService provinceService;
	
	/** 市服务 */
	@Autowired
	private TCityService tCityService;
	
	/** 县区服务 */
	@Autowired
	private TDistrictService districtService;
	
	/** 查询规则 */
	@Autowired
	private SettlementPsbrulesService settlementPsbrulesService;
	
	/** 查询psb厂家信息 时间 */
	@Autowired
	private SettlementPsbinfoService psbInfoService;
	
	@ModelAttribute
	public SettlementPsbrulesrelation get(@RequestParam(required=false) String id) {
		SettlementPsbrulesrelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementPsbrulesrelationService.get(id);
		}
		if (entity == null){
			entity = new SettlementPsbrulesrelation();
		}
		return entity;
	}
	
	@RequestMapping(value = "serPsbTime")
	@ResponseBody
	public Map<String, Object> serPsbTime(SettlementPsbrulesrelation settlementPsbrulesrelation, HttpServletRequest request, HttpServletResponse response, Model model){
		log.info("查询psb的合作开始日期和结束日期作为规则的生效失效时间.");
		Map<String, Object> map = new HashMap<String, Object>();
		// 添加按照psb厂商 查询并显示生效和失效时间
		String psbid = settlementPsbrulesrelation.getPsbid();
		SettlementPsbinfo psbinfo = psbInfoService.get(psbid);
		Date begintime = psbinfo.getBegintime();
		Date endtime = psbinfo.getEndtime();
		map.put("begintime", DateUtils.getDatetime(begintime));
		map.put("endtime", DateUtils.getDatetime(endtime));
		return map;
	}
	
	@RequestMapping(value = "serSubTree")
	@ResponseBody
	public Object secSubTree(TCity tCity, Model model) {
		Page<TCity> pc=new Page<TCity>();
		pc.setOrderBy(" a.CityName ASC ");
		List<TCity> serSubTreeList = tCityService.findPage(pc,tCity).getList();
		return serSubTreeList;
	}
	
	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementPsbrulesrelation settlementPsbrulesrelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementPsbrulesrelation> page = settlementPsbrulesrelationService.findPage(new Page<SettlementPsbrulesrelation>(request, response), settlementPsbrulesrelation); 
		
		List<SettlementPsbrulesrelation> list = page.getList();
		String[] ids = null;
		List<SettlementPsbrulesrelation> list2 = new ArrayList<SettlementPsbrulesrelation>();
		for (SettlementPsbrulesrelation psbrulesrelation : list) {
			String hotelrules = psbrulesrelation.getHotelrules();
			String disrules = psbrulesrelation.getDisrules();
			String cityrules = psbrulesrelation.getCityrules();
			if(StringUtils.isNotEmpty(hotelrules)){
				ids = hotelrules.split(",");
			}
			if(StringUtils.isNotEmpty(disrules)){
				ids = disrules.split(",");
			}
			if(StringUtils.isNotEmpty(cityrules)){
				ids = cityrules.split(",");
			}
			List<SettlementPsbrules> psbrulesList = settlementPsbrulesService.findRulesByPsbids(ids);
			String ruledesc = "";
			for (SettlementPsbrules psbrules : psbrulesList) {
				ruledesc = ruledesc.concat(psbrules.getId()+":"+psbrules.getRulename()+";  ");
			}
			psbrulesrelation.setRuledesc(ruledesc);
			list2.add(psbrulesrelation);
			page.setList(list2);
		}
		
		//psb编码
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
				 
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(settlementPsbrulesrelation.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(settlementPsbrulesrelation.getProcode());
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
		if(StringUtils.isNotBlank(settlementPsbrulesrelation.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(settlementPsbrulesrelation.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		
		model.addAttribute("page", page);
		return "psb/psbrulesrelation/rulesrelationList";
	}

	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:view")
	@RequestMapping(value = "form")
	public String form(SettlementPsbrulesrelation settlementPsbrulesrelation, Model model) {
		model.addAttribute("settlementPsbrulesrelation", settlementPsbrulesrelation);
		
		//psb编码
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
				 
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(settlementPsbrulesrelation.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(settlementPsbrulesrelation.getProcode());
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
		if(StringUtils.isNotBlank(settlementPsbrulesrelation.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(settlementPsbrulesrelation.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		return "psb/psbrulesrelation/hotelRulesrelationForm";
	}

	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:edit")
	@RequestMapping(value = "save")
	public String save(SettlementPsbrulesrelation settlementPsbrulesrelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementPsbrulesrelation)){
			return form(settlementPsbrulesrelation, model);
		}
		String citycode = settlementPsbrulesrelation.getCitycode();
		if(citycode.startsWith(",")){
			citycode = citycode.substring(1);
		}
		settlementPsbrulesrelation.setCitycode(citycode);
		settlementPsbrulesrelationService.addrules(settlementPsbrulesrelation);
		addMessage(redirectAttributes, "保存psbrulesrelation成功");
		return "redirect:"+Global.getAdminPath()+"/psbrulesrelation/settlementPsbrulesrelation/list?repage";
	}
	
	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementPsbrulesrelation settlementPsbrulesrelation, RedirectAttributes redirectAttributes) {
		settlementPsbrulesrelation.setDelflag(2);
		settlementPsbrulesrelationService.returnUpdate(settlementPsbrulesrelation);
		addMessage(redirectAttributes, "删除规则映射信息成功");
		return "redirect:"+Global.getAdminPath()+"/psbrulesrelation/settlementPsbrulesrelation/list?repage";
	}
	
	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:edit")
	@RequestMapping(value = "deletelist")
	@ResponseBody
	public String deletelist(String ids, SettlementPsbrulesrelation settlementPsbrulesrelation, RedirectAttributes redirectAttributes) {
		if(ids == null || ids == ""){
			log.info("没有要删除的数据.");
			return "没有要删除的数据.";
		}
		String[] arrids = ids.split(",");
		int delcount = settlementPsbrulesrelationService.deleteids(arrids);
		return "批量删除 "+delcount+" 条数据.";
	}
	
	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(SettlementPsbrulesrelation settlementPsbrulesrelation, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "科目映射列表.xlsx";
    		List<SettlementPsbrulesrelation> list = settlementPsbrulesrelationService.findList(settlementPsbrulesrelation);
 
    		String[] ids = null;
    		List<SettlementPsbrulesrelation> list2 = new ArrayList<SettlementPsbrulesrelation>();
    		for (SettlementPsbrulesrelation psbrulesrelation : list) {
    			String hotelrules = psbrulesrelation.getHotelrules();
    			String disrules = psbrulesrelation.getDisrules();
    			String cityrules = psbrulesrelation.getCityrules();
    			if(StringUtils.isNotEmpty(hotelrules)){
    				ids = hotelrules.split(",");
    			}
    			if(StringUtils.isNotEmpty(disrules)){
    				ids = disrules.split(",");
    			}
    			if(StringUtils.isNotEmpty(cityrules)){
    				ids = cityrules.split(",");
    			}
    			List<SettlementPsbrules> psbrulesList = settlementPsbrulesService.findRulesByPsbids(ids);
    			String ruledesc = "";
    			for (SettlementPsbrules psbrules : psbrulesList) {
    				ruledesc = ruledesc.concat(psbrules.getId()+":"+psbrules.getRulename()+";  ");
    			}
    			psbrulesrelation.setRuledesc(ruledesc);
    			list2.add(psbrulesrelation);
    		}
    		
    		new ExportExcel("科目映射", SettlementPsbrulesrelation.class, 1).setDataList(list2).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "酒店科目映射excel导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/psbrulesrelation/settlementPsbrulesrelation/list?repage";
	}
	
	// --------------- 区域规则配置 ----------------

	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:view")
	@RequestMapping(value = "disform")
	public String disform(SettlementPsbrulesrelation settlementPsbrulesrelation, Model model) {
		model.addAttribute("settlementPsbrulesrelation", settlementPsbrulesrelation);
		
		//psb编码
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
				 
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(settlementPsbrulesrelation.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(settlementPsbrulesrelation.getProcode());
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
		if(StringUtils.isNotBlank(settlementPsbrulesrelation.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(settlementPsbrulesrelation.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		return "psb/psbrulesrelation/disRulesrelationForm";
	}

	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:edit")
	@RequestMapping(value = "dissave")
	public String dissave(SettlementPsbrulesrelation settlementPsbrulesrelation, Model model, RedirectAttributes redirectAttributes) {		
		if (!beanValidator(model, settlementPsbrulesrelation)){
			return form(settlementPsbrulesrelation, model);
		}
		String citycode = settlementPsbrulesrelation.getCitycode();
		if(citycode.startsWith(",")){
			citycode = citycode.substring(1);
		}
		settlementPsbrulesrelation.setCitycode(citycode);
		settlementPsbrulesrelationService.addDisRules(settlementPsbrulesrelation);
		addMessage(redirectAttributes, "保存psbrulesrelation成功");
		return "redirect:"+Global.getAdminPath()+"/psbrulesrelation/settlementPsbrulesrelation/list?repage";
	}
	
	// --------------- 城市规则配置 ----------------
	
	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:view")
	@RequestMapping(value = {"citylist", ""})
	public String citylist(SettlementPsbrulesrelation settlementPsbrulesrelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		settlementPsbrulesrelation.setAreatype(1);
		Page<SettlementPsbrulesrelation> page = settlementPsbrulesrelationService.findPage(new Page<SettlementPsbrulesrelation>(request, response), settlementPsbrulesrelation); 
		
		//psb编码
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
				 
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(settlementPsbrulesrelation.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(settlementPsbrulesrelation.getProcode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
//          List<TCity> cityList = tCityService.getByParent(city);
			Page<TCity> pc=new Page<TCity>();
			pc.setOrderBy(" a.CityName ASC ");
			List<TCity> cityList = tCityService.findPage(pc,city).getList();
			model.addAttribute("cityList", cityList);
		}
		
		model.addAttribute("page", page);
		return "psb/psbrulesrelation/settlementPsbcityrulesList";
	}

	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:view")
	@RequestMapping(value = "cityform")
	public String cityform(SettlementPsbrulesrelation settlementPsbrulesrelation, Model model) {
		model.addAttribute("settlementPsbrulesrelation", settlementPsbrulesrelation);
		
		//psb编码
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
				 
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(settlementPsbrulesrelation.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(settlementPsbrulesrelation.getProcode());
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
		if(StringUtils.isNotBlank(settlementPsbrulesrelation.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(settlementPsbrulesrelation.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		return "psb/psbrulesrelation/cityRulesrelationForm";
	}

	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:edit")
	@RequestMapping(value = "citysave")
	public String citysave(SettlementPsbrulesrelation settlementPsbrulesrelation, Model model, RedirectAttributes redirectAttributes) {		
//		if (!beanValidator(model, settlementPsbrulesrelation)){
//			return cityform(settlementPsbrulesrelation, model);
//		}
		settlementPsbrulesrelationService.addCityRules(settlementPsbrulesrelation);
		addMessage(redirectAttributes, "保存psbrulesrelation成功");
		return "redirect:"+Global.getAdminPath()+"/psbrulesrelation/settlementPsbrulesrelation/list?repage";
	}
	
	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:edit")
	@RequestMapping(value = "citydelete")
	public String citydelete(SettlementPsbrulesrelation settlementPsbrulesrelation, RedirectAttributes redirectAttributes) {
		settlementPsbrulesrelationService.delete(settlementPsbrulesrelation);
		addMessage(redirectAttributes, "删除psbrulesrelation成功");
		return "redirect:"+Global.getAdminPath()+"/psbrulesrelation/settlementPsbrulesrelation/citylist?repage";
	}
	
	@RequiresPermissions("psbrulesrelation:settlementPsbrulesrelation:view")
	@RequestMapping(value = "cityexportexcel")
	public String cityexportexcel(SettlementPsbrulesrelation settlementPsbrulesrelation, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "城市科目映射列表.xlsx";
            settlementPsbrulesrelation.setAreatype(1);
    		List<SettlementPsbrulesrelation> list = settlementPsbrulesrelationService.findList(settlementPsbrulesrelation);
    		new ExportExcel("城市科目映射", SettlementPsbrulesrelation.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "酒店科目映射excel导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/psbrulesrelation/settlementPsbrulesrelation/citylist?repage";
	}

}