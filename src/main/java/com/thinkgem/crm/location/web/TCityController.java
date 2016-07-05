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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.service.TCityService;

import java.util.List;

/**
 * CRM地理位置信息Controller
 * @author 段文昌
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/location/tCity")
public class TCityController extends BaseController {

	@Autowired
	private TCityService tCityService;
	
	@ModelAttribute
	public TCity get(@RequestParam(required=false) String id) {
		TCity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tCityService.get(id);
		}
		if (entity == null){
			entity = new TCity();
		}
		return entity;
	}
	
	@RequiresPermissions("location:tCity:view")
	@RequestMapping(value = {"list", ""})
	public String list(TCity tCity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TCity> page = tCityService.findPage(new Page<TCity>(request, response), tCity); 
		model.addAttribute("page", page);
		return "crm/location/tCityList";
	}

	@RequiresPermissions("location:tCity:view")
	@RequestMapping(value = "form")
	public String form(TCity tCity, Model model) {
		model.addAttribute("tCity", tCity);
		return "crm/location/tCityForm";
	}

	@RequiresPermissions("location:tCity:edit")
	@RequestMapping(value = "save")
	public String save(TCity tCity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tCity)){
			return form(tCity, model);
		}
		tCityService.save(tCity);
		addMessage(redirectAttributes, "保存市成功");
		return "redirect:"+Global.getAdminPath()+"/location/tCity/?repage";
	}
	
	@RequiresPermissions("location:tCity:edit")
	@RequestMapping(value = "delete")
	public String delete(TCity tCity, RedirectAttributes redirectAttributes) {
		tCityService.delete(tCity);
		addMessage(redirectAttributes, "删除市成功");
		return "redirect:"+Global.getAdminPath()+"/location/tCity/?repage";
	}

	@RequestMapping(value = "serSubTree")
	@ResponseBody
	public Object secSubTree(TCity tCity, Model model) {
		List<TCity> serSubTreeList = tCityService.getByParent(tCity);
		return serSubTreeList;
	}

}