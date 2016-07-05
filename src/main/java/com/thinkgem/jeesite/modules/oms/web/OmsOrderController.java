/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.web;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticImp;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticImpObj;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrder;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderItem;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderLogistic;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderLogisticExp;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderReport;
import com.thinkgem.jeesite.modules.oms.service.OmsOrderItemService;
import com.thinkgem.jeesite.modules.oms.service.OmsOrderService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * OMS订单Controller
 * @author jiajianhong
 * @version 2016-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/oms/omsOrder")
public class OmsOrderController extends BaseController {

	@Autowired
	private OmsOrderService omsOrderService;
	
	@Autowired
	private OmsOrderItemService itemService;
	
	//获取当前时间
	private Date getDate(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.MINUTE,0);
		return cal.getTime();
	}
	
	@ModelAttribute
	public OmsOrder get(@RequestParam(required=false) Long orderId) {
		OmsOrder entity = null;
		if (null != orderId){
			entity = omsOrderService.get(orderId);
		}
		if (entity == null){
			entity = new OmsOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("oms:omsOrder:reportList")
	@RequestMapping(value = {"reportList", ""})
	public String reportList(OmsOrderReport omsOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
//		if(omsOrder.getStartTime()==null){
//			omsOrder.setStartTime(getDate());
//		}
		Page<OmsOrderReport> page = omsOrderService.findReportPage(new Page<OmsOrderReport>(request, response), omsOrder); 
		model.addAttribute("page", page);
		return "modules/oms/omsOrderReportList";
	}
	
	/**
	 * 物流相关，导入  导出
	 * @param omsOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oms:omsOrder:reportLogistics")
	@RequestMapping(value = {"reportLogistics", ""})
	public String reportLogistics(OmsOrderLogistic omsOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		String oriDate = omsOrder.getExpBatchDay();
		
		if (StringUtils.isNotBlank(omsOrder.getExpBatchDay())) {
			omsOrder.setExpBatchDay(oriDate.replaceAll("-", ""));
		}
		
		Page<OmsOrderLogistic> page = omsOrderService.findLogisticsReportPage(new Page<OmsOrderLogistic>(request, response), omsOrder); 
		model.addAttribute("page", page);
		omsOrder.setExpBatchDay(oriDate);
		
		return "modules/oms/omsOrderReportLogistics";
	}
	
	@RequiresPermissions("oms:omsOrder:reportLogistics")
	@RequestMapping(value = {"uploadDialog", ""})
	public String uploadDialog(OmsOrderLogistic omsOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		return "modules/oms/omsUploadDialog";
	}
	
	
	@RequiresPermissions("oms:omsOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(OmsOrder omsOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
//		if(omsOrder.getStartTime()==null){
//			omsOrder.setStartTime(getDate());
//		}
		Page<OmsOrder> page = omsOrderService.findPage(new Page<OmsOrder>(request, response), omsOrder); 
		model.addAttribute("page", page);
		return "modules/oms/omsOrderList";
	}

	@RequiresPermissions("oms:omsOrder:view")
	@RequestMapping(value = "form")
	public String form(OmsOrder omsOrder, Model model) {
		model.addAttribute("omsOrder", omsOrder);
		return "modules/oms/omsOrderForm";
	}

	@RequiresPermissions("oms:omsOrder:edit")
	@RequestMapping(value = "save")
	public String save(OmsOrder omsOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, omsOrder)){
			return form(omsOrder, model);
		}
		omsOrderService.save(omsOrder);
		addMessage(redirectAttributes, "保存OMS订单成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsOrder/?repage";
	}
	
	@RequiresPermissions("oms:omsOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(OmsOrder omsOrder, RedirectAttributes redirectAttributes) {
		omsOrderService.delete(omsOrder);
		addMessage(redirectAttributes, "删除OMS订单成功");
		return "redirect:"+Global.getAdminPath()+"/oms/omsOrder/?repage";
	}

    /**
     * 导出采购订单数据
     * @param omsOrder
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("oms:omsOrder:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(OmsOrder omsOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            logger.info("开始导出采购订单数据");
            String fileName = "采购订单数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
//            Page<OmsOrder> page = omsOrderService.findPage(new Page<OmsOrder>(request, response, -1), omsOrder);
//			更新为导出全部数据
            List<OmsOrder> list = omsOrderService.findList(omsOrder);
            new ExportExcel("采购订单数据", OmsOrder.class).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            logger.error("导出采购订单数据失败！失败信息：", e);
            addMessage(redirectAttributes, "导出采购订单数据失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/oms/omsOrder/?repage";
    }
    
    /**
     * 导出采购订单数据
     * @param omsOrder
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("oms:omsOrder:reportLogistics")
    @RequestMapping(value = "exporLogisticsFileQuery", method= RequestMethod.POST)
    public String exporLogisticsFileQuery(OmsOrderLogisticExp orderLogistic, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            logger.info("开始导出采购订单数据");
            
    		String oriDate = orderLogistic.getExpBatchDay();
    		
    		if (StringUtils.isNotBlank(orderLogistic.getExpBatchDay())) {
    			orderLogistic.setExpBatchDay(oriDate.replaceAll("-", ""));
    		}          
            
            String fileName = "采购订单数据 "+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";

            //更新为导出全部数据
            List<OmsOrderLogisticExp> list = omsOrderService.findLogisticsReport(orderLogistic);
            orderLogistic.setExpBatchDay(oriDate);
            
            new ExportExcel("采购订单数据", OmsOrderLogistic.class).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            logger.error("导出采购订单数据失败！失败信息：", e);
            addMessage(redirectAttributes, "导出采购订单数据失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/oms/omsOrder/?repage";
    }
    
    
    // 添加物流的导出 
    @RequiresPermissions("oms:omsOrder:reportLogistics")
    @RequestMapping(value = "exporLogisticsFile", method= RequestMethod.POST)
    public String exporLogisticsFile(OmsOrderLogistic orderLogistic, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            logger.info("开始导出采购订单-物流数据");
            String fileName = "采购订单物流数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";

            OmsOrderLogistic orderLogisticQ = new OmsOrderLogistic();
            List<OmsOrderLogistic> list = omsOrderService.findLogisticsReport(orderLogisticQ);
            
            //成功之后修改导出的时间戳
            omsOrderService.addPiciForExp(list);
            
            new ExportExcel("采购订单物流数据", OmsOrderLogistic.class).setDataList(list).write(response, fileName).dispose();
            
            return null;
        } catch (Exception e) {
            logger.error("导出采购订单数据失败！失败信息：", e);
            addMessage(redirectAttributes, "导出采购订单数据失败！失败信息："+e.getMessage());
        }
        
        return "redirect:" + adminPath + "/oms/omsOrder/reportLogistics/?repage";
    }    
    
    
    
    @RequiresPermissions("oms:omsOrder:reportList")
    @RequestMapping(value = "reportexport", method= RequestMethod.POST)
    public String exporReporttFile(OmsOrderReport omsOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            logger.info("开始导出采购订单数据");
            String fileName = "采购订单数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
//            Page<OmsOrder> page = omsOrderService.findPage(new Page<OmsOrder>(request, response, -1), omsOrder);
//			更新为导出全部数据
            List<OmsOrderReport> list = omsOrderService.findReport(omsOrder);
            Map<String, Integer[]> map=new HashMap<String, Integer[]>();
            String controlNo=null;
            //业务需要一个运单号  详细地址-商品数量-商品序号
            if(list!=null){
            	for (int i = 0; i < list.size(); i++) {
            		//商品数量
            		int cou=list.get(i).getNum();
            		//商品序号
            		int num=0;
            		String id=list.get(i).getOrderId().toString();
            		Integer[] nums=map.get(id);
            		if(nums==null){
            			for (int j = i+1; j < list.size(); j++) {
                			if(id.equals(list.get(j).getOrderId().toString())){
                				cou=cou+list.get(j).getNum();
                			}
    					}
            		}else{
            			cou=nums[0];
            			num=nums[1];
            		}
            		controlNo="";
            		for(int k=0;k<list.get(i).getNum();k++){
            			num++;
            			if("".equals(controlNo)){
            				controlNo=list.get(i).getReceiverAddress()+"-"+cou+"-"+num;	
            			}else{
            				controlNo=controlNo+";"+list.get(i).getReceiverAddress()+"-"+cou+"-"+num;
            			}
            		}
            		list.get(i).setControlNo(controlNo);
            		nums=new Integer[2];
            		nums[0]=cou;
            		nums[1]=num;
            		map.put(id, nums);
				}
            	
            }
            new ExportExcel("采购订单数据", OmsOrderReport.class).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            logger.error("导出采购订单数据失败！失败信息：", e);
            addMessage(redirectAttributes, "导出采购订单数据失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/oms/omsOrder/reportList/?repage";
    }
    
    
    /**
     * 修改地址
     * @param omsOrder
     * @param model
     * @return
     */
    @RequiresPermissions("oms:omsOrder:edit")
	@RequestMapping(value = "orderEdit")
	public String orderEdit(OmsOrder omsOrder, Model model) {
		model.addAttribute("omsOrder", omsOrder);
		return "modules/oms/omsOrderEdit";
	}
    
    @RequiresPermissions("oms:omsOrder:edit")
	@RequestMapping(value = "updateReceiver")
	public String updateReceiver(OmsOrder order, Model model, RedirectAttributes redirectAttributes) {
		
		OmsOrder o=omsOrderService.get(order.getId());
		if(o!=null&&"WAIT_SELLER_SEND_GOODS".equals(o.getStatus())){
			StringBuffer msg=new StringBuffer();
			msg.append("用户"+UserUtils.getUser().getLoginName()+"在"+DateUtils.formatDateTime(new Date())+"修改订单收货地址，修改前如下：");
			msg.append("收货人："+o.getReceiverName()+",");
			msg.append("收货电话："+o.getReceiverMobile()+",");
			msg.append("收货邮编："+o.getReceiverZip()+",");
			msg.append("收货省份："+o.getReceiverState()+",");
			msg.append("收货城市："+o.getReceiverCity()+",");
			msg.append("收货区县："+o.getReceiverDistrict()+",");
			msg.append("收货地址："+o.getReceiverAddress()+",");
			msg.append(";   ");
			order.setOrderId(o.getOrderId());
			if(o.getRemarks()!=null){
				order.setRemarks(o.getRemarks()+";"+msg.toString());
			}else{
				order.setRemarks(msg.toString());
			}
			order.setStatus(o.getStatus());
			int cou= omsOrderService.updateReceiver(order);
			if(cou>0){
	    		addMessage(redirectAttributes, "修改成功！");	
	    	}else{
	    		addMessage(redirectAttributes, "修改失败！");
	    	}
		}else{
			addMessage(redirectAttributes, "修改失败！");
		}
		return "redirect:"+Global.getAdminPath()+"/oms/omsOrder/list";
	}
    
    
    /**
     * 修改预发货时间
     * @param id
     * @param invoiceTime
     * @param redirectAttributes
     * @return
     */
    
    @ResponseBody
    @RequiresPermissions("oms:omsOrder:edit")
	@RequestMapping(value = "updateInvoiceTime")
    public Map<String, Object> updateInvoiceTime(Long id,String invoiceTime, RedirectAttributes redirectAttributes){
    	Map<String, Object> json=new HashMap<String, Object>();
    	json.put("success", false);
    	json.put("msg", "修改失败！");
    	try{
    		if(id!=null){
    			OmsOrderItem o=itemService.get(id+"");
        		if(o!=null){
        			StringBuffer msg=new StringBuffer();
            		msg.append("用户"+UserUtils.getUser().getLoginName()+"在"+DateUtils.formatDateTime(new Date())+"修改订单预发货时间，修改前如下：");
            		msg.append(o.getInvoiceTime());
            		o.setInvoiceTime(invoiceTime);
            		if(o.getRemarks()!=null){
            			o.setRemarks(o.getRemarks()+"; "+msg.toString());
            		}else{
            			o.setRemarks(msg.toString());
            		}
            		
            		itemService.updateInvoiceTime(o);
            		json.put("success", true);
                	json.put("msg", "修改成功！");
        		}
    		}
    	}catch(Exception e){
    		   logger.error("修改预发货时间失败，订单id:"+id, e);
    		   json.put("msg", e);
    	}
    	return json;
    }
    
    
	@RequiresPermissions("oms:omsOrder:reportLogistics")
	@RequestMapping(value = {"impLogistic", ""})
	public String impLogistic(OmsLogisticImp imp, HttpServletRequest request, HttpServletResponse response, Model model) {
		MultipartFile file = imp.getXlsFile();
		try {
			InputStream stream = file.getInputStream();
			XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(stream));
			
			Sheet sheet = wb.getSheetAt(0);
			int rows_num = sheet.getLastRowNum();// 获取行数
			
			logger.debug("\n\n---表\t《" + file.getName() + "》\t 共有数据---：\t" + rows_num + "\t行");
			
			for (int rows = 1; rows <= rows_num; rows++) { //从第一行开始
				Row row = sheet.getRow(rows);// 取得某一行 对象
				try { 
					OmsLogisticImpObj rowObj = new OmsLogisticImpObj();
					Long orderId = 0L;
					try {
						if (row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_STRING) {
							String orderIdStr = row.getCell(0).getStringCellValue();
							orderId = Long.parseLong(orderIdStr);
						} else if (row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							double orderIdDouble = row.getCell(0).getNumericCellValue();
							
							orderId = Math.round(orderIdDouble);
						}
					} catch (Exception e) {
						throw new Exception("获取订单ID出错");
					}
					if (orderId.intValue() == 0) {
						throw new Exception("获取订单ID出错");
					}
					
					rowObj.setOrderId(orderId);
					
					String yzOrderId = "";
					try {
						yzOrderId = row.getCell(1).getStringCellValue();
						
						rowObj.setYzOrderid(yzOrderId);
					} catch (Exception e) {
						throw new Exception("获取订单号 出错");
					}
					
					if (StringUtils.isBlank(yzOrderId)) {
						throw new Exception("获取订单号 为空");
					}
					
					
					if (row.getCell(2).getCellType() == XSSFCell.CELL_TYPE_STRING) {
						rowObj.setYunDanSelf(row.getCell(2).getStringCellValue());
					} else if (row.getCell(2).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
						double yundanSelfDouble = row.getCell(2).getNumericCellValue();
						
						rowObj.setYunDanSelf(String.valueOf(Math.round(yundanSelfDouble)));
					}
					
					rowObj.setYunDanWuliu(row.getCell(3).getStringCellValue());
					
					rowObj.setVendorName(row.getCell(4).getStringCellValue());
					rowObj.setPeiSongCmpy(row.getCell(5).getStringCellValue());
					rowObj.setQianShouStatus(row.getCell(6).getStringCellValue());
					
					int resultUpdate = omsOrderService.updateForImp(rowObj);
					if (resultUpdate == 0) {
						throw new Exception("没找到匹配的记录");
					}
					Cell result = row.createCell(7);
					
					result.setCellValue("成功");	
					CellStyle style = wb.createCellStyle();
					Font font = wb.createFont();  
					font.setColor(IndexedColors.GREEN.getIndex());
					
					style.setFont(font);
					result.setCellStyle(style);
				} catch (Exception e) {
					logger.error("---------------");
					Cell result = row.createCell(7);
					result.setCellValue(e.getMessage());
					
					CellStyle style = wb.createCellStyle();
					Font font = wb.createFont();  
					font.setColor(Font.COLOR_RED);  
					
					style.setFont(font);
					result.setCellStyle(style);
				}
			}
			
			//将变化后的excel返回回去
			SXSSFWorkbook wbRes = new SXSSFWorkbook(wb);
			
			response.reset();
	        response.setContentType("application/octet-stream; charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment; filename="+Encodes.urlEncode(file.getOriginalFilename()));
			
	        wbRes.write(response.getOutputStream());
	        wbRes.dispose();
	        
	        return null;
		} catch (Exception e) {
			logger.error("---------------", e);
		}
		
		
		return "redirect:" + adminPath + "/oms/omsOrder/reportLogistics/?repage";
	}
}