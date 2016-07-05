/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.distributprice.web;

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
import com.thinkgem.gds.distributprice.entity.RackDistributePrice;
import com.thinkgem.gds.distributprice.service.RackDistributePriceService;

/**
 * 酒店分销价Controller
 * @author zhaochuanbin
 * @version 2016-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/distributprice/rackDistributePrice")
public class RackDistributePriceController extends BaseController {

	@Autowired
	private RackDistributePriceService rackDistributePriceService;
	
	@ModelAttribute
	public RackDistributePrice get(@RequestParam(required=false) String id) {
		RackDistributePrice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rackDistributePriceService.get(id);
		}
		if (entity == null){
			entity = new RackDistributePrice();
		}
		return entity;
	}
	
	@RequiresPermissions("distributprice:rackDistributePrice:view")
	@RequestMapping(value = {"list", ""})
	public String list(RackDistributePrice rackDistributePrice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RackDistributePrice> page = rackDistributePriceService.findPage(new Page<RackDistributePrice>(request, response), rackDistributePrice); 
		model.addAttribute("page", page);
		return "gds/distributprice/rackDistributePriceList";
	}

	@RequiresPermissions("distributprice:rackDistributePrice:view")
	@RequestMapping(value = "form")
	public String form(RackDistributePrice rackDistributePrice, Model model) {
		model.addAttribute("rackDistributePrice", rackDistributePrice);
		return "gds/distributprice/rackDistributePriceForm";
	}

	@RequiresPermissions("distributprice:rackDistributePrice:edit")
	@RequestMapping(value = "save")
	public String save(RackDistributePrice rackDistributePrice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rackDistributePrice)){
			return form(rackDistributePrice, model);
		}
		
		if(rackDistributePrice.getIsNewRecord()){
		    RackDistributePrice reObj = rackDistributePriceService.getbyhotelandroomtype(rackDistributePrice.getHotelid(),rackDistributePrice.getRoomtypeid());
		    if(reObj!=null){
	            addMessage(redirectAttributes, "该酒店房型的分销价已存在，请不要重新创建！");
	            return "redirect:"+Global.getAdminPath()+"/distributprice/rackDistributePrice/?repage";
	        }else{
	            rackDistributePriceService.save(rackDistributePrice);
	            addMessage(redirectAttributes, "保存酒店分销价成功");
	            return "redirect:"+Global.getAdminPath()+"/distributprice/rackDistributePrice/?repage";
	        }
		}else{
		    rackDistributePriceService.save(rackDistributePrice);
            addMessage(redirectAttributes, "保存酒店分销价成功");
            return "redirect:"+Global.getAdminPath()+"/distributprice/rackDistributePrice/?repage";
		}
	}
	
	@RequiresPermissions("distributprice:rackDistributePrice:edit")
	@RequestMapping(value = "delete")
	public String delete(RackDistributePrice rackDistributePrice, RedirectAttributes redirectAttributes) {
		rackDistributePriceService.delete(rackDistributePrice);
		addMessage(redirectAttributes, "删除酒店分销价成功");
		return "redirect:"+Global.getAdminPath()+"/distributprice/rackDistributePrice/?repage";
	}

}