/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomtypemapping.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fangbaba.gds.face.service.IHotelChangePushService;
import com.google.common.base.Strings;
import com.thinkgem.gds.roomtypemapping.entity.RoomtypeMapping;
import com.thinkgem.gds.roomtypemapping.service.RoomtypeMappingService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
/**
 * 房型映射Controller
 * @author 姜贺
 * @version 2016-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/roomtypemapping/roomtypeMapping")
public class RoomtypeMappingController extends BaseController {

	@Autowired
	private RoomtypeMappingService roomtypeMappingService;
	@Autowired
	private IHotelChangePushService iHotelChangePushService;
	private int excelRow=4; 
	@ModelAttribute
	public RoomtypeMapping get(@RequestParam(required=false) String id) {
		RoomtypeMapping entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = roomtypeMappingService.get(id);
		}
		if (entity == null){
			entity = new RoomtypeMapping();
		}
		return entity;
	}
	
	@RequiresPermissions("roomtypemapping:roomtypeMapping:view")
	@RequestMapping(value = {"list", ""})
	public String list(RoomtypeMapping roomtypeMapping, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RoomtypeMapping> page = roomtypeMappingService.findPage(new Page<RoomtypeMapping>(request, response), roomtypeMapping); 
		model.addAttribute("page", page);
		return "gds/roomtypemapping/roomtypeMappingList";
	}

	@RequiresPermissions("roomtypemapping:roomtypeMapping:view")
	@RequestMapping(value = "form")
	public String form(RoomtypeMapping roomtypeMapping, Model model) {
		model.addAttribute("roomtypeMapping", roomtypeMapping);
		return "gds/roomtypemapping/roomtypeMappingForm";
	}

	@RequiresPermissions("roomtypemapping:roomtypeMapping:edit")
	@RequestMapping(value = "save")
	public String save(RoomtypeMapping roomtypeMapping, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, roomtypeMapping)){
			return form(roomtypeMapping, model);
		}
		roomtypeMappingService.save(roomtypeMapping);
		if (roomtypeMapping.getHotelid()!=null) {
			iHotelChangePushService.sendHotelChange(roomtypeMapping.getHotelid());
		}
		addMessage(redirectAttributes, "保存房型映射成功");
		return "redirect:"+Global.getAdminPath()+"/roomtypemapping/roomtypeMapping/?repage";
	}
	
	@RequiresPermissions("roomtypemapping:roomtypeMapping:edit")
	@RequestMapping(value = "delete")
	public String delete(RoomtypeMapping roomtypeMapping, RedirectAttributes redirectAttributes) {
		roomtypeMappingService.delete(roomtypeMapping);
		addMessage(redirectAttributes, "删除房型映射成功");
		return "redirect:"+Global.getAdminPath()+"/roomtypemapping/roomtypeMapping/?repage";
	}
	/**
	 * 导入酒店映射数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("roomtypemapping:roomtypeMapping:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/roomtypemapping/roomtypeMapping/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			List<Long> hotelids = new ArrayList<Long>();
			Set<Long>  hotelidset = new HashSet<Long>();
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 2, 0);
			List<RoomtypeMapping> list = ei.getDataList(RoomtypeMapping.class);
			for (RoomtypeMapping roomtype : list){
				try{
					StringBuilder msg = new StringBuilder("");
					if (roomtype.getHotelid()==null) {
						msg.append("酒店id为空,");
					}
					if (Strings.isNullOrEmpty(roomtype.getOtaRoomtypename())) {
						msg.append("ota房型名字为空,");
					}
					if (Strings.isNullOrEmpty(roomtype.getRoomtypename())) {
						msg.append("房型名字为空,");
					}
					if (roomtype.getBedtype()==null) {
						msg.append("床型为空,");
					}
					if (Strings.isNullOrEmpty(roomtype.getChannelid())) {
						msg.append("渠道id为空");
					}
					if (!"".equals(msg.toString())) {
						failureMsg.append("<br /> 第"+(failureNum+excelRow)+"行:"+msg.toString());
						failureNum++;
						continue;
					}
					List<Long> roomtypeids = roomtypeMappingService.findRoomtypeByName(roomtype.getHotelid(),roomtype.getRoomtypename().trim());
					if (CollectionUtils.isEmpty(roomtypeids)) {
						failureMsg.append("<br /> 第"+(failureNum+excelRow)+"行:根据酒店id和房型名字未能查到数据，请检查房型名字是否正确,");
						failureNum++;
						continue;
					}else if (roomtypeids.size()!=1) {
						failureMsg.append("<br /> 第"+(failureNum+excelRow)+"行:根据酒店id和房型名字查询过多数据，请检查房型名字是否正确,");
						failureNum++;
						continue;
					}
					roomtype.setCreatetime(new Date());
					roomtype.setUpdatetime(new Date());
					roomtype.setCreateby(UserUtils.getUser().getId());
					roomtype.setUpdateby(UserUtils.getUser().getId());
					roomtype.setState(DictUtils.getDictValue("已生效", "RoomtypeMappingEnum", "2"));
					roomtype.setRoomtypeid(roomtypeids.get(0));
					roomtypeMappingService.save(roomtype);
					hotelidset.add(roomtype.getHotelid());
					successNum++;
				}catch (Exception ex) {
					failureMsg.append("<br/>酒店id "+roomtype.getRoomtypeid()+" 导入失败：请检查导入的数据是否正确获取条记录已经存在");
					logger.error("导入酒店映射失败:"+roomtype.getHotelid(),ex);
				}
			}
			if (!CollectionUtils.isEmpty(hotelidset)) {
				hotelids.addAll(hotelidset);
				iHotelChangePushService.sendHotelChange(hotelids);
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条酒店映射，错误信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条酒店映射"+failureMsg.toString());
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入酒店映射失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/roomtypemapping/roomtypeMapping/list?repage";
    }
	
	/**
	 * 下载房型数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("roomtypemapping:roomtypeMapping:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "酒店映射数据导入模板.xlsx";
            new ExportExcel("templates.excel/roomtypemapping.xlsx").write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/roomtypemapping/roomtypeMapping/list?repage";
    }
}