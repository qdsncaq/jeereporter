/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.visit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.thinkgem.crm.location.entity.AllAreas;
import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TDistrict;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.crm.syslabel.entity.RfCrmSysLabel;
import com.thinkgem.crm.syslabel.service.RfCrmSysLabelService;
import com.thinkgem.crm.user.service.SyOrgUserService;
import com.thinkgem.crm.visit.entity.RfCrmMarketingVisitRecord;
import com.thinkgem.crm.visit.entity.RfCrmSwitchVisitRecord;
import com.thinkgem.crm.visit.entity.RfCrmWashVisitRecord;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.crm.visit.entity.RfCrmVisitRecord;
import com.thinkgem.crm.visit.service.RfCrmVisitRecordService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CRM拜访记录Controller
 * @author 段文昌
 * @version 2016-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/visit/rfCrmVisitRecord")
public class RfCrmVisitRecordController extends BaseController {

	@Autowired
	private RfCrmVisitRecordService rfCrmVisitRecordService;

	/** 系统配置服务 */
	@Autowired
	private RfCrmSysLabelService rfCrmSysLabelService;
	/** 省服务 */
	@Autowired
	private TProvinceService provinceService;
	/** 市服务 */
	@Autowired
	private TCityService cityService;
	/** 县区服务 */
	@Autowired
	private TDistrictService districtService;

    @Autowired
    private SyOrgUserService syOrgUserService;

	@ModelAttribute
	public RfCrmVisitRecord get(@RequestParam(required=false) String id) {
		RfCrmVisitRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rfCrmVisitRecordService.get(id);
		}
		if (entity == null){
			entity = new RfCrmVisitRecord();
		}
		return entity;
	}

	/**
	 * 拜访记录全数据
	 * @param rfCrmVisitRecord
	 * @param request
	 * @param response
	 * @param model
     * @return
     */
	@RequiresPermissions("visit:rfCrmVisitRecord:view")
	@RequestMapping(value = {"list"})
	public String list(RfCrmVisitRecord rfCrmVisitRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		//如果是分销
		if(StringUtils.equals(RfCrmVisitRecord.VISIT_SWITCH_TYPE, rfCrmVisitRecord.getQueryVisitType())){
			rfCrmVisitRecord.setVisitType("3"); //3-分销业务洽谈
		}
		//如果是采销采购,供应链
		if(StringUtils.equals(RfCrmVisitRecord.VISIT_MARKETING_TYPE, rfCrmVisitRecord.getQueryVisitType())){
			rfCrmVisitRecord.setVisitType("11"); //11-供应链业务洽谈
		}

		//如果是采销采购,供应链
		if(StringUtils.equals(RfCrmVisitRecord.VISIT_WASH_TYPE, rfCrmVisitRecord.getQueryVisitType())){
			rfCrmVisitRecord.setVisitType("16"); //16-布草洗涤业务洽谈
		}

		Page<RfCrmVisitRecord> page = rfCrmVisitRecordService.findPage(new Page<RfCrmVisitRecord>(request, response), rfCrmVisitRecord);
		model.addAttribute("page", page);
		//设置页面级联菜单等属性
		this.setPreQuery(rfCrmVisitRecord, request, response, model);
		return "crm/visit/rfCrmVisitRecordList";
	}


	/**
	 * 拜访记录全数据
	 * @param rfCrmVisitRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("visit:rfCrmVisitRecord:view")
	@RequestMapping(value = {"index"})
	public String index(RfCrmVisitRecord rfCrmVisitRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		//如果是分销
		if(StringUtils.equals(RfCrmVisitRecord.VISIT_SWITCH_TYPE, rfCrmVisitRecord.getQueryVisitType())){
			rfCrmVisitRecord.setVisitType("3"); //3-分销业务洽谈
		}
		//如果是采销采购,供应链
		if(StringUtils.equals(RfCrmVisitRecord.VISIT_MARKETING_TYPE, rfCrmVisitRecord.getQueryVisitType())){
			rfCrmVisitRecord.setVisitType("11"); //11-供应链业务洽谈
		}

		//如果是采销采购,供应链
		if(StringUtils.equals(RfCrmVisitRecord.VISIT_WASH_TYPE, rfCrmVisitRecord.getQueryVisitType())){
			rfCrmVisitRecord.setVisitType("16"); //16-布草洗涤业务洽谈
		}

		//设置页面级联菜单等属性
		this.setPreQuery(rfCrmVisitRecord, request, response, model);
		return "crm/visit/rfCrmVisitRecordList";
	}

	@RequiresPermissions("visit:rfCrmVisitRecord:view")
	@RequestMapping(value = "form")
	public String form(RfCrmVisitRecord rfCrmVisitRecord, Model model) {
		model.addAttribute("rfCrmVisitRecord", rfCrmVisitRecord);
		return "crm/visit/rfCrmVisitRecordForm";
	}

	@RequiresPermissions("visit:rfCrmVisitRecord:view")
	@RequestMapping(value = "view")
	public String view(RfCrmVisitRecord rfCrmVisitRecord, Model model) {
		model.addAttribute("rfCrmVisitRecord", rfCrmVisitRecord);
		return "crm/visit/rfCrmVisitRecordView";
	}

	@RequiresPermissions("visit:rfCrmVisitRecord:edit")
	@RequestMapping(value = "save")
	public String save(RfCrmVisitRecord rfCrmVisitRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rfCrmVisitRecord)){
			return form(rfCrmVisitRecord, model);
		}
		rfCrmVisitRecordService.save(rfCrmVisitRecord);
		addMessage(redirectAttributes, "保存拜访记录成功");
		return "redirect:"+Global.getAdminPath()+"/visit/rfCrmVisitRecord/?repage";
	}
	
	@RequiresPermissions("visit:rfCrmVisitRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(RfCrmVisitRecord rfCrmVisitRecord, RedirectAttributes redirectAttributes) {
		rfCrmVisitRecordService.delete(rfCrmVisitRecord);
		addMessage(redirectAttributes, "删除拜访记录成功");
		return "redirect:"+Global.getAdminPath()+"/visit/rfCrmVisitRecord/?repage";
	}


	/**
	 * 导出拜访记录数据
	 * @param rfCrmVisitRecord 拜访记录
	 * @param request
	 * @param response
     * @return
     */
	@RequiresPermissions("visit:rfCrmVisitRecord:edit")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(RfCrmVisitRecord rfCrmVisitRecord, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//以下是导出excel文件
			if(StringUtils.equals(RfCrmVisitRecord.VISIT_SWITCH_TYPE, rfCrmVisitRecord.getQueryVisitType())){
				rfCrmVisitRecord.setVisitType("3");
				List<RfCrmVisitRecord> list = rfCrmVisitRecordService.findList(rfCrmVisitRecord);
				// 如果是分销
				fileName = "分销业务拜访数据"+fileName;
				new ExportExcel("分销业务拜访记录", RfCrmSwitchVisitRecord.class).setDataList(list).write(response, fileName).dispose();
			} else if(StringUtils.equals(RfCrmVisitRecord.VISIT_MARKETING_TYPE, rfCrmVisitRecord.getQueryVisitType())){
				rfCrmVisitRecord.setVisitType("11");
				List<RfCrmVisitRecord> list = rfCrmVisitRecordService.findList(rfCrmVisitRecord);
				// 如果是采销
				fileName = "供应链业务拜访数据"+fileName;
				new ExportExcel("供应链业务拜访记录", RfCrmMarketingVisitRecord.class).setDataList(list).write(response, fileName).dispose();
			} else if(StringUtils.equals(RfCrmVisitRecord.VISIT_WASH_TYPE, rfCrmVisitRecord.getQueryVisitType())){
				rfCrmVisitRecord.setVisitType("16");
				List<RfCrmVisitRecord> list = rfCrmVisitRecordService.findList(rfCrmVisitRecord);
				// 如果是布草洗涤
				fileName = "布草洗涤业务拜访数据"+fileName;
				new ExportExcel("布草洗涤业务拜访记录", RfCrmWashVisitRecord.class).setDataList(list).write(response, fileName).dispose();
			} else {
				List<RfCrmVisitRecord> list = rfCrmVisitRecordService.findList(rfCrmVisitRecord);
				// 全数据
				fileName = "拜访数据"+fileName;
				new ExportExcel("拜访记录", RfCrmVisitRecord.class).setDataList(list).write(response, fileName).dispose();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出拜访记录失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/crm/visit/rfCrmVisitRecord/list?repage";
	}


	/**
	 * 查询列表前,设置页面筛选默认值
	 * @param rfCrmVisitRecord
	 * @param request
	 * @param response
	 * @param model
	 */
	private void setPreQuery(RfCrmVisitRecord rfCrmVisitRecord, HttpServletRequest request, HttpServletResponse response, Model model){

		//拜访类型
		List<RfCrmSysLabel> visitTypeList = rfCrmSysLabelService.getVisitType();
		model.addAttribute("visitTypeList", visitTypeList);
		//拜访方式
		List<RfCrmSysLabel> visitWayList = rfCrmSysLabelService.getVisitWay();
		model.addAttribute("visitWayList", visitWayList);
		//样品发放
		List<RfCrmSysLabel> visitSampleGrantList = rfCrmSysLabelService.getSampleGrant();
		model.addAttribute("visitSampleGrantList", visitSampleGrantList);

		//省
		List<TProvince> provinceList = provinceService.findList(new TProvince());
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(rfCrmVisitRecord.getProvCode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(rfCrmVisitRecord.getProvCode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
			List<TCity> cityList = cityService.getByParent(city);
			model.addAttribute("cityList", cityList);
		}
//		 区县 (暂时去掉)
		if(StringUtils.isNotBlank(rfCrmVisitRecord.getCityCode())){
			//查询城市ID(前台变相可传)
			TCity city = cityService.getByCode(rfCrmVisitRecord.getCityCode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
	}

    /**
     * 查询所有省市区信息,并按树形返回
     * @param model
     * @return
     */
    @RequiresPermissions("visit:rfCrmVisitRecord:view")
    @RequestMapping(value = "allAreas")
    public String findAllAreas(String code, Model model) {

        // 查询所有省
        List<AllAreas> provinceList = provinceService.findAllToAllAreas();

        // 根据用户code查询用户信息
        List<AllAreas> userList = StringUtils.isBlank(code) ? new ArrayList<AllAreas>() : syOrgUserService.findUsersByCode(Arrays.asList(code.split(",")));

        model.addAttribute("areas", JSON.toJSONString(provinceList));
        model.addAttribute("selectUsers", JSON.toJSONString(userList));
        model.addAttribute("selectUsersCode", Collections3.extractToString(userList, "code", ","));
        model.addAttribute("selectUsersName", Collections3.extractToString(userList, "name", ","));

        return "crm/visit/selectSalersQuery";
    }

    @RequiresPermissions("visit:rfCrmVisitRecord:view")
    @RequestMapping(value = "findAreasByParentId")
    @ResponseBody
    public String findAreasByParentId(AllAreas allAreas) {
        List<AllAreas> allAreasList = new ArrayList<AllAreas>();

        if (allAreas.getType().equalsIgnoreCase("1")) {
            allAreasList = cityService.findAllToAllAreas(allAreas);
        } else {
            allAreasList = districtService.findAllToAllAreas(allAreas);
        }

        return JSON.toJSONString(allAreasList);

    }

}