package com.thinkgem.jeesite.modules.settlement.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.settlement.common.ExcelExport;
import com.thinkgem.jeesite.modules.settlement.common.NetUtil;
import com.thinkgem.jeesite.modules.settlement.common.UrlUtils;

@Controller
@RequestMapping(value = "${adminPath}/scadmin/tripdetail")
public class TripDetailBillController {
	
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = {"list", ""})
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject addObject = packageDate(request);
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/caiwu/tripdetails";
		String result = null;
		try {
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject message = JSONObject.parseObject(result);

		JSONObject pageObj = message.getJSONObject("page");
		Page page = new Page();
		page.setPageNo(pageObj.getIntValue("pageNo"));
		page.setPageSize(pageObj.getIntValue("pageSize"));
		page.setCount(pageObj.getLongValue("totalRecord"));
		page.setList(pageObj.getJSONArray("result"));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", pageObj);
		modelAndView.addObject("page", page);
		
		modelAndView.addObject("params", addObject);
		modelAndView.setViewName("modules/settlement/tripDetailList");
		return modelAndView;
	}
	
	/**
	 * 调整金额
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"update", ""})
	@ResponseBody
	public int updateDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject addObject = new JSONObject();
		String id = request.getParameter("id");
		String changeOrdertotal = request.getParameter("changeOrdertotal");
		String remarks = request.getParameter("remarks");
		addObject.put("id", id);
		addObject.put("ordertotal", changeOrdertotal);
		addObject.put("remarks", remarks);
		
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/caiwu/updatedetails";
		String result = null;
		try {
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			return 999;
		}
		return Integer.parseInt(result);
	}
	
	/**
	 * 旅行社周账单明细导出功能(导出查询全部数据)
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = {"exportall", ""})
	@ResponseBody
	public void exportAll(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject addObject = packageDate(request);
		
		/**  */
		OutputStream fOut = null;
		try {
			String[] titile={"订单号","客户ID","客户名称","酒店ID","酒店名称","订单总额","下单时间",};
			addObject.put("length", titile.length);
			addObject.put("currentPage", false);
			
			String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
			String settlementConUrl = stlmt_server_url + "/sc/caiwu/tripdetails/export1";
			String result = null;
			try {
				result = NetUtil.httpPostParameters(settlementConUrl, addObject.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<String[]> listData = new ArrayList<String[]>();
			JSONArray jsonData = (JSONArray) JSONObject.parse(result);
			
			String jsonString = jsonData.toJSONString();
			
			String s1 = jsonString.replace("[[", "");
			String s2 = s1.replace("[", "");
			String s3 = s2.replace("]]", "");
			String s4 = s3.replace("\"", "");
			
			String[] split = s4.split("],");
			for (String string : split) {
				String[] split2 = string.split(",");
				listData.add(split2);
			}
			String excelName = "旅行社周账单明细";
			ExcelExport.exprotBefore(request,response,excelName);
			Boolean[] isdigit={null,null,null,null,null,true,null };
			int[] columnWidth={35,28,28,30,35,28,35 };
			HSSFWorkbook workbook =ExcelExport.export(excelName, titile,listData,columnWidth,isdigit);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (UnsupportedEncodingException e1) {
			log.error("导出失败，失败原因："+e1.getMessage());
			e1.getMessage();
		} catch (Exception e) {
			log.error("导出失败，失败原因："+e.getMessage());
			e.getMessage();
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {
				e.getMessage();
			}
		}
		log.info("导出数据成功！");
	}
	
	/**
	 * 接收页面传递的参数 model和分页信息
	 * @param request
	 * @return
	 */
	public JSONObject packageDate(HttpServletRequest request){
		JSONObject addObject = new JSONObject();
		/** 封装数据 */
		String id = request.getParameter("id");
		addObject.put("id", id);
		
		String orderid = request.getParameter("orderid");
		String hotelid = request.getParameter("hotelid");
		String hotelname = request.getParameter("hotelname");
		String status = request.getParameter("status");
		
		addObject.put("orderid", orderid);
		addObject.put("hotelid", hotelid);
		addObject.put("hotelname", hotelname);
		addObject.put("status", status);
		
		/** 封装分页对象 */
		String sPageNo = request.getParameter("pageNo");
		if(sPageNo == null || sPageNo.trim().length() == 0){
			sPageNo = "1";
		}
		Integer pageNo = Integer.parseInt(sPageNo);
		String sPageSize = request.getParameter("pageSize");
		if(sPageSize == null || sPageSize.trim().length() == 0){
			sPageSize = "10";
		}
		Integer pageSize = Integer.parseInt(sPageSize);
		addObject.put("pageNo", pageNo);
		
		int start = 0;
		if(pageNo != 1){
			start = (pageNo - 1) * pageSize + 1;
		}
		addObject.put("start", start);
		addObject.put("pageSize", pageSize);
		addObject.put("currentPage", true);
		
		return addObject;
	}

}