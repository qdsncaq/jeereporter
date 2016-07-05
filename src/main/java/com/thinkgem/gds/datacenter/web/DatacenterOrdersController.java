/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.datacenter.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import redis.clients.jedis.Jedis;

import com.fangbaba.datacenter.enums.BussinssTypeEnum;
import com.fangbaba.gds.face.service.IGdsOrderService;
import com.fangbaba.gds.face.service.IOtaHotelFullFlagService;
import com.fangbaba.order.common.enums.OperationSourceEnum;
import com.fangbaba.order.service.OrderService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.lz.mongo.bislog.BisLog;
import com.lz.mongo.bislog.BisLogDelegate;
import com.mk.settlement.service.ISettlementRelationService;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.gds.datacenter.entity.DatacenterOrders;
import com.thinkgem.gds.datacenter.service.DatacenterOrdersService;
import com.thinkgem.jeesite.common.cache.RedisCacheManager;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 分销订单查询Controller
 * @author LYN
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/datacenter/orders")
public class DatacenterOrdersController extends BaseController {

	@Autowired
	private DatacenterOrdersService datacenterOrdersService;
	@Autowired
	private TProvinceService provinceService;
	/** 市服务 */
	@Autowired
	private TCityService cityService;
	/** 县区服务 */
	@Autowired
	private TDistrictService districtService;
	
	@Autowired
	private IGdsOrderService gdsOrderService;
	
    @Autowired
    private BisLogDelegate bisLogDelegate;
    @Autowired
    private OrderService orderService;
    @Autowired
	private IOtaHotelFullFlagService otaHotelFullFlagService;
    
    @Autowired
    private RedisCacheManager redisCacheManager;
    @Autowired
    private ISettlementRelationService iSettlementRelationService;
	
	@ModelAttribute
	public DatacenterOrders get(@RequestParam(required=false) String id) {
		DatacenterOrders entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = datacenterOrdersService.get(id);
		}
		if (entity == null){
			entity = new DatacenterOrders();
		}
		return entity;
	}
	
	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = {"list", ""})
	public String list(DatacenterOrders datacenterOrders, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DatacenterOrders> page1 = new Page<DatacenterOrders>(request, response);
		page1.setOrderBy("createtime desc");
		Page<DatacenterOrders> page = datacenterOrdersService.findPageAll(page1, datacenterOrders);
		model.addAttribute("page", page);
		return "modules/gds/datacenter/datacenterOrdersList";
	}

	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = "form")
	public String form(DatacenterOrders datacenterOrders, Model model) {
		model.addAttribute("datacenterOrders", datacenterOrders);
		return "modules/gds/datacenter/datacenterOrdersForm";
	}

	@RequiresPermissions("datacenter:order:edit")
	@RequestMapping(value = "save")
	public String save(DatacenterOrders datacenterOrders, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, datacenterOrders)){
			return form(datacenterOrders, model);
		}
		datacenterOrdersService.save(datacenterOrders);
		addMessage(redirectAttributes, "保存分销订单查询成功");
		return "redirect:"+Global.getAdminPath()+"/datacenter/order/?repage";
	}
	
    @RequiresPermissions("datacenter:order:view")
    @RequestMapping(value = "cancel")
    public String cencal() {
        return "modules/gds/datacenter/addCancelInfo";
    }
    
    @RequiresPermissions("datacenter:order:edit")
	@RequestMapping(value = "delete")
	public String delete(DatacenterOrders datacenterOrders, RedirectAttributes redirectAttributes) {
		datacenterOrdersService.delete(datacenterOrders);
		addMessage(redirectAttributes, "删除分销订单查询成功");
		return "redirect:"+Global.getAdminPath()+"/datacenter/order/?repage";
	}
	
	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(DatacenterOrders orders,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "DatacenterOrders.xlsx";
    		List<DatacenterOrders> list = datacenterOrdersService.findList(orders);
    		new ExportExcel("分销订单数据", DatacenterOrders.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "分销订单数据excel导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/datacenter/orders/?repage";
	}
	
	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = "/neworder",method = RequestMethod.POST)
	public ResponseEntity<Integer> newOrder() {
		String orderid = null;
        Jedis jedis = null;
        try {
            jedis = redisCacheManager.getJedis();
            orderid = jedis.get("toBeConfirmed");
    	} catch (Exception e) {
    		orderid = null;
    	} finally {
    		if (jedis != null) {
    			jedis.close();
    		}
    	}
        logger.info("redis中新订单ID为: '" + orderid + "' length:" + (StringUtils.isBlank(orderid) ? 0 : orderid.length()));
        if(StringUtils.isBlank(orderid))
        	return new ResponseEntity<Integer>(0, HttpStatus.OK);
        else
        	return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
	
	
	 @RequiresPermissions("datacenter:order:view")
	 @RequestMapping(value = "/confirm",method = RequestMethod.POST)
     public ResponseEntity<Map<String, Object>> confirm(String orderid){
         logger.info("确认订单开始，orderid.:"+orderid );
         Map<String, Object> rtnMap = Maps.newHashMap();
         User user = UserUtils.getUser();
         try {
 			 checkParams(orderid, user);
 			 String message  = "发起确认订单操作";
             Boolean flag = gdsOrderService.confirmOrder(Long.parseLong(orderid),user.getLoginName());
             logger.info("确认订单结束，订单中心返回信息,orderid:"+orderid+",loginuser:"+user.getLoginName()+",flag:"+flag);
             if(flag==null || !flag){
            	 rtnMap.put("result", flag == null ? "订单中心返回为空！" : "订单确认失败，请重试!");
            	 message  = "确认订单操作失败";
             }else{
            	 rtnMap.put("result", "SUCCESS");
             }
             saveBisLog("order", BussinssTypeEnum.CONFIRM.getId(), orderid, getLogUserName(user), message);
             return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
         } catch (Exception e) {
        	 saveBisLog("order", BussinssTypeEnum.CONFIRM.getId(), orderid, getLogUserName(user), "确认订单异常:"+e.getLocalizedMessage());
             logger.error("确认订单失败",e);
             rtnMap.put("result", "确认订单异常:"+e.getLocalizedMessage());
             return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
         }         
     }
	
	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = "/orderin", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> orderIn(String orderid) {
		logger.info("订单入住开始，orderid.:" + orderid);
		Map<String, Object> rtnMap = Maps.newHashMap();
		User user = UserUtils.getUser();
		try {
			checkParams(orderid, user);
			String message ="发起订单入住操作";
			// 客服设置订单入住
			orderService.modifyOrderInByCustomer(Long.parseLong(orderid), user.getLoginName(), OperationSourceEnum.customService);
			logger.info("订单入住结束,返回信息,orderid:{},loginuser:{}", orderid, user.getLoginName());
			rtnMap.put("result", "SUCCESS");
			saveBisLog("order", BussinssTypeEnum.CHECKIN.getId(), orderid, getLogUserName(user), message);
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		} catch (Exception e) {
			saveBisLog("order", BussinssTypeEnum.CHECKIN.getId(), orderid, getLogUserName(user), "订单入住异常:"+e.getLocalizedMessage());
			logger.error("订单入住失败", e);
			rtnMap.put("result", "订单入住异常:"+e.getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		}
	}
	
	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = "/orderok", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> orderOk(String orderid) {
		logger.info("订单完成开始，orderid.:" + orderid);
		Map<String, Object> rtnMap = Maps.newHashMap();
		User user = UserUtils.getUser();
		try {
			checkParams(orderid, user);
			String message = "发起订单完成操作";
			// 客服设置订单完成
			orderService.modifyOrderOkByCustomer(Long.parseLong(orderid), user.getLoginName(), OperationSourceEnum.customService);
			logger.info("订单完成结束, orderid:{}, loginuser:{}", orderid, user.getLoginName());
			rtnMap.put("result", "SUCCESS");
			saveBisLog("order", BussinssTypeEnum.FINISHED.getId(), orderid, getLogUserName(user), message);
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("订单完成失败", e);
			saveBisLog("order", BussinssTypeEnum.FINISHED.getId(), orderid, getLogUserName(user), "订单完成异常:"+e.getLocalizedMessage());
			rtnMap.put("result", "订单完成异常:"+e.getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		}
	}
	
	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = "/ordernoshow", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> orderNoshow(String orderid) {
		logger.info("订单未到开始，orderid.:" + orderid);
		Map<String, Object> rtnMap = Maps.newHashMap();
		User user = UserUtils.getUser();
		try {
			checkParams(orderid, user);
			String message = "发起订单未到店操作";
			// 客服设置订单完成
			orderService.modifyOrderNoshowByCustomer(Long.parseLong(orderid), user.getLoginName(), OperationSourceEnum.customService);
			logger.info("订单未到开始,orderid:{},loginuser:{}", orderid, user.getLoginName());
			rtnMap.put("result", "SUCCESS");
			saveBisLog("order", BussinssTypeEnum.NOSHOW.getId(), orderid, getLogUserName(user), message);
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		} catch (Exception e) {
            saveBisLog("order", BussinssTypeEnum.NOSHOW.getId(), orderid, getLogUserName(user), "订单未到异常:"+e.getLocalizedMessage());
			logger.error("订单未到异常", e);
			rtnMap.put("result", "订单未到异常:"+e.getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		}
	}
	
	  @RequiresPermissions("datacenter:order:view")
	  @RequestMapping(value = "/refuse",method = RequestMethod.POST)
      public ResponseEntity<Map<String, Object>> refuseOrder(String orderid, String content){
          logger.info("拒绝订单开始，orderid.:" + orderid);
          Map<String, Object> rtnMap = Maps.newHashMap();
		  User user = UserUtils.getUser();
          try {
  			  checkParams(orderid, user);
		   	  
              Boolean flag = gdsOrderService.refuseOrder(Long.parseLong(orderid),user.getLoginName());
              logger.info("拒绝订单结束，订单中心返回信息,orderid:" + orderid + ",loginuser:" + user.getLoginName() + ",flag:" + flag);
              String message = "发起取消订单操作::" + content;
              if(flag == null || !flag){
            	  rtnMap.put("result", flag == null ? "订单中心返回为空！" : "拒绝确认失败，请重试!");
            	  message = "拒绝订单失败";
              } else {
            	  DatacenterOrders datacenterOrder = new DatacenterOrders();
                  datacenterOrder.setId(orderid);
                  datacenterOrder.setCancelreasondesc(content);
                  datacenterOrder.setUpdatetime(new Date());
                  datacenterOrdersService.update(datacenterOrder);
                  
                  rtnMap.put("result", "SUCCESS");
                  message = "拒绝订单成功";
              }
              
              saveBisLog("order", BussinssTypeEnum.CANCEL.getId(), orderid, getLogUserName(user), message);
              return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
          } catch (Exception e) {
              logger.error("拒绝订单失败",e);
			  saveBisLog("order", BussinssTypeEnum.CANCEL.getId(), orderid, getLogUserName(user), "拒绝订单异常:" + e.getLocalizedMessage());
              rtnMap.put("result", "拒绝订单异常:" + e.getLocalizedMessage());
              return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
          }
      }

	private String getLogUserName(User user) {
		return user.getLoginName() + "("+user.getName()+")";
	}

	/**
	 * 保存业务操作日志
	 * @param system
	 * @param bussinssType
	 * @param orderid
	 * @param optUser
	 * @param message
	 */
	private void saveBisLog(String system, String bussinssType, String orderid, String optUser, String message) {
		BisLog bisLog = new BisLog();
		  bisLog.setSystem(system);
		  bisLog.setBussinssType(bussinssType);
		  bisLog.setBussinessId(orderid);
		  bisLog.setOperator(optUser);
		  bisLog.setContent(message);
		  bisLogDelegate.saveBigLog(bisLog);
	}
	
	/**
	 * 检查参数
	 * @param orderid
	 * @param user
	 */
	private void checkParams(String orderid, User user) {
		if (user == null) {
			throw new RuntimeException("用户登录失败");
		}
		if (Strings.isNullOrEmpty(orderid)) {
			throw new RuntimeException("订单id为空");
		}
	}
	  
	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = "/noroom", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> bossRefuseOrder(String orderid, String content) {
		logger.info("投诉订单开始，orderid:{}", orderid);
		Map<String, Object> rtnMap = Maps.newHashMap();
		User user = UserUtils.getUser();
		try {
			checkParams(orderid, user);
			Boolean flag = orderService.bossRefuseOrder(Long.valueOf(orderid), user.getLoginName(), OperationSourceEnum.customService);
			logger.info("投诉订单结束，订单中心返回信息,orderid:" + orderid + ",loginuser:" + user.getLoginName() + ",flag:" + flag);
			String message = "投订单诉成功";
			if (flag == null || !flag) {
				rtnMap.put("result", flag == null ? "订单中心返回为空！" : "投诉订单失败，请重试!");
				message = "投订单诉失败";
			} else {
				rtnMap.put("result", "SUCCESS");
			}
			saveBisLog("order", BussinssTypeEnum.NOROOM.getId(), orderid, getLogUserName(user), message);
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("拒绝订单失败", e);
			saveBisLog("order", BussinssTypeEnum.NOROOM.getId(), orderid, getLogUserName(user), "投诉订单异常:" + e.getLocalizedMessage());
			logger.error("投诉订单失败", e);
			rtnMap.put("result", "投诉订单异常:" + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		}
	}
	  
	  /**
	   * 客服查询页面
	   * @param datacenterOrders
	   * @param request
	   * @param response
	   * @param model
	   * @return
	   */
	  @RequiresPermissions("datacenter:order:view")
		@RequestMapping(value = {"cslist", ""})
		public String scList(DatacenterOrders datacenterOrders, HttpServletRequest request, HttpServletResponse response, Model model) {
			Page<DatacenterOrders> page1 = new Page<DatacenterOrders>(request, response);
			page1.setOrderBy("createtime desc");
			Page<DatacenterOrders> page = datacenterOrdersService.findPage(page1, datacenterOrders); 
			//加是否设置过一键满房
			List<DatacenterOrders> list = page.getList();
			for (DatacenterOrders datacenterOrder:list) {
				datacenterOrder.setFullflag(otaHotelFullFlagService.queryFullFlag(Long.valueOf(datacenterOrder.getChannelid().toString()), Long.valueOf(datacenterOrder.getHotelid())));
			}
			model.addAttribute("page", page);			
			return "modules/gds/datacenter/CSdatacenterOrdersList";
		}
	  
	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = "/setpaymentamount", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> setPaymentAmount(String orderid, String payment_amount, String note, String objname) {
		logger.info("设置赔付金额开始，orderid:{},payment_amount:{}, objname:{}", orderid, payment_amount, objname);
		Map<String, Object> rtnMap = Maps.newHashMap();
		try {
			User user = UserUtils.getUser();
			checkParams(orderid, user);
			if (Strings.isNullOrEmpty(payment_amount)) {
				throw new RuntimeException("赔付金额不可以为空");
			}
			int flag = -1;
			if ("酒店".equals(objname)) {
				// 设置赔付金额
				flag = iSettlementRelationService.bossCompensate(Long.parseLong(orderid), new BigDecimal(payment_amount), note);
			}
			if ("渠道".equals(objname)) {
				// 设置赔付金额
				flag = iSettlementRelationService.hotelCompensate(Long.parseLong(orderid), new BigDecimal(payment_amount), note);
			}
			logger.info("设置赔付金额结束,orderid:{},loginuser:{}", orderid, user.getLoginName());
			rtnMap.put("result", "SUCCESS");
			if (flag == -1) {
				rtnMap.put("result", "FALSE");
				rtnMap.put("message", "设置过赔付金额失败！");
			} else if(flag == 1){
				rtnMap.put("message", "赔付金额只能录入一次，如需再次调整，请找结算人员操作！");
			}
			if (flag == 0) {
				saveBisLog("order", BussinssTypeEnum.SET_PAYMENTAMOUNT.getId(), orderid, getLogUserName(user), String.format("设置%s赔付金额：%s", objname, payment_amount));
			}
			rtnMap.put("flag", flag);
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("设置赔付金额失败", e);
			rtnMap.put("result", "设置赔付金额异常:" + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(rtnMap, HttpStatus.OK);
		}
	}
	
	/**
	 * 调到设置赔付金额的界面
	 * 
	 * @return
	 */
	@RequiresPermissions("datacenter:order:view")
	@RequestMapping(value = "setPaymentAmount/{objname}")
	public String setPaymentAmount(@PathVariable(value = "objname") String objname, Model model) {
		model.addAttribute("objname", objname);
		return "modules/gds/datacenter/setPaymentAmount";
	}

    @RequiresPermissions("datacenter:order:view")
    @RequestMapping(value = "setDiscount")
    public String setDiscount(String orderId, String totalPrice, String oldDiscount, String discount, RedirectAttributes redirectAttributes) {
        if (datacenterOrdersService.setDiscout(orderId, totalPrice, oldDiscount, discount)) {
            addMessage(redirectAttributes, "设置订单优惠金额成功");
        } else {
            addMessage(redirectAttributes, "设置订单优惠金额失败,请稍后再试");
        }

        return "redirect:"+Global.getAdminPath()+"/datacenter/otaOrderdetail/cslistorderid?orderid=" + orderId;
    }
}