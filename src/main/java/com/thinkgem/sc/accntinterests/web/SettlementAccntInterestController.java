/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntinterests.web;

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
import com.thinkgem.sc.accntinterests.entity.SettlementAccntInterest;
import com.thinkgem.sc.accntinterests.service.SettlementAccntInterestService;

/**
 * accntInterestsController
 * @author ld
 * @version 2016-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/accntinterests/settlementAccntInterest")
public class SettlementAccntInterestController extends BaseController {

	@Autowired
	private SettlementAccntInterestService settlementAccntInterestService;
	
	@ModelAttribute
	public SettlementAccntInterest get(@RequestParam(required=false) String id) {
		SettlementAccntInterest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementAccntInterestService.get(id);
		}
		if (entity == null){
			entity = new SettlementAccntInterest();
		}
		return entity;
	}
	
	@RequiresPermissions("accntinterests:settlementAccntInterest:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementAccntInterest settlementAccntInterest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementAccntInterest> page = settlementAccntInterestService.findPage(new Page<SettlementAccntInterest>(request, response), settlementAccntInterest); 
		model.addAttribute("page", page);
		return "sc/accntinterests/settlementAccntInterestList";
	}

	@RequiresPermissions("accntinterests:settlementAccntInterest:view")
	@RequestMapping(value = "form")
	public String form(SettlementAccntInterest settlementAccntInterest, Model model) {
		model.addAttribute("settlementAccntInterest", settlementAccntInterest);
		return "sc/accntinterests/settlementAccntInterestForm";
	}

	@RequiresPermissions("accntinterests:settlementAccntInterest:edit")
	@RequestMapping(value = "save")
	public String save(SettlementAccntInterest settlementAccntInterest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementAccntInterest)){
			return form(settlementAccntInterest, model);
		}
		settlementAccntInterestService.save(settlementAccntInterest);
		addMessage(redirectAttributes, "保存accntInterests成功");
		return "redirect:"+Global.getAdminPath()+"/accntinterests/settlementAccntInterest/?repage";
	}
	
	@RequiresPermissions("accntinterests:settlementAccntInterest:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementAccntInterest settlementAccntInterest, RedirectAttributes redirectAttributes) {
		settlementAccntInterestService.delete(settlementAccntInterest);
		addMessage(redirectAttributes, "删除accntInterests成功");
		return "redirect:"+Global.getAdminPath()+"/accntinterests/settlementAccntInterest/?repage";
	}

}