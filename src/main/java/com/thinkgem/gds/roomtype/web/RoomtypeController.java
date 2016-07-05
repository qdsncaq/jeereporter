/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomtype.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.gds.roomtype.entity.RoomtypePrices;
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
import com.thinkgem.gds.roomtype.entity.Roomtype;
import com.thinkgem.gds.roomtype.service.RoomtypeService;

import java.util.List;

/**
 * 房型Controller
 * @author jiajianhong
 * @version 2016-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/roomtype/roomtype")
public class RoomtypeController extends BaseController {

	@Autowired
	private RoomtypeService roomtypeService;
	
	@ModelAttribute
	public Roomtype get(@RequestParam(required=false) String id) {
		Roomtype entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = roomtypeService.get(id);
		}
		if (entity == null){
			entity = new Roomtype();
		}
		return entity;
	}
	
	@RequiresPermissions("roomtype:roomtype:view")
	@RequestMapping(value = {"list", ""})
	public String list(Roomtype roomtype, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Roomtype> page = roomtypeService.findPage(new Page<Roomtype>(request, response), roomtype); 
		model.addAttribute("page", page);
		return "gds/roomtype/roomtypeList";
	}

	@RequiresPermissions("roomtype:roomtype:view")
	@RequestMapping(value = "form")
	public String form(Roomtype roomtype, Model model) {
		model.addAttribute("roomtype", roomtype);
		return "gds/roomtype/roomtypeForm";
	}

	@RequiresPermissions("roomtype:roomtype:edit")
	@RequestMapping(value = "save")
	public String save(Roomtype roomtype, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, roomtype)){
			return form(roomtype, model);
		}
		roomtypeService.save(roomtype);
		addMessage(redirectAttributes, "保存房型成功");
		return "redirect:"+Global.getAdminPath()+"/roomtype/roomtype/?repage";
	}
	
	@RequiresPermissions("roomtype:roomtype:edit")
	@RequestMapping(value = "delete")
	public String delete(Roomtype roomtype, RedirectAttributes redirectAttributes) {
		roomtypeService.delete(roomtype);
		addMessage(redirectAttributes, "删除房型成功");
		return "redirect:"+Global.getAdminPath()+"/roomtype/roomtype/?repage";
	}

    @RequiresPermissions("hotel:hotel:view")
    @RequestMapping(value = "price")
    public String price(Roomtype roomtype, Model model, RedirectAttributes redirectAttributes) {

        List<RoomtypePrices> dailyRateList = roomtypeService.findDailyRate(roomtype);
        model.addAttribute("dailyRateList", dailyRateList);

        List<RoomtypePrices> rackRateList = roomtypeService.findRackRate(roomtype);
        model.addAttribute("rackRateList", rackRateList);

        List<RoomtypePrices> weekRateList = roomtypeService.findWeekRate(roomtype);
        model.addAttribute("weekRateList", weekRateList);

        return "gds/hotel/roomtypePrices";
    }

}