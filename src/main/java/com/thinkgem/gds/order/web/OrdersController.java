/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.order.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.gds.order.entity.Orders;
import com.thinkgem.gds.order.service.OrdersService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;

/**
 * orderController
 * @author tankai
 * @version 2016-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orders")
public class OrdersController extends BaseController {

	@Autowired
	private OrdersService ordersService;
	
	@ModelAttribute
	public Orders get(@RequestParam(required=false) String id) {
		Orders entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ordersService.get(id);
		}
		if (entity == null){
			entity = new Orders();
		}
		return entity;
	}
	
	@RequiresPermissions("order:orders:view")
	@RequestMapping(value = {"list", ""})
	public String list(Orders orders, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Orders> page1 = new Page<Orders>(request, response);
		page1.setOrderBy("createtime desc");
		Page<Orders> page = ordersService.findPage(page1, orders); 
		model.addAttribute("page", page);
		return "gds/order/ordersList";
	}

	@RequiresPermissions("order:orders:view")
	@RequestMapping(value = "form")
	public String form(Orders orders, Model model) {
		model.addAttribute("orders", orders);
		return "gds/order/ordersForm";
	}
	@RequiresPermissions("order:orders:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(Orders orders,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "order_"+DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm")+".xlsx";
    		List<Orders> list = ordersService.findList(orders);
    		new ExportExcel("订单数据", Orders.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "订单数据excel导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/order/orders/?repage";
	}

	@RequiresPermissions("order:orders:edit")
	@RequestMapping(value = "save")
	public String save(Orders orders, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orders)){
			return form(orders, model);
		}
		ordersService.save(orders);
		addMessage(redirectAttributes, "保存order成功");
		return "redirect:"+Global.getAdminPath()+"/order/orders/?repage";
	}
	
	@RequiresPermissions("order:orders:edit")
	@RequestMapping(value = "delete")
	public String delete(Orders orders, RedirectAttributes redirectAttributes) {
		ordersService.delete(orders);
		addMessage(redirectAttributes, "删除order成功");
		return "redirect:"+Global.getAdminPath()+"/order/orders/?repage";
	}

}