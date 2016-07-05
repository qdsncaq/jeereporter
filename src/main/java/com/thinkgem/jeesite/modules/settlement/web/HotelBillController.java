//package com.thinkgem.jeesite.modules.settlement.web;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.mk.settlement.enums.AccountRoleEnums;
//import com.thinkgem.jeesite.common.persistence.Page;
//import com.thinkgem.jeesite.common.web.BaseController;
//import com.thinkgem.jeesite.modules.settlement.entity.HotelWeeklyBill;
//import com.thinkgem.jeesite.modules.settlement.service.AccountService;
//import com.thinkgem.sc.accntbill.entity.SettlementAccntBill;
//import com.thinkgem.sc.accntbill.service.SettlementAccntBillService;
//
//@Controller
//@RequestMapping(value = "${adminPath}/scadmin/hotelbill")
//public class HotelBillController extends BaseController {
//    /**
//     * 日志对象
//     */
//    private Logger log = LoggerFactory.getLogger(HotelBillController.class);
//    
//    /**
//     * 
//     */
////    @Autowired
////    private AccountService accountService;
//    
//    /**
//     * 
//     */
//    @Autowired
//    private SettlementAccntBillService settlementAccntBillService;
//    
//    /**
//     * 
//     * @param request
//     * @param response
//     * @param model
//     * @return
//     */
//    @RequiresPermissions("scadmin:hotelbill:view")
//    @RequestMapping(value = {"list", ""})
//    //public String list(HotelWeeklyBill hotelWeeklyBill, HttpServletRequest request, HttpServletResponse response, Model model) {
//    public String list(SettlementAccntBill settlementAccntBill, HttpServletRequest request, HttpServletResponse response, Model model) {
////        Page<HotelWeeklyBill> pageParam = new Page<HotelWeeklyBill>(request, response);
////        pageParam.setPageSize(-1);
////        Page<HotelWeeklyBill> pageData = accountService.requestHotelWeeklyBill(pageParam, hotelWeeklyBill);
////        pageData.setMessage("结算明细账");
////        model.addAttribute("page", pageData);
////        return "modules/settlement/hotelWeeklyBillsList";
//        //settlementAccntBill.setAccountrole(AccountRoleEnums.BOSS.getIndex());
//        Page<SettlementAccntBill> page = settlementAccntBillService.findPage(new Page<SettlementAccntBill>(request, response), settlementAccntBill); 
//        model.addAttribute("page", page);
//        return "sc/accntbill/settlementAccntBillList";
//    }
//}
