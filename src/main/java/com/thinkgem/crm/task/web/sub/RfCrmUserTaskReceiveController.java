/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.task.web.sub;

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
import com.thinkgem.crm.task.entity.sub.RfCrmUserTaskReceive;
import com.thinkgem.crm.task.service.sub.RfCrmUserTaskReceiveService;

/**
 * BMS用户表Controller
 * @author 李雪楠
 * @version 2016-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/task/sub/rfCrmUserTaskReceive")
public class RfCrmUserTaskReceiveController extends BaseController {

	@Autowired
	private RfCrmUserTaskReceiveService rfCrmUserTaskReceiveService;
	
	@ModelAttribute
	public RfCrmUserTaskReceive get(@RequestParam(required=false) String id) {
		RfCrmUserTaskReceive entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmUserTaskReceiveService.get(id);
		}
		if (entity == null){
			entity = new RfCrmUserTaskReceive();
		}
		return entity;
	}
	
	@RequiresPermissions("task:sub:rfCrmUserTaskReceive:view")
	@RequestMapping(value = {"list", ""})
	public String list(RfCrmUserTaskReceive rfCrmUserTaskReceive, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmUserTaskReceive> page = rfCrmUserTaskReceiveService.findPage(new Page<RfCrmUserTaskReceive>(request, response), rfCrmUserTaskReceive); 
		model.addAttribute("page", page);
		return "crm/task/sub/rfCrmUserTaskReceiveList";
	}

	@RequiresPermissions("task:sub:rfCrmUserTaskReceive:view")
	@RequestMapping(value = "form")
	public String form(RfCrmUserTaskReceive rfCrmUserTaskReceive, Model model) {
		model.addAttribute("rfCrmUserTaskReceive", rfCrmUserTaskReceive);
		return "crm/task/sub/rfCrmUserTaskReceiveForm";
	}

	@RequiresPermissions("task:sub:rfCrmUserTaskReceive:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmUserTaskReceive rfCrmUserTaskReceive, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rfCrmUserTaskReceive)){
			return form(rfCrmUserTaskReceive, model);
		}
		rfCrmUserTaskReceiveService.save(rfCrmUserTaskReceive);
		addMessage(redirectAttributes, "保存用户成功");
		return "redirect:"+Global.getAdminPath()+"/crm/task/sub/rfCrmUserTaskReceive/?repage";
	}
	
	@RequiresPermissions("task:sub:rfCrmUserTaskReceive:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmUserTaskReceive rfCrmUserTaskReceive, RedirectAttributes redirectAttributes) {
		rfCrmUserTaskReceiveService.delete(rfCrmUserTaskReceive);
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:"+Global.getAdminPath()+"/crm/task/sub/rfCrmUserTaskReceive/?repage";
	}

}