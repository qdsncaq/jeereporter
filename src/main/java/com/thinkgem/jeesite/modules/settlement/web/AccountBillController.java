package com.thinkgem.jeesite.modules.settlement.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 结算中心结算账单Controller.
 * 
 * @author chuaiqing.
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/scadmin/bill")
public class AccountBillController extends BaseController {

    @RequiresPermissions("scadmin:bill:view")
    @RequestMapping(value = {"list", ""})
    public String list(Model model) {
        return "";
    }
}
