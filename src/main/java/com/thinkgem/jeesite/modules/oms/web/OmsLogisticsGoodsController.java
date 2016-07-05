/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.web;

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
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticsGoods;
import com.thinkgem.jeesite.modules.oms.service.OmsLogisticsGoodsService;

/**
 * 商品物流数据Controller
 * @author oms
 * @version 2016-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/oms/omsLogisticsGoods")
public class OmsLogisticsGoodsController extends BaseController {

	@Autowired
	private OmsLogisticsGoodsService omsLogisticsGoodsService;
	
	@ModelAttribute
	public OmsLogisticsGoods get(@RequestParam(required=false) String id) {
		OmsLogisticsGoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = omsLogisticsGoodsService.get(id);
		}
		if (entity == null){
			entity = new OmsLogisticsGoods();
		}
		return entity;
	}
	
	@RequiresPermissions("oms:omsLogisticsGoods:view")
	@RequestMapping(value = {"list", ""})
	public String list(OmsLogisticsGoods omsLogisticsGoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OmsLogisticsGoods> page = omsLogisticsGoodsService.findPage(new Page<OmsLogisticsGoods>(request, response), omsLogisticsGoods); 
		model.addAttribute("page", page);
		return "modules/oms/omsLogisticsGoodsList";
	}

	@RequiresPermissions("oms:omsLogisticsGoods:view")
	@RequestMapping(value = "form")
	public String form(OmsLogisticsGoods omsLogisticsGoods, Model model) {
		model.addAttribute("omsLogisticsGoods", omsLogisticsGoods);
		return "modules/oms/omsLogisticsGoodsForm";
	}

	@RequiresPermissions("oms:omsLogisticsGoods:edit")
	@RequestMapping(value = "save")
	public String save(OmsLogisticsGoods omsLogisticsGoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, omsLogisticsGoods)){
			return form(omsLogisticsGoods, model);
		}
		omsLogisticsGoodsService.save(omsLogisticsGoods);
		addMessage(redirectAttributes, "保存商品物流数据成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsLogisticsGoods/?repage";
	}
	
	@RequiresPermissions("oms:omsLogisticsGoods:edit")
	@RequestMapping(value = "delete")
	public String delete(OmsLogisticsGoods omsLogisticsGoods, RedirectAttributes redirectAttributes) {
		omsLogisticsGoodsService.delete(omsLogisticsGoods);
		addMessage(redirectAttributes, "删除商品物流数据成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsLogisticsGoods/?repage";
	}

}