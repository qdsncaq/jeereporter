/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbinfo.web;

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

/**
 * psbinfoController
 * @author lm
 * @version 2016-04-06
 */
@Controller
@RequestMapping(value = "${adminPath}/psbinfo/settlementPsbinfo")
public class SettlementPsbinfoController extends BaseController {

	@Autowired
	private SettlementPsbinfoService settlementPsbinfoService;
	
	@ModelAttribute
	public SettlementPsbinfo get(@RequestParam(required=false) String id) {
		SettlementPsbinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementPsbinfoService.get(id);
		}
		if (entity == null){
			entity = new SettlementPsbinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("psbinfo:settlementPsbinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementPsbinfo settlementPsbinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementPsbinfo> page = settlementPsbinfoService.findPage(new Page<SettlementPsbinfo>(request, response), settlementPsbinfo); 
		model.addAttribute("page", page);
		return "psb/psbinfo/settlementPsbinfoList";
	}
	
	@RequiresPermissions("psbinfo:settlementPsbinfo:view")
	@RequestMapping(value = {"serAllPsb", ""})
	@ResponseBody
	public List<SettlementPsbinfo> serAllPsb(SettlementPsbinfo settlementPsbinfo, Model model) {
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		return psbList;
	}

	@RequiresPermissions("psbinfo:settlementPsbinfo:view")
	@RequestMapping(value = "form")
	public String form(SettlementPsbinfo settlementPsbinfo, Model model) {
		model.addAttribute("settlementPsbinfo", settlementPsbinfo);
		return "psb/psbinfo/settlementPsbinfoForm";
	}

	@RequiresPermissions("psbinfo:settlementPsbinfo:edit")
	@RequestMapping(value = "save")
	public String save(SettlementPsbinfo settlementPsbinfo, Model model, RedirectAttributes redirectAttributes) {
		String psbname = settlementPsbinfo.getPsbname();
		SettlementPsbinfo psbinfo = new SettlementPsbinfo();
		psbinfo.setPsbname(psbname);
		List<SettlementPsbinfo> list = settlementPsbinfoService.findList(psbinfo);
		if(list == null || list.size() == 0){
			settlementPsbinfo.setDelflag(1);
			settlementPsbinfo.setCreatetime(new Date());
		}
		settlementPsbinfo.setUpdatetime(new Date());
		if (!beanValidator(model, settlementPsbinfo)){
			return form(settlementPsbinfo, model);
		}
		settlementPsbinfoService.save(settlementPsbinfo);
		addMessage(redirectAttributes, "保存psbinfo成功");
		return "redirect:"+Global.getAdminPath()+"/psbinfo/settlementPsbinfo/list?repage";
	}
	
	@RequiresPermissions("psbinfo:settlementPsbinfo:edit")
	@RequestMapping(value = "update")
	public String update(SettlementPsbinfo settlementPsbinfo, Model model, RedirectAttributes redirectAttributes) {
		settlementPsbinfo.setUpdatetime(new Date());
		settlementPsbinfoService.save(settlementPsbinfo);
		addMessage(redirectAttributes, "保存psbinfo成功");
		return "redirect:"+Global.getAdminPath()+"/psbinfo/settlementPsbinfo/list?repage";
	}
	
	@RequiresPermissions("psbinfo:settlementPsbinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementPsbinfo settlementPsbinfo, RedirectAttributes redirectAttributes) {
		settlementPsbinfo.setDelflag(2);// 删除标识
		settlementPsbinfoService.update(settlementPsbinfo);
		addMessage(redirectAttributes, "删除psb厂家信息成功");
		return "redirect:"+Global.getAdminPath()+"/psbinfo/settlementPsbinfo/list?repage";
	}
	
    @RequiresPermissions("psbinfo:settlementPsbinfo:view")
    @RequestMapping(value = "exportexcel")
    public String exportexcel(SettlementPsbinfo settlementPsbinfo, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "PSB厂家信息列表.xlsx";
            List<SettlementPsbinfo> list = settlementPsbinfoService.findList(settlementPsbinfo);
            new ExportExcel("PSB厂家信息", SettlementPsbinfo.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "PSB厂家信息excel导出失败！失败信息："+e.getMessage());
        }
        return "redirect:"+Global.getAdminPath()+"/psbinfo/settlementPsbinfo/?repage";
    }
	
}