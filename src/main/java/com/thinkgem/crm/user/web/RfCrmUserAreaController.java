/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.thinkgem.crm.location.entity.AllAreas;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import com.thinkgem.crm.hotel.entity.RfCrmHotelPublic;
import com.thinkgem.crm.hotel.service.RfCrmHotelPublicService;
import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.crm.user.entity.RfCrmUserArea;
import com.thinkgem.crm.user.entity.SyOrgUser;
import com.thinkgem.crm.user.service.RfCrmUserAreaService;
import com.thinkgem.crm.user.service.SyOrgUserService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 用户区域设置Controller
 * @author 李雪楠
 * @version 2016-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/user/rfCrmUserArea")
public class RfCrmUserAreaController extends BaseController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RfCrmUserAreaService rfCrmUserAreaService;
	
	@Autowired
	private SyOrgUserService syOrgUserService;
	
	@Autowired
	private RfCrmHotelPublicService rfCrmHotelPublicService;
	
	/** 省服务 */
	@Autowired
	private TProvinceService provinceService;
	
	/** 市服务 */
	@Autowired
	private TCityService cityService;
	
	/** 县区服务 */
	@Autowired
	private TDistrictService districtService;
	
	@ModelAttribute
	public RfCrmUserArea get(@RequestParam(required=false) String id, Model model) {
		RfCrmUserArea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmUserAreaService.get(id);
		}
		if (entity == null){
			entity = new RfCrmUserArea();
		}
		syOrgUserService.getSyOrgUserList(model);
		getFormInfoCity(entity, model);
		getFormInfoUserByUserCode(entity, model);
		
		return entity;
	}
	
	@RequiresPermissions("user:rfCrmUserArea:view")
	@RequestMapping(value = {"list", ""})
	public String list(RfCrmUserArea rfCrmUserArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RfCrmUserArea> page = rfCrmUserAreaService.findPage(new Page<RfCrmUserArea>(request, response), rfCrmUserArea); 
		model.addAttribute("page", page);
		
		syOrgUserService.getSyOrgUserList(model);
		
		getFormInfoCity(rfCrmUserArea, model);
		return "crm/user/rfCrmUserAreaList";
	}

	

	@RequiresPermissions("user:rfCrmUserArea:view")
	@RequestMapping(value = "form")
	public String form(RfCrmUserArea rfCrmUserArea, Model model) {
		model.addAttribute("rfCrmUserArea", rfCrmUserArea);
		return "crm/user/rfCrmUserAreaForm";
	}

	@RequiresPermissions("user:rfCrmUserArea:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmUserArea rfCrmUserArea, Model model, RedirectAttributes redirectAttributes) {
		log.info("CRM-OFFICE-保存区域" + rfCrmUserArea.getUserCode());
		if (!beanValidator(model, rfCrmUserArea)){
			return form(rfCrmUserArea, model);
		}
		if("".equals(rfCrmUserArea.getCityCode())){
			rfCrmUserArea.setCityCode(null);
		}
		rfCrmUserAreaService.save(rfCrmUserArea);
		addMessage(redirectAttributes, "保存所属区域成功");
		log.info("CRM-OFFICE-保存区域成功" + rfCrmUserArea.getUserCode());
		return "redirect:"+Global.getAdminPath()+"/crm/user/rfCrmUserArea/?repage";
	}
	
	@RequiresPermissions("user:rfCrmUserArea:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmUserArea rfCrmUserArea, RedirectAttributes redirectAttributes) {
		//退回公海在删除
		log.info("CRM-OFFICE-删除区域 并退回公海" + rfCrmUserArea.getUserCode());
		RfCrmHotelPublic rfCrmHotelPublic = new RfCrmHotelPublic();
		rfCrmHotelPublic.setHotelUserId(rfCrmUserArea.getUserCode());
		rfCrmHotelPublicService.returnPublicByUser(rfCrmHotelPublic);
		rfCrmUserAreaService.delete(rfCrmUserArea);
		addMessage(redirectAttributes, "删除所属区域成功");
		log.info("CRM-OFFICE-删除区域 并退回公海成功" + rfCrmUserArea.getUserCode());
		return "redirect:"+Global.getAdminPath()+"/crm/user/rfCrmUserArea/?repage";
	}

    @RequiresPermissions("visit:rfCrmVisitRecord:view")
    @RequestMapping(value = "findUserByArea")
    @ResponseBody
    public List<AllAreas> findUserByArea(RfCrmUserArea rfCrmUserArea) {

        // 根据区域查询不同的用户
        List<AllAreas> userAreaList = rfCrmUserAreaService.findUserByArea(rfCrmUserArea);

        return userAreaList;
    }

	
	/**
	 * 获取列表查询基础信息(单个用户)-修改中用到
	 * @param rfCrmUserArea
	 * @param model
	 */
	private void getFormInfoUserByUserCode(RfCrmUserArea rfCrmUserArea, Model model) {
		//销售
		SyOrgUser syOrgUser = syOrgUserService.get(rfCrmUserArea.getUserCode());
		model.addAttribute("syOrgUser",syOrgUser);
	}
	
	/**
	 * 获取列表查询基础信息(所有用户列表)
	 * @param rfCrmUserArea
	 * @param model
	 */
	private void getFormInfoUserNotExistUserArea(RfCrmUserArea rfCrmUserArea, Model model) {
		//销售
		SyOrgUser syOrgUser = new SyOrgUser();
		List<SyOrgUser> syOrgUserList = syOrgUserService.findListNotExistUserArea(syOrgUser);
		
		model.addAttribute("syOrgUserList",syOrgUserList);
	}
	
	/**
	 * 获取列表查询基础信息(城市)
	 * @param rfCrmUserArea
	 * @param model
	 */
	private void getFormInfoCity(RfCrmUserArea rfCrmUserArea, Model model) {
		 
		//省
		List<TProvince> provinceList = provinceService.findList(new TProvince());
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(rfCrmUserArea.getProvCode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(rfCrmUserArea.getProvCode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
			List<TCity> cityList = cityService.getByParent(city);
			model.addAttribute("cityList", cityList);
		}
	}
}