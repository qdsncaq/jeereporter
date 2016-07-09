/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.rpt.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.rpt.entity.ZinccoatingthicknessLog;
import com.thinkgem.jeesite.modules.rpt.service.ZinccoatingthicknessLogService;

/**
 * 锌层测厚日志Controller
 * @author aiqing.chu
 * @version 2016-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/rpt/zinccoatingthicknessLog")
public class ZinccoatingthicknessLogController extends BaseController {

	@Autowired
	private ZinccoatingthicknessLogService zinccoatingthicknessLogService;
	
	@ModelAttribute
	public ZinccoatingthicknessLog get(@RequestParam(required=false) String id) {
		ZinccoatingthicknessLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zinccoatingthicknessLogService.get(id);
		}
		if (entity == null){
			entity = new ZinccoatingthicknessLog();
		}
		return entity;
	}
	
	/**
	 * 
	 * @param zinccoatingthicknessLog
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("rpt:zinccoatingthicknessLog:view")
	@RequestMapping(value = {"queryList", ""})
	public String queryList(ZinccoatingthicknessLog zinccoatingthicknessLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZinccoatingthicknessLog> page = new Page<ZinccoatingthicknessLog>();
		page.setOrderBy(" a.logtime asc limit 100");
		zinccoatingthicknessLog.setPage(page);
		List<ZinccoatingthicknessLog> list = zinccoatingthicknessLogService.queryList(zinccoatingthicknessLog);
		page.setList(list);
		model.addAttribute("page", page);
		return "modules/rpt/zinccoatingthicknessLogQueryList";
	}
	
	@RequiresPermissions("rpt:zinccoatingthicknessLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZinccoatingthicknessLog zinccoatingthicknessLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZinccoatingthicknessLog> pagination = new Page<ZinccoatingthicknessLog>(request, response);
		pagination.setOrderBy(" a.logtime asc ");
		Page<ZinccoatingthicknessLog> page = zinccoatingthicknessLogService.findPage(pagination, zinccoatingthicknessLog); 
		model.addAttribute("page", page);
		return "modules/rpt/zinccoatingthicknessLogList";
	}

	@RequiresPermissions("rpt:zinccoatingthicknessLog:view")
	@RequestMapping(value = "form")
	public String form(ZinccoatingthicknessLog zinccoatingthicknessLog, Model model) {
		model.addAttribute("zinccoatingthicknessLog", zinccoatingthicknessLog);
		return "modules/rpt/zinccoatingthicknessLogForm";
	}

	@RequiresPermissions("rpt:zinccoatingthicknessLog:edit")
	@RequestMapping(value = "save")
	public String save(ZinccoatingthicknessLog zinccoatingthicknessLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zinccoatingthicknessLog)){
			return form(zinccoatingthicknessLog, model);
		}
		zinccoatingthicknessLogService.save(zinccoatingthicknessLog);
		addMessage(redirectAttributes, "保存锌层测试日志成功");
		return "redirect:"+Global.getAdminPath()+"/rpt/zinccoatingthicknessLog/?repage";
	}
	
	@RequiresPermissions("rpt:zinccoatingthicknessLog:edit")
	@RequestMapping(value = "delete")
	public String delete(ZinccoatingthicknessLog zinccoatingthicknessLog, RedirectAttributes redirectAttributes) {
		zinccoatingthicknessLogService.delete(zinccoatingthicknessLog);
		addMessage(redirectAttributes, "删除锌层测试日志成功");
		return "redirect:"+Global.getAdminPath()+"/rpt/zinccoatingthicknessLog/?repage";
	}

}