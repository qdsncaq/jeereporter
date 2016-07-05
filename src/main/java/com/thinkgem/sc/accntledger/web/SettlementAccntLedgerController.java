/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntledger.web;

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
import com.thinkgem.sc.accntledger.entity.SettlementAccntLedger;
import com.thinkgem.sc.accntledger.service.SettlementAccntLedgerService;

/**
 * accntledgerController
 * @author lm
 * @version 2016-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/accntledger/settlementAccntLedger")
public class SettlementAccntLedgerController extends BaseController {

	@Autowired
	private SettlementAccntLedgerService settlementAccntLedgerService;
	
	@ModelAttribute
	public SettlementAccntLedger get(@RequestParam(required=false) String id) {
		SettlementAccntLedger entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementAccntLedgerService.get(id);
		}
		if (entity == null){
			entity = new SettlementAccntLedger();
		}
		return entity;
	}
	
	@RequiresPermissions("accntledger:settlementAccntLedger:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementAccntLedger settlementAccntLedger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementAccntLedger> page = settlementAccntLedgerService.findPage(new Page<SettlementAccntLedger>(request, response), settlementAccntLedger); 
		model.addAttribute("page", page);
		return "sc/accntledger/settlementAccntLedgerList";
	}

	@RequiresPermissions("accntledger:settlementAccntLedger:view")
	@RequestMapping(value = "form")
	public String form(SettlementAccntLedger settlementAccntLedger, Model model) {
		model.addAttribute("settlementAccntLedger", settlementAccntLedger);
		return "sc/accntledger/settlementAccntLedgerForm";
	}

	@RequiresPermissions("accntledger:settlementAccntLedger:edit")
	@RequestMapping(value = "save")
	public String save(SettlementAccntLedger settlementAccntLedger, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementAccntLedger)){
			return form(settlementAccntLedger, model);
		}
		settlementAccntLedgerService.save(settlementAccntLedger);
		addMessage(redirectAttributes, "保存accntledger成功");
		return "redirect:"+Global.getAdminPath()+"/accntledger/settlementAccntLedger/?repage";
	}
	
	@RequiresPermissions("accntledger:settlementAccntLedger:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementAccntLedger settlementAccntLedger, RedirectAttributes redirectAttributes) {
		settlementAccntLedgerService.delete(settlementAccntLedger);
		addMessage(redirectAttributes, "删除accntledger成功");
		return "redirect:"+Global.getAdminPath()+"/accntledger/settlementAccntLedger/?repage";
	}
    
    @RequiresPermissions("accntledger:settlementAccntLedger:view")
    @RequestMapping(value = "exportexcel")
    public String exportexcel(SettlementAccntLedger settlementAccntLedger, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "账户余额列表.xlsx";
            List<SettlementAccntLedger> list = settlementAccntLedgerService.findList(settlementAccntLedger);
            new ExportExcel("账户余额", SettlementAccntLedger.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "账户余额excel导出失败！失败信息："+e.getMessage());
        }
        return "redirect:"+Global.getAdminPath()+"/accntledger/settlementAccntLedger/?repage";
    }

}