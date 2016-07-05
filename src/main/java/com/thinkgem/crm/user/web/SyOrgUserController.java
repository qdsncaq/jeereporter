/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lz.mongo.bislog.BisLog;
import com.lz.mongo.bislog.BisLogDelegate;
import com.thinkgem.crm.hotel.entity.RfCrmHotelPublic;
import com.thinkgem.crm.hotel.enums.RfCrmHotelPublicLogRemarkEnum;
import com.thinkgem.crm.hotel.service.RfCrmHotelPublicService;
import com.thinkgem.crm.user.common.RfCrmCommonService;
import com.thinkgem.crm.user.entity.RfCrmUserArea;
import com.thinkgem.crm.user.entity.RfCrmUserRelation;
import com.thinkgem.crm.user.entity.SyOrgUser;
import com.thinkgem.crm.user.service.RfCrmUserAreaService;
import com.thinkgem.crm.user.service.RfCrmUserRelationService;
import com.thinkgem.crm.user.service.SyOrgUserService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.auth.entity.AuthUser;
import com.thinkgem.jeesite.modules.auth.service.AuthUserService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.ws.UserInfoOperService;
import com.thinkgem.jeesite.ws.model.Result;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
/**
 * BMS用户表Controller
 * @author 李雪楠
 * @version 2016-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/user/syOrgUser")
public class SyOrgUserController extends BaseController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String PW = "123456";
	
	@Autowired
	private SyOrgUserService syOrgUserService;
	
	@Autowired
	private UserInfoOperService userInfoOperService;
	
	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	private RfCrmCommonService rfCrmCommonService;
	
	@Autowired
	private RfCrmUserAreaService rfCrmUserAreaService;
	
	@Autowired
	private RfCrmHotelPublicService rfCrmHotelPublicService;

    @Autowired
    private RfCrmUserRelationService rfCrmUserRelationService;
	
    @Autowired
    private BisLogDelegate bisLogDelegate;
    
	@ModelAttribute
	public SyOrgUser get(@RequestParam(required=false) String id, Model model) {
		SyOrgUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = syOrgUserService.get(id);
			entity.setUserMobileNew(entity.getUserMobile());
		}
		if (entity == null){
			entity = new SyOrgUser();
		}
		rfCrmCommonService.getProvAndCity(entity.getProvCode(), model);
		return entity;
	}
	
	@RequiresPermissions("user:syOrgUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(SyOrgUser syOrgUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SyOrgUser> page = syOrgUserService.findPage(new Page<SyOrgUser>(request, response), syOrgUser); 
		model.addAttribute("page", page);
		return "crm/user/syOrgUserList";
	}

	@RequiresPermissions("user:syOrgUser:view")
	@RequestMapping(value = "form")
	public String form(SyOrgUser syOrgUser, Model model) {
		model.addAttribute("syOrgUser", syOrgUser);
		return "crm/user/syOrgUserForm";
	}

	@RequiresPermissions("user:syOrgUser:edit")
	@RequestMapping(value = "save")
	public String save(SyOrgUser syOrgUser, Model model, RedirectAttributes redirectAttributes) {
		log.info("CRM-OFFICE保存销售用户开始");
		if (!beanValidator(model, syOrgUser)){
			return form(syOrgUser, model);
		}
		String msg = "保存用户成功";
		String returnStr = "redirect:"+Global.getAdminPath()+"/crm/user/syOrgUser/?repage";
		//表示新增数据
		if(syOrgUser.getUserCode() == null){
			//1.校验新增用户是否存在
			SyOrgUser user = new SyOrgUser();
			user.setUserMobile(syOrgUser.getUserMobileNew());
			List<SyOrgUser> list = syOrgUserService.findList(user);
			if(CollectionUtils.isNotEmpty(list)){
				log.info("CRM-OFFICE 该用户手机号已经存在-syOrgUser");
				msg = "该用户手机号已经存在,请重新填写!";
				addMessage(redirectAttributes, msg);
				return returnStr;
			}
			
			//2.增加用户需要通知统一后台登录
            Result result = new Result();
			if (StringUtils.isNotBlank(syOrgUser.getLevel()) &&
					(!syOrgUser.getLevel().equalsIgnoreCase(DictUtils.getDictValue("销售", "area_level", "3")) &&
							!syOrgUser.getLevel().equalsIgnoreCase(DictUtils.getDictValue("合伙人", "area_level", "4")))) {

				List<String> authRoleList = new ArrayList<String>();
				authRoleList.add("CRM_EMPLOYEE");
				authRoleList.add("ROLE_OFFICE_ADMIN");

				/*result = userInfoOperService.syncUserInfo(syOrgUser.getUserMobileNew(),
                        PW, "CRM", authRoleList, "CRM");*/
                
                //如果是区总和区经需要增加office的登录权限
                AuthUser authUser = new AuthUser();
                authUser.setUsername(syOrgUser.getUserName());
                authUser.setPassword(PW);
                authUser.setSource("CRM");
                authUser.setPhone(syOrgUser.getUserMobileNew());
                
                List<String> roleCodeList = new ArrayList<String>();
                roleCodeList.add("CRM_EMPLOYEE");
                roleCodeList.add("ROLE_OFFICE_ADMIN");
				
                boolean flag =  authUserService.saveSysUserAndRole(authUser, roleCodeList, "销售角色");
                if(flag){
                    result.setCode(Result.SUCCESS);
                    result.setMsg("保存成功");
                } else{
                    result.setCode(Result.DB_ERROR);
                    result.setMsg("插入数据库失败");
                }
            } else {
                result = userInfoOperService.syncUserInfo(syOrgUser.getUserMobileNew(),
                        syOrgUser.getUserMobileNew(),
                        PW, "CRM", "CRM_EMPLOYEE", "CRM");
            }
			if(!Result.SUCCESS.equals(result.getCode())){
				log.info("CRM-OFFICE " + result.getMsg() + "-authUser");
				msg = result.getMsg();
				addMessage(redirectAttributes, msg);
				return returnStr;
			}
			msg = "保存用户成功,用户初始密码是：" + PW;
		}else{
			//3.如果不是新增数据,则要检查手机号,如果更换手机号,则需要通知同统一后台
			if(syOrgUser.getUserMobileNew() != null && !syOrgUser.getUserMobileNew().equals(syOrgUser.getUserMobile())){
				log.info("CRM-OFFICE 更换手机号同步统一登录平台");
				//手机号不等说明更换了手机号
				boolean flag = authUserService.updateUserName(syOrgUser.getUserMobile(), syOrgUser.getUserMobileNew());
				if(!flag){
					log.info("CRM-OFFICE 更换手机号同步统一登录平台失败");
					msg = "同步统一登录平台数据失败!";
					addMessage(redirectAttributes, msg);
					return returnStr;
				}
				
				log.info("CRM-OFFICE 更换手机号同步统一登录平台成功");
			}
		}
		
		//4.保存sy_org_user
		syOrgUser.setUserMobile(syOrgUser.getUserMobileNew());
		syOrgUser.setsMtime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		syOrgUser.setUserPassword(null);
		syOrgUserService.save(syOrgUser);
		log.info("CRM-OFFICE保存销售用户成功");
		
		//5.如果是新增需要在查询一次user_code
		if(StringUtils.isBlank(syOrgUser.getUserCode())){
			SyOrgUser sou = new SyOrgUser();
			sou.setUserMobile(syOrgUser.getUserMobile());
			List<SyOrgUser> list = syOrgUserService.findList(sou);
			sou = list.get(0);
			syOrgUser.setUserCode(sou.getUserCode());
		}
		
		//5.如果在职\外来人员,将该销售所属的酒店退回公海
		/*if("1".equals(syOrgUser.getUserState()) || "4".equals(syOrgUser.getUserState()) ){
			log.info("CRM-OFFICE-保存销售所属酒店退回公海开始");
			RfCrmHotelPublic rfCrmHotelPublic = new RfCrmHotelPublic();
			rfCrmHotelPublic.setHotelUserId(syOrgUser.getUserCode());
			rfCrmHotelPublicService.returnPublicByUser(rfCrmHotelPublic);
			log.info("CRM-OFFICE-保存销售所属酒店退回公海成功");
		}*/
		
		//6.保存用户所属区域(必须是在职的情况下保存)
		if(StringUtils.isNotBlank(syOrgUser.getProvCode()) && 
		   "1".equals(syOrgUser.getUserState())){
			log.info("CRM-OFFICE-保存销售所属区域开始");
			//5.1查询销售所属区域
			RfCrmUserArea query = new RfCrmUserArea();
			query.setUserCode(syOrgUser.getUserCode());
			List<RfCrmUserArea> list = rfCrmUserAreaService.findList(query);
			
			//5.2判断是否已经存在该数据
			RfCrmUserArea rfCrmUserArea = null;
			if(CollectionUtils.isNotEmpty(list)){
				rfCrmUserArea = list.get(0);
			}
			//5.3不存在直接new一个
			if(rfCrmUserArea == null){
				rfCrmUserArea = new RfCrmUserArea();
			}
			rfCrmUserArea.setUserCode(syOrgUser.getUserCode());
			rfCrmUserArea.setProvCode(syOrgUser.getProvCode());
			rfCrmUserArea.setCityCode("".equals(syOrgUser.getCityCode())?null:syOrgUser.getCityCode());
			rfCrmUserArea.setAvailable("1");
			if(rfCrmUserArea.getId() == null){
				//说明新增
				rfCrmUserArea.setCreateTime(new Date());
			}
			rfCrmUserArea.setUpdateTime(new Date());
			
			rfCrmUserAreaService.save(rfCrmUserArea);
			log.info("CRM-OFFICE-保存销售所属区域成功");
		}

        // 保存用户的级别关系
        if (StringUtils.isNoneBlank(syOrgUser.getLevel()) &&
                "1".equals(syOrgUser.getUserState())) {
            log.info("CRM-OFFICE-保存用户级别关系开始");
            RfCrmUserRelation userRelation = null;
            // 检查用户是否存在
            userRelation = rfCrmUserRelationService.findByUserCode(syOrgUser.getUserCode());

            if (null == userRelation) {
                userRelation = new RfCrmUserRelation();
                userRelation.setIsNewRecord(true);
            } else {
                userRelation.setIsNewRecord(false);
                userRelation.setId(userRelation.getRid().toString());
            }

            userRelation.setLevel(syOrgUser.getLevel());//职务
            userRelation.setType(syOrgUser.getType());//所属公司 1-乐住 2-眯客

			Integer roleId = null;
			if(Objects.equals(syOrgUser.getType(),1)){ // 如果是乐住公司
				if(Objects.equals(syOrgUser.getLevel(),DictUtils.getDictValue("区总", "area_level", "1"))){
					roleId = RfCrmUserRelation.UserRelationRoleEnum.LARGE_MGR_IMIKE.getKey(); //区总角色
				}else if(Objects.equals(syOrgUser.getLevel(),DictUtils.getDictValue("区经", "area_level", "2"))){
					roleId = RfCrmUserRelation.UserRelationRoleEnum.MGR_IMIKE.getKey(); //区经角色
				}else if(Objects.equals(syOrgUser.getLevel(),DictUtils.getDictValue("销售", "area_level", "3"))){
					roleId = RfCrmUserRelation.UserRelationRoleEnum.SALER_IMIKE.getKey(); //销售角色
				}else if(Objects.equals(syOrgUser.getLevel(),DictUtils.getDictValue("合伙人", "area_level", "4"))){
					roleId = RfCrmUserRelation.UserRelationRoleEnum.PARTNER_IMIKE.getKey(); //合伙人角色
				}
			} else if(Objects.equals(syOrgUser.getType(),2)){ // 如果是金盾公司
//				if(Objects.equals(syOrgUser.getLevel(),DictUtils.getDictValue("区总", "area_level", "1"))){
//					roleId = RfCrmUserRelation.UserRelationRoleEnum.LARGE_MGR_GOLD.getKey(); //区总角色
//				}else
				if(Objects.equals(syOrgUser.getLevel(),DictUtils.getDictValue("区经", "area_level", "1"))){
					roleId = RfCrmUserRelation.UserRelationRoleEnum.MGR_GOLD.getKey(); //区经角色
				}else if(Objects.equals(syOrgUser.getLevel(),DictUtils.getDictValue("合伙人", "area_level", "4"))){
					roleId = RfCrmUserRelation.UserRelationRoleEnum.PARTNER_GOLD.getKey(); //区经角色 //合伙人角色
				}
			}
			userRelation.setRoleId(roleId);//角色,与结算组角色码相同

            userRelation.setUserCode(syOrgUser.getUserCode());
            userRelation.setQzUserCode(syOrgUser.getQzUserCode());
            userRelation.setQzUserName(syOrgUser.getQzUserName());
            userRelation.setQjUserCode(syOrgUser.getQjUserCode());
            userRelation.setQjUserName(syOrgUser.getQjUserName());

            rfCrmUserRelationService.save(userRelation);
            log.info("CRM-OFFICE-保存用户级别关系成功");
        }

		addMessage(redirectAttributes, msg);
		return returnStr;
	}
	

	@RequiresPermissions("user:syOrgUser:edit")
	@RequestMapping(value = "returnPublic")
	@ResponseBody
	public String returnPublic(SyOrgUser syOrgUser, RedirectAttributes redirectAttributes) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "退回公海成功");
		map.put("code", "success");
		User user = UserUtils.getUser();         
        if ( user == null ) {
            logger.info("用户登录失败");
            map.put("result", "用户登录失败");
            return JSON.toJSONString(map);
        }          
        String  loginuser = user.getLoginName();
		BisLog bisLog = new BisLog();
	   	bisLog.setSystem("sysorguser");
	   	bisLog.setOperator(loginuser);
	   	bisLog.setBussinessId(syOrgUser.getUserCode());
	   	bisLog.setBussinssType(RfCrmHotelPublicLogRemarkEnum.ONESELFRETURN.getValue()); 
		bisLog.setCreateTime(new Date());
		try {
			//如果在职\外来人员,将该销售所属的酒店退回公海
			if(StringUtils.isNotBlank(syOrgUser.getUserCode())){
				log.info("CRM-OFFICE-保存销售所属酒店退回公海开始 userCode = " + syOrgUser.getUserCode());
				RfCrmHotelPublic rfCrmHotelPublic = new RfCrmHotelPublic();
				rfCrmHotelPublic.setHotelUserId(syOrgUser.getUserCode());
				rfCrmHotelPublicService.returnPublicByUser(rfCrmHotelPublic);
				log.info("CRM-OFFICE-保存销售所属酒店退回公海成功 userCode = " + syOrgUser.getUserCode());;
				bisLog.setContent("CRM-OFFICE-保存销售所属酒店退回公海成功 userCode = " + syOrgUser.getUserCode());
				bisLogDelegate.saveBigLog(bisLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "退回公海失败");
			map.put("code", "error");
			log.info("CRM-OFFICE-保存销售所属酒店退回公海失败 userCode = " + syOrgUser.getUserCode());;
			bisLog.setContent("CRM-OFFICE-保存销售所属酒店退回公海失败 userCode = " + syOrgUser.getUserCode());
			bisLogDelegate.saveBigLog(bisLog);
		}
		return JSON.toJSONString(map);
	}
	
	@RequiresPermissions("user:syOrgUser:edit")
	@RequestMapping(value = "delete")
	public String delete(SyOrgUser syOrgUser, RedirectAttributes redirectAttributes) {
		syOrgUserService.delete(syOrgUser);
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:"+Global.getAdminPath()+"/crm/user/syOrgUser/?repage";
	}
	/**
	 * 根据省编码查当前省的销售，省－销售及联
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("hotel:hotel:view")
	@RequestMapping(value = "getSeller")
	@ResponseBody
	public String getSeller(Model model,HttpServletRequest request) {
		int code = Integer.parseInt(request.getParameter("provcode"));
		logger.info("选择的省编码为："+code);
//		List<SyOrgUser> list = syOrgUserService.getSellersByProvCode(code);
		List<Map<String,String>> map = syOrgUserService.getSellersByProvCode(code);
		logger.info("当前省的销售为："+JSONObject.toJSONString(map));
		model.addAttribute("sellers", map);
//		List<AuthUser> allAuthUsers = authUserService.findList(new AuthUser());
//		List<AuthUser> selectedAuthUsers = authUserService.findListByAuthRole(authRole.getId());
//
//		model.addAttribute("allAuthUsers", Collections3.subtract(allAuthUsers, selectedAuthUsers));
//		model.addAttribute("authRole", authRole);
//		model.addAttribute("userList", selectedAuthUsers);
//		String selectedUsers = Collections3.extractToString(selectedAuthUsers, "id", ",");
//		model.addAttribute("selectIds", selectedUsers);
		return JSONObject.toJSONString(map);
	}
    /**
     * 导出销售用户数据
     * @param syOrgUser
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("user:syOrgUser:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(SyOrgUser syOrgUser, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            logger.info("开始导出销售用户数据");
            String fileName = "销售用户数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			List<SyOrgUser> syOrgUserList = syOrgUserService.findList(syOrgUser);
            new ExportExcel("销售用户数据", SyOrgUser.class).setDataList(syOrgUserList).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            logger.error("导出销售用户数据失败！失败信息：", e);
            addMessage(redirectAttributes, "导出销售用户数据失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/crm/user/syOrgUser/?repage";
    }

}