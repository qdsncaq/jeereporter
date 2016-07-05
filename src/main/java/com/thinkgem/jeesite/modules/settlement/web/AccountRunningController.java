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
import com.thinkgem.jeesite.modules.settlement.entity.AccountRunning;
import com.thinkgem.jeesite.modules.settlement.service.AccountService;

/**
 * 结算中心流水账Controller.
 * 
 * @author chuaiqing.
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/scadmin/runningaccount")
public class AccountRunningController extends BaseController {

    /**
     * 
     */
    @Autowired
    private AccountService accountService;
    
    /**
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("scadmin:runningaccount:view")
    @RequestMapping(value = {"list", ""})
    public String list(AccountRunning accountRunning, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<AccountRunning> pageParam = new Page<AccountRunning>(request, response);
        pageParam.setPageSize(10);
        Page<AccountRunning> pageData = accountService.requestAccountRunningList(pageParam, accountRunning);
        pageData.setMessage("结算明细账");
        model.addAttribute("page", pageData);
        return "modules/settlement/runningaccountList";
    }
}
