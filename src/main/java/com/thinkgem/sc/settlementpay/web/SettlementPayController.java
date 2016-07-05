/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.settlementpay.web;

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
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.sc.accntwithdraw.entity.SettlementAccountWithdrawcash;
import com.thinkgem.sc.settlementpay.entity.SettlementPay;
import com.thinkgem.sc.settlementpay.service.SettlementPayService;

/**
 * settlementpayController
 * @author lm
 * @version 2016-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/settlementpay/settlementPay")
public class SettlementPayController extends BaseController {

	@Autowired
	private SettlementPayService settlementPayService;
	
	@ModelAttribute
	public SettlementPay get(@RequestParam(required=false) String id) {
		SettlementPay entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementPayService.get(id);
		}
		if (entity == null){
			entity = new SettlementPay();
		}
		return entity;
	}
	
	@RequiresPermissions("settlementpay:settlementPay:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementPay settlementPay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementPay> page = settlementPayService.findPage(new Page<SettlementPay>(request, response), settlementPay); 
		model.addAttribute("page", page);
		return "sc/settlementpay/settlementPayList";
	}

	@RequiresPermissions("settlementpay:settlementPay:view")
	@RequestMapping(value = "form")
	public String form(SettlementPay settlementPay, Model model) {
		model.addAttribute("settlementPay", settlementPay);
		return "sc/settlementpay/settlementPayForm";
	}

	@RequiresPermissions("settlementpay:settlementPay:edit")
	@RequestMapping(value = "save")
	public String save(SettlementPay settlementPay, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementPay)){
			return form(settlementPay, model);
		}
		settlementPayService.save(settlementPay);
		addMessage(redirectAttributes, "保存settlementpay成功");
		return "redirect:"+Global.getAdminPath()+"/settlementpay/settlementPay/?repage";
	}
	
	@RequiresPermissions("settlementpay:settlementPay:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementPay settlementPay, RedirectAttributes redirectAttributes) {
		settlementPayService.delete(settlementPay);
		addMessage(redirectAttributes, "删除settlementpay成功");
		return "redirect:"+Global.getAdminPath()+"/settlementpay/settlementPay/?repage";
	}
	
	@RequiresPermissions("settlementpay:settlementPay:view")
	@RequestMapping(value = {"exportexcel", ""})
	public String exportexcel(SettlementPay settlementPay, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "支付记录列表.xlsx";
    		List<SettlementPay> list = settlementPayService.findList(settlementPay);
    		new ExportExcel("支付记录", SettlementPay.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "支付记录excel导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/settlementpay/settlementPayList/?repage";
	}

}