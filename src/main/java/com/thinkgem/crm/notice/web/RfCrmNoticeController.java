/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.notice.web;

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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.crm.notice.entity.RfCrmNotice;
import com.thinkgem.crm.notice.service.RfCrmNoticeService;
import com.thinkgem.crm.syslabel.entity.RfCrmSysLabel;
import com.thinkgem.crm.syslabel.service.RfCrmSysLabelService;

/**
 * 公告Controller
 * @author 姜浩
 * @version 2016-05-27
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/notice/rfCrmNotice")
public class RfCrmNoticeController extends BaseController {

	@Autowired
	private RfCrmNoticeService rfCrmNoticeService;
	
	@Autowired
	private RfCrmSysLabelService rfCrmSysLabelService;
	
	@ModelAttribute
	public RfCrmNotice get(@RequestParam(required=false) String id) {
		RfCrmNotice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmNoticeService.get(id);
		}
		if (entity == null){
			entity = new RfCrmNotice();
		}
		return entity;
	}
	
	@RequiresPermissions("notice:rfCrmNotice:view")
	@RequestMapping(value = {"list", ""})
	public String list(RfCrmNotice rfCrmNotice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmNotice> page = rfCrmNoticeService.findPage(new Page<RfCrmNotice>(request, response), rfCrmNotice);
		
		//通知方式
		this.getNoticeType(model);
		
		model.addAttribute("page", page);
		return "crm/notice/rfCrmNoticeList";
	}

	private void getNoticeType(Model model) {
		RfCrmSysLabel rfCrmSysLabel = new RfCrmSysLabel();
		rfCrmSysLabel.setCodeLabel("notice_type");
		rfCrmSysLabel.setAvailable("1");
		List<RfCrmSysLabel> noticeTypeList = rfCrmSysLabelService.findList(rfCrmSysLabel);
		model.addAttribute("noticeTypeList", noticeTypeList);
	}

	@RequiresPermissions("notice:rfCrmNotice:view")
	@RequestMapping(value = "form")
	public String form(RfCrmNotice rfCrmNotice, Model model) {
		this.getNoticeType(model);
		model.addAttribute("rfCrmNotice", rfCrmNotice);
		return "crm/notice/rfCrmNoticeForm";
	}

	@RequiresPermissions("notice:rfCrmNotice:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmNotice rfCrmNotice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rfCrmNotice)){
			return form(rfCrmNotice, model);
		}
		User user = UserUtils.getUser();
		rfCrmNotice.setRemarks(user.getName());
		rfCrmNoticeService.save(rfCrmNotice);
		
		addMessage(redirectAttributes, "保存公告成功");
		return "redirect:"+Global.getAdminPath()+"/crm/notice/rfCrmNotice/?repage";
	}
	
	@RequiresPermissions("notice:rfCrmNotice:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmNotice rfCrmNotice, RedirectAttributes redirectAttributes) {
		rfCrmNoticeService.delete(rfCrmNotice);
		addMessage(redirectAttributes, "删除公告成功");
		return "redirect:"+Global.getAdminPath()+"/crm/notice/rfCrmNotice/?repage";
	}

}