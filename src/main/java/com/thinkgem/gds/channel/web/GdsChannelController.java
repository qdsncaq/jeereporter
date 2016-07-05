/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.channel.web;

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

import com.fangbaba.gds.enums.GdsChannelValidStateEnum;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.gds.channel.entity.GdsChannel;
import com.thinkgem.gds.channel.service.GdsChannelService;

/**
 * 渠道管理Controller
 * @author tankai
 * @version 2016-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/channel/gdsChannel")
public class GdsChannelController extends BaseController {

	@Autowired
	private GdsChannelService gdsChannelService;
	
	@ModelAttribute
	public GdsChannel get(@RequestParam(required=false) String id) {
		GdsChannel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gdsChannelService.get(id);
		}
		if (entity == null){
			entity = new GdsChannel();
		}
		return entity;
	}
	
	@RequiresPermissions("channel:gdsChannel:view")
	@RequestMapping(value = {"list", ""})
	public String list(GdsChannel gdsChannel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GdsChannel> page = gdsChannelService.findPage(new Page<GdsChannel>(request, response), gdsChannel); 
		model.addAttribute("page", page);
		return "gds/channel/gdsChannelList";
	}

	@RequiresPermissions("channel:gdsChannel:view")
	@RequestMapping(value = "form")
	public String form(GdsChannel gdsChannel, Model model) {
		model.addAttribute("gdsChannel", gdsChannel);
		return "gds/channel/gdsChannelForm";
	}

	@RequiresPermissions("channel:gdsChannel:edit")
	@RequestMapping(value = "save")
	public String save(GdsChannel gdsChannel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gdsChannel)){
			return form(gdsChannel, model);
		}
		gdsChannelService.save(gdsChannel);
		addMessage(redirectAttributes, "保存渠道管理成功");
		return "redirect:"+Global.getAdminPath()+"/channel/gdsChannel/?repage";
	}
	@RequiresPermissions("channel:gdsChannel:edit")
	@RequestMapping(value = "open")
	public String open(GdsChannel gdsChannel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gdsChannel)){
			return form(gdsChannel, model);
		}
		gdsChannel.setState(GdsChannelValidStateEnum.on.getId()+"");
		gdsChannelService.openOrClose(gdsChannel);
		addMessage(redirectAttributes, "保存渠道管理成功");
		return "redirect:"+Global.getAdminPath()+"/channel/gdsChannel/?repage";
	}
	@RequiresPermissions("channel:gdsChannel:edit")
	@RequestMapping(value = "close")
	public String close(GdsChannel gdsChannel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gdsChannel)){
			return form(gdsChannel, model);
		}
		gdsChannel.setState(GdsChannelValidStateEnum.off.getId()+"");
		gdsChannelService.openOrClose(gdsChannel);
		addMessage(redirectAttributes, "保存渠道管理成功");
		return "redirect:"+Global.getAdminPath()+"/channel/gdsChannel/?repage";
	}
	
	@RequiresPermissions("channel:gdsChannel:edit")
	@RequestMapping(value = "delete")
	public String delete(GdsChannel gdsChannel, RedirectAttributes redirectAttributes) {
		gdsChannelService.delete(gdsChannel);
		addMessage(redirectAttributes, "删除渠道管理成功");
		return "redirect:"+Global.getAdminPath()+"/channel/gdsChannel/?repage";
	}

}