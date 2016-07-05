/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.crm.hotel.entity.EHotelExport;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.dubbo.rpc.RpcContext;
import com.fangbaba.basic.face.bean.BasicHotelTags;
import com.fangbaba.basic.face.bean.Taggroup;
import com.fangbaba.basic.face.bean.Tags;
import com.fangbaba.basic.face.service.HotelTagsService;
import com.mk.hms.dubbo.model.BaseModel;
import com.mk.hms.dubbo.service.HotelInfoService;
import com.thinkgem.crm.hotel.entity.EHotel;
import com.thinkgem.crm.hotel.entity.TRoomType;
import com.thinkgem.crm.hotel.service.EHotelService;
import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TDistrict;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.crm.mongo.entity.HotelAuditLogMongo;
import com.thinkgem.crm.mongo.service.HotelAuditLogMongoService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.auth.service.AuthUserService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * CRM酒店信息Controller
 * @author 李雪楠
 * @version 2016-03-28
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/hotel/eHotel")
public class EHotelController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(EHotelController.class);
	
	private static final String MODIFY_PHONE = "1";//修改手机号
	
	private static final String TRANSFER_HOTLE = "2";// 转移酒店
	
	@Autowired
	private EHotelService eHotelService;
	
	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	private HotelInfoService hotelInfoServiceDubbo;

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
    private HotelTagsService hotelTagsAPIService;
	/** 酒店审核日志服务 */
	@Autowired
	private HotelAuditLogMongoService auditLogMongoService;

	@ModelAttribute
	public EHotel get(@RequestParam(required=false) String id) {
		EHotel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = eHotelService.get(id);
		}
		if (entity == null){
			entity = new EHotel();
		}
		return entity;
	}
	
	@RequiresPermissions("hotel:eHotel:view")
	@RequestMapping(value = {"list", ""})
	public String list(EHotel eHotel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EHotel> page = eHotelService.findPage(new Page<EHotel>(request, response), eHotel); 
		model.addAttribute("page", page);
		return "crm/hotel/eHotelList";
	}

	@RequiresPermissions("hotel:eHotel:view")
	@RequestMapping(value = "form")
	public String form(EHotel eHotel, Model model) {
		model.addAttribute("eHotel", eHotel);
		return "crm/hotel/eHotelForm";
	}

	@RequiresPermissions("hotel:eHotel:edit")
	@RequestMapping(value = "save")
	public String save(EHotel eHotel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, eHotel)){
			return form(eHotel, model);
		}
		eHotelService.save(eHotel);
		addMessage(redirectAttributes, "保存任务成功");
		return "redirect:"+Global.getAdminPath()+"/crm/hotel/eHotel/?repage";
	}
	/**
	 * 更换老板手机号
	 * @param eHotel 
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hotel:eHotel:edit")
	@RequestMapping(value = "modifyBossPhone")
	public String modifyBossPhone(EHotel eHotel, Model model, RedirectAttributes redirectAttributes) {
		logger.info("CRM-OFFICE-modifyBossPhone 更换手机号开始");
		
		if (!beanValidator(model, eHotel)){
			logger.info("CRM-OFFICE-modifyBossPhone 校验失败");
			return form(eHotel, model);
		}
		String msg = "修改手机号成功";
		String returnStr = "redirect:"+Global.getAdminPath()+"/crm/hotel/eHotel/?repage";
		boolean phoneSame =  eHotel.getBossPhoneNew().equals(eHotel.getBossPhone());
		try {
			//修改老板手机号
			if(MODIFY_PHONE.equals(eHotel.getModifyType())){
				logger.info("CRM-OFFICE-modifyBossPhone 更换手机号 MODIFY_PHONE = " + MODIFY_PHONE);
				//手机号一样,更新名称
				if(phoneSame){
					BaseModel baseModel = hotelInfoServiceDubbo.modifyBossPhone(MODIFY_PHONE,Long.parseLong(eHotel.getId()),eHotel.getBossName(),eHotel.getBossPhone(),eHotel.getBossPhoneNew()) ;
					if(!baseModel.isSuccess()){
						logger.info("CRM-OFFICE-modifyBossPhone 更换手机号  - 手机号一样,更新名称失败 ");
						msg = baseModel.getErrorMsg();
					}
					logger.info("CRM-OFFICE-modifyBossPhone 更换手机号  - 手机号一样,更新名称成功 ");
					addMessage(redirectAttributes, msg);
					return returnStr;
				}
				//1.同步统一登录平台
				if(eHotel.getBossPhoneNew() != null){
					logger.info("CRM-OFFICE-modifyBossPhone 更换手机号同步统一登录平台开始");
					//手机号不等说明更换了手机号
					boolean flag = authUserService.updateUserName(eHotel.getBossPhone(), eHotel.getBossPhoneNew());
					if(!flag){
						logger.info("CRM-OFFICE-modifyBossPhone 更换手机号同步统一登录平台失败");
						msg = "同步统一登录平台数据失败!";
						addMessage(redirectAttributes, msg);
						return returnStr;
					}else{
						logger.info("CRM-OFFICE-modifyBossPhone 调用crmas");
						//调用crmas
						BaseModel baseModel = hotelInfoServiceDubbo.modifyBossPhone(MODIFY_PHONE,Long.parseLong(eHotel.getId()),eHotel.getBossName(),eHotel.getBossPhone(),eHotel.getBossPhoneNew()) ;
						if(!baseModel.isSuccess()){
							msg = baseModel.getErrorMsg();
						}
					}
					logger.info("CRM-OFFICE-modifyBossPhone 更换手机号同步统一登录平台成功");
				}else{
					msg = "手机号没有改变,无需更换!";
				}
			}else{
				//调用crmas
				logger.info("CRM-OFFICE-modifyBossPhone 更换手机号 TRANSFER_HOTLE = " + TRANSFER_HOTLE);
				BaseModel baseModel = hotelInfoServiceDubbo.modifyBossPhone(TRANSFER_HOTLE,Long.parseLong(eHotel.getId()),eHotel.getBossName(),eHotel.getBossPhone(),eHotel.getBossPhoneNew()) ;
				if(!baseModel.isSuccess()){
					msg = baseModel.getErrorMsg();
					logger.info("CRM-OFFICE-modifyBossPhone 更换手机号  - 转店失败  msg = " + msg);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		}
		logger.info("CRM-OFFICE-modifyBossPhone 更换手机号结束");
		addMessage(redirectAttributes, msg);
		return returnStr;
	}
	
	@RequiresPermissions("hotel:eHotel:edit")
	@RequestMapping(value = "delete")
	public String delete(EHotel eHotel, RedirectAttributes redirectAttributes) {
		eHotelService.delete(eHotel);
		addMessage(redirectAttributes, "删除任务成功");
		return "redirect:"+Global.getAdminPath()+"/crm/hotel/eHotel/?repage";
	}

    @RequiresPermissions("hotel:eHotel:view")
    @RequestMapping(value = "selfBuildHotelList")
    public String selfBuildHotelList(EHotel eHotel, HttpServletRequest request, HttpServletResponse response, Model model) {
        eHotel.setIsSelfBuildHotel("T");
        Page<EHotel> page = eHotelService.findSelfBuildHotelPage(new Page<EHotel>(request, response), eHotel);

        // 设置省市区的级联
        //省
        List<TProvince> provinceList = provinceService.findList(new TProvince());
        model.addAttribute("provinceList",provinceList);
        //市
        if(StringUtils.isNotBlank(eHotel.getProvcode())){
            //查询省ID(前台变相可传)
            TProvince province = provinceService.getByCode(eHotel.getProvcode());
            //获取城市数据
            TCity city = new TCity();
            city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
            List<TCity> cityList = cityService.getByParent(city);
            model.addAttribute("cityList", cityList);
        }
//		 区县 (暂时去掉)
        if(StringUtils.isNotBlank(eHotel.getCitycode())){
            //查询城市ID(前台变相可传)
            TCity city = cityService.getByCode(eHotel.getCitycode());

            TDistrict district = new TDistrict();
            district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
            List<TDistrict> districtList = districtService.getByParent(district);
            model.addAttribute("districtList", districtList);
        }

        model.addAttribute("page", page);
        return "crm/hotel/selfBuildHotelList";
    }

    @RequiresPermissions("hotel:eHotel:view")
    @RequestMapping(value = "selfBuildHotelform")
    public String selfBuildHotelform(EHotel eHotel, Model model) {

        eHotel = eHotelService.getSelfBuildHotel(eHotel);

        model.addAttribute("eHotel", eHotel);
        return "crm/hotel/selfBuildHotelForm";
    }

    @RequiresPermissions("hotel:eHotel:edit")
    @RequestMapping(value = "checkSelfBuildHotel")
    public String checkSelfBuildHotel(EHotel eHotel, RedirectAttributes redirectAttributes) {

    	this.checkHotel(eHotel, redirectAttributes);

        return "redirect:"+Global.getAdminPath()+"/crm/hotel/eHotel/selfBuildHotelList?repage";
    }


    @RequiresPermissions("hotel:eHotel:edit")
    @RequestMapping(value = "rejectSelfBuildHotel")
    public String rejectSelfBuildHotel(EHotel eHotel, String reason, RedirectAttributes redirectAttributes) {
        this.rejectHotel(eHotel, reason, redirectAttributes);
        return "redirect:"+Global.getAdminPath()+"/crm/hotel/eHotel/selfBuildHotelList?repage";
    }

    /**
     * 审核自建酒店和非自建酒店公共方法
     * @param eHotel
     * @param redirectAttributes
     */
	private void checkHotel(EHotel eHotel, RedirectAttributes redirectAttributes) {
		// 获取当前用户信息
        User user = UserUtils.getUser();

        // 调用审核酒店的接口
        BaseModel baseModel = hotelInfoServiceDubbo.checkSelfBuildHotel(Long.parseLong(eHotel.getId()),
                user.getLoginName(), user.getName());

        if (baseModel.isSuccess()) {
            logger.info("酒店[id=" + eHotel.getId() + "]审核成功");
            addMessage(redirectAttributes, "酒店审核成功");
        } else {
            logger.info("酒店[id=" + eHotel.getId() + "]审核失败," +
                    "失败码是:" + baseModel.getErrorCode() + ",原因是:" + baseModel.getErrorMsg());
            addMessage(redirectAttributes, "酒店审核失败,原因是" + baseModel.getErrorMsg());
        }
	}

	/**
	 * 驳回自建酒店和非自建酒店公共方法
	 * @param eHotel
	 * @param reason
	 * @param redirectAttributes
	 */
	private void rejectHotel(EHotel eHotel, String reason,
			RedirectAttributes redirectAttributes) {
		if (StringUtils.isBlank(reason)) {
            addMessage(redirectAttributes, "请填写驳回原因");
        } else {

            if (reason.length() <= 200) {

                // 获取当前用户信息
                User user = UserUtils.getUser();

                BaseModel baseModel = hotelInfoServiceDubbo.hotelCheckReturn(Long.parseLong(eHotel.getId()),
                        reason, user.getLoginName(), user.getName());
                if (baseModel.isSuccess()) {
                    logger.info("酒店[id=" + eHotel.getId() + "]审核被成功驳回");
                    addMessage(redirectAttributes, "酒店审核被成功驳回");
                } else {
                    logger.info("酒店[id=" + eHotel.getId() + "]审核驳回失败," +
                            "失败码是:" + baseModel.getErrorCode() + ",原因是:" + baseModel.getErrorMsg());

                    addMessage(redirectAttributes, "酒店审核驳回失败,原因是" + baseModel.getErrorMsg());
                }
            } else {
                addMessage(redirectAttributes, "驳回原因超过200限制,请精简后再次提交");
            }

        }
	}

    /**
     * 导出自建酒店的数据
     * @param eHotel
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("hotel:eHotel:view")
    @RequestMapping(value = "exportSelfBuildHotels")
    public String exportExcel(EHotel eHotel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "自建酒店数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<EHotel> page = eHotelService.findSelfBuildHotelPage(new Page<EHotel>(request, response, -1), eHotel);

            new ExportExcel("自建酒店记录", EHotel.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出自建酒店记录失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/crm/hotel/eHotel/selfBuildHotelList?repage";
    }

    /*************************2016-05-19审核酒店*********************************/

	@RequiresPermissions("hotel:eHotel:view")
	@RequestMapping(value = {"checkList"})
	public String checkList(EHotel eHotel, HttpServletRequest request, HttpServletResponse response, Model model) {
		eHotel.setIsSelfBuildHotel("F");
        Page<EHotel> page = eHotelService.findSelfBuildHotelPage(new Page<EHotel>(request, response), eHotel);
		model.addAttribute("page", page);

		//省
		List<TProvince> provinceList = provinceService.findList(new TProvince());
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(eHotel.getProvcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(eHotel.getProvcode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
			List<TCity> cityList = cityService.getByParent(city);
			model.addAttribute("cityList", cityList);
		}
//		 区县 (暂时去掉)
		if(StringUtils.isNotBlank(eHotel.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = cityService.getByCode(eHotel.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}

		return "crm/hotel/eHotelCheckList";
	}

    @SuppressWarnings("unchecked")
	@RequiresPermissions("hotel:eHotel:view")
    @RequestMapping(value = "checkform")
    public String checkform(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
    	if(org.apache.commons.lang3.StringUtils.isBlank(id)){
    		//不知道这个方法为啥调用2次
    		return null;
    	}
    	EHotel eHotel = new EHotel();
    	eHotel.setId(id);
    	eHotel = eHotelService.getCheckHotel(eHotel);

    	//获取房型数据
    	TRoomType tHotelType = new TRoomType();
    	tHotelType.setHotelid(id);
    	eHotel.setRoomTypeList(eHotelService.findTHotelTypeByHotelId(tHotelType));

    	//接口返回的map
    	Map<String,Object> value = this.getTagsByHotelPmsCode(eHotel.getPms());
    	//具体类型明细数据
    	List<Map<String, Object>> detail = new ArrayList<Map<String,Object>>();

    	Map<String,Object> tempAllMap = new HashMap<String, Object>();
    	Map<String,Object> tempHotelMap = new HashMap<String, Object>();
    	Object objAll = value.get("all");
    	if(objAll != null){
	    	tempAllMap = (Map<String,Object>)objAll;

	    	Object objHotel = value.get("hotel");
	    	tempHotelMap = (Map<String,Object>)objHotel;

	    	detail = (List<Map<String, Object>>)tempAllMap.get("设施服务");
	    	eHotel.setBasicService(this.getReturnMap(detail, tempHotelMap));

	    	detail = (List<Map<String, Object>>)tempAllMap.get("类型特色");
	    	eHotel.setTypeSpecial(this.getReturnMap(detail, tempHotelMap));

	    	detail = (List<Map<String, Object>>)tempAllMap.get("商圈位置");
	    	eHotel.setBusinessArea(this.getReturnMap(detail, tempHotelMap));
    	}
    	model.addAttribute("eHotel",eHotel);

		//审核日志
		HotelAuditLogMongo auditLogMongo = new HotelAuditLogMongo();
		auditLogMongo.setHotelId(Long.parseLong(id)); //设置酒店ID参数
		Page<HotelAuditLogMongo> searchPage = new Page<HotelAuditLogMongo>(request, response);
		searchPage.setPageSize(100);
		Page<HotelAuditLogMongo> page = auditLogMongoService.auditLogList(searchPage, auditLogMongo);
		model.addAttribute("page", page);

        return "crm/hotel/eHotelCheckInfo";
    }

	/**
	 * 导出审核酒店数据
	 *
	 * @param eHotel   酒店信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("hotel:eHotel:view")
	@RequestMapping(value = "exportAuditHotels", method = RequestMethod.POST)
	public String exportFile(EHotel eHotel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			eHotel.setIsSelfBuildHotel("F");
			String fileName = DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			//以下是导出excel文件
			List<EHotel> list = eHotelService.findSelfBuildHotelList(eHotel);
			fileName = "酒店审核数据" + fileName;
			new ExportExcel("酒店审核数据", EHotelExport.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出数据失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/crm/hotel/eHotel/checkList?repage";
	}

	private List<Map<String, Object>> getReturnMap(List<Map<String, Object>> detail, Map<String, Object> tempHotelMap) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//判断对象是否为空
		if (CollectionUtils.isEmpty(detail)) {
			return list;
		}
    	for (int i = 0; i < detail.size(); i++) {
    		Map<String, Object> map = new HashMap<String,Object>();
    		Map<String,Object>  tempMap = (Map<String,Object>)detail.get(i);
    		//要判断tempMap是否为空
    		if(MapUtils.isEmpty(tempMap)) {
    			continue;
    		}
    		String checkbox = null;
			if(MapUtils.isNotEmpty(tempHotelMap) && tempHotelMap.get(tempMap.get("id")) != null){
				checkbox = "true";
			}else{
				checkbox = "false";
			}
    		map.put("name", tempMap.get("name").toString());
    		map.put("checkbox", checkbox);
    		list.add(map);
		}
    	return list;
	}

    @RequiresPermissions("hotel:eHotel:edit")
    @RequestMapping(value = "checkNormalHotel")
    public String checkNormalHotel(String hotelId, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response, Model model) {
    	EHotel eHotel = new EHotel();
    	eHotel.setId(hotelId);
    	this.checkHotel(eHotel, redirectAttributes);
    	return this.checkList(new EHotel(), request, response, model);
    }

    @RequiresPermissions("hotel:eHotel:edit")
    @RequestMapping(value = "rejectNormalHotel")
    public String rejectNormalHotel(String hotelId, String reason,RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response, Model model) {
    	EHotel eHotel = new EHotel();
    	eHotel.setId(hotelId);
    	this.rejectHotel(eHotel, reason, redirectAttributes);
    	return this.checkList(new EHotel(), request, response, model);
    }

	public Map<String,Object> getTagsByHotelPmsCode(String hotelPmsId){
		Map<String,Object> value = new HashMap<String,Object>();
		try {
			BasicHotelTags basicHotelTags = hotelTagsAPIService.queryHotelTagsByHotelid(hotelPmsId);
			List<Tags> list = basicHotelTags.getAllTags();//所有标签
			List<Tags> hotelList = basicHotelTags.getHotelTags();//酒店标签
			//解析所有的酒店标签
			Map<String,List<Map<String,String>>> map = null;
			//解析当前酒店的标签
			Map<String,String> hotelMap = null;

			if(list != null && list.size()>0){
				map = parseAllTags(list);
				value.put("all",map);
			}
			if(hotelList != null && hotelList.size()>0){
				hotelMap = parseHotelTags(hotelList);
				value.put("hotel",hotelMap);
			}

		} catch (Exception e) {
			logger.error("查询标签异常",e);
		}finally{
			RpcContext.removeContext();
		}
		return value;
	}


	/**
	 * 解析所有标签
	 * @param list
	 * @return
	 */
	private Map<String,List<Map<String,String>>> parseAllTags(List<Tags> list){
		Map<String,List<Map<String,String>>> map = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> tagList = null;
		Map<String,String> tagMap = null;
		for(Tags tag:list){
			Taggroup group = tag.getGroup();
			String groupName = group.getGroupname();
			Long id = tag.getId();
			String name = tag.getTagname();
			tagMap = new HashMap<String,String>();
			tagMap.put("id", id.toString());
			tagMap.put("name", name);
			if(!map.containsKey(groupName)){
				tagList = new ArrayList<Map<String,String>>();
				tagList.add(tagMap);
			}else{
				tagList = map.get(groupName);
				tagList.add(tagMap);
			}
			map.put(groupName, tagList);
		}
		return map;
	}

	/**
	 * 解析当前酒店标签
	 * @param list
	 * @return
	 */
	private Map<String,String> parseHotelTags(List<Tags> list){
		Map<String,String> map = new HashMap<String,String>();;
		for(Tags tag:list){
			Long id = tag.getId();
			String name = tag.getTagname();
			map.put(id.toString(), name);
		}
		return map;
	}
	@RequiresPermissions("hotel:eHotel:view")
	@RequestMapping(value = "auditLogList")
	public String auditLogList(HotelAuditLogMongo auditLogMongo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HotelAuditLogMongo> page = auditLogMongoService.auditLogList(new Page<HotelAuditLogMongo>(request, response), auditLogMongo);
		model.addAttribute("page", page);
		model.addAttribute("auditLogMongo", auditLogMongo);
		return "crm/hotel/auditLogList";
	}

	/**
	 * 导出自建酒店的数据
	 * @param eHotel
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hotel:eHotel:view")
	@RequestMapping(value = "returnCheckList")
	public String returnCheckList(EHotel eHotel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		return "redirect:" + adminPath + "/crm/hotel/eHotel/checkList?repage";
	}

}