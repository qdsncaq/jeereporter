/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomprice.web;

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
import com.thinkgem.gds.roomprice.entity.Roomprice;
import com.thinkgem.gds.roomprice.service.RoompriceService;

/**
 * 房型价格Controller
 * @author jiajianhong
 * @version 2016-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/roomprice/roomprice")
public class RoompriceController extends BaseController {

	@Autowired
	private RoompriceService roompriceService;
	
	@ModelAttribute
	public Roomprice get(@RequestParam(required=false) String id) {
		Roomprice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = roompriceService.get(id);
		}
		if (entity == null){
			entity = new Roomprice();
		}
		return entity;
	}
	
	@RequiresPermissions("roomprice:roomprice:view")
	@RequestMapping(value = {"list", ""})
	public String list(Roomprice roomprice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Roomprice> page = roompriceService.findPage(new Page<Roomprice>(request, response), roomprice); 
		model.addAttribute("page", page);
		return "gds/roomprice/roompriceList";
	}

	@RequiresPermissions("roomprice:roomprice:view")
	@RequestMapping(value = "form")
	public String form(Roomprice roomprice, Model model) {
		model.addAttribute("roomprice", roomprice);
		return "gds/roomprice/roompriceForm";
	}

	@RequiresPermissions("roomprice:roomprice:edit")
	@RequestMapping(value = "save")
	public String save(Roomprice roomprice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, roomprice)){
			return form(roomprice, model);
		}
		roompriceService.save(roomprice);
		addMessage(redirectAttributes, "保存房型价格成功");
		return "redirect:"+Global.getAdminPath()+"/roomprice/roomprice/?repage";
	}
	
	@RequiresPermissions("roomprice:roomprice:edit")
	@RequestMapping(value = "delete")
	public String delete(Roomprice roomprice, RedirectAttributes redirectAttributes) {
		roompriceService.delete(roomprice);
		addMessage(redirectAttributes, "删除房型价格成功");
		return "redirect:"+Global.getAdminPath()+"/roomprice/roomprice/?repage";
	}

}