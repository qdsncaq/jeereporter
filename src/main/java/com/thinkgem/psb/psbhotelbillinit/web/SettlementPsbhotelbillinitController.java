/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbhotelbillinit.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TDistrict;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.psb.psbhotelbillinit.entity.SettlementPsbhotelbillinit;
import com.thinkgem.psb.psbhotelbillinit.service.SettlementPsbhotelbillinitService;
import com.thinkgem.psb.psbinfo.entity.SettlementPsbinfo;
import com.thinkgem.psb.psbinfo.service.SettlementPsbinfoService;

/**
 * psbhotelbillController
 * @author lm
 * @version 2016-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/psbhotelbill/settlementPsbhotelbillinit")
public class SettlementPsbhotelbillinitController extends BaseController {
	
	@Autowired
	private SettlementPsbhotelbillinitService billinitService;
	
	@Autowired
	private SettlementPsbinfoService settlementPsbinfoService;
	
	/** 省服务 */
	@Autowired
	private TProvinceService provinceService;
	
	/** 市服务 */
	@Autowired
	private TCityService tCityService;
	
	/** 县区服务 */
	@Autowired
	private TDistrictService districtService;
	
	@ModelAttribute
	public SettlementPsbhotelbillinit get(@RequestParam(required=false) String id) {
		SettlementPsbhotelbillinit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = billinitService.get(id);
		}
		if (entity == null){
			entity = new SettlementPsbhotelbillinit();
		}
		return entity;
	}
	
	/**
	 * 查询列表
	 * @param settlementPsbhotelbillinit
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("psbhotelbill:settlementPsbhotelbillinit:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementPsbhotelbillinit settlementPsbhotelbillinit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementPsbhotelbillinit> page = billinitService.findPage(new Page<SettlementPsbhotelbillinit>(request, response), settlementPsbhotelbillinit); 
		
		//psb编码
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
				 
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(settlementPsbhotelbillinit.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(settlementPsbhotelbillinit.getProcode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
//          List<TCity> cityList = tCityService.getByParent(city);
			Page<TCity> pc=new Page<TCity>();
			pc.setOrderBy(" a.CityName ASC ");
			List<TCity> cityList = tCityService.findPage(pc,city).getList();
			model.addAttribute("cityList", cityList);
		}
//		区县 (暂时去掉)
		if(StringUtils.isNotBlank(settlementPsbhotelbillinit.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(settlementPsbhotelbillinit.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		
		model.addAttribute("page", page);
		return "psb/psbhotelbill/settlementPsbhotelbillinitList";
	}
	
	@RequiresPermissions("psbhotelbill:settlementPsbhotelbillinit:view")
	@RequestMapping(value = "form")
	public String form(SettlementPsbhotelbillinit settlementPsbhotelbillinit, Model model) {
		model.addAttribute("settlementPsbhotelbillinit", settlementPsbhotelbillinit);
		
		//psb编码
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(settlementPsbhotelbillinit.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(settlementPsbhotelbillinit.getProcode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
//          List<TCity> cityList = tCityService.getByParent(city);
			Page<TCity> pc=new Page<TCity>();
			pc.setOrderBy(" a.CityName ASC ");
			List<TCity> cityList = tCityService.findPage(pc,city).getList();
			model.addAttribute("cityList", cityList);
		}
		
		return "psb/psbhotelbill/settlementPsbhotelbillinitForm";
	}
	
	/**
	 * 导出
	 * @param settlementPsbhotelbillinit
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("psbhotelbill:SettlementPsbhotelbillinit:view")
	@RequestMapping(value = "exportexcel")
	public String exportexcel(SettlementPsbhotelbillinit settlementPsbhotelbillinit, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
            String fileName = "账单明细列表.xlsx";
            List<SettlementPsbhotelbillinit> list = billinitService.findList(settlementPsbhotelbillinit);
            new ExportExcel("账单明细", SettlementPsbhotelbillinit.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "账单明细excel导出失败！失败信息："+e.getMessage());
        }
		return "redirect:" + Global.getAdminPath() + "/psbhotelbill/settlementPsbhotelbillinit/?repage";
	}
	
	/**
	 * 酒店账单初始化
	 * 		查询所有有上线记录的酒店,生成一条账单明细
	 * 		状态: 1初始化
	 */
	@RequiresPermissions("psbhotelbill:settlementPsbhotelbillinit:view")
	@RequestMapping(value = "psbbillinit")
	@ResponseBody
	public void psbbillinit(){
		billinitService.psbbillinit(null, 1);
	}
	
	/**
	 * 输入日期 其余条件预览账单 点击按钮生成账单
	 * @param typeParam
	 * @param billinit
	 * @param model
	 * @return
	 */
	@RequiresPermissions("psbhotelbill:settlementPsbhotelbillinit:psbsettlement")
	@RequestMapping(value = "previewbill")
	public Object previewbill(int typeParam, SettlementPsbhotelbillinit billinit, Model model, RedirectAttributes redirectAttributes){
		Date begintime = billinit.getBegintime();
		Date endtime = billinit.getEndtime();
		if(begintime == null || endtime == null){
			addMessage(redirectAttributes, "不能为空.");
			//psb编码
			List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
			model.addAttribute("psbList", psbList);
			
			//省
//	      List<TProvince> provinceList = provinceService.findList(new TProvince());
			Page<TProvince> pp=new Page<TProvince>();
			pp.setOrderBy(" a.ProName ASC ");
			List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
					 
			model.addAttribute("provinceList",provinceList);
			//市
			if(StringUtils.isNotBlank(billinit.getProcode())){
				//查询省ID(前台变相可传)
				TProvince province = provinceService.getByCode(billinit.getProcode());
				//获取城市数据
				TCity city = new TCity();
				city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
//	          List<TCity> cityList = tCityService.getByParent(city);
				Page<TCity> pc=new Page<TCity>();
				pc.setOrderBy(" a.CityName ASC ");
				List<TCity> cityList = tCityService.findPage(pc,city).getList();
				model.addAttribute("cityList", cityList);
			}
//			区县 (暂时去掉)
			if(StringUtils.isNotBlank(billinit.getCitycode())){
				//查询城市ID(前台变相可传)
				TCity city = tCityService.getByCode(billinit.getCitycode());

				TDistrict district = new TDistrict();
				district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
				List<TDistrict> districtList = districtService.getByParent(district);
				model.addAttribute("districtList", districtList);
			}
			
			model.addAttribute("billinit", billinit);
			return "psb/psbhotelbill/billinitupdateView";
		}
		Page<SettlementPsbhotelbillinit> page = new Page<SettlementPsbhotelbillinit>();
		List<SettlementPsbhotelbillinit> billlist =  billinitService.generatePsbBill(billinit, typeParam);
		
		//psb编码
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
				 
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(billinit.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(billinit.getProcode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
//          List<TCity> cityList = tCityService.getByParent(city);
			Page<TCity> pc=new Page<TCity>();
			pc.setOrderBy(" a.CityName ASC ");
			List<TCity> cityList = tCityService.findPage(pc,city).getList();
			model.addAttribute("cityList", cityList);
		}
//		区县 (暂时去掉)
		if(StringUtils.isNotBlank(billinit.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(billinit.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		
		page.setList(billlist);
		model.addAttribute("billinit", billinit);
		model.addAttribute("page", page);
		return "psb/psbhotelbill/billinitupdateView";
	}
	
	/**
	 * 跳转到输入指定日期界面
	 * @param settlementPsbhotelbillinit
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("psbhotelbill:settlementPsbhotelbillinit:view")
	@RequestMapping(value = {"preview", ""})
	public String preview(SettlementPsbhotelbillinit settlementPsbhotelbillinit, HttpServletRequest request, HttpServletResponse response, Model model) {
		//psb编码
		List<SettlementPsbinfo> psbList = settlementPsbinfoService.findAllPsb();
		model.addAttribute("psbList", psbList);
		
		//省
//      List<TProvince> provinceList = provinceService.findList(new TProvince());
		Page<TProvince> pp=new Page<TProvince>();
		pp.setOrderBy(" a.ProName ASC ");
		List<TProvince> provinceList = provinceService.findPage(pp, new TProvince()).getList();
				 
		model.addAttribute("provinceList",provinceList);
		//市
		if(StringUtils.isNotBlank(settlementPsbhotelbillinit.getProcode())){
			//查询省ID(前台变相可传)
			TProvince province = provinceService.getByCode(settlementPsbhotelbillinit.getProcode());
			//获取城市数据
			TCity city = new TCity();
			city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
//          List<TCity> cityList = tCityService.getByParent(city);
			Page<TCity> pc=new Page<TCity>();
			pc.setOrderBy(" a.CityName ASC ");
			List<TCity> cityList = tCityService.findPage(pc,city).getList();
			model.addAttribute("cityList", cityList);
		}
		if(StringUtils.isNotBlank(settlementPsbhotelbillinit.getCitycode())){
			//查询城市ID(前台变相可传)
			TCity city = tCityService.getByCode(settlementPsbhotelbillinit.getCitycode());

			TDistrict district = new TDistrict();
			district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
			List<TDistrict> districtList = districtService.getByParent(district);
			model.addAttribute("districtList", districtList);
		}
		
		return "psb/psbhotelbill/billinitupdateView";
	}
	
}