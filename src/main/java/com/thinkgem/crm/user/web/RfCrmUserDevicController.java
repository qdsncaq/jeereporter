/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.crm.user.entity.RfCrmUserDevic;
import com.thinkgem.crm.user.service.RfCrmUserDevicService;

import java.util.List;

/**
 * CRM/OMS登录信息统计Controller
 * @author 段文昌
 * @version 2016-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/user/rfCrmUserDevic")
public class RfCrmUserDevicController extends BaseController {

	@Autowired
	private RfCrmUserDevicService rfCrmUserDevicService;
	
	@ModelAttribute
	public RfCrmUserDevic get(@RequestParam(required=false) String id) {
		RfCrmUserDevic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmUserDevicService.get(id);
		}
		if (entity == null){
			entity = new RfCrmUserDevic();
		}
		return entity;
	}
	
	@RequiresPermissions("user:rfCrmUserDevic:view")
	@RequestMapping(value = {"list", ""})
	public String list(RfCrmUserDevic rfCrmUserDevic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmUserDevic> page = rfCrmUserDevicService.findPage(new Page<RfCrmUserDevic>(request, response), rfCrmUserDevic); 
		model.addAttribute("page", page);
		return "crm/user/rfCrmUserDevicList";
	}

	@RequiresPermissions("user:rfCrmUserDevic:oms")
	@RequestMapping(value = {"omsLoginList"})
	public String findOmsLoginList(RfCrmUserDevic rfCrmUserDevic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmUserDevic> page = rfCrmUserDevicService.findOmsLoginPage(new Page<RfCrmUserDevic>(request, response), rfCrmUserDevic);
		model.addAttribute("page", page);
		return "crm/user/rfCrmUserLoginList";
	}

	@RequiresPermissions("user:rfCrmUserDevic:view")
	@RequestMapping(value = "form")
	public String form(RfCrmUserDevic rfCrmUserDevic, Model model) {
		model.addAttribute("rfCrmUserDevic", rfCrmUserDevic);
		return "crm/user/rfCrmUserDevicForm";
	}

	@RequiresPermissions("user:rfCrmUserDevic:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmUserDevic rfCrmUserDevic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rfCrmUserDevic)){
			return form(rfCrmUserDevic, model);
		}
		rfCrmUserDevicService.save(rfCrmUserDevic);
		addMessage(redirectAttributes, "保存信息成功");
		return "redirect:"+Global.getAdminPath()+"/crm/user/rfCrmUserDevic/?repage";
	}
	
	@RequiresPermissions("user:rfCrmUserDevic:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmUserDevic rfCrmUserDevic, RedirectAttributes redirectAttributes) {
		rfCrmUserDevicService.delete(rfCrmUserDevic);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:"+Global.getAdminPath()+"/crm/user/rfCrmUserDevic/?repage";
	}

	/**
	 * 导出登录统计记录数据
	 * @param rfCrmUserDevic 登录信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user:rfCrmUserDevic:oms")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(RfCrmUserDevic rfCrmUserDevic, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			//以下是导出excel文件
			List<RfCrmUserDevic> list = rfCrmUserDevicService.findOmsLoginList(rfCrmUserDevic);
			// 全数据
			fileName = "OMS登录统计数据" + fileName;
			new ExportExcel("OMS-APP登录统计", RfCrmUserDevic.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出登录数据失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/crm/user/rfCrmUserDevic/loginList?repage";
	}

}