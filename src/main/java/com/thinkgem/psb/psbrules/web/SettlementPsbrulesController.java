/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbrules.web;

import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.psb.psbinfo.entity.SettlementPsbinfo;
import com.thinkgem.psb.psbinfo.service.SettlementPsbinfoService;
import com.thinkgem.psb.psbrules.entity.SettlementPsbrules;
import com.thinkgem.psb.psbrules.service.SettlementPsbrulesService;

/**
 * psbrulesController
 * @author lm
 * @version 2016-04-06
 */
@Controller
@RequestMapping(value = "${adminPath}/psbrules/settlementPsbrules")
public class SettlementPsbrulesController extends BaseController {

	@Autowired
	private SettlementPsbrulesService settlementPsbrulesService;
	
	@Autowired
	private SettlementPsbinfoService settlementPsbinfoService;
	
	@ModelAttribute
	public SettlementPsbrules get(@RequestParam(required=false) String id) {
		SettlementPsbrules entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementPsbrulesService.get(id);
		}
		if (entity == null){
			entity = new SettlementPsbrules();
		}
		return entity;
	}
	
	@RequiresPermissions("psbrules:settlementPsbrules:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementPsbrules settlementPsbrules, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementPsbrules> page = settlementPsbrulesService.findPage(new Page<SettlementPsbrules>(request, response), settlementPsbrules); 
		
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		model.addAttribute("page", page);
		return "psb/psbrules/settlementPsbrulesList";
	}

	@RequiresPermissions("psbrules:settlementPsbrules:view")
	@RequestMapping(value = "form")
	public String form(SettlementPsbrules settlementPsbrules, Model model) {
		model.addAttribute("settlementPsbrules", settlementPsbrules);
		
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		return "psb/psbrules/settlementPsbrulesForm";
	}

	@RequiresPermissions("psbrules:settlementPsbrules:edit")
	@RequestMapping(value = "save")
	public String save(SettlementPsbrules settlementPsbrules, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementPsbrules)){
			return form(settlementPsbrules, model);
		}
		if(settlementPsbrules.getId() == null || settlementPsbrules.getId().equalsIgnoreCase("")){
			settlementPsbrules.setDelflag(1);
			settlementPsbrules.setCreatetime(new Date());
		}
		settlementPsbrules.setUpdatetime(new Date());
		String fee10 = settlementPsbrules.getFee10();
		if(fee10.contains("n*")){
			fee10 = fee10.replace("n*", "*roomnums*");
			settlementPsbrules.setFee10(fee10);
		}
		settlementPsbrulesService.save(settlementPsbrules);
		addMessage(redirectAttributes, "保存psbrules成功");
		return "redirect:"+Global.getAdminPath()+"/psbrules/settlementPsbrules/list?repage";
	}
	
	@RequiresPermissions("psbrules:settlementPsbrules:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementPsbrules settlementPsbrules, RedirectAttributes redirectAttributes) {
		settlementPsbrules.setDelflag(2);// 删除标识
		settlementPsbrules.setUpdatetime(new Date());
		settlementPsbrulesService.update(settlementPsbrules);
		addMessage(redirectAttributes, "删除psbrules成功");
		return "redirect:"+Global.getAdminPath()+"/psbrules/settlementPsbrules/list?repage";
	}
	
	/**
	 * 
	 * @param settlementAccntBill
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("psbrules:settlementPsbrules:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(SettlementPsbrules settlementPsbrules, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "PSB科目列表.xlsx";
    		List<SettlementPsbrules> list = settlementPsbrulesService.findList(settlementPsbrules);
    		new ExportExcel("PSB科目", SettlementPsbrules.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "PSB科目excel导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/psbrules/settlementPsbrules/?repage";
	}
	
	/**
	 * 查询PSB下所有有效规则
	 * @param settlementPsbrules
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"serPsbRule", ""})
	@ResponseBody
	public Object serPsbRule(SettlementPsbrules settlementPsbrules, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SettlementPsbrules> psbRules = settlementPsbrulesService.findRulesByPsbid(settlementPsbrules.getPsbid());
		return psbRules;
	}

}