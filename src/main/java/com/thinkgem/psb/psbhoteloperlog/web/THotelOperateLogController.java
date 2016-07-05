/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbhoteloperlog.web;

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
import com.thinkgem.psb.psbhoteloperlog.entity.THotelOperateLog;
import com.thinkgem.psb.psbhoteloperlog.service.THotelOperateLogService;

/**
 * psbhoteloperlogController
 * @author lm
 * @version 2016-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/psbhoteloperlog/tHotelOperateLog")
public class THotelOperateLogController extends BaseController {

	@Autowired
	private THotelOperateLogService tHotelOperateLogService;
	
	@ModelAttribute
	public THotelOperateLog get(@RequestParam(required=false) String id) {
		THotelOperateLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tHotelOperateLogService.get(id);
		}
		if (entity == null){
			entity = new THotelOperateLog();
		}
		return entity;
	}
	
	@RequiresPermissions("psbhoteloperlog:tHotelOperateLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(THotelOperateLog tHotelOperateLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<THotelOperateLog> page = tHotelOperateLogService.findPage(new Page<THotelOperateLog>(request, response), tHotelOperateLog); 
		model.addAttribute("page", page);
		model.addAttribute("tHotelOperateLog", tHotelOperateLog);
		return "psb/psbhoteloperlog/tHotelOperateLogList";
	}

	@RequiresPermissions("psbhoteloperlog:tHotelOperateLog:view")
	@RequestMapping(value = "form")
	public String form(THotelOperateLog tHotelOperateLog, Model model) {
		model.addAttribute("tHotelOperateLog", tHotelOperateLog);
		return "psb/psbhoteloperlog/tHotelOperateLogForm";
	}

	@RequiresPermissions("psbhoteloperlog:tHotelOperateLog:edit")
	@RequestMapping(value = "save")
	public String save(THotelOperateLog tHotelOperateLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tHotelOperateLog)){
			return form(tHotelOperateLog, model);
		}
		tHotelOperateLogService.save(tHotelOperateLog);
		addMessage(redirectAttributes, "保存psbhoteloperlog成功");
		return "redirect:"+Global.getAdminPath()+"/psbhoteloperlog/tHotelOperateLog/?repage";
	}
	
	@RequiresPermissions("psbhoteloperlog:tHotelOperateLog:edit")
	@RequestMapping(value = "delete")
	public String delete(THotelOperateLog tHotelOperateLog, RedirectAttributes redirectAttributes) {
		tHotelOperateLogService.delete(tHotelOperateLog);
		addMessage(redirectAttributes, "删除psbhoteloperlog成功");
		return "redirect:"+Global.getAdminPath()+"/psbhoteloperlog/tHotelOperateLog/?repage";
	}

}