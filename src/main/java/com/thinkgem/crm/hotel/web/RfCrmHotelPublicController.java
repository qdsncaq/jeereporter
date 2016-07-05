/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.web;

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
import com.thinkgem.crm.hotel.entity.RfCrmHotelPublic;
import com.thinkgem.crm.hotel.service.RfCrmHotelPublicService;

/**
 * CRM酒店公私海信息Controller
 * @author 段文昌
 * @version 2016-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/hotel/rfCrmHotelPublic")
public class RfCrmHotelPublicController extends BaseController {

	@Autowired
	private RfCrmHotelPublicService rfCrmHotelPublicService;
	
	@ModelAttribute
	public RfCrmHotelPublic get(@RequestParam(required=false) String id) {
		RfCrmHotelPublic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmHotelPublicService.get(id);
		}
		if (entity == null){
			entity = new RfCrmHotelPublic();
		}
		return entity;
	}
	
	@RequiresPermissions("hotel:rfCrmHotelPublic:view")
	@RequestMapping(value = {"list", ""})
	public String list(RfCrmHotelPublic rfCrmHotelPublic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmHotelPublic> page = rfCrmHotelPublicService.findPage(new Page<RfCrmHotelPublic>(request, response), rfCrmHotelPublic); 
		model.addAttribute("page", page);
		return "crm/hotel/rfCrmHotelPublicList";
	}

	@RequiresPermissions("hotel:rfCrmHotelPublic:view")
	@RequestMapping(value = "form")
	public String form(RfCrmHotelPublic rfCrmHotelPublic, Model model) {
		model.addAttribute("rfCrmHotelPublic", rfCrmHotelPublic);
		return "crm/hotel/rfCrmHotelPublicForm";
	}

	@RequiresPermissions("hotel:rfCrmHotelPublic:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmHotelPublic rfCrmHotelPublic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rfCrmHotelPublic)){
			return form(rfCrmHotelPublic, model);
		}
		rfCrmHotelPublicService.save(rfCrmHotelPublic);
		addMessage(redirectAttributes, "保存酒店成功");
		return "redirect:"+Global.getAdminPath()+"/crm/hotel/rfCrmHotelPublic/?repage";
	}

	/**
	 * 退还公海
	 * @param rfCrmHotelPublic
	 * @param model
	 * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("hotel:rfCrmHotelPublic:return")
	@RequestMapping(value = "returnPublic")
	public String returnPublic(RfCrmHotelPublic rfCrmHotelPublic, Model model, RedirectAttributes redirectAttributes) {
		rfCrmHotelPublicService.returnPublic(rfCrmHotelPublic);
		addMessage(redirectAttributes, "释放公海成功");
		return "redirect:"+Global.getAdminPath()+"/hotel/hotel/list?repage";
	}
	
	@RequiresPermissions("hotel:rfCrmHotelPublic:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmHotelPublic rfCrmHotelPublic, RedirectAttributes redirectAttributes) {
		rfCrmHotelPublicService.delete(rfCrmHotelPublic);
		addMessage(redirectAttributes, "删除酒店成功");
		return "redirect:"+Global.getAdminPath()+"/crm/hotel/rfCrmHotelPublic/?repage";
	}

}