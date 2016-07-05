/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.orderdetail.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fangbaba.datacenter.enums.BussinssTypeEnum;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.lz.mongo.bislog.BisLog;
import com.lz.mongo.bislog.BisLogDelegate;
import com.thinkgem.gds.orderdetail.entity.Orderdetails;
import com.thinkgem.gds.orderdetail.service.OrderdetailsService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * orderdetailController
 * @author tankai
 * @version 2016-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/orderdetail/orderdetails")
public class OrderdetailsController extends BaseController {


	@Autowired
	private OrderdetailsService orderdetailsService;
	@Autowired
	private BisLogDelegate bisLogDelegate;
	
	@ModelAttribute
	public Orderdetails get(@RequestParam(required=false) String id) {
		Orderdetails entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderdetailsService.get(id);
		}
		if (entity == null){
			entity = new Orderdetails();
		}
		return entity;
	}
	
	@RequiresPermissions("orderdetail:orderdetails:view")
	@RequestMapping(value = {"list", ""})
	public String list(Orderdetails orderdetails, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Orderdetails> page = orderdetailsService.findPage(new Page<Orderdetails>(request, response), orderdetails); 
		model.addAttribute("page", page);
		return "gds/orderdetail/orderdetailsList";
	}

	@RequiresPermissions("orderdetail:orderdetails:view")
	@RequestMapping(value = "form")
	public String form(Orderdetails orderdetails, Model model) {
		model.addAttribute("orderdetails", orderdetails);
		return "gds/orderdetail/orderdetailsForm";
	}

	@RequiresPermissions("orderdetail:orderdetails:edit")
	@RequestMapping(value = "save")
	public String save(Orderdetails orderdetails, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderdetails)){
			return form(orderdetails, model);
		}
		orderdetailsService.save(orderdetails);
		addMessage(redirectAttributes, "保存orderdetail成功");
		return "redirect:"+Global.getAdminPath()+"/orderdetail/orderdetails/?repage";
	}
	
	@RequiresPermissions("orderdetail:orderdetails:edit")
	@RequestMapping(value = "delete")
	public String delete(Orderdetails orderdetails, RedirectAttributes redirectAttributes) {
		orderdetailsService.delete(orderdetails);
		addMessage(redirectAttributes, "删除orderdetail成功");
		return "redirect:"+Global.getAdminPath()+"/orderdetail/orderdetails/?repage";
	}
	
	
    @RequestMapping(value = "savecsremark")
    public ResponseEntity<Map<String, Object>> savecsremark(String orderid, String remark){
        logger.info("保存客服备注传来的orderid：{},客服备注：{}",orderid,remark);
        Map<String, Object> rtnMap = Maps.newHashMap();
        if (Strings.isNullOrEmpty(orderid)) {
            logger.info("订单id为空");
            rtnMap.put("result", "订单id为空");
            return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
        }
        User user = UserUtils.getUser();         
        if (user == null) {
            logger.info("登录用户有问题");
            rtnMap.put("result", "登录用户有问题");
            return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
        }
        String loginuser = user.getLoginName();
        String name = user.getName();
        BisLog bisLog = new BisLog();
        bisLog.setSystem("order");
        bisLog.setOperator(loginuser + "( " + name + " )");
        bisLog.setBussinessId(orderid);
        bisLog.setBussinssType(BussinssTypeEnum.CSREMARK.getId());
        bisLog.setContent(remark);
        bisLogDelegate.saveBigLog(bisLog);
        rtnMap.put("result", "SUCCESS");
        return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);         
    }

}