/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.web;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticsDirect;
import com.thinkgem.jeesite.modules.oms.service.OmsLogisticsDirectService;

/**
 * 直送基础数据Controller
 * @author oms
 * @version 2016-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/oms/omsLogisticsDirect")
public class OmsLogisticsDirectController extends BaseController {

	@Autowired
	private OmsLogisticsDirectService omsLogisticsDirectService;
	
	@ModelAttribute
	public OmsLogisticsDirect get(@RequestParam(required=false) String id) {
		OmsLogisticsDirect entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = omsLogisticsDirectService.get(id);
		}
		if (entity == null){
			entity = new OmsLogisticsDirect();
		}
		return entity;
	}
	
	@RequiresPermissions("oms:omsLogisticsDirect:view")
	@RequestMapping(value = {"list", ""})
	public String list(OmsLogisticsDirect omsLogisticsDirect, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OmsLogisticsDirect> page = omsLogisticsDirectService.findPage(new Page<OmsLogisticsDirect>(request, response), omsLogisticsDirect); 
		model.addAttribute("page", page);
		return "modules/oms/omsLogisticsDirectList";
	}

	@RequiresPermissions("oms:omsLogisticsDirect:view")
	@RequestMapping(value = "form")
	public String form(OmsLogisticsDirect omsLogisticsDirect, Model model) {
		model.addAttribute("omsLogisticsDirect", omsLogisticsDirect);
		return "modules/oms/omsLogisticsDirectForm";
	}

	@RequiresPermissions("oms:omsLogisticsDirect:edit")
	@RequestMapping(value = "save")
	public String save(OmsLogisticsDirect omsLogisticsDirect, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, omsLogisticsDirect)){
			return form(omsLogisticsDirect, model);
		}
		omsLogisticsDirectService.save(omsLogisticsDirect);
		addMessage(redirectAttributes, "保存直送基础数据成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsLogisticsDirect/?repage";
	}
	
	@RequiresPermissions("oms:omsLogisticsDirect:edit")
	@RequestMapping(value = "delete")
	public String delete(OmsLogisticsDirect omsLogisticsDirect, RedirectAttributes redirectAttributes) {
		omsLogisticsDirectService.delete(omsLogisticsDirect);
		addMessage(redirectAttributes, "删除直送基础数据成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsLogisticsDirect/?repage";
	}

}