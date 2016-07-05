/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntdetail.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mk.settlement.enums.AccountRoleEnums;
import com.mk.settlement.enums.BizTypeEnums;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.sc.accntdetail.entity.SettlementAccntDetail;
import com.thinkgem.sc.accntdetail.entity.SettlementAccntDetail1;
import com.thinkgem.sc.accntdetail.service.SettlementAccntDetailService;

/**
 * accntdetailController
 * @author lm
 * @version 2016-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/accntdetail/settlementAccntDetail")
public class SettlementAccntDetailController extends BaseController {

	@Autowired
	private SettlementAccntDetailService settlementAccntDetailService;
	
	@ModelAttribute
	public SettlementAccntDetail get(@RequestParam(required=false) String id) {
		SettlementAccntDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementAccntDetailService.get(id);
		}
		if (entity == null){
			entity = new SettlementAccntDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("accntdetail:settlementAccntDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementAccntDetail settlementAccntDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementAccntDetail> page = settlementAccntDetailService.findPage(new Page<SettlementAccntDetail>(request, response), settlementAccntDetail); 
		model.addAttribute("page", page);
		if(settlementAccntDetail.getBiztype()==null){
			model.addAttribute("bzt", 0);
		}else{
			model.addAttribute("bzt", settlementAccntDetail.getBiztype());
		}
		return "sc/accntdetail/settlementAccntDetailList";
	}
	
	/**
	 * 
	 * @param settlementAccntDetail
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("accntdetail:settlementAccntDetail:view")
    @RequestMapping(value = {"listOfBalance", ""})
    public String listOfBalance(SettlementAccntDetail settlementAccntDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<SettlementAccntDetail> page = settlementAccntDetailService.findAllBalanceDetails(new Page<SettlementAccntDetail>(request, response), settlementAccntDetail); 
        model.addAttribute("page", page);
        return "sc/accntdetail/settlementAccntDetailListOfBalance";
    }

	@RequiresPermissions("accntdetail:settlementAccntDetail:view")
	@RequestMapping(value = "form")
	public String form(SettlementAccntDetail settlementAccntDetail, Model model) {
		model.addAttribute("settlementAccntDetail", settlementAccntDetail);
		return "sc/accntdetail/settlementAccntDetailForm";
	}

	@RequiresPermissions("accntdetail:settlementAccntDetail:edit")
	@RequestMapping(value = "save")
	public String save(SettlementAccntDetail settlementAccntDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementAccntDetail)){
			return form(settlementAccntDetail, model);
		}
		settlementAccntDetailService.save(settlementAccntDetail);
		addMessage(redirectAttributes, "保存accntdetail成功");
		return "redirect:"+Global.getAdminPath()+"/accntdetail/settlementAccntDetail/?repage";
	}
	
	@RequiresPermissions("accntdetail:settlementAccntDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementAccntDetail settlementAccntDetail, RedirectAttributes redirectAttributes) {
		settlementAccntDetailService.delete(settlementAccntDetail);
		addMessage(redirectAttributes, "删除accntdetail成功");
		return "redirect:"+Global.getAdminPath()+"/accntdetail/settlementAccntDetail/?repage";
	}
	
	@RequiresPermissions("accntdetail:settlementAccntDetail:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(SettlementAccntDetail settlementAccntDetail, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "账单流水列表.xlsx";
    		List<SettlementAccntDetail> list = settlementAccntDetailService.findList(settlementAccntDetail);
    		//除了biztype=6 的导出 的 Freetotal为优惠金额
    		if(settlementAccntDetail.getBiztype()!=null && settlementAccntDetail.getBiztype()==BizTypeEnums.RECHARGE.getIndex()){
    			new ExportExcel("账单流水", SettlementAccntDetail.class, 1).setDataList(list).write(response, fileName).dispose();
    		}else{ //除了biztype=6之外的导出 的 Freetotal为订单成功率系数
    			new ExportExcel("账单流水", SettlementAccntDetail1.class, 1).setDataList(list).write(response, fileName).dispose();
    		}
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "账单流水excel导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/accntdetail/settlementAccntDetail/?repage";
	}
	
	// 旅行社周账单查询明细
	@RequiresPermissions("accntdetail:settlementAccntDetail:view")
	@RequestMapping(value = {"finddetails", ""})
	public String finddetails(SettlementAccntDetail settlementAccntDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementAccntDetail> page = settlementAccntDetailService.findPage(new Page<SettlementAccntDetail>(request, response), settlementAccntDetail); 
		model.addAttribute("page", page);
		return "sc/accntdetail/tripWeekDetailList";
	}
	
	// 洗涤月账单查询明细
	@RequiresPermissions("accntdetail:settlementAccntDetail:view")
	@RequestMapping(value = {"washfinddetails", ""})
	public String washfinddetails(SettlementAccntDetail settlementAccntDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementAccntDetail> page = settlementAccntDetailService.findPage(new Page<SettlementAccntDetail>(request, response, -1), settlementAccntDetail); 
		model.addAttribute("page", page);
		return "sc/accntdetail/washMonthDetailList";
	}

	// 去哪儿周账单查询明细
	@RequiresPermissions("accntdetail:settlementAccntDetail:view")
	@RequestMapping(value = {"qunarfinddetails", ""})
	public String qunarfinddetails(SettlementAccntDetail settlementAccntDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementAccntDetail> page = settlementAccntDetailService.findPage(new Page<SettlementAccntDetail>(request, response), settlementAccntDetail); 
		model.addAttribute("page", page);
		return "sc/accntdetail/qunarWeekDetailList";
	}
	
	/**
	 * 调整金额
	 * 		明细表添加记录
	 * @param settlementAccntDetail
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("accntbill:settlementAccntBill:view")
	@RequestMapping(value = "modify")
	@ResponseBody
	public String modify(SettlementAccntDetail settlementAccntDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			int modifyCount = settlementAccntDetailService.modify(settlementAccntDetail);
			if(modifyCount == 999){
				return "结算中心报错.";
			} else if(modifyCount > 0){
				return "修改完成.";
			} else{
				return "修改失败.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	/**
	 * 销售月账单查询明细
	 * @param settlementAccntBill
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "salesbillFinddetails")
	public String salesbillFinddetails(SettlementAccntDetail settlementAccntDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		String resultValue = "sc/accntdetail/SalesMonthBillAccntDetailList";
	    Integer biztype = settlementAccntDetail.getBiztype();
	    Integer accountrole = settlementAccntDetail.getAccountrole();
		if(biztype == BizTypeEnums.TRIP.getIndex()){// 注意:::如果是分销业务,销售的月账单更新的是detail表的no1字段.
			settlementAccntDetail.setNo1(settlementAccntDetail.getNo());
			settlementAccntDetail.setNo(null);
		}
		Page<SettlementAccntDetail> page = settlementAccntDetailService.findPage(new Page<SettlementAccntDetail>(request, response), settlementAccntDetail); 
		model.addAttribute("page", page);
		BizTypeEnums biztypeEnums = BizTypeEnums.getBizType(biztype);
		/*
		TRIP(2, "分销"),
        WASH(3, "洗涤"),
        YOUZAN(4, "采购"),
        RECHARGE(6, "充值"),
        WITHDRAWCASH(7, "提现"),
        REPAYMENT(8, "还款"),
        PAY(9, "支付"),
        PMSPAY(10, "PMS代收房费"),
        MIKEPAY(11, "眯客代收房费"),
        HOTELINTEREST(20, "酒店计息"),
        SALERINTEREST(21, "销售计息"),
        TA(22, "旅行社分销");
		 */
		switch (biztypeEnums) {
        case TRIP:
        case TA:
        case PMSPAY:
        case MIKEPAY:
            // 旅行社业务账单明细
            if (AccountRoleEnums.QUNAR.getIndex() == accountrole) {
                resultValue = "sc/accntdetail/qunarWeekDetailList";
            } else {
                resultValue = "sc/accntdetail/tripWeekDetailList";
            }
            break;
        case WASH:
            // 洗涤业务账单明细
            resultValue = "sc/accntdetail/washMonthDetailList";
            break;
        default:
            break;
        }
		return resultValue;
	}
	
}