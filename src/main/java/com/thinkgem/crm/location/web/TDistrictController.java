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
import com.thinkgem.crm.location.entity.TDistrict;
import com.thinkgem.crm.location.service.TDistrictService;

import java.util.List;

/**
 * CRM地理位置信息Controller
 * @author 段文昌
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/location/tDistrict")
public class TDistrictController extends BaseController {

	@Autowired
	private TDistrictService tDistrictService;
	
	@ModelAttribute
	public TDistrict get(@RequestParam(required=false) String id) {
		TDistrict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tDistrictService.get(id);
		}
		if (entity == null){
			entity = new TDistrict();
		}
		return entity;
	}
	
	@RequiresPermissions("location:tDistrict:view")
	@RequestMapping(value = {"list", ""})
	public String list(TDistrict tDistrict, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TDistrict> page = tDistrictService.findPage(new Page<TDistrict>(request, response), tDistrict); 
		model.addAttribute("page", page);
		return "crm/location/tDistrictList";
	}

	@RequiresPermissions("location:tDistrict:view")
	@RequestMapping(value = "form")
	public String form(TDistrict tDistrict, Model model) {
		model.addAttribute("tDistrict", tDistrict);
		return "crm/location/tDistrictForm";
	}

	@RequiresPermissions("location:tDistrict:edit")
	@RequestMapping(value = "save")
	public String save(TDistrict tDistrict, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tDistrict)){
			return form(tDistrict, model);
		}
		tDistrictService.save(tDistrict);
		addMessage(redirectAttributes, "保存县/区成功");
		return "redirect:"+Global.getAdminPath()+"/location/tDistrict/?repage";
	}
	
	@RequiresPermissions("location:tDistrict:edit")
	@RequestMapping(value = "delete")
	public String delete(TDistrict tDistrict, RedirectAttributes redirectAttributes) {
		tDistrictService.delete(tDistrict);
		addMessage(redirectAttributes, "删除县/区成功");
		return "redirect:"+Global.getAdminPath()+"/location/tDistrict/?repage";
	}

	@RequestMapping(value = "serSubTree")
	@ResponseBody
	public Object secSubTree(TDistrict tDistrict, Model model) {
		List<TDistrict> serSubTreeList = tDistrictService.getByParent(tDistrict);
		return serSubTreeList;
	}

}