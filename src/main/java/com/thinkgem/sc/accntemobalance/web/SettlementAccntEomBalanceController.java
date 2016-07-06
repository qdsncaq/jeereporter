///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.accntemobalance.web;
//
//import java.util.List;
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
//import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
//import com.thinkgem.sc.accntemobalance.entity.SettlementAccntEomBalance;
//import com.thinkgem.sc.accntemobalance.service.SettlementAccntEomBalanceService;
//
///**
// * settlementAccntEmobalanceController
// * @author lm
// * @version 2016-06-04
// */
//@Controller
//@RequestMapping(value = "${adminPath}/settlementaccntemobalance/settlementAccntEomBalance")
//public class SettlementAccntEomBalanceController extends BaseController {
//
//	@Autowired
//	private SettlementAccntEomBalanceService settlementAccntEomBalanceService;
//	
//	@ModelAttribute
//	public SettlementAccntEomBalance get(@RequestParam(required=false) String id) {
//		SettlementAccntEomBalance entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = settlementAccntEomBalanceService.get(id);
//		}
//		if (entity == null){
//			entity = new SettlementAccntEomBalance();
//		}
//		return entity;
//	}
//	
//	@RequiresPermissions("settlementaccntemobalance:settlementAccntEomBalance:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(SettlementAccntEomBalance settlementAccntEomBalance, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<SettlementAccntEomBalance> page = settlementAccntEomBalanceService.findPage(new Page<SettlementAccntEomBalance>(request, response), settlementAccntEomBalance); 
//		model.addAttribute("page", page);
//		return "sc/accntemobalance/settlementAccntEomBalanceList";
//	}
//
//	@RequiresPermissions("settlementaccntemobalance:settlementAccntEomBalance:view")
//	@RequestMapping(value = "form")
//	public String form(SettlementAccntEomBalance settlementAccntEomBalance, Model model) {
//		model.addAttribute("settlementAccntEomBalance", settlementAccntEomBalance);
//		return "sc/accntemobalance/settlementAccntEomBalanceForm";
//	}
//	
//	@RequiresPermissions("settlementaccntemobalance:settlementAccntEomBalance:view")
//	@RequestMapping(value = "exportexcel")
//	public String exportexcel(SettlementAccntEomBalance settlementAccntEomBalance, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		try {
//            String fileName = "固化账户余额列表.xlsx";
//    		List<SettlementAccntEomBalance> list = settlementAccntEomBalanceService.findList(settlementAccntEomBalance);
//    		new ExportExcel("固化账户余额列表", SettlementAccntEomBalance.class, 1).setDataList(list).write(response, fileName).dispose();
//    		return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			addMessage(redirectAttributes, "固化账户余额列表excel导出失败！失败信息："+e.getMessage());
//		}
//		return "redirect:"+Global.getAdminPath()+"/accntemobalance/settlementAccntEomBalance/?repage";
//	}
//
//
//	@RequiresPermissions("settlementaccntemobalance:settlementAccntEomBalance:edit")
//	@RequestMapping(value = "save")
//	public String save(SettlementAccntEomBalance settlementAccntEomBalance, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, settlementAccntEomBalance)){
//			return form(settlementAccntEomBalance, model);
//		}
//		settlementAccntEomBalanceService.save(settlementAccntEomBalance);
//		addMessage(redirectAttributes, "保存settlementAccntEmobalance成功");
//		return "redirect:"+Global.getAdminPath()+"/accntemobalance/settlementAccntEomBalance/?repage";
//	}
//	
//	@RequiresPermissions("settlementaccntemobalance:settlementAccntEomBalance:edit")
//	@RequestMapping(value = "delete")
//	public String delete(SettlementAccntEomBalance settlementAccntEomBalance, RedirectAttributes redirectAttributes) {
//		settlementAccntEomBalanceService.delete(settlementAccntEomBalance);
//		addMessage(redirectAttributes, "删除settlementAccntEmobalance成功");
//		return "redirect:"+Global.getAdminPath()+"/accntemobalance/settlementAccntEomBalance/?repage";
//	}
//
//}