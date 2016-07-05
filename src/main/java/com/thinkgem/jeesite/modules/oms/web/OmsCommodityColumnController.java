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
import com.thinkgem.jeesite.modules.oms.entity.OmsCommodityColumn;
import com.thinkgem.jeesite.modules.oms.service.OmsCommodityColumnService;

/**
 * oms商品类型Controller
 * @author yaoxiang
 * @version 2016-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/oms/omsCommodityColumn")
public class OmsCommodityColumnController extends BaseController {

	@Autowired
	private OmsCommodityColumnService omsCommodityColumnService;
	
	@ModelAttribute
	public OmsCommodityColumn get(@RequestParam(required=false) String id) {
		OmsCommodityColumn entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = omsCommodityColumnService.get(id);
		}
		if (entity == null){
			entity = new OmsCommodityColumn();
		}
		return entity;
	}
	
	@RequiresPermissions("oms:omsCommodityColumn:view")
	@RequestMapping(value = {"list", ""})
	public String list(OmsCommodityColumn omsCommodityColumn, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OmsCommodityColumn> page = omsCommodityColumnService.findPage(new Page<OmsCommodityColumn>(request, response), omsCommodityColumn); 
		model.addAttribute("page", page);
		return "modules/oms/omsCommodityColumnList";
	}

	@RequiresPermissions("oms:omsCommodityColumn:view")
	@RequestMapping(value = "form")
	public String form(OmsCommodityColumn omsCommodityColumn, Model model) {
		model.addAttribute("omsCommodityColumn", omsCommodityColumn);
		return "modules/oms/omsCommodityColumnForm";
	}

	@RequiresPermissions("oms:omsCommodityColumn:edit")
	@RequestMapping(value = "save")
	public String save(OmsCommodityColumn omsCommodityColumn, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, omsCommodityColumn)){
			return form(omsCommodityColumn, model);
		}
		omsCommodityColumnService.save(omsCommodityColumn);
		addMessage(redirectAttributes, "保存商品类型成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsCommodityColumn/?repage";
	}
	
	@RequiresPermissions("oms:omsCommodityColumn:edit")
	@RequestMapping(value = "delete")
	public String delete(OmsCommodityColumn omsCommodityColumn, RedirectAttributes redirectAttributes) {
		omsCommodityColumnService.delete(omsCommodityColumn);
		addMessage(redirectAttributes, "删除商品类型成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsCommodityColumn/?repage";
	}

}