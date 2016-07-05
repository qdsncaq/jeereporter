/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.syslabel.web;

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
import com.thinkgem.crm.syslabel.entity.RfCrmSysLabel;
import com.thinkgem.crm.syslabel.service.RfCrmSysLabelService;

/**
 * CRM系统配置Controller
 * @author 段文昌
 * @version 2016-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/syslabel/rfCrmSysLabel")
public class RfCrmSysLabelController extends BaseController {

	@Autowired
	private RfCrmSysLabelService rfCrmSysLabelService;
	
	@ModelAttribute
	public RfCrmSysLabel get(@RequestParam(required=false) String id) {
		RfCrmSysLabel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmSysLabelService.get(id);
		}
		if (entity == null){
			entity = new RfCrmSysLabel();
		}
		return entity;
	}
	
	@RequiresPermissions("syslabel:rfCrmSysLabel:view")
	@RequestMapping(value = {"list", ""})
	public String list(RfCrmSysLabel rfCrmSysLabel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmSysLabel> page = rfCrmSysLabelService.findPage(new Page<RfCrmSysLabel>(request, response), rfCrmSysLabel); 
		model.addAttribute("page", page);
		return "crm/syslabel/rfCrmSysLabelList";
	}

	@RequiresPermissions("syslabel:rfCrmSysLabel:view")
	@RequestMapping(value = "form")
	public String form(RfCrmSysLabel rfCrmSysLabel, Model model) {
		model.addAttribute("rfCrmSysLabel", rfCrmSysLabel);
		return "crm/syslabel/rfCrmSysLabelForm";
	}

	@RequiresPermissions("syslabel:rfCrmSysLabel:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmSysLabel rfCrmSysLabel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rfCrmSysLabel)){
			return form(rfCrmSysLabel, model);
		}
		rfCrmSysLabelService.save(rfCrmSysLabel);
		addMessage(redirectAttributes, "保存系统配置成功");
		return "redirect:"+Global.getAdminPath()+"/syslabel/rfCrmSysLabel/?repage";
	}
	
	@RequiresPermissions("syslabel:rfCrmSysLabel:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmSysLabel rfCrmSysLabel, RedirectAttributes redirectAttributes) {
		rfCrmSysLabelService.delete(rfCrmSysLabel);
		addMessage(redirectAttributes, "删除系统配置成功");
		return "redirect:"+Global.getAdminPath()+"/syslabel/rfCrmSysLabel/?repage";
	}

}