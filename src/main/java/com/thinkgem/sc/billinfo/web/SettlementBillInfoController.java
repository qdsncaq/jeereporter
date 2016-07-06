///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.billinfo.web;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.thinkgem.jeesite.common.config.Global;
//import com.thinkgem.jeesite.common.persistence.Page;
//import com.thinkgem.jeesite.common.web.BaseController;
//import com.thinkgem.jeesite.common.utils.StringUtils;
//import com.thinkgem.sc.billinfo.entity.SettlementBillInfo;
//import com.thinkgem.sc.billinfo.service.SettlementBillInfoService;
//
///**
// * billinfoController
// * @author lm
// * @version 2016-03-28
// */
//@Controller
//@RequestMapping(value = "${adminPath}/billinfo/settlementBillInfo")
//public class SettlementBillInfoController extends BaseController {
//
//	@Autowired
//	private SettlementBillInfoService settlementBillInfoService;
//	
//	@ModelAttribute
//	public SettlementBillInfo get(@RequestParam(required=false) String id) {
//		SettlementBillInfo entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = settlementBillInfoService.get(id);
//		}
//		if (entity == null){
//			entity = new SettlementBillInfo();
//		}
//		return entity;
//	}
//	
//	@RequiresPermissions("billinfo:settlementBillInfo:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(SettlementBillInfo settlementBillInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<SettlementBillInfo> page = settlementBillInfoService.findPage(new Page<SettlementBillInfo>(request, response), settlementBillInfo); 
//		model.addAttribute("page", page);
//		return "sc/billinfo/settlementBillInfoList";
//	}
//
//	@RequiresPermissions("billinfo:settlementBillInfo:view")
//	@RequestMapping(value = "form")
//	public String form(SettlementBillInfo settlementBillInfo, Model model) {
//		model.addAttribute("settlementBillInfo", settlementBillInfo);
//		return "sc/billinfo/settlementBillInfoForm";
//	}
//
//	@RequiresPermissions("billinfo:settlementBillInfo:edit")
//	@RequestMapping(value = "save")
//	public String save(SettlementBillInfo settlementBillInfo, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, settlementBillInfo)){
//			return form(settlementBillInfo, model);
//		}
//		settlementBillInfoService.save(settlementBillInfo);
//		Long billid = settlementBillInfo.getBillid();
//		addMessage(redirectAttributes, "保存billinfo成功");
//		return "redirect:"+Global.getAdminPath()+"/billinfo/settlementBillInfo/?billid="+billid+"&repage";
//	}
//	
//	@RequiresPermissions("billinfo:settlementBillInfo:edit")
//	@RequestMapping(value = "delete")
//	public String delete(SettlementBillInfo settlementBillInfo, RedirectAttributes redirectAttributes) {
//		settlementBillInfoService.delete(settlementBillInfo);
//		addMessage(redirectAttributes, "删除billinfo成功");
//		return "redirect:"+Global.getAdminPath()+"/billinfo/settlementBillInfo/?repage";
//	}
//
//}