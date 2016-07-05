/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.task.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.crm.task.entity.RfCrmUserTask;
import com.thinkgem.crm.task.entity.sub.RfCrmUserTaskReceive;
import com.thinkgem.crm.task.service.RfCrmUserTaskService;
import com.thinkgem.crm.task.service.sub.RfCrmUserTaskReceiveService;
import com.thinkgem.crm.user.entity.RfCrmUserArea;
import com.thinkgem.crm.user.entity.SyOrgUser;
import com.thinkgem.crm.user.service.RfCrmUserAreaService;
import com.thinkgem.crm.user.service.SyOrgUserService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * CRM任务发送Controller
 * @author 李雪楠
 * @version 2016-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/task/rfCrmUserTask")
public class RfCrmUserTaskController extends BaseController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RfCrmUserTaskService rfCrmUserTaskService;
	
	@Autowired
	private SyOrgUserService syOrgUserService;
	
	@Autowired
	private TProvinceService provinceService;
	
	@Autowired
	private RfCrmUserAreaService rfCrmUserAreaService;
	
	@Autowired
	private RfCrmUserTaskReceiveService rfCrmUserTaskReceiveService;
	
	@ModelAttribute
	public RfCrmUserTask get(@RequestParam(required=false) String id,Model model,HttpServletRequest request, HttpServletResponse response) {
		RfCrmUserTask entity = null;
		
		//id != null 查看功能, id = null 是添加
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmUserTaskService.get(id);
			
			//任务接收人
			RfCrmUserTaskReceive rfCrmUserTaskReceive = new RfCrmUserTaskReceive();
			rfCrmUserTaskReceive.setTaskId(Long.parseLong(id));
			List<RfCrmUserTaskReceive> list = rfCrmUserTaskReceiveService.findList(rfCrmUserTaskReceive);
			
			List<String> strs = new ArrayList<String>();
			strs.add("*");
			for (int i = 0; i < list.size(); i++) {
				RfCrmUserTaskReceive receive = list.get(i);
				strs.add(receive.getReceiveUserId());
			}
			SyOrgUser syOrgUser = new SyOrgUser();
			syOrgUser.setUserCodeList(strs);
			syOrgUser.setTaskId(Long.parseLong(id));
			Page<SyOrgUser> page = syOrgUserService.findPage(new Page<SyOrgUser>(request,response) , syOrgUser);
			
			model.addAttribute("page", page);
			
			model.addAttribute("taskView", true);
		}
		if (entity == null){
			entity = new RfCrmUserTask();
		}
		
		//省
		List<TProvince> provinceList = provinceService.findList(new TProvince());
		model.addAttribute("provinceList",provinceList);
		 
		return entity;
	}
	
	@RequiresPermissions("task:rfCrmUserTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(RfCrmUserTask rfCrmUserTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmUserTask> page = rfCrmUserTaskService.findPage(new Page<RfCrmUserTask>(request, response), rfCrmUserTask); 
		model.addAttribute("page", page);
		//查询人员表
		syOrgUserService.getSyOrgUserList(model);
		
		return "crm/task/rfCrmUserTaskList";
	}

	@RequiresPermissions("task:rfCrmUserTask:view")
	@RequestMapping(value = "form")
	public String form(RfCrmUserTask rfCrmUserTask, Model model) {
		model.addAttribute("rfCrmUserTask", rfCrmUserTask);
		return "crm/task/rfCrmUserTaskForm";
	}

	@RequiresPermissions("task:rfCrmUserTask:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmUserTask rfCrmUserTask, Model model, RedirectAttributes redirectAttributes) {
		log.info("CRM-OFFICE-开始发布任务");
		if (!beanValidator(model, rfCrmUserTask)){
			return form(rfCrmUserTask, model);
		}
		User user = UserUtils.getUser();
		//当前发布人
		rfCrmUserTask.setSenderUserId(user.getId());
		rfCrmUserTask.setSenderUserName(user.getName());
		//时间
		if(rfCrmUserTask.getCreateTime() == null){
			rfCrmUserTask.setCreateTime(new Date());
		}
		rfCrmUserTask.setUpdateTime(new Date());
		rfCrmUserTask.setIsComplete("1");
		
		
		//根据省市保存任务接收人
		List<RfCrmUserArea> list = null;
		if(rfCrmUserTask.getCityCode() != null && !"".equals(rfCrmUserTask.getCityCode())){
			RfCrmUserArea rfCrmUserArea = new RfCrmUserArea();
			rfCrmUserArea.setCityCode(rfCrmUserTask.getCityCode());
			list = rfCrmUserAreaService.findList(rfCrmUserArea);
		}else if(rfCrmUserTask.getProvCode() != null){
			RfCrmUserArea rfCrmUserArea = new RfCrmUserArea();
			rfCrmUserArea.setProvCode(rfCrmUserTask.getProvCode());
			list = rfCrmUserAreaService.findList(rfCrmUserArea);
		}
		if(CollectionUtils.isEmpty(list)){
			addMessage(redirectAttributes, "保存任务失败,没有查询到任务接收人");
			return "redirect:"+Global.getAdminPath()+"/crm/task/rfCrmUserTask/?repage";
		}
				
		rfCrmUserTaskService.save(rfCrmUserTask);
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		
		for (RfCrmUserArea rfCrmUserArea : list) {
			//开始发送
			if(map.get(rfCrmUserArea.getUserCode()) != null || "4".equals(rfCrmUserArea.getUserState())){
				continue;
			}
			RfCrmUserTaskReceive rfCrmUserTaskReceive = new RfCrmUserTaskReceive();
			rfCrmUserTaskReceive.setTaskId(Long.parseLong(rfCrmUserTask.getId()));
			rfCrmUserTaskReceive.setReceiveUserId(rfCrmUserArea.getUserCode());
			rfCrmUserTaskReceive.setIsComplete("2");
			rfCrmUserTaskReceive.setCreateTime(new Date());
			
			map.put(rfCrmUserArea.getUserCode(), true);
			
			rfCrmUserTaskReceiveService.save(rfCrmUserTaskReceive);
		}
		addMessage(redirectAttributes, "保存任务成功");
		log.info("CRM-OFFICE-开始发布任务成功");
		return "redirect:"+Global.getAdminPath()+"/crm/task/rfCrmUserTask/?repage";
	}
	
	@RequiresPermissions("task:rfCrmUserTask:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmUserTask rfCrmUserTask, RedirectAttributes redirectAttributes) {
		log.info("CRM-OFFICE-删除任务");
		rfCrmUserTaskService.delete(rfCrmUserTask);
		addMessage(redirectAttributes, "删除任务成功");
		log.info("CRM-OFFICE-删除任务成功");
		return "redirect:"+Global.getAdminPath()+"/crm/task/rfCrmUserTask/?repage";
	}

}