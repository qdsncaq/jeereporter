/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.schedulercenter.web;

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
import com.thinkgem.sc.schedulercenter.entity.SettlementSchedulerCenter;
import com.thinkgem.sc.schedulercenter.service.SettlementSchedulerCenterService;

/**
 * schedulercenterController
 * @author lm
 * @version 2016-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/schedulercenter/settlementSchedulerCenter")
public class SettlementSchedulerCenterController extends BaseController {

	@Autowired
	private SettlementSchedulerCenterService settlementSchedulerCenterService;
	
	@ModelAttribute
	public SettlementSchedulerCenter get(@RequestParam(required=false) String id) {
		SettlementSchedulerCenter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementSchedulerCenterService.get(id);
		}
		if (entity == null){
			entity = new SettlementSchedulerCenter();
		}
		return entity;
	}
	
	@RequiresPermissions("schedulercenter:settlementSchedulerCenter:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementSchedulerCenter settlementSchedulerCenter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementSchedulerCenter> page = settlementSchedulerCenterService.findPage(new Page<SettlementSchedulerCenter>(request, response), settlementSchedulerCenter); 
		model.addAttribute("page", page);
		return "sc/schedulercenter/settlementSchedulerCenterList";
	}

	@RequiresPermissions("schedulercenter:settlementSchedulerCenter:view")
	@RequestMapping(value = "form")
	public String form(SettlementSchedulerCenter settlementSchedulerCenter, Model model) {
		model.addAttribute("settlementSchedulerCenter", settlementSchedulerCenter);
		return "sc/schedulercenter/settlementSchedulerCenterForm";
	}

	@RequiresPermissions("schedulercenter:settlementSchedulerCenter:edit")
	@RequestMapping(value = "save")
	public String save(SettlementSchedulerCenter settlementSchedulerCenter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementSchedulerCenter)){
			return form(settlementSchedulerCenter, model);
		}
		settlementSchedulerCenterService.save(settlementSchedulerCenter);
		addMessage(redirectAttributes, "保存schedulercenter成功");
		return "redirect:"+Global.getAdminPath()+"/schedulercenter/settlementSchedulerCenter/?repage";
	}
	
	@RequiresPermissions("schedulercenter:settlementSchedulerCenter:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementSchedulerCenter settlementSchedulerCenter, RedirectAttributes redirectAttributes) {
		settlementSchedulerCenterService.delete(settlementSchedulerCenter);
		addMessage(redirectAttributes, "删除schedulercenter成功");
		return "redirect:"+Global.getAdminPath()+"/schedulercenter/settlementSchedulerCenter/?repage";
	}

}