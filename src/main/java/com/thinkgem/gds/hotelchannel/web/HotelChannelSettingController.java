/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.hotelchannel.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fangbaba.gds.enums.ChannelEnum;
import com.fangbaba.gds.enums.ChannelStateEnum;
import com.fangbaba.gds.face.service.IHotelChannelSettingService;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.gds.hotelchannel.entity.HotelChannelSetting;
import com.thinkgem.gds.hotelchannel.service.HotelChannelSettingService;

/**
 * 酒店渠道分销开关Controller
 * @author zhaochuanbin
 * @version 2016-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/hotelchannel/hotelChannelSetting")
public class HotelChannelSettingController extends BaseController {
    
    private static Logger logger = LoggerFactory.getLogger(HotelChannelSettingController.class);

	@Autowired
	private HotelChannelSettingService hotelChannelSettingService;
	
	@Autowired
	private IHotelChannelSettingService gdsHotelChannelSettingService;
	
	@ModelAttribute
	public HotelChannelSetting get(@RequestParam(required=false) String id) {
		HotelChannelSetting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hotelChannelSettingService.get(id);
		}
		if (entity == null){
			entity = new HotelChannelSetting();
		}
		return entity;
	}
	
	@RequiresPermissions("hotelchannel:hotelChannelSetting:view")
	@RequestMapping(value = {"list", ""})
	public String list(HotelChannelSetting hotelChannelSetting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HotelChannelSetting> page = hotelChannelSettingService.findPage(new Page<HotelChannelSetting>(request, response), hotelChannelSetting); 
		model.addAttribute("page", page);
		return "gds/hotelchannel/hotelChannelSettingList";
	}

	@RequiresPermissions("hotelchannel:hotelChannelSetting:view")
	@RequestMapping(value = "form")
	public String form(HotelChannelSetting hotelChannelSetting, Model model) {
		model.addAttribute("hotelChannelSetting", hotelChannelSetting);
		return "gds/hotelchannel/hotelChannelSettingForm";
	}

	@RequiresPermissions("hotelchannel:hotelChannelSetting:edit")
	@RequestMapping(value = "save")
	public String save(HotelChannelSetting hotelChannelSetting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hotelChannelSetting)){
			return form(hotelChannelSetting, model);
		}
		hotelChannelSettingService.save(hotelChannelSetting);
		addMessage(redirectAttributes, "保存酒店渠道分销开关成功");
		return "redirect:"+Global.getAdminPath()+"/hotelchannel/hotelChannelSetting/?repage";
	}
	
	/**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hotelchannel:hotelChannelSetting:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "districtImportTemplate.xlsx";
            new ExportExcel("templates.excel/districtImportTemplate.xlsx").write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/hotelchannel/hotelChannelSetting/list?repage";
    }
    
	@RequiresPermissions("hotelchannel:hotelChannelSetting:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HotelChannelSetting> list = ei.getDataList(HotelChannelSetting.class);
			for (HotelChannelSetting hotelChannelSetting : list){
				try{
					savestatus(hotelChannelSetting.getHotelid().toString(), hotelChannelSetting.getChannelid(), hotelChannelSetting.getState(), "systemimport");
					successNum++;
				}catch (Exception e) {
					failureMsg.append("导入失败："+e.getMessage());
					failureNum++;
					logger.error("导入失败：",e);
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条记录，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条数据"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/hotelchannel/hotelChannelSetting/list?repage";
    }
	
	@ResponseBody
	@RequestMapping(value = "savestatus")
    public Integer savestatus(String hotelid,String channel,String status,String operator) {
	    logger.info("hotelid:"+hotelid+",channel:"+channel+",status:"+status+",operator:"+operator);
	    
	    Integer renum = gdsHotelChannelSettingService.openOrCloseSwitch(hotelid, ChannelEnum.getById(Integer.parseInt(channel)), 
	            ChannelStateEnum.getById(Integer.parseInt(status)), operator);
	    logger.info("hotelid:"+hotelid+",channel:"+channel+",status:"+status+",operator:"+operator+",renum:"+renum);
        return renum;
    }
	
	@RequiresPermissions("hotelchannel:hotelChannelSetting:edit")
	@RequestMapping(value = "delete")
	public String delete(HotelChannelSetting hotelChannelSetting, RedirectAttributes redirectAttributes) {
		hotelChannelSettingService.delete(hotelChannelSetting);
		addMessage(redirectAttributes, "删除酒店渠道分销开关成功");
		return "redirect:"+Global.getAdminPath()+"/hotelchannel/hotelChannelSetting/?repage";
	}

}