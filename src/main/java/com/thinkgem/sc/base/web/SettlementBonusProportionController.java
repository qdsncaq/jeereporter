/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.base.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TDistrict;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TDistrictService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.sc.base.entity.SettlementBonusProportion;
import com.thinkgem.sc.base.service.SettlementBonusProportionService;

/**
 * 区域经理奖金分配比例Controller
 * @author aiqing.chu@fangbaba.com
 * @version 2016-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/base/settlementBonusProportion")
public class SettlementBonusProportionController extends BaseController {

	@Autowired
	private SettlementBonusProportionService settlementBonusProportionService;
	
	/** 
	 * 省服务
	 */
    @Autowired
    private TProvinceService tProvinceService;
    
    /** 
     * 市服务
     */
    @Autowired
    private TCityService tCityService;
    
    /** 
     * 县区服务 
     */
    @Autowired
    private TDistrictService tDistrictService;
	
	@ModelAttribute
	public SettlementBonusProportion get(@RequestParam(required=false) String id) {
		SettlementBonusProportion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementBonusProportionService.get(id);
		}
		if (entity == null){
			entity = new SettlementBonusProportion();
		}
		return entity;
	}
	
	@RequiresPermissions("base:settlementBonusProportion:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementBonusProportion settlementBonusProportion, HttpServletRequest request, HttpServletResponse response, Model model) {
	    User user = UserUtils.getUser();
	    String loginname = user.getLoginName();
	    settlementBonusProportion.setOperator(loginname);
	    Page<SettlementBonusProportion> page = settlementBonusProportionService.findPage(new Page<SettlementBonusProportion>(request, response), settlementBonusProportion); 
		model.addAttribute("page", page);
		
        //以下是省市区县,前台列表筛选显示
		String provCode = settlementBonusProportion.getProvince();
		String cityCode = settlementBonusProportion.getCity();
		
        //省
        List<TProvince> provinceList = tProvinceService.findList(new TProvince());
        model.addAttribute("provinceList",provinceList);
        //市
        if(StringUtils.isNotBlank(provCode)){
            //查询省ID(前台变相可传)
            TProvince province = tProvinceService.getByCode(provCode);
            //获取城市数据
            TCity city = new TCity();
            city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
            List<TCity> cityList = tCityService.getByParent(city);
            model.addAttribute("cityList", cityList);
        }
        // 区县 (暂时去掉)
        if(StringUtils.isNotBlank(cityCode)){
            //查询城市ID(前台变相可传)
            TCity city = tCityService.getByCode(cityCode);
            TDistrict district = new TDistrict();
            district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
            List<TDistrict> districtList = tDistrictService.getByParent(district);
            model.addAttribute("districtList", districtList);
        }
		return "sc/base/settlementBonusProportionList";
	}

	@RequiresPermissions("base:settlementBonusProportion:view")
	@RequestMapping(value = "form")
	public String form(SettlementBonusProportion settlementBonusProportion, Model model) {
		model.addAttribute("settlementBonusProportion", settlementBonusProportion);
		
		//以下是省市区县,前台列表筛选显示
        String provCode = settlementBonusProportion.getProvince();
        String cityCode = settlementBonusProportion.getCity();
        
        //省
        List<TProvince> provinceList = tProvinceService.findList(new TProvince());
        model.addAttribute("provinceList",provinceList);
        //市
        if(StringUtils.isNotBlank(provCode)){
            //查询省ID(前台变相可传)
            TProvince province = tProvinceService.getByCode(provCode);
            //获取城市数据
            TCity city = new TCity();
            city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
            List<TCity> cityList = tCityService.getByParent(city);
            model.addAttribute("cityList", cityList);
        }
        // 区县 (暂时去掉)
        if(StringUtils.isNotBlank(cityCode)){
            //查询城市ID(前台变相可传)
            TCity city = tCityService.getByCode(cityCode);
            TDistrict district = new TDistrict();
            district.setCityid(Integer.valueOf(String.valueOf(city.getCityid())));
            List<TDistrict> districtList = tDistrictService.getByParent(district);
            model.addAttribute("districtList", districtList);
        }
		return "sc/base/settlementBonusProportionForm";
	}

	@RequiresPermissions("base:settlementBonusProportion:edit")
	@RequestMapping(value = "save")
	public String save(SettlementBonusProportion settlementBonusProportion, Model model, RedirectAttributes redirectAttributes) {
	    Date date = new Date();
		if (!beanValidator(model, settlementBonusProportion)){
			return form(settlementBonusProportion, model);
		}
		User currentUser = settlementBonusProportion.getCurrentUser();
		settlementBonusProportion.setOperator(currentUser.getLoginName());
		settlementBonusProportion.setUpdatetime(date);
		if (settlementBonusProportion.getCreatetime() == null) {
		    settlementBonusProportion.setCreatetime(date);
		}
		if (settlementBonusProportion.getId() == null || settlementBonusProportion.getId().trim().length() == 0) {
		    // 新建数据
		    settlementBonusProportion.setIsNewRecord(true);
		    settlementBonusProportion.setId(String.valueOf(IdGen.randomLong()));
		}
		settlementBonusProportionService.save(settlementBonusProportion);
		addMessage(redirectAttributes, "保存奖金分配比例成功");
		return "redirect:"+Global.getAdminPath()+"/base/settlementBonusProportion/?repage";
	}
	
	@RequiresPermissions("base:settlementBonusProportion:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementBonusProportion settlementBonusProportion, RedirectAttributes redirectAttributes) {
		settlementBonusProportionService.delete(settlementBonusProportion);
		addMessage(redirectAttributes, "删除奖金分配比例成功");
		return "redirect:"+Global.getAdminPath()+"/base/settlementBonusProportion/?repage";
	}

}