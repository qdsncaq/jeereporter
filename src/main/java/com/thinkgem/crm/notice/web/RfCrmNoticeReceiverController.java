/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.notice.web;

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
import com.thinkgem.crm.notice.entity.RfCrmNoticeReceiver;
import com.thinkgem.crm.notice.service.RfCrmNoticeReceiverService;

/**
 * 通知Controller
 * @author 李雪楠
 * @version 2016-05-27
 */
@Controller
@RequestMapping(value = "${adminPath}/notice/rfCrmNoticeReceiver")
public class RfCrmNoticeReceiverController extends BaseController {

	@Autowired
	private RfCrmNoticeReceiverService rfCrmNoticeReceiverService;
	
	@ModelAttribute
	public RfCrmNoticeReceiver get(@RequestParam(required=false) String id) {
		RfCrmNoticeReceiver entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmNoticeReceiverService.get(id);
		}
		if (entity == null){
			entity = new RfCrmNoticeReceiver();
		}
		return entity;
	}
	
	@RequiresPermissions("notice:rfCrmNoticeReceiver:view")
	@RequestMapping(value = {"list", ""})
	public String list(RfCrmNoticeReceiver rfCrmNoticeReceiver, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmNoticeReceiver> page = rfCrmNoticeReceiverService.findPage(new Page<RfCrmNoticeReceiver>(request, response), rfCrmNoticeReceiver); 
		model.addAttribute("page", page);
		return "crm/notice/rfCrmNoticeReceiverList";
	}

	@RequiresPermissions("notice:rfCrmNoticeReceiver:view")
	@RequestMapping(value = "form")
	public String form(RfCrmNoticeReceiver rfCrmNoticeReceiver, Model model) {
		model.addAttribute("rfCrmNoticeReceiver", rfCrmNoticeReceiver);
		return "crm/notice/rfCrmNoticeReceiverForm";
	}

	@RequiresPermissions("notice:rfCrmNoticeReceiver:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmNoticeReceiver rfCrmNoticeReceiver, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rfCrmNoticeReceiver)){
			return form(rfCrmNoticeReceiver, model);
		}
		rfCrmNoticeReceiverService.save(rfCrmNoticeReceiver);
		addMessage(redirectAttributes, "保存通知成功");
		return "redirect:"+Global.getAdminPath()+"/notice/rfCrmNoticeReceiver/?repage";
	}
	
	@RequiresPermissions("notice:rfCrmNoticeReceiver:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmNoticeReceiver rfCrmNoticeReceiver, RedirectAttributes redirectAttributes) {
		rfCrmNoticeReceiverService.delete(rfCrmNoticeReceiver);
		addMessage(redirectAttributes, "删除通知成功");
		return "redirect:"+Global.getAdminPath()+"/notice/rfCrmNoticeReceiver/?repage";
	}

}