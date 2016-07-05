/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.location.web;

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
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TProvinceService;

/**
 * CRM地理位置信息Controller
 * @author 段文昌
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/location/tProvince")
public class TProvinceController extends BaseController {

	@Autowired
	private TProvinceService tProvinceService;
	
	@ModelAttribute
	public TProvince get(@RequestParam(required=false) String id) {
		TProvince entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tProvinceService.get(id);
		}
		if (entity == null){
			entity = new TProvince();
		}
		return entity;
	}
	
	@RequiresPermissions("location:tProvince:view")
	@RequestMapping(value = {"list", ""})
	public String list(TProvince tProvince, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TProvince> page = tProvinceService.findPage(new Page<TProvince>(request, response), tProvince); 
		model.addAttribute("page", page);
		return "crm/location/tProvinceList";
	}

	@RequiresPermissions("location:tProvince:view")
	@RequestMapping(value = "form")
	public String form(TProvince tProvince, Model model) {
		model.addAttribute("tProvince", tProvince);
		return "crm/location/tProvinceForm";
	}

	@RequiresPermissions("location:tProvince:edit")
	@RequestMapping(value = "save")
	public String save(TProvince tProvince, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tProvince)){
			return form(tProvince, model);
		}
		tProvinceService.save(tProvince);
		addMessage(redirectAttributes, "保存省成功");
		return "redirect:"+Global.getAdminPath()+"/location/tProvince/?repage";
	}
	
	@RequiresPermissions("location:tProvince:edit")
	@RequestMapping(value = "delete")
	public String delete(TProvince tProvince, RedirectAttributes redirectAttributes) {
		tProvinceService.delete(tProvince);
		addMessage(redirectAttributes, "删除省成功");
		return "redirect:"+Global.getAdminPath()+"/location/tProvince/?repage";
	}

}