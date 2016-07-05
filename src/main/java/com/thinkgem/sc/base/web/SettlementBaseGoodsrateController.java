/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.base.web;

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
import com.thinkgem.sc.base.entity.SettlementBaseGoodsrate;
import com.thinkgem.sc.base.service.SettlementBaseGoodsrateService;

/**
 * 采购商品提成比例维护Controller
 * @author aiqing.chu@fangbaba.com
 * @version 2016-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/base/settlementBaseGoodsrate")
public class SettlementBaseGoodsrateController extends BaseController {

	@Autowired
	private SettlementBaseGoodsrateService settlementBaseGoodsrateService;
	
	@ModelAttribute
	public SettlementBaseGoodsrate get(@RequestParam(required=false) String id) {
		SettlementBaseGoodsrate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementBaseGoodsrateService.get(id);
		}
		if (entity == null){
			entity = new SettlementBaseGoodsrate();
		}
		return entity;
	}
	
	@RequiresPermissions("base:settlementBaseGoodsrate:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementBaseGoodsrate settlementBaseGoodsrate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementBaseGoodsrate> page = settlementBaseGoodsrateService.findPage(new Page<SettlementBaseGoodsrate>(request, response), settlementBaseGoodsrate); 
		model.addAttribute("page", page);
		return "sc/base/settlementBaseGoodsrateList";
	}

	@RequiresPermissions("base:settlementBaseGoodsrate:view")
	@RequestMapping(value = "form")
	public String form(SettlementBaseGoodsrate settlementBaseGoodsrate, Model model) {
		model.addAttribute("settlementBaseGoodsrate", settlementBaseGoodsrate);
		return "sc/base/settlementBaseGoodsrateForm";
	}

	@RequiresPermissions("base:settlementBaseGoodsrate:edit")
	@RequestMapping(value = "save")
	public String save(SettlementBaseGoodsrate settlementBaseGoodsrate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementBaseGoodsrate)){
			return form(settlementBaseGoodsrate, model);
		}
		settlementBaseGoodsrateService.save(settlementBaseGoodsrate);
		addMessage(redirectAttributes, "保存采购商品提成比例成功");
		return "redirect:"+Global.getAdminPath()+"/base/settlementBaseGoodsrate/?repage";
	}
	
	@RequiresPermissions("base:settlementBaseGoodsrate:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementBaseGoodsrate settlementBaseGoodsrate, RedirectAttributes redirectAttributes) {
		settlementBaseGoodsrateService.delete(settlementBaseGoodsrate);
		addMessage(redirectAttributes, "删除采购商品提成比例成功");
		return "redirect:"+Global.getAdminPath()+"/base/settlementBaseGoodsrate/?repage";
	}

}