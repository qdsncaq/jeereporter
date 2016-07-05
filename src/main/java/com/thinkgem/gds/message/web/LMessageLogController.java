/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.message.web;

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

import com.thinkgem.gds.message.entity.LMessageLog;
import com.thinkgem.gds.message.service.LMessageLogService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 短信Controller
 * @author jianghe
 * @version 2016-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/message/lMessageLog")
public class LMessageLogController extends BaseController {

	@Autowired
	private LMessageLogService lMessageLogService;
	
	@ModelAttribute
	public LMessageLog get(@RequestParam(required=false) String id) {
		LMessageLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lMessageLogService.get(id);
		}
		if (entity == null){
			entity = new LMessageLog();
		}
		return entity;
	}
	
	@RequiresPermissions("message:lMessageLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(LMessageLog lMessageLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LMessageLog> page1 = new Page<LMessageLog>(request, response);
		page1.setOrderBy("time desc");
		Page<LMessageLog> page = lMessageLogService.findPage(page1, lMessageLog); 
		model.addAttribute("page", page);
		return "gds/message/lMessageLogList";
	}

	@RequiresPermissions("message:lMessageLog:view")
	@RequestMapping(value = "form")
	public String form(LMessageLog lMessageLog, Model model) {
		model.addAttribute("lMessageLog", lMessageLog);
		return "gds/message/lMessageLogForm";
	}

	@RequiresPermissions("message:lMessageLog:edit")
	@RequestMapping(value = "save")
	public String save(LMessageLog lMessageLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lMessageLog)){
			return form(lMessageLog, model);
		}
		lMessageLogService.save(lMessageLog);
		addMessage(redirectAttributes, "保存message成功");
		return "redirect:"+Global.getAdminPath()+"/message/lMessageLog/?repage";
	}
	
	@RequiresPermissions("message:lMessageLog:edit")
	@RequestMapping(value = "delete")
	public String delete(LMessageLog lMessageLog, RedirectAttributes redirectAttributes) {
		lMessageLogService.delete(lMessageLog);
		addMessage(redirectAttributes, "删除message成功");
		return "redirect:"+Global.getAdminPath()+"/message/lMessageLog/?repage";
	}

}