///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.gds.datacenter.web;
//
//import java.math.BigDecimal;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.fangbaba.basic.face.bean.RoomtypeModel;
//import com.fangbaba.basic.face.service.RoomtypeService;
//import com.fangbaba.datacenter.enums.BussinssTypeEnum;
//import com.google.common.base.Strings;
//import com.google.common.collect.Maps;
//import com.lz.mongo.bislog.BisLog;
//import com.lz.mongo.bislog.BisLogDelegate;
//import com.mk.settlement.service.ISettlementRelationService;
//import com.mongodb.client.model.Filters;
//import com.thinkgem.gds.datacenter.entity.DatacenterOrders;
//import com.thinkgem.gds.datacenter.entity.OtaOrderdetail;
//import com.thinkgem.gds.datacenter.service.DatacenterOrdersService;
//import com.thinkgem.gds.datacenter.service.OtaOrderdetailService;
//import com.thinkgem.jeesite.common.config.Global;
//import com.thinkgem.jeesite.common.persistence.Page;
//import com.thinkgem.jeesite.common.web.BaseController;
//import com.thinkgem.jeesite.modules.sys.entity.User;
//import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
//
///**
// * 订单详情Controller
// * @author LYN
// * @version 2016-03-29
// */
//@Controller
//@RequestMapping(value = "${adminPath}/datacenter/otaOrderdetail")
//public class OtaOrderdetailController extends BaseController {
//
//	@Autowired
//	private OtaOrderdetailService otaOrderdetailService;	
//    @Autowired
//    private BisLogDelegate bisLogDelegate;
//	@Autowired
//	private DatacenterOrdersService datacenterOrdersService;
//	@Autowired
//	private RoomtypeService roomtypeService;
//	@Autowired
//    private ISettlementRelationService iSettlementRelationService;
//	
//	@ModelAttribute
//	public OtaOrderdetail get(@RequestParam(required=false) String id) {
//		OtaOrderdetail entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = otaOrderdetailService.get(id);
//		}
//		if (entity == null){
//			entity = new OtaOrderdetail();
//		}
//		return entity;
//	}
//	
//	@RequiresPermissions("orderdetails:otaOrderdetail:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(OtaOrderdetail otaOrderdetail, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<OtaOrderdetail> page = otaOrderdetailService.findPage(new Page<OtaOrderdetail>(request, response), otaOrderdetail); 
//		model.addAttribute("page", page);
//		return "datacenter/orderdetails/otaOrderdetailList";
//	}
//
//	@RequiresPermissions("orderdetails:otaOrderdetail:view")
//	@RequestMapping(value = "form")
//	public String form(OtaOrderdetail otaOrderdetail, Model model) {
//		model.addAttribute("otaOrderdetail", otaOrderdetail);
//		return "datacenter/orderdetails/otaOrderdetailForm";
//	}
//
//	@RequiresPermissions("orderdetails:otaOrderdetail:edit")
//	@RequestMapping(value = "save")
//	public String save(OtaOrderdetail otaOrderdetail, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, otaOrderdetail)){
//			return form(otaOrderdetail, model);
//		}
//		otaOrderdetailService.save(otaOrderdetail);
//		addMessage(redirectAttributes, "保存订单详情成功");
//		return "redirect:"+Global.getAdminPath()+"/orderdetails/otaOrderdetail/?repage";
//	}
//	
//	@RequiresPermissions("orderdetails:otaOrderdetail:edit")
//	@RequestMapping(value = "delete")
//	public String delete(OtaOrderdetail otaOrderdetail, RedirectAttributes redirectAttributes) {
//		otaOrderdetailService.delete(otaOrderdetail);
//		addMessage(redirectAttributes, "删除订单详情成功");
//		return "redirect:"+Global.getAdminPath()+"/orderdetails/otaOrderdetail/?repage";
//	}
//
//	@RequestMapping(value = "listorderid")
//	public String listByOrderid(OtaOrderdetail orderdetails, HttpServletRequest request, HttpServletResponse response, Model model) {
//		List<OtaOrderdetail> otaorderdetails = otaOrderdetailService.findList(orderdetails); 
//		for( OtaOrderdetail order : otaorderdetails ){
//			RoomtypeModel roomtypeModel = roomtypeService.queryById(order.getRoomtypeid());
//			if( roomtypeModel != null ){
//				order.setRoomtypename(roomtypeModel.getName());
//			}
//		}
//		model.addAttribute("otaorderdetails", otaorderdetails);
//		Long orderid = orderdetails.getOrderid();
//		if( orderid != null ){
//			DatacenterOrders datacenterOrders = datacenterOrdersService.get(""+orderid);
//			model.addAttribute("datacenterOrders", datacenterOrders);
//			
//			//mongo find
//			Bson filter = Filters.and(Filters.eq(BisLog.CN_SYSTEM, "order"), Filters.eq(BisLog.CN_BUSSINESSID, orderid.toString()));
//			Bson sort = new Document(BisLog.CN_CREATETIME, -1);
//			List<BisLog> list = bisLogDelegate.find(filter, sort);
//			List<BisLog> loglist = new LinkedList<BisLog>();
//			for (BisLog bisLog : list) {
//                if(StringUtils.isNotBlank(bisLog.getBussinssType())){
//					BussinssTypeEnum btEnum = BussinssTypeEnum.getEnum(bisLog.getBussinssType());
//					if(btEnum != null)
//						bisLog.setBussinssType(btEnum.getName());		
//				}				
//				loglist.add(bisLog);
//			}
//			model.addAttribute("loglist", list);
//		}
//		return "modules/gds/datacenter/orderdetails";
//	}	
//	
//	/**
//	 * 客服详情页面
//	 * @param orderdetails
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "cslistorderid")
//	public String cslistByOrderid(OtaOrderdetail orderdetails, HttpServletRequest request, HttpServletResponse response, Model model) {
//		List<OtaOrderdetail> otaorderdetails = otaOrderdetailService.findList(orderdetails); 
//		for( OtaOrderdetail order : otaorderdetails ){
//			RoomtypeModel roomtypeModel = roomtypeService.queryById(order.getRoomtypeid());
//			if( roomtypeModel != null ){
//				order.setRoomtypename(roomtypeModel.getName());
//			}
//		}
//		model.addAttribute("otaorderdetails", otaorderdetails);
//		Long orderid = orderdetails.getOrderid();
//		if( orderid != null ){
//			DatacenterOrders datacenterOrders = datacenterOrdersService.get(""+orderid);
//			model.addAttribute("datacenterOrders", datacenterOrders);
//			
//			//mongo find
//			Bson filter = Filters.and(Filters.eq(BisLog.CN_SYSTEM, "order"), Filters.eq(BisLog.CN_BUSSINESSID, orderid.toString()));
//			Bson sort = new Document(BisLog.CN_CREATETIME, -1);
//			List<BisLog> list = bisLogDelegate.find(filter, sort);
//			List<BisLog> loglist = new LinkedList<BisLog>();
//			for (BisLog bisLog : list) {		
//                if(StringUtils.isNotBlank(bisLog.getBussinssType())){
//					BussinssTypeEnum btEnum = BussinssTypeEnum.getEnum(bisLog.getBussinssType());
//					if(btEnum != null)
//						bisLog.setBussinssType(btEnum.getName());		
//				}				
//				loglist.add(bisLog);
//			}
//			model.addAttribute("loglist", list);
//		}
//		return "modules/gds/datacenter/CSorderdetails";
//	}
//	
//}