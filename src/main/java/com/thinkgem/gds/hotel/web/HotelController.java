/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotel.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.gds.roomtype.entity.Roomtype;
import com.thinkgem.gds.roomtype.service.RoomtypeService;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.fangbaba.basic.face.base.RetInfo;
import com.fangbaba.basic.face.bean.HotelModel;
import com.fangbaba.basic.face.enums.HotelBusinessEnum;
import com.lz.mongo.bislog.BisLog;
import com.lz.mongo.bislog.BisLogDelegate;
import com.thinkgem.crm.hotel.enums.RfCrmHotelPublicLogRemarkEnum;
import com.fangbaba.datacenter.enums.BussinssTypeEnum;
import com.lz.mongo.bislog.BisLog;
import com.lz.mongo.bislog.BisLogDelegate;
import com.mongodb.client.model.Filters;
import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TDistrict;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.gds.datacenter.entity.DatacenterOrders;
import com.thinkgem.gds.datacenter.service.DatacenterOrdersService;
import com.thinkgem.gds.hotel.entity.Hotel;
import com.thinkgem.gds.hotel.service.HotelService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * modelController
 * @author tankai
 * @version 2016-03-11
 */
@Controller("HotelController2")
@RequestMapping(value = "${adminPath}/hotel/hotel")
public class HotelController extends BaseController {

	@Autowired
	private HotelService hotelService;
	
//	@Autowired
//	private ProvinceService provinceService;
//	@Autowired
//	private CityService cityService;
	@Autowired
	private com.fangbaba.basic.face.service.HotelBusinessService  hotelBusinessService;
	@Autowired
	private com.fangbaba.basic.face.service.HotelService theHotelService;

	/** 省服务 */
	@Autowired
	private TProvinceService tProvinceService;
	/** 市服务 */
	@Autowired
	private TCityService tCityService;
	/** 县区服务 */
	@Autowired
	private TDistrictService tDistrictService;
	@Autowired
	private com.fangbaba.gds.face.service.IOtaHotelFullFlagService otaHotelFullFlagService;
	
	@Autowired
	private BisLogDelegate bisLogDelegate;

    @Autowired
    private RoomtypeService roomtypeService;
	 
	 
	@ModelAttribute
	public Hotel get(@RequestParam(required=false) String id) {
		Hotel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hotelService.get(id);
		}
		if (entity == null){
			entity = new Hotel();
		}
		return entity;
	}
	
	@RequiresPermissions("hotel:hotel:view")
	@RequestMapping(value = {"list", ""})
	public String list(Hotel hotel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Hotel> page = hotelService.findPage(new Page<Hotel>(request, response), hotel); 
		model.addAttribute("page", page);
//		List<ProvinceModel> provinces = provinceService.queryAllProvinces();
//		List<CityModel> citys = cityService.queryAllCitys();
//		model.addAttribute("provinces", provinces);
//		model.addAttribute("citys", citys);
		/**
		 * 以下是省市区县,前台列表筛选显示
		 */
		//省
		List<TProvince> provinceList = tProvinceService.findList(new TProvince());
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(hotel.getProvcode())){
			//查询省ID(前台变相可传)
			TProvince province = tProvinceService.getByCode(hotel.getProvcode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
			List<TCity> cityList = tCityService.getByParent(city);
			model.addAttribute("cityList", cityList);
		}
		// 区县 (暂时去掉)
		if(StringUtils.isNotBlank(hotel.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(hotel.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = tDistrictService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		return "gds/hotel/hotelList";
	}
	/**
	 * 批量选择酒店退回公海
	 * @param hotel
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hotel:hotel:return")
	@RequestMapping(value = "queryforReturn")
	public String queryforReturn(Hotel hotel,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		Map<String, String> map = new HashMap<String, String>();
//		if(CollectionUtils.isEmpty(hotel.getIds())  && !StringUtils.isNoneBlank(hotel.getSalesName())){
//			map.put("result", "请输入酒店ID(多个id用,号分割)或销售姓名");
//			return JSON.toJSONString(map);
//		}
		User user = UserUtils.getUser();         
        if ( user == null ) {
            logger.info("用户登录失败");
            map.put("result", "用户登录失败");
            return JSON.toJSONString(map);
        }          
        String  loginuser = user.getLoginName();
		BisLog bisLog = new BisLog();
	   	bisLog.setSystem("office hotel");
	   	bisLog.setOperator(loginuser);
	   	bisLog.setBussinessId(user.getId());
	   	bisLog.setBussinssType(RfCrmHotelPublicLogRemarkEnum.ONESELFRETURN.getValue()); 
		bisLog.setCreateTime(new Date());
		try {
    		List<Hotel> list = hotelService.findList(hotel);
    		for (Hotel bean:list) {
    			if(bean.getIsPrivate().equals("1")){//私海退回公海
    				String userId = bean.getHotelUserId();
    				Date now = new Date();
    				hotelService.returnPublicByUser("2",now,now,userId,Long.parseLong(bean.getId()));
    				bisLog.setContent("CRM-OFFICE-"+bean.getHotelname()+"酒店退回公海成功 userCode = " + userId);
    				bisLogDelegate.saveBigLog(bisLog);
    			}
			}
    		map.put("msg", "退回公海成功");
    		map.put("code", "success");
    		return  "gds/hotel/hotelList";
//    		 return "redirect:"+Global.getAdminPath()+"/hotel/hotel/?repage";
		} catch (Exception e) {
			logger.error("退回公海失败:",e);
			map.put("msg", "退回公海失败");
    		map.put("code", "error");
//			e.printStackTrace();
			addMessage(redirectAttributes, "酒店数据退回公海失败！失败信息："+e.getMessage());
			return JSON.toJSONString(map);
		}
//		return "gds/hotel/hotelList";
	}
	/**
	 * 公海酒店批量分配销售
	 * @param hotel
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hotel:hotel:view")
	@RequestMapping(value = "assignHotelToSeller")
	@ResponseBody
	public String assignHotelToSeller(Hotel hotel,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Map<String, String> map = new HashMap<String, String>();
		User user = UserUtils.getUser();         
        if ( user == null ) {
            logger.info("用户登录失败");
            map.put("result", "用户登录失败");
            return JSON.toJSONString(map);
        }          
        String  loginuser = user.getLoginName();
		BisLog bisLog = new BisLog();
	   	bisLog.setSystem("office hotel");
	   	bisLog.setOperator(loginuser);
	   	bisLog.setBussinssType(RfCrmHotelPublicLogRemarkEnum.ASSIGN.getValue()); 
		bisLog.setCreateTime(new Date());
		try {
			String ids = request.getParameter("ids");
			String code = request.getParameter("code");
			List<Long> hotelIds = new ArrayList<Long>();
			for(int i=0;i<ids.split(",").length;i++){
				hotelIds.add(Long.parseLong(ids.split(",")[i]));
			}
			String saleName=request.getParameter("saleName");
			hotel.setIds(hotelIds);
			hotel.setSalesName(saleName);
    		List<Hotel> list = hotelService.findList(hotel);
    		for (Hotel bean:list) {
    			String userId = bean.getHotelUserId();
    			bisLog.setBussinessId(bean.getId());
    			Date now = new Date();
    			//公海酒店分配销售
    			if(bean.getIsPrivate().equals("2")){
	    			hotelService.assignHotelToSeller(code,now,now,Long.parseLong(bean.getId()));
	    			bisLog.setContent("CRM-OFFICE-"+bean.getHotelname()+"公海酒店分配销售成功 userCode = " + userId);
	    			bisLog.setBussinessId(bean.getId());
					bisLogDelegate.saveBigLog(bisLog);
    			}
			}
    		map.put("msg", "公海酒店分配销售成功");
    		map.put("code", "success");
    		return JSON.toJSONString(map);
		} catch (Exception e) {
			logger.error("退回公海失败:",e);
//			e.printStackTrace();
			bisLog.setContent("CRM-OFFICE-公海酒店分配销售成功 userCode = " + loginuser);
			bisLog.setBussinessId(String.valueOf(System.currentTimeMillis()));
			bisLogDelegate.saveBigLog(bisLog);
			addMessage(redirectAttributes, "公海酒店分配销售失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hotel/hotel/?repage";
	}
	/**
	 * 私海酒店批量从一个销售转到另一个销售名下
	 * @param hotel
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hotel:hotel:transfer")
	@RequestMapping(value = "transferToOtherSeller")
	@ResponseBody
	public String transferToOtherSeller(Hotel hotel,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		User user = UserUtils.getUser();
		if (user == null) {
			logger.info("用户登录失败");
			// map.put("result", "用户登录失败");
			return null;
		}
		String loginuser = user.getLoginName();
		//写日志
		BisLog bisLog = new BisLog();
		bisLog.setSystem("office hotel");
		bisLog.setOperator(loginuser);
		bisLog.setBussinssType(HotelBusinessEnum.SWITCHS.getId());
		bisLog.setCreateTime(new Date());
		try {
			String code = request.getParameter("code");
			String ids = request.getParameter("ids");
			List<Long> hotelIds = new ArrayList<Long>();
			for(int i=0;i<ids.split(",").length;i++){
				if(NumberUtils.isNumber(ids.split(",")[i])){
					hotelIds.add(Long.parseLong(ids.split(",")[i]));
				}
			}
			String saleName=request.getParameter("salesName");
			hotel.setIds(hotelIds);
			hotel.setSalesName(saleName);
			
    		List<Hotel> list = hotelService.findList(hotel);
    		Date now = new Date();
    		for (Hotel bean:list) {
    			//私海酒店从一个销售转到另一个销售名下
    			if(bean.getIsPrivate().equals("1")){
	    			hotelService.transferHotelToSeller(code, now, now, bean.getHotelUserId(),Long.parseLong(bean.getId()));
	    			bisLog.setContent("酒店转到另一个销售名下成功");
	    			bisLog.setBussinessId(bean.getId());
	    			bisLogDelegate.saveBigLog(bisLog);
    			}
			}
    		return null;
		} catch (Exception e) {
			logger.error("酒店转到另一个销售名下失败:",e);
//			e.printStackTrace();
			addMessage(redirectAttributes, "酒店转到另一个销售名下失败！失败信息："+e.getMessage());
			bisLog.setContent("酒店转到另一个销售名下失败！失败信息："+e.getMessage());
			bisLog.setBussinessId(String.valueOf(System.currentTimeMillis()));
			bisLogDelegate.saveBigLog(bisLog);
		}
		return "redirect:"+Global.getAdminPath()+"/hotel/hotel/?repage";
	}
	/**
	 * 弹出选择销售的页面
	 * @param hotel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("hotel:hotel:view")
	@RequestMapping(value = "transferToSeller")
	public String transferToSeller(Hotel hotel,Model model,HttpServletRequest request) {
		List<TProvince> provinceList = tProvinceService.findList(new TProvince());
		model.addAttribute("provinceList",provinceList);
		List<Hotel> list = hotelService.findList(hotel);
		model.addAttribute("hotels", list);

		return "crm/hotel/transferToSeller";
	}
	
	@RequiresPermissions("hotel:hotel:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(Hotel hotel,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "hotel_"+DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm")+".xlsx";
    		List<Hotel> list = hotelService.findList(hotel);
    		for (Hotel bean:list) {
    			String  switchopen= bean.getSwitchOpen();
                if(switchopen==null || "".equals(switchopen)){
                	bean.setSwitchopendesc(DictUtils.getDictLabel(switchopen, "BusinessEnums", ""));
                	bean.setSwitchcreatetime(null);
                }else{
                	bean.setSwitchopendesc(DictUtils.getDictLabel(switchopen, "BusinessEnums", ""));
                }
			}
    		new ExportExcel("酒店数据", Hotel.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			logger.error("导出酒店信息失败:",e);
			e.printStackTrace();
			addMessage(redirectAttributes, "酒店数据excel导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hotel/hotel/?repage";
	}

	@RequiresPermissions("hotel:hotel:view")
	@RequestMapping(value = "form")
	public String form(Hotel hotel, Model model) {
		model.addAttribute("hotel", hotel);

		if (hotel.getId() != null) {

            // 增加房型的信息
            Roomtype roomtype = new Roomtype();
            roomtype.setHotelid(Long.parseLong(hotel.getId()));
            List<Roomtype> roomtypeList = roomtypeService.findList(roomtype);
            model.addAttribute("roomtypeList", roomtypeList);

			//mongo find
			Bson filter = Filters.and(Filters.eq(BisLog.CN_BUSSINESSTYPE, String.valueOf(10003)), Filters.eq(BisLog.CN_BUSSINESSID, String.valueOf(hotel.getId())));
			Bson sort = new Document(BisLog.CN_CREATETIME, -1);
			List<BisLog> list = bisLogDelegate.find(filter, sort);
			List<BisLog> loglist = new LinkedList<BisLog>();
			for (BisLog bisLog : list) {
				if(org.apache.commons.lang.StringUtils.isNotBlank(bisLog.getBussinssType())){
					BussinssTypeEnum btEnum = BussinssTypeEnum.getEnum(bisLog.getBussinssType());
					if(btEnum != null)
						bisLog.setBussinssType(btEnum.getName());
					if(org.apache.commons.lang.StringUtils.isBlank(bisLog.getOperator()))
						bisLog.setOperator("系统");
				}
				loglist.add(bisLog);
			}
			model.addAttribute("loglist", list);
		}
		return "gds/hotel/hotelForm";
	}

	@RequiresPermissions("hotel:hotel:edit")
	@RequestMapping(value = "save")
	public String save(Hotel hotel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hotel)){
			return form(hotel, model);
		}
		hotelService.save(hotel);
		addMessage(redirectAttributes, "保存model成功");
		return "redirect:"+Global.getAdminPath()+"/hotel/hotel/?repage";
	}
	
	@RequiresPermissions("hotel:hotel:edit")
	@RequestMapping(value = "delete")
	public String delete(Hotel hotel, RedirectAttributes redirectAttributes) {
		hotelService.delete(hotel);
		addMessage(redirectAttributes, "删除model成功");
		return "redirect:"+Global.getAdminPath()+"/hotel/hotel/?repage";
	}
	/**
	 * 开通分销
	 * @param hotel
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hotelbusiness:hotelbusiness:operate")
	@RequestMapping(value = "openDistribution")
	public String openDistribution(Hotel hotel, RedirectAttributes redirectAttributes) {
		HotelModel hotelmodel = theHotelService.queryById(Long.parseLong(hotel.getId()));
		User user = UserUtils.getUser();
		if (user == null) {
			logger.info("用户登录失败");
			// map.put("result", "用户登录失败");
			return null;
		}
		String loginuser = user.getLoginName();
		//写日志
		BisLog bisLog = new BisLog();
		bisLog.setSystem("office hotel");
		bisLog.setOperator(loginuser);
		bisLog.setBussinssType(HotelBusinessEnum.SWITCHS.getId());
		bisLog.setCreateTime(new Date());
		bisLog.setBussinessId(hotel.getId());
		RetInfo<String> resultInfo = hotelBusinessService.openBusiness(hotelmodel.getHotelpms(), HotelBusinessEnum.SWITCHS, user.getLoginName());
		if (!resultInfo.isResult()) {
			bisLog.setContent("开通分销失败"+resultInfo.getMsg());
			addMessage(redirectAttributes, "开通分销失败"+resultInfo.getMsg());
		} else {
			bisLog.setContent("开通分销成功");
			addMessage(redirectAttributes, "开通分销成功");
		}
		bisLogDelegate.saveBigLog(bisLog);
		return "redirect:"+Global.getAdminPath()+"/hotel/hotel/?repage";
	}
	/**
	 * 关闭分销
	 * @param hotel
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hotelbusiness:hotelbusiness:operate")
	@RequestMapping(value = "closeDistribution")
	public String closeDistribution(Hotel hotel, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();         
        if ( user == null ) {
            logger.info("用户登录失败");
//            map.put("result", "用户登录失败");
            return null;
        }          
        String  loginuser = user.getLoginName();
		BisLog bisLog = new BisLog();
	   	bisLog.setSystem("office hotel");
	   	bisLog.setOperator(loginuser);
	   	bisLog.setBussinssType(HotelBusinessEnum.SWITCHS.getId()); 
		bisLog.setCreateTime(new Date());
		bisLog.setBussinessId(hotel.getId());
		HotelModel hotelmodel = theHotelService.queryById(Long.parseLong(hotel.getId()));
		RetInfo<String> resultInfo = hotelBusinessService.closeBusiness(hotelmodel.getHotelpms(), HotelBusinessEnum.SWITCHS, user.getLoginName());
		if (!resultInfo.isResult()) {
			bisLog.setContent("关闭分销失败"+resultInfo.getMsg());
			addMessage(redirectAttributes, "关闭分销失败"+resultInfo.getMsg());
		} else {
			bisLog.setContent("关闭分销成功");
			addMessage(redirectAttributes, "关闭分销成功");
		}
		bisLogDelegate.saveBigLog(bisLog);
		return "redirect:"+Global.getAdminPath()+"/hotel/hotel/?repage";
	}
	@RequestMapping(value = "fullRoom")
	public String fullRoom(Long otatype, Long hotelid,Integer flag, RedirectAttributes redirectAttributes) {
		Integer result = 0;
		result =  otaHotelFullFlagService.saveOrUpdateFullFlag(flag, hotelid, otatype);
		if (result>0) {
			addMessage(redirectAttributes, "操作成功");
		} 
		return "redirect:"+Global.getAdminPath()+"/datacenter/orders/cslist?repage";
	}

}