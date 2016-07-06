///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.sc.accntbill.web;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.apache.shiro.authz.annotation.RequiresRoles;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.google.common.collect.Lists;
//import com.mk.settlement.enums.AccountRoleEnums;
//import com.mk.settlement.enums.BizTypeEnums;
//import com.thinkgem.jeesite.common.config.Global;
//import com.thinkgem.jeesite.common.persistence.Page;
//import com.thinkgem.jeesite.common.utils.StringUtils;
//import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
//import com.thinkgem.jeesite.common.web.BaseController;
//import com.thinkgem.sc.accntbill.entity.HotelMonthlyWashAccountBill;
//import com.thinkgem.sc.accntbill.entity.HotelWeeklyAccountBill;
//import com.thinkgem.sc.accntbill.entity.SalesMonthAccountBill;
//import com.thinkgem.sc.accntbill.entity.SettlementAccntBill;
//import com.thinkgem.sc.accntbill.service.SettlementAccntBillService;
//
///**
// * accntbillController
// * @author lm
// * @version 2016-03-21
// */
//@Controller
//@RequestMapping(value = "${adminPath}/accntbill/settlementAccntBill")
//public class SettlementAccntBillController extends BaseController {
//
//	@Autowired
//	private SettlementAccntBillService settlementAccntBillService;
//	
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@ModelAttribute
//	public SettlementAccntBill get(@RequestParam(required=false) String id) {
//		SettlementAccntBill entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = settlementAccntBillService.get(id);
//		}
//		if (entity == null){
//			entity = new SettlementAccntBill();
//		}
//		return entity;
//	}
//	
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(SettlementAccntBill settlementAccntBill, HttpServletRequest request, HttpServletResponse response, Model model) {
//		settlementAccntBill.setBiztype(BizTypeEnums.TRIP.getIndex());// 业务类型：旅行社
//		settlementAccntBill.setAccountrole(AccountRoleEnums.TRIP.getIndex());// 账户角色：旅行社
//		Page<SettlementAccntBill> page = settlementAccntBillService.findPage(new Page<SettlementAccntBill>(request, response), settlementAccntBill); 
//		model.addAttribute("page", page);
//		return "sc/accntbill/settlementAccntBillList";
//	}
//	
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 */
//    @RequiresPermissions("accntbill:settlementAccntBill:view")
//    @RequestMapping(value = {"hotellist", ""})
//    public String hotelList(SettlementAccntBill settlementAccntBill, HttpServletRequest request, HttpServletResponse response, 
//            Model model, RedirectAttributes redirectAttributes) {
//        // 酒店老板周账单，设置账户角色为酒店老板.
//        settlementAccntBill.setAccountrole(AccountRoleEnums.BOSS.getIndex());
//        settlementAccntBill.setBiztype(BizTypeEnums.TRIP.getIndex());
//        Page<SettlementAccntBill> pageReq = new Page<SettlementAccntBill>(request, response);
//        pageReq.setOrderBy(" a.begin_date desc, a.end_date desc, length(a.hotelid) desc, a.hotelid desc ");
//        Page<SettlementAccntBill> page = settlementAccntBillService.findPage(pageReq, settlementAccntBill); 
//        model.addAttribute("page", page);
//        return "modules/settlement/hotelWeeklyBillsList";
//    }
//
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = {"washlist", ""})
//	public String washlist(SettlementAccntBill settlementAccntBill, HttpServletRequest request, HttpServletResponse response, Model model) {
//		settlementAccntBill.setBiztype(BizTypeEnums.WASH.getIndex());// 业务类型：洗涤
// 		settlementAccntBill.setAccountrole(AccountRoleEnums.BOSS.getIndex()); //此处只是老板的
//		Page<SettlementAccntBill> pageReq = new Page<SettlementAccntBill>(request, response);
//        pageReq.setOrderBy(" a.begin_date desc, a.end_date desc, length(a.hotelid) desc, a.hotelid desc ");
//		Page<SettlementAccntBill> page = settlementAccntBillService.findPage(pageReq, settlementAccntBill);
//		model.addAttribute("page", page);
//		return "sc/accntbill/accntWashBillList";
//	}
//	
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = {"qunarlist", ""})
//	public String qunarlist(SettlementAccntBill settlementAccntBill, HttpServletRequest request, HttpServletResponse response, Model model) {
//		settlementAccntBill.setBiztype(BizTypeEnums.TRIP.getIndex());		
//		settlementAccntBill.setAccountrole(AccountRoleEnums.QUNAR.getIndex()); // 业务类型：OTA分销, 角色：去哪儿
//		Page<SettlementAccntBill> pageReq = new Page<SettlementAccntBill>(request, response);
//        pageReq.setOrderBy(" a.begin_date desc, a.end_date desc ");
//		Page<SettlementAccntBill> page = settlementAccntBillService.findPage(pageReq, settlementAccntBill); 
//		model.addAttribute("page", page);
//		return "sc/accntbill/qunarAccntBillList";
//	}
//
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param model
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = "form")
//	public String form(SettlementAccntBill settlementAccntBill, Model model) {
//		model.addAttribute("settlementAccntBill", settlementAccntBill);
//		return "sc/accntbill/settlementAccntBillForm";
//	}
//	
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param model
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = "qunarform")
//	public String qunarform(SettlementAccntBill settlementAccntBill, Model model) {
//		model.addAttribute("settlementAccntBill", settlementAccntBill);
//		return "sc/accntbill/qunarAccntBillForm";
//	}
//
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param model
//	 * @param redirectAttributes
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:edit")
//	@RequestMapping(value = "save")
//	public String save(SettlementAccntBill settlementAccntBill, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, settlementAccntBill)){
//			return form(settlementAccntBill, model);
//		}
//		settlementAccntBillService.save(settlementAccntBill);
//		addMessage(redirectAttributes, "保存accntbill成功");
//		return "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/?repage";
//	}
//	
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param redirectAttributes
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:edit")
//	@RequestMapping(value = "delete")
//	public String delete(SettlementAccntBill settlementAccntBill, RedirectAttributes redirectAttributes) {
//		settlementAccntBillService.delete(settlementAccntBill);
//		addMessage(redirectAttributes, "删除accntbill成功");
//		return "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/?repage";
//	}
//	
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param response
//	 * @param redirectAttributes
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = "exportexcel")
//	public String exportexcel(SettlementAccntBill settlementAccntBill, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		try {
//            String fileName = "旅行社周账单列表.xlsx";
//    		List<SettlementAccntBill> list = settlementAccntBillService.findList(settlementAccntBill);
//    		new ExportExcel("旅行社周账单", SettlementAccntBill.class, 1).setDataList(list).write(response, fileName).dispose();
//    		return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			addMessage(redirectAttributes, "旅行社周账单excel导出失败！失败信息："+e.getMessage());
//		}
//		return "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/?repage";
//	}
//	
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param response
//	 * @param redirectAttributes
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = "washexportexcel")
//	public String washexportexcel(SettlementAccntBill settlementAccntBill, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		try {
//			settlementAccntBill.setBiztype(BizTypeEnums.WASH.getIndex());
//            String fileName = "洗涤月账单列表.xlsx";
//    		List<SettlementAccntBill> list = settlementAccntBillService.findList(settlementAccntBill);
//    		new ExportExcel("洗涤月账单", HotelMonthlyWashAccountBill.class, 1).setDataList(list).write(response, fileName).dispose();
//    		return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			addMessage(redirectAttributes, "洗涤月账单excel导出失败！失败信息："+e.getMessage());
//		}
//		return "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/?repage";
//	}
//	
//	/**
//	 * 
//	 * @param settlementAccntBill
//	 * @param response
//	 * @param redirectAttributes
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = "qunarexportexcel")
//	public String qunarexportexcel(SettlementAccntBill settlementAccntBill, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		try {
//            String fileName = "去那儿周账单列表.xlsx";
//    		settlementAccntBill.setBiztype(2);		
//    		settlementAccntBill.setAccountrole(6); // 业务类型：OTA分销, 角色：去哪儿
//    		List<SettlementAccntBill> list = settlementAccntBillService.findList(settlementAccntBill);
//    		new ExportExcel("去那儿周账单", SettlementAccntBill.class, 1).setDataList(list).write(response, fileName).dispose();
//    		return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			addMessage(redirectAttributes, "去那儿周账单excel导出失败！失败信息："+e.getMessage());
//		}
//		return "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/?repage";
//	}
//	
//	/**
//	 * 结算
//	 * @param ids
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = "bill")
//	@ResponseBody
//	public int bill(String ids, Integer status) {
//		if(ids == null || ids.trim() == ""){
//			return 100;
//		}
//		int count = settlementAccntBillService.bill(ids, 2);
//		return count;
//
//
//	}
//	
//	/**
//	 * 旅行社周账单重新结算
//	 * @param id
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = "rebill")
//	@ResponseBody
//	public boolean rebill(long id) {
//		boolean flag = false;
//		try {
//			flag = settlementAccntBillService.rebill(id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return flag;
//	}
//	
//	/**
//	 * 去哪儿周账单重新结算
//	 * @param id
//	 * @return
//	 */
//	@RequiresPermissions("accntbill:settlementAccntBill:view")
//	@RequestMapping(value = "qunarrebill")
//	@ResponseBody
//	public boolean qunarrebill(long id) {
//		boolean flag = false;
//		try {
//			flag = settlementAccntBillService.rebill(id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return flag;
//	}
//	
//    /**
//     * 酒店老板周账单重新结算
//     * @param id
//     * @return
//     */
//    @RequiresPermissions("accntbill:settlementAccntBill:view")
//    @RequestMapping(value = "hotelrebill")
//    @ResponseBody
//    public boolean hotelrebill(long id) {
//        boolean flag = false;
//        try {
//            flag = settlementAccntBillService.hotelReBill(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }
//    
//	/**
//	 * 酒店老板周账单财务确认.
//	 * @param id
//	 * @param model
//	 * @param redirectAttributes
//	 * @return
//	 */
//	@RequiresRoles("SC_FI_BILLCONFIRM")
//    @RequestMapping(value = "confirm")
//    public String confirm(SettlementAccntBill settlementAccntBill, Model model, RedirectAttributes redirectAttributes) {
//	    int accountrole = settlementAccntBill.getAccountrole().intValue();
//	    String resultStr = "";
//	    if (AccountRoleEnums.TRIP.getIndex() == accountrole) {
//	        resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/list/?repage";
//	    } else if (AccountRoleEnums.QUNAR.getIndex() == accountrole) {
//	        resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/qunarlist/?repage";
//	    } else if (AccountRoleEnums.BOSS.getIndex() == accountrole) {
//	    	if (settlementAccntBill.getBiztype()==BizTypeEnums.WASH.getIndex()){
//	    		resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/washlist/?repage";
//	    	} else {
//	    		resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/hotellist/?repage";
//	    	}
//	    } else if (AccountRoleEnums.SALER.getIndex() == accountrole ||
//	            AccountRoleEnums.DRIVER.getIndex() == accountrole ||
//	            AccountRoleEnums.WASHINGPLANT.getIndex() == accountrole ||
//	            AccountRoleEnums.MANAGER_IMIKE.getIndex() == accountrole ||
//	            AccountRoleEnums.PARTNER_IMIKE.getIndex() == accountrole ||
//	            AccountRoleEnums.MANAGER_JINDUN.getIndex() == accountrole ||
//	            AccountRoleEnums.PARTNER_JINDUN.getIndex() == accountrole) {
//	        resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/salesMonthBill/?repage";
//	        if (settlementAccntBill.getHotelid() == null || "".equals(settlementAccntBill.getHotelid())) {
//	            settlementAccntBill.setHotelid(settlementAccntBill.getAccountid());
//	        }
//	    } else {
//	        return "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/list/?repage";
//	    }
//        settlementAccntBillService.fiConfirmHotelBill(settlementAccntBill);
//        return resultStr;
//    }
//	
//	   /**
//     * 
//     * @param settlementAccntBill
//     * @param response
//     * @param redirectAttributes
//     * @return
//     */
//    @RequiresPermissions("accntbill:settlementAccntBill:view")
//    @RequestMapping(value = "exphotellist2excel")
//    public String exphotellist2excel(SettlementAccntBill settlementAccntBill, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//        try {
//            String fileName = "酒店老板周账单列表.xlsx";
//            // 酒店老板周账单，设置账户角色为酒店老板.
//            settlementAccntBill.setAccountrole(AccountRoleEnums.BOSS.getIndex());
//            settlementAccntBill.setBiztype(BizTypeEnums.TRIP.getIndex());
//            Page<SettlementAccntBill> pageReq = new Page<SettlementAccntBill>();
//            pageReq.setOrderBy(" a.begin_date desc, a.end_date desc, length(a.hotelid) desc, a.hotelid desc ");
//            settlementAccntBill.setPage(pageReq);
//            List<SettlementAccntBill> list = settlementAccntBillService.findList(settlementAccntBill);
//            new ExportExcel("酒店老板周账单", HotelWeeklyAccountBill.class, 1).setDataList(list).write(response, fileName).dispose();
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            addMessage(redirectAttributes, "酒店老板周账单excel导出失败！失败信息："+e.getMessage());
//        }
//        return "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/hotellist/?repage";
//    }
//    
//    /**
//     * 酒店老板周账单财务确认.
//     * @param id
//     * @param model
//     * @param redirectAttributes
//     * @return
//     */
//    @RequiresRoles("SC_FI_BILLCONFIRM")
//    @RequestMapping(value = "batchconfirm")
//    public String batchConfirm(String ids, String billtype, Model model, RedirectAttributes redirectAttributes) {
//        String resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/hotellist/?repage";
//        try {
//            String[] idsarray = ids.split(",");
//            if (idsarray == null || idsarray.length == 0) {
//                return resultStr;
//            }
//            if ("HOTEL".equals(billtype)) {
//                resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/hotellist/?repage";
//            } else if ("TRIP".equals(billtype)) {
//                resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/list/?repage";
//            } else if ("QUNAR".equals(billtype)) {
//                resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/qunarlist/?repage";
//            } else {
//                resultStr = "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/list/?repage";
//            }
//            List<String> billIds = Lists.newArrayList(idsarray);
//            settlementAccntBillService.fiBatchConfirmHotelBill(billIds);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return resultStr;
//    }
//	
//    /**
//     * 销售月账单列表
//     * @param accountid
//     * @param accountyear
//     * @param accountmonth
//     * @param settlementAccntBill
//     * @param request
//     * @param response
//     * @param model
//     * @return
//     */
//    @RequiresPermissions("accntbill:settlementAccntBill:salesMonthBill:view")
//    @RequestMapping(value = "salesMonthBill")
//    public String selectSalesMonthBill(String accountid, Integer accountyear, Integer accountmonth, SettlementAccntBill settlementAccntBill, HttpServletRequest request, HttpServletResponse response, Model model){
//    	// 销售角色列表
//        List<Integer> salerRoles = new ArrayList<Integer>();
//        salerRoles.add(AccountRoleEnums.SALER.getIndex());
//        salerRoles.add(AccountRoleEnums.PARTNER_IMIKE.getIndex());
//        salerRoles.add(AccountRoleEnums.MANAGER_IMIKE.getIndex());
//        salerRoles.add(AccountRoleEnums.PARTNER_JINDUN.getIndex());
//        salerRoles.add(AccountRoleEnums.MANAGER_JINDUN.getIndex());
//        settlementAccntBill.setRoles(salerRoles);
//        
//        settlementAccntBill.setAccountid(accountid);
//    	settlementAccntBill.setAccountyear(accountyear);
//    	settlementAccntBill.setAccountmonth(accountmonth);
//    	
//    	Page<SettlementAccntBill> page = settlementAccntBillService.findPage(new Page<SettlementAccntBill>(request, response), settlementAccntBill); 
//		model.addAttribute("page", page);
//    	return "sc/accntbill/salesAccntBillList";
//    }
//    
//    /**
//     * 销售月账单导出
//     * @param settlementAccntBill
//     * @param response
//     * @param redirectAttributes
//     * @return
//     */
//	 @RequestMapping(value = "salesBillExportexcel")
//	 public String salesBillExportexcel(SettlementAccntBill settlementAccntBill, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//	     try {
//	         String fileName = "销售月账单列表.xlsx";
//	         List<SettlementAccntBill> list = settlementAccntBillService.findList(settlementAccntBill);
//	         new ExportExcel("销售月账单", SalesMonthAccountBill.class, 1).setDataList(list).write(response, fileName).dispose();
//	         return null;
//	     } catch (Exception e) {
//	         e.printStackTrace();
//	         addMessage(redirectAttributes, "销售月账单excel导出失败！失败信息："+e.getMessage());
//	     }
//	     return "redirect:"+Global.getAdminPath()+"/accntbill/salesAccntBillList/?repage";
//	 }
//	 
//    /**
//     * 洗涤月账单重新结算.
//     * @param id
//     * @return
//     */
//    @RequiresPermissions("accntbill:settlementAccntBill:view")
//    @RequestMapping(value = "washrebill")
//    @ResponseBody
//    public String washrebill(SettlementAccntBill settlementAccntBill, Model model, RedirectAttributes redirectAttributes) {
//        Long id = Long.getLong(settlementAccntBill.getId());
//        try {
//            boolean flag = settlementAccntBillService.washingReBill(id);
//            if (flag) {
//                logger.info("洗涤月账单ID: {}重新结算成功.", id);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:"+Global.getAdminPath()+"/accntbill/settlementAccntBill/washlist/?repage";
//    }
//
//}
