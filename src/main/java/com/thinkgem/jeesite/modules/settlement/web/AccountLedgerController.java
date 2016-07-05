package com.thinkgem.jeesite.modules.settlement.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.settlement.entity.AccountLedger;
import com.thinkgem.jeesite.modules.settlement.service.AccountService;

/**
 * 结算中心总账Controller.
 * 
 * @author chuaiqing.
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/scadmin/ledger")
public class AccountLedgerController extends BaseController {
	
	@Autowired
	private AccountService accountService;

    @RequiresPermissions("scadmin:ledger:view")
    @RequestMapping(value = {"list", ""})
    public String list(AccountLedger accountLedger, HttpServletRequest request, HttpServletResponse response, Model model) {
    	Page<AccountLedger> page = accountService.requestAccountLedgerList(new Page<AccountLedger>(request, response), accountLedger);
    	model.addAttribute("page", page);
        return "modules/settlement/ledgeraccountList";
    }
}
