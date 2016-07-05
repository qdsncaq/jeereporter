/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oms.entity.OmsMarketInfo;
import com.thinkgem.jeesite.modules.oms.service.OmsMarketInfoService;

/**
 * oms 商场模块Controller
 * @author yaoxiang
 * @version 2016-03-28
 */
@Controller
@RequestMapping(value = "${adminPath}/oms/omsMarketInfo")
public class OmsMarketInfoController extends BaseController {

	@Autowired
	private OmsMarketInfoService omsMarketInfoService;
	
	
	@ResponseBody
	@RequestMapping("/marketSelect")
	public List<Map<String, Object>> findMarketSelect(){
		return omsMarketInfoService.findMarketSelect();
	}
	
	
	
	@ModelAttribute
	public OmsMarketInfo get(@RequestParam(required=false) String id) {
		OmsMarketInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = omsMarketInfoService.get(id);
		}
		if (entity == null){
			entity = new OmsMarketInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("oms:omsMarketInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OmsMarketInfo omsMarketInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OmsMarketInfo> page = omsMarketInfoService.findPage(new Page<OmsMarketInfo>(request, response), omsMarketInfo); 
		model.addAttribute("page", page);
		return "modules/oms/omsMarketInfoList";
	}

	@RequiresPermissions("oms:omsMarketInfo:view")
	@RequestMapping(value = "form")
	public String form(OmsMarketInfo omsMarketInfo, Model model) {
		model.addAttribute("omsMarketInfo", omsMarketInfo);
		return "modules/oms/omsMarketInfoForm";
	}

	@RequiresPermissions("oms:omsMarketInfo:edit")
	@RequestMapping(value = "save")
	public String save(OmsMarketInfo omsMarketInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, omsMarketInfo)){
			return form(omsMarketInfo, model);
		}
		omsMarketInfoService.save(omsMarketInfo);
		addMessage(redirectAttributes, "保存商场管理成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsMarketInfo/?repage";
	}
	
	@RequiresPermissions("oms:omsMarketInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(OmsMarketInfo omsMarketInfo, RedirectAttributes redirectAttributes) {
		omsMarketInfoService.delete(omsMarketInfo);
		addMessage(redirectAttributes, "删除商场管理成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsMarketInfo/?repage";
	}

}