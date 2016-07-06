///**
// * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.thinkgem.gds.hotelmapping.web;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.fangbaba.gds.face.service.IHotelSearchService;
//import com.google.common.base.Strings;
//import com.thinkgem.gds.hotelmapping.entity.HotelMapping;
//import com.thinkgem.gds.hotelmapping.service.HotelMappingService;
//import com.thinkgem.jeesite.common.config.Global;
//import com.thinkgem.jeesite.common.persistence.Page;
//import com.thinkgem.jeesite.common.utils.StringUtils;
//import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
//import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
//import com.thinkgem.jeesite.common.web.BaseController;
//import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
//import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
//
///**
// * 酒店映射Controller
// * @author zhangyajun
// * @version 2016-04-15
// */
//@Controller
//@RequestMapping(value = "${adminPath}/hotelmapping/hotelMapping")
//public class HotelMappingController extends BaseController {
//
//	@Autowired
//	private HotelMappingService hotelMappingService;
//	@Autowired
//	private IHotelSearchService hotelSearchService;
//	
//	/**
//	 * 验证Bean实例对象
//	 */
//	/*@Autowired
//	protected Validator validator;*/
//	@ModelAttribute
//	public HotelMapping get(@RequestParam(required=false) String id) {
//		HotelMapping entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = hotelMappingService.get(id);
//		}
//		if (entity == null){
//			entity = new HotelMapping();
//		}
//		return entity;
//	}
//	
//	@RequiresPermissions("hotelmapping:hotelMapping:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(HotelMapping hotelMapping, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<HotelMapping> page = hotelMappingService.findPage(new Page<HotelMapping>(request, response), hotelMapping); 
//		model.addAttribute("page", page);
//		return "gds/hotelmapping/hotelMappingList";
//	}
//
//	@RequiresPermissions("hotelmapping:hotelMapping:view")
//	@RequestMapping(value = "form")
//	public String form(HotelMapping hotelMapping, Model model) {
//		model.addAttribute("hotelMapping", hotelMapping);
//		return "gds/hotelmapping/hotelMappingForm";
//	}
//
//	@RequiresPermissions("hotelmapping:hotelMapping:edit")
//	@RequestMapping(value = "save")
//	public String save(HotelMapping hotelMapping, Model model, RedirectAttributes redirectAttributes) throws Exception {
//		if (!beanValidator(model, hotelMapping)){
//			return form(hotelMapping, model);
//		}
//		hotelMapping.setCreatetime(new Date());
//		hotelMapping.setUpdatetime(new Date());
//		hotelMapping.setCreateby(UserUtils.getUser().getId());
//		hotelMapping.setUpdateby(UserUtils.getUser().getId());
//		if (Strings.isNullOrEmpty(hotelMapping.getState())) {
//			hotelMapping.setState(DictUtils.getDictValue("已生效", "HotelMappingState", "2"));
//		}
//		hotelMappingService.save(hotelMapping);
//		hotelSearchService.initEsByHotelid(hotelMapping.getHotelid());
//		addMessage(redirectAttributes, "保存酒店映射成功");
//		return "redirect:"+Global.getAdminPath()+"/hotelmapping/hotelMapping/?repage";
//	}
//	
//	@RequiresPermissions("hotelmapping:hotelMapping:edit")
//	@RequestMapping(value = "delete")
//	public String delete(HotelMapping hotelMapping, RedirectAttributes redirectAttributes) throws Exception {
//		hotelMappingService.delete(hotelMapping);
//		hotelSearchService.initEsByHotelid(hotelMapping.getHotelid());
//		addMessage(redirectAttributes, "删除酒店映射成功");
//		return "redirect:"+Global.getAdminPath()+"/hotelmapping/hotelMapping/?repage";
//	}
//	/**
//	 * 导入酒店映射数据
//	 * @param file
//	 * @param redirectAttributes
//	 * @return
//	 */
//	@RequiresPermissions("hotelmapping:hotelMapping:edit")
//    @RequestMapping(value = "import", method=RequestMethod.POST)
//    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
//		if(Global.isDemoMode()){
//			addMessage(redirectAttributes, "演示模式，不允许操作！");
//			return "redirect:" + adminPath + "/hotelmapping/hotelMapping/list?repage";
//		}
//		try {
//			int successNum = 0;
//			int failureNum = 0;
//			StringBuilder failureMsg = new StringBuilder();
//			ImportExcel ei = new ImportExcel(file, 1, 0);
//			List<HotelMapping> list = ei.getDataList(HotelMapping.class);
//			
//			for (HotelMapping hotel : list){
//				try{
//					StringBuilder msg = new StringBuilder("");
//					if (hotel.getHotelid()==null) {
//						msg.append("酒店id为空,");
//					}
//					if (Strings.isNullOrEmpty(hotel.getHotelname())) {
//						msg.append("ota酒店名字为空,");
//					}
//					
//					if (Strings.isNullOrEmpty(hotel.getChannelid())) {
//						msg.append("渠道id为空");
//					}
//					if (!"".equals(msg.toString())) {
//						failureMsg.append("<br /> 第"+(failureNum+3)+"行:"+msg.toString());
//						failureNum++;
//						continue;
//					}
//					
//					
//					hotel.setCreatetime(new Date());
//					hotel.setUpdatetime(new Date());
//					hotel.setCreateby(UserUtils.getUser().getId());
//					hotel.setUpdateby(UserUtils.getUser().getId());
//					hotel.setState(DictUtils.getDictValue("已生效", "HotelMappingState", "2"));
//					
//					hotelMappingService.save(hotel);
//					hotelSearchService.initEsByHotelid(hotel.getHotelid());
//					successNum++;
//				}catch (Exception ex) {
//					failureMsg.append("<br/>酒店id "+hotel.getHotelid()+" 导入失败：请检查导入的数据是否正确获取条记录已经存在");
//					logger.error("导入酒店映射失败:"+hotel.getHotelid(),ex);
//				}
//			}
//			if (failureNum>0){
//				failureMsg.insert(0, "，失败 "+failureNum+" 条酒店映射，错误信息如下：");
//			}
//			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条酒店映射"+failureMsg.toString());
//		} catch (Exception e) {
//			addMessage(redirectAttributes, "导入酒店映射失败！失败信息："+e.getMessage());
//		}
//		return "redirect:" + adminPath + "/hotelmapping/hotelMapping/list?repage";
//    }
//	
//	/**
//	 * 下载酒店映射数据模板
//	 * @param response
//	 * @param redirectAttributes
//	 * @return
//	 */
//	@RequiresPermissions("hotelmapping:hotelMapping:view")
//    @RequestMapping(value = "import/template")
//    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		try {
//            String fileName = "酒店映射数据导入模板.xlsx";
//            new ExportExcel("templates.excel/hotelmapping.xlsx").write(response, fileName).dispose();
//    		return null;
//		} catch (Exception e) {
//			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
//		}
//		return "redirect:" + adminPath + "/hotelmapping/hotelMapping/list?repage";
//    }
//
//}