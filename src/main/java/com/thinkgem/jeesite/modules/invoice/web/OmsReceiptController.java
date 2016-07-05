/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.invoice.web;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.invoice.entity.OmsReceipt;
import com.thinkgem.jeesite.modules.invoice.service.OmsReceiptService;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrder;
import com.thinkgem.jeesite.modules.oms.service.OmsOrderService;

/**
 * 发票票据管理Controller
 * @author aiqing.chu@fangbaba.com
 * @version 2016-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/invoice/omsReceipt")
public class OmsReceiptController extends BaseController {

	@Autowired
	private OmsReceiptService omsReceiptService;
	
	@Autowired
    private OmsOrderService omsOrderService;
	
	@ModelAttribute
	public OmsReceipt get(@RequestParam(required=false) String id) {
		OmsReceipt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = omsReceiptService.get(id);
		}
		if (entity == null){
			entity = new OmsReceipt();
		}
		return entity;
	}
	
	@RequiresPermissions("invoice:omsReceipt:view")
	@RequestMapping(value = {"list", ""})
	public String list(OmsReceipt omsReceipt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OmsReceipt> page = omsReceiptService.findPage(new Page<OmsReceipt>(request, response), omsReceipt); 
		model.addAttribute("page", page);
		return "modules/invoice/omsReceiptList";
	}

	@RequiresPermissions("invoice:omsReceipt:view")
	@RequestMapping(value = "form")
	public String form(OmsReceipt omsReceipt, Model model) {
		model.addAttribute("omsReceipt", omsReceipt);
		return "modules/invoice/omsReceiptForm";
	}

	@RequiresPermissions("invoice:omsReceipt:edit")
	@RequestMapping(value = "save")
	public String save(OmsReceipt omsReceipt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, omsReceipt)){
			return form(omsReceipt, model);
		}
		if (omsReceipt.getId() != null && !"".equals(omsReceipt.getId())) {
		    omsReceipt.setIsopen(1);
		}
		omsReceiptService.save(omsReceipt);
		addMessage(redirectAttributes, "保存采购发票成功");
		return "redirect:"+Global.getAdminPath()+"/invoice/omsReceipt/?repage";
	}
	
    @RequiresPermissions("invoice:omsReceipt:edit")
    @RequestMapping(value = "invoiceorders")
    public String invoiceOrders(OmsReceipt omsReceipt, HttpServletRequest request, HttpServletResponse response, Model model) {
        String invoiceid = omsReceipt.getId();
        List<OmsOrder> list = omsOrderService.findInvoiceOrders(invoiceid);
        model.addAttribute("list", list);
        return "modules/invoice/omsInvoiceOrderList";
    }	
	
	@RequiresPermissions("invoice:omsReceipt:delete")
	@RequestMapping(value = "delete")
	public String delete(OmsReceipt omsReceipt, RedirectAttributes redirectAttributes) {
		omsReceiptService.delete(omsReceipt);
		addMessage(redirectAttributes, "删除采购发票成功");
		return "redirect:"+Global.getAdminPath()+"/invoice/omsReceipt/?repage";
	}
	
	   /**
     * 
     * @param omsReceipt
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("accntbill:settlementAccntBill:view")
    @RequestMapping(value = "exportexcel")
    public String exportexcel(OmsReceipt omsReceipt, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "采购发票列表.xlsx";
            List<OmsReceipt> list = omsReceiptService.findList(omsReceipt);
            new ExportExcel("采购发票", OmsReceipt.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "采购发票列表excel导出失败！失败信息："+e.getMessage());
        }
        return "redirect:"+Global.getAdminPath()+"/invoice/omsReceipt/?repage";
    }

}