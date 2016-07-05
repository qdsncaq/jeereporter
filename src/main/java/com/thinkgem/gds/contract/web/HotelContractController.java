/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.contract.web;

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
import com.thinkgem.gds.contract.entity.HotelContract;
import com.thinkgem.gds.contract.service.HotelContractService;

/**
 * 合同Controller
 * @author zhaochuanbin
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/contract/hotelContract")
public class HotelContractController extends BaseController {

	@Autowired
	private HotelContractService hotelContractService;
	
	@ModelAttribute
	public HotelContract get(@RequestParam(required=false) String id) {
		HotelContract entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hotelContractService.get(id);
		}
		if (entity == null){
			entity = new HotelContract();
		}
		return entity;
	}
	
	@RequiresPermissions("contract:hotelContract:view")
	@RequestMapping(value = {"list", ""})
	public String list(HotelContract hotelContract, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HotelContract> page = hotelContractService.findPage(new Page<HotelContract>(request, response), hotelContract); 
		model.addAttribute("page", page);
		return "gds/contract/hotelContractList";
	}

	@RequiresPermissions("contract:hotelContract:view")
	@RequestMapping(value = "form")
	public String form(HotelContract hotelContract, Model model) {
		model.addAttribute("hotelContract", hotelContract);
		return "gds/contract/hotelContractForm";
	}

	@RequiresPermissions("contract:hotelContract:edit")
	@RequestMapping(value = "save")
	public String save(HotelContract hotelContract, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hotelContract)){
			return form(hotelContract, model);
		}
		hotelContractService.save(hotelContract);
		addMessage(redirectAttributes, "保存合同成功");
		return "redirect:"+Global.getAdminPath()+"/contract/hotelContract/?repage";
	}
	
	@RequiresPermissions("contract:hotelContract:edit")
	@RequestMapping(value = "delete")
	public String delete(HotelContract hotelContract, RedirectAttributes redirectAttributes) {
		hotelContractService.delete(hotelContract);
		addMessage(redirectAttributes, "删除合同成功");
		return "redirect:"+Global.getAdminPath()+"/contract/hotelContract/?repage";
	}

}