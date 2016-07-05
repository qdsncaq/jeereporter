/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.bank.web;

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
import com.thinkgem.crm.bank.entity.THotelBank;
import com.thinkgem.crm.bank.service.THotelBankService;

/**
 * 酒店银行账号Controller
 * @author 李雪楠
 * @version 2016-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/bank/tHotelBank")
public class THotelBankController extends BaseController {

	@Autowired
	private THotelBankService tHotelBankService;
	
	@ModelAttribute
	public THotelBank get(@RequestParam(required=false) String id) {
		THotelBank entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tHotelBankService.get(id);
		}
		if (entity == null){
			entity = new THotelBank();
		}
		return entity;
	}
	
	@RequiresPermissions("bank:tHotelBank:view")
	@RequestMapping(value = {"list", ""})
	public String list(THotelBank tHotelBank, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<THotelBank> page = tHotelBankService.findPage(new Page<THotelBank>(request, response), tHotelBank); 
		model.addAttribute("page", page);
		return "crm/bank/tHotelBankList";
	}

	@RequiresPermissions("bank:tHotelBank:view")
	@RequestMapping(value = "form")
	public String form(THotelBank tHotelBank, Model model) {
		model.addAttribute("tHotelBank", tHotelBank);
		return "crm/bank/tHotelBankForm";
	}

	@RequiresPermissions("bank:tHotelBank:edit")
	@RequestMapping(value = "save")
	public String save(THotelBank tHotelBank, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tHotelBank)){
			return form(tHotelBank, model);
		}
		tHotelBankService.save(tHotelBank);
		addMessage(redirectAttributes, "保存酒店银行账号成功");
		return "redirect:"+Global.getAdminPath()+"/crm/bank/tHotelBank/?repage";
	}
	
	@RequiresPermissions("bank:tHotelBank:edit")
	@RequestMapping(value = "delete")
	public String delete(THotelBank tHotelBank, RedirectAttributes redirectAttributes) {
		tHotelBankService.delete(tHotelBank);
		addMessage(redirectAttributes, "删除酒店银行账号成功");
		return "redirect:"+Global.getAdminPath()+"/crm/bank/tHotelBank/?repage";
	}

}