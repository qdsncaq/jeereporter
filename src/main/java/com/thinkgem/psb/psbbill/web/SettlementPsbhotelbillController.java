/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbbill.web;

import java.util.List;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.psb.psbbill.entity.SettlementPsbhotelbill;
import com.thinkgem.psb.psbbill.service.SettlementPsbhotelbillService;
import com.thinkgem.psb.psbinfo.entity.SettlementPsbinfo;
import com.thinkgem.psb.psbinfo.service.SettlementPsbinfoService;

/**
 * psbbillController
 * @author lm
 * @version 2016-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/psbbill/settlementPsbhotelbill")
public class SettlementPsbhotelbillController extends BaseController {
	
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SettlementPsbhotelbillService settlementPsbhotelbillService;
	
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
	
	@ModelAttribute
	public SettlementPsbhotelbill get(@RequestParam(required=false) String id) {
		SettlementPsbhotelbill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementPsbhotelbillService.get(id);
		}
		if (entity == null){
			entity = new SettlementPsbhotelbill();
		}
		return entity;
	}
	
	@RequiresPermissions("psbbill:settlementPsbhotelbill:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementPsbhotelbill settlementPsbhotelbill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementPsbhotelbill> page = settlementPsbhotelbillService.findPage(new Page<SettlementPsbhotelbill>(request, response), settlementPsbhotelbill); 
		
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
		if(StringUtils.isNotBlank(settlementPsbhotelbill.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(settlementPsbhotelbill.getProcode());
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
		if(StringUtils.isNotBlank(settlementPsbhotelbill.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(settlementPsbhotelbill.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		
		model.addAttribute("page", page);
		return "psb/psbbill/settlementPsbhotelbillList";
	}

	@RequiresPermissions("psbbill:settlementPsbhotelbill:view")
	@RequestMapping(value = "form")
	public String form(SettlementPsbhotelbill settlementPsbhotelbill, Model model) {
		model.addAttribute("settlementPsbhotelbill", settlementPsbhotelbill);
		return "psb/psbbill/settlementPsbhotelbillForm";
	}

	@RequiresPermissions("psbbill:settlementPsbhotelbill:edit")
	@RequestMapping(value = "save")
	public String save(SettlementPsbhotelbill settlementPsbhotelbill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementPsbhotelbill)){
			return form(settlementPsbhotelbill, model);
		}
		settlementPsbhotelbillService.save(settlementPsbhotelbill);
		addMessage(redirectAttributes, "保存psbbill成功");
		return "redirect:"+Global.getAdminPath()+"/psbbill/settlementPsbhotelbill/?repage";
	}
	
	@RequiresPermissions("psbbill:settlementPsbhotelbill:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementPsbhotelbill settlementPsbhotelbill, RedirectAttributes redirectAttributes) {
		settlementPsbhotelbillService.delete(settlementPsbhotelbill);
		addMessage(redirectAttributes, "删除psbbill成功");
		return "redirect:"+Global.getAdminPath()+"/psbbill/settlementPsbhotelbill/?repage";
	}

	/**
	 * 导出
	 * @param settlementPsbhotelbill
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("psbbill:settlementPsbhotelbill:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(SettlementPsbhotelbill settlementPsbhotelbill, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			settlementPsbhotelbillService.exportexcel(settlementPsbhotelbill, response);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "结算签收单excel导出失败！失败信息：" + e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/psbbill/settlementPsbhotelbill/?repage";
	}
	
	/**
	 * 批量 修改账单状态为: 已结算
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("psbbill:settlementPsbhotelbill:view")
	@RequestMapping(value = "settlebill")
	@ResponseBody
	public String settlebill(String ids){
		if(ids == null || ids == ""){
			log.info("修改'已结算'状态,没有选择数据.不执行.");
			return "没有选择数据.不执行.";
		}
		String[] arrids = ids.split(",");
		int settlebillCount = settlementPsbhotelbillService.settlebill(arrids);
		return "修改'已结算'状态  "+settlebillCount+" 条数据.";
	}
	
	/**
	 * 批量 修改账单状态为: 本期不结算
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("psbbill:settlementPsbhotelbill:view")
	@RequestMapping(value = "nobill")
	@ResponseBody
	public String nobill(String ids){
		if(ids == null || ids == ""){
			log.info("修改'本期不结算'状态,没有选择数据.不执行.");
			return "没有选择数据.不执行.";
		}
		String[] arrids = ids.split(",");
		int nobillCount = settlementPsbhotelbillService.nobill(arrids);
		return "修改'本期不结算'状态  "+nobillCount+" 条数据.";
	}
	
}