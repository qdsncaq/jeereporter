/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.accntwithdraw.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mk.settlement.enums.AccountRoleEnums;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.sc.accntwithdraw.entity.HotelWithdrawExcelclazz;
import com.thinkgem.sc.accntwithdraw.entity.SalerWithdrawExcelclazz;
import com.thinkgem.sc.accntwithdraw.entity.SettlementAccountWithdrawcash;
import com.thinkgem.sc.accntwithdraw.service.SettlementAccountWithdrawcashService;
import com.thinkgem.sc.enums.WithdrawStatusEnums;

/**
 * accntwithdrawController
 * @author lm
 * @version 2016-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/accntwithdraw/settlementAccountWithdrawcash")
public class SettlementAccountWithdrawcashController extends BaseController {
	
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SettlementAccountWithdrawcashService settlementAccountWithdrawcashService;
	
	@ModelAttribute
	public SettlementAccountWithdrawcash get(@RequestParam(required=false) String id) {
		SettlementAccountWithdrawcash entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementAccountWithdrawcashService.get(id);
		}
		if (entity == null){
			entity = new SettlementAccountWithdrawcash();
		}
		return entity;
	}
	
	@RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementAccountWithdrawcash settlementAccountWithdrawcash, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementAccountWithdrawcash> page = settlementAccountWithdrawcashService.findPage(new Page<SettlementAccountWithdrawcash>(request, response), settlementAccountWithdrawcash); 
		model.addAttribute("page", page);
		return "sc/accntwithdraw/settlementAccountWithdrawcashList";
	}

	/**
	 * 酒店老板提现申请列表页面.
	 * 
	 * @param settlementAccountWithdrawcash
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequestMapping(value = {"listOfHotel", ""})
    public String listOfHotel(SettlementAccountWithdrawcash settlementAccountWithdrawcash, HttpServletRequest request, HttpServletResponse response, Model model) {
        settlementAccountWithdrawcash.setAccountrole(AccountRoleEnums.BOSS.getIndex());
        
//        List<Integer> statuses = new ArrayList<Integer>();
//        List<Role> roleList = UserUtils.getUser().getRoleList();
//        for (Role role : roleList) {
//            if ("HOTEL_TXSH_OPERATOR".equals(role.getEnname())) {
//                statuses.add(WithdrawStatusEnums.ACCSUBMIT.getIndex());
//            }
//            if ("HOTEL_TXSH_HR".equals(role.getEnname())) {
//                statuses.add(WithdrawStatusEnums.OPERATOR_CONFIRM.getIndex());
//            }
//            if ("HOTEL_TXSH_FI".equals(role.getEnname())) {
//                statuses.add(WithdrawStatusEnums.CHECK.getIndex());
//                statuses.add(WithdrawStatusEnums.HR_CONFIRM.getIndex());
//            }
//        }
//        if (statuses.size() > 0) {
//            settlementAccountWithdrawcash.setStatuses(statuses);
//        } else {
//            settlementAccountWithdrawcash.setStatus(9999);
//        }

        Page<SettlementAccountWithdrawcash> page = settlementAccountWithdrawcashService.findPage(new Page<SettlementAccountWithdrawcash>(request, response), settlementAccountWithdrawcash); 
        model.addAttribute("page", page);
        return "sc/accntwithdraw/settlementAccountWithdrawcashListOfHotel";
    }
    
    /**
     * 销售提现申请列表页面.
     * 
     * @param settlementAccountWithdrawcash
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequestMapping(value = {"listOfSaler", ""})
    public String listOfSaler(SettlementAccountWithdrawcash settlementAccountWithdrawcash, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 销售角色列表
        List<Integer> salerRoles = new ArrayList<Integer>();
        salerRoles.add(AccountRoleEnums.SALER.getIndex());
        salerRoles.add(AccountRoleEnums.PARTNER_IMIKE.getIndex());
        salerRoles.add(AccountRoleEnums.MANAGER_IMIKE.getIndex());
        salerRoles.add(AccountRoleEnums.PARTNER_JINDUN.getIndex());
        salerRoles.add(AccountRoleEnums.MANAGER_JINDUN.getIndex());
        settlementAccountWithdrawcash.setAccountroles(salerRoles);
        
        // 根据审核角色过滤不同状态的销售提现申请数据
        List<Integer> statuses = new ArrayList<Integer>();
        List<Role> roleList = UserUtils.getUser().getRoleList();
        for (Role role : roleList) {
            if ("SALER_TXSH_OPERATOR".equals(role.getEnname())) {
                statuses.add(WithdrawStatusEnums.ACCSUBMIT.getIndex());
                statuses.add(WithdrawStatusEnums.OPERATOR_CONFIRM.getIndex());
                statuses.add(WithdrawStatusEnums.OPERATOR_CANCEL.getIndex());
            }
            if ("SALER_TXSH_HR".equals(role.getEnname())) {
                statuses.add(WithdrawStatusEnums.OPERATOR_CONFIRM.getIndex());
                statuses.add(WithdrawStatusEnums.HR_CONFIRM.getIndex());
                statuses.add(WithdrawStatusEnums.HR_CANCEL.getIndex());
            }
            if ("SALER_TXSH_FI".equals(role.getEnname())) {
                statuses.add(WithdrawStatusEnums.CHECK.getIndex());
                statuses.add(WithdrawStatusEnums.HR_CONFIRM.getIndex());
                statuses.add(WithdrawStatusEnums.PLAYMONEY.getIndex());
                statuses.add(WithdrawStatusEnums.CANCEL.getIndex());
            }
        }
        if (statuses.size() > 0) {
            settlementAccountWithdrawcash.setStatuses(statuses);
        } else {
            settlementAccountWithdrawcash.setStatus(9999);
        }
        
        Page<SettlementAccountWithdrawcash> page = settlementAccountWithdrawcashService.findPage(new Page<SettlementAccountWithdrawcash>(request, response), settlementAccountWithdrawcash); 
        model.addAttribute("page", page);
        return "sc/accntwithdraw/settlementAccountWithdrawcashListOfSaler";
    }
    
    /**
     * 销售提现查询列表页面.
     * 
     * @param settlementAccountWithdrawcash
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequestMapping(value = {"listOfSalerQuery", ""})
    @RequiresRoles("SALER_TXCX_ALL")
    public String listOfSalerQuery(SettlementAccountWithdrawcash settlementAccountWithdrawcash, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 销售角色列表
        List<Integer> salerRoles = new ArrayList<Integer>();
        salerRoles.add(AccountRoleEnums.SALER.getIndex());
        salerRoles.add(AccountRoleEnums.PARTNER_IMIKE.getIndex());
        salerRoles.add(AccountRoleEnums.MANAGER_IMIKE.getIndex());
        salerRoles.add(AccountRoleEnums.PARTNER_JINDUN.getIndex());
        salerRoles.add(AccountRoleEnums.MANAGER_JINDUN.getIndex());
        settlementAccountWithdrawcash.setAccountroles(salerRoles);
        
        Page<SettlementAccountWithdrawcash> page = settlementAccountWithdrawcashService.findPage(new Page<SettlementAccountWithdrawcash>(request, response), settlementAccountWithdrawcash); 
        model.addAttribute("page", page);
        return "sc/accntwithdraw/settlementAccountWithdrawcashListOfSalerQuery";
    }
	
	@RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
	@RequestMapping(value = "form")
	public String form(SettlementAccountWithdrawcash settlementAccountWithdrawcash, Model model) {
		model.addAttribute("settlementAccountWithdrawcash", settlementAccountWithdrawcash);
		return "sc/accntwithdraw/settlementAccountWithdrawcashForm";
	}

	@RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:edit")
	@RequestMapping(value = "save")
	public String save(SettlementAccountWithdrawcash settlementAccountWithdrawcash, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementAccountWithdrawcash)){
			return form(settlementAccountWithdrawcash, model);
		}
		settlementAccountWithdrawcashService.save(settlementAccountWithdrawcash);
		addMessage(redirectAttributes, "保存accntwithdraw成功");
		return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/?repage";
	}
	
	@RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementAccountWithdrawcash settlementAccountWithdrawcash, RedirectAttributes redirectAttributes) {
		settlementAccountWithdrawcashService.delete(settlementAccountWithdrawcash);
		addMessage(redirectAttributes, "删除accntwithdraw成功");
		return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/?repage";
	}
	
	/**
	 * 点击通过
		流水明细中状态更改，
		提现申请记录表更改状态，
		提现申请记录表结算后余额记录添加，
		账户余额表查询，
	 * @param ids
	 * @param settlementAccountWithdrawcash
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public int update(String ids, SettlementAccountWithdrawcash settlementAccountWithdrawcash, Model model, RedirectAttributes redirectAttributes) {
		if(ids == null || ids == ""){
			log.info("没有要提交的数据.");
			return -1;
		}
		int count = 0;
		try {
			List<Long> lids = new ArrayList<Long>();
			String[] idsall = ids.split(",");
			for (String id : idsall) {
				lids.add(Long.parseLong(id));
			}
			count = settlementAccountWithdrawcashService.updateStatus(lids);
			log.info("审核通过.共" + count + "条数据.");
			addMessage(redirectAttributes, "审核通过.共" + count + "条数据.");
		} catch (Exception e) {
			log.error("提现申请第一步审核异常.", e);
		}
		return count;
	}

	/**
	 * 审核驳回
	 * 	明细表状态改为 取消
	 * 	账户余额 加回去
	 * 	提现申请记录表   状态改为-驳回
	 * @param ids
	 * @param settlementAccountWithdrawcash
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "updateno")
	@ResponseBody
	public int updateno(String ids, SettlementAccountWithdrawcash settlementAccountWithdrawcash, Model model, RedirectAttributes redirectAttributes) {
		if(ids == null || ids == ""){
			log.info("没有要提交的数据.");
			return -1;
		}
		int count = 0;
		try {
			List<Long> lids = new ArrayList<Long>();
			String[] idsall = ids.split(",");
			for (String id : idsall) {
				lids.add(Long.parseLong(id));
			}
			count = settlementAccountWithdrawcashService.updateNo(lids);
			log.info("驳回" + count + "条数据.");
			addMessage(redirectAttributes, "驳回" + count + "条数据.");
		} catch (Exception e) {
			log.error("驳回处理操作异常.", e);
		}
		
		return count;
	}
	
	@RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(SettlementAccountWithdrawcash accntWithdrawcash,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "提现申请列表.xlsx";
    		List<SettlementAccountWithdrawcash> list = settlementAccountWithdrawcashService.findList(accntWithdrawcash);
    		new ExportExcel("提现申请", SettlementAccountWithdrawcash.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "提现申请数据excel导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/?repage";
	}
	
	/**
	 * 导出酒店提现申请.
	 * 
	 * @param accntWithdrawcash
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequestMapping(value = "exportexcelHotel")
    public String exportexcelHotel(SettlementAccountWithdrawcash accntWithdrawcash,HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "酒店提现申请列表.xlsx";
            accntWithdrawcash.setAccountrole(AccountRoleEnums.BOSS.getIndex());
            List<SettlementAccountWithdrawcash> list = settlementAccountWithdrawcashService.findList(accntWithdrawcash);
            new ExportExcel("酒店提现申请", HotelWithdrawExcelclazz.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "酒店提现申请数据excel导出失败！失败信息："+e.getMessage());
        }
        return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/listOfHotel/?repage";
    }
    
    /**
     * 导出销售提现申请.
     * 
     * @param accntWithdrawcash
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequestMapping(value = "exportexcelSaler")
    public String exportexcelSaler(SettlementAccountWithdrawcash accntWithdrawcash,HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "销售提现申请列表.xlsx";
            // 销售角色列表
            List<Integer> salerRoles = new ArrayList<Integer>();
            salerRoles.add(AccountRoleEnums.SALER.getIndex());
            salerRoles.add(AccountRoleEnums.PARTNER_IMIKE.getIndex());
            salerRoles.add(AccountRoleEnums.MANAGER_IMIKE.getIndex());
            salerRoles.add(AccountRoleEnums.PARTNER_JINDUN.getIndex());
            salerRoles.add(AccountRoleEnums.MANAGER_JINDUN.getIndex());
            accntWithdrawcash.setAccountroles(salerRoles);
            
            // 根据审核角色过滤不同状态的销售提现申请数据
            List<Integer> statuses = new ArrayList<Integer>();
            List<Role> roleList = UserUtils.getUser().getRoleList();
            for (Role role : roleList) {
                if ("SALER_TXSH_OPERATOR".equals(role.getEnname())) {
                    statuses.add(WithdrawStatusEnums.ACCSUBMIT.getIndex());
                    statuses.add(WithdrawStatusEnums.OPERATOR_CONFIRM.getIndex());
                    statuses.add(WithdrawStatusEnums.OPERATOR_CANCEL.getIndex());
                }
                if ("SALER_TXSH_HR".equals(role.getEnname())) {
                    statuses.add(WithdrawStatusEnums.OPERATOR_CONFIRM.getIndex());
                    statuses.add(WithdrawStatusEnums.HR_CONFIRM.getIndex());
                    statuses.add(WithdrawStatusEnums.HR_CANCEL.getIndex());
                }
                if ("SALER_TXSH_FI".equals(role.getEnname())) {
                    statuses.add(WithdrawStatusEnums.CHECK.getIndex());
                    statuses.add(WithdrawStatusEnums.HR_CONFIRM.getIndex());
                    statuses.add(WithdrawStatusEnums.PLAYMONEY.getIndex());
                    statuses.add(WithdrawStatusEnums.CANCEL.getIndex());
                }
            }
            if (statuses.size() > 0) {
                accntWithdrawcash.setStatuses(statuses);
            } else {
                accntWithdrawcash.setStatus(9999);
            }
            
            List<SettlementAccountWithdrawcash> list = settlementAccountWithdrawcashService.findList(accntWithdrawcash);
            new ExportExcel("销售提现申请", SalerWithdrawExcelclazz.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "酒店提现申请数据excel导出失败！失败信息："+e.getMessage());
        }
        return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/listOfSaler/?repage";
    }
    
    /**
     * 导出销售提现申请.
     * 
     * @param accntWithdrawcash
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequestMapping(value = "exportexcelSalerQuery")
    public String exportexcelSalerQuery(SettlementAccountWithdrawcash accntWithdrawcash,HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "销售提现查询列表.xlsx";
            // 销售角色列表
            List<Integer> salerRoles = new ArrayList<Integer>();
            salerRoles.add(AccountRoleEnums.SALER.getIndex());
            salerRoles.add(AccountRoleEnums.PARTNER_IMIKE.getIndex());
            salerRoles.add(AccountRoleEnums.MANAGER_IMIKE.getIndex());
            salerRoles.add(AccountRoleEnums.PARTNER_JINDUN.getIndex());
            salerRoles.add(AccountRoleEnums.MANAGER_JINDUN.getIndex());
            accntWithdrawcash.setAccountroles(salerRoles);
            
            List<SettlementAccountWithdrawcash> list = settlementAccountWithdrawcashService.findList(accntWithdrawcash);
            new ExportExcel("销售提现查询", SalerWithdrawExcelclazz.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "酒店提现查询数据excel导出失败！失败信息："+e.getMessage());
        }
        return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/listOfSalerQuery/?repage";
    }
    
	// 修改状态 打款
	@RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
	@RequiresRoles("SC_FI_PAYDONE")
	@RequestMapping(value = "paydone")
	@ResponseBody
	public int paydone(String ids, SettlementAccountWithdrawcash accntWithdrawcash,HttpServletResponse response, RedirectAttributes redirectAttributes){
		int count = 0;
		try {
			if(ids == null || ids == ""){
				log.info("没有要提交的数据.");
				return -1;
			}
			List<Long> lids = new ArrayList<Long>();
			String[] idsall = ids.split(",");
			for (String id : idsall) {
				lids.add(Long.parseLong(id));
			}
			count = settlementAccountWithdrawcashService.updatePaydone(lids);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "修改已打款状态报错."+e.getMessage());
		}
		return count;
	}
	
	/**
	 * 销售提现申请运营审核确认.
	 * 
	 * @param settlementAccountWithdrawcash
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
	@RequiresRoles(value={"SC_FI_SALESREVIEWED", "SALER_TXSH_OPERATOR"})
    @RequestMapping(value = "operatorConfirm")
	public String operatorConfirm(SettlementAccountWithdrawcash settlementAccountWithdrawcash, RedirectAttributes redirectAttributes) {
	    int confirmResult = settlementAccountWithdrawcashService.salerWithdrawcashOperatorConfirm(settlementAccountWithdrawcash);
	    if (1 == confirmResult) {
	        addMessage(redirectAttributes, "销售提现申请运营审核确认成功.");
	    } else {
	        addMessage(redirectAttributes, "销售提现申请运营审核确认失败.");
	    }
        return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/listOfSaler/?repage";
	}
    /**
     * 销售提现申请运营审核驳回.
     * 
     * @param settlementAccountWithdrawcash
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequiresRoles(value={"SC_FI_SALESREVIEWED", "SALER_TXSH_OPERATOR"})
    @RequestMapping(value = "operatorCancel")
    public String operatorCancel(SettlementAccountWithdrawcash settlementAccountWithdrawcash, RedirectAttributes redirectAttributes) {
        int cancelResult = settlementAccountWithdrawcashService.salerWithdrawcashOperatorCancel(settlementAccountWithdrawcash);
        if (1 == cancelResult) {
            addMessage(redirectAttributes, "销售提现申请运营审核驳回成功.");
        } else {
            addMessage(redirectAttributes, "销售提现申请运营审核驳回失败.");
        }
        return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/listOfSaler/?repage";
    }
    
    /**
     * 销售提现申请人事审核确认.
     * 
     * @param settlementAccountWithdrawcash
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequiresRoles(value={"SC_FI_SALESREVIEWED", "SALER_TXSH_HR"})
    @RequestMapping(value = "hrConfirm")
    public String hrConfirm(SettlementAccountWithdrawcash settlementAccountWithdrawcash, RedirectAttributes redirectAttributes) {
        int confirmResult = settlementAccountWithdrawcashService.salerWithdrawcashHrConfirm(settlementAccountWithdrawcash);
        if (1 == confirmResult) {
            addMessage(redirectAttributes, "销售提现申请人事审核确认成功.");
        } else {
            addMessage(redirectAttributes, "销售提现申请人事审核确认失败.");
        }
        return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/listOfSaler/?repage";
    }
    /**
     * 销售提现申请人事审核驳回.
     * 
     * @param settlementAccountWithdrawcash
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequiresRoles(value={"SC_FI_SALESREVIEWED", "SALER_TXSH_HR"})
    @RequestMapping(value = "hrCancel")
    public String hrCancel(SettlementAccountWithdrawcash settlementAccountWithdrawcash, RedirectAttributes redirectAttributes) {
        int cancelResult = settlementAccountWithdrawcashService.salerWithdrawcashHrCancel(settlementAccountWithdrawcash);
        if (1 == cancelResult) {
            addMessage(redirectAttributes, "销售提现申请人事审核驳回成功.");
        } else {
            addMessage(redirectAttributes, "销售提现申请人事审核驳回失败.");
        }
        return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/listOfSaler/?repage";
    }
    
    /**
     * 销售提现申请财务审核确认.
     * 
     * @param settlementAccountWithdrawcash
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequiresRoles(value={"SC_FI_SALESREVIEWED", "SALER_TXSH_FI"})
    @RequestMapping(value = "fiConfirm")
    public String fiConfirm(SettlementAccountWithdrawcash settlementAccountWithdrawcash, RedirectAttributes redirectAttributes) {
        int confirmResult = settlementAccountWithdrawcashService.salerWithdrawcashFiConfirm(settlementAccountWithdrawcash);
        if (1 == confirmResult) {
            addMessage(redirectAttributes, "销售提现申请财务审核确认成功.");
        } else {
            addMessage(redirectAttributes, "销售提现申请财务审核确认失败.");
        }
        return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/listOfSaler/?repage";
    }
    /**
     * 销售提现申请财务审核驳回.
     * 
     * @param settlementAccountWithdrawcash
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("accntwithdraw:settlementAccountWithdrawcash:view")
    @RequiresRoles(value={"SC_FI_SALESREVIEWED", "SALER_TXSH_FI"})
    @RequestMapping(value = "fiCancel")
    public String fiCancel(SettlementAccountWithdrawcash settlementAccountWithdrawcash, RedirectAttributes redirectAttributes) {
        int cancelResult = settlementAccountWithdrawcashService.salerWithdrawcashFiCancel(settlementAccountWithdrawcash);
        if (1 == cancelResult) {
            addMessage(redirectAttributes, "销售提现申请财务审核驳回成功.");
        } else {
            addMessage(redirectAttributes, "销售提现申请财务审核驳回失败.");
        }
        return "redirect:"+Global.getAdminPath()+"/accntwithdraw/settlementAccountWithdrawcash/listOfSaler/?repage";
    }

}