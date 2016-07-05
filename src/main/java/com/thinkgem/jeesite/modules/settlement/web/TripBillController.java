package com.thinkgem.jeesite.modules.settlement.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@RequestMapping(value = "${adminPath}/scadmin/tripbill")
public class TripBillController {
	
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 旅行社周账单列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list", ""})
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject addObject = packageDate(request);
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/caiwu/bills/list";
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
		modelAndView.setViewName("modules/settlement/tripList");
		return modelAndView;
	}
	
	@RequestMapping(value = {"settlement", ""})
	@ResponseBody
	public String settlement(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject addObject = new JSONObject();
		String ids = request.getParameter("ids");
		// System.out.println(ids);
		log.info("要结算的账单id列表:{}", ids);
		addObject.put("ids", ids);
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/caiwu/bills/settlement";
		String result = null;
		try {
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 调整明细数据之后， 账单需要重新结算 重新生成一条新的账单
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = {"resettlement", ""})
	@ResponseBody
	public String resettlement(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject addObject = new JSONObject();
		String id = request.getParameter("id");
		addObject.put("id", id);
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/caiwu/bills/resettlement";
		String result = null;
		try {
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 旅行社周账单导出功能(导出查询全部数据)
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
			String[] titile={"开始日期","结束日期","客户ID","客户名称","结算方式","付款方式","财务状态","结算金额","订单总额","折扣金额","调整金额","充值卡抵扣金额","账单状态",};
			addObject.put("length", titile.length);
			addObject.put("currentPage", false);
			
			String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
			String settlementConUrl = stlmt_server_url + "/sc/caiwu/bills/export1";
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
			String excelName = "旅行社周账单";
			ExcelExport.exprotBefore(request,response,excelName);
			Boolean[] isdigit={null,null,true,null,null,null,null,true,true,true,true,true,null};
			int[] columnWidth={35,28,28,30,35,28,35,45,55,55,55,55,55 };
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
		String accountid = request.getParameter("accountid");
		String hotelid = request.getParameter("hotelid");
		String sctype = request.getParameter("sctype");
		String accountrole = request.getParameter("accountrole");
		String scstate = request.getParameter("scstate");
		String accountname = request.getParameter("accountname");
		String paytype = request.getParameter("paytype");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date sbeginDate = null;
		Date sendDate = null;
		if(beginDate != null  && beginDate.trim().length() > 0){
			try {
				sbeginDate = sf.parse(beginDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endDate != null && endDate.trim().length() > 0){
			try {
				sendDate = sf.parse(endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		addObject.put("accountid", accountid);
		addObject.put("hotelid", hotelid);
		addObject.put("sctype", sctype);
		addObject.put("accountrole", accountrole);
		addObject.put("scstate", scstate);
		addObject.put("accountname", accountname);
		addObject.put("beginDate", beginDate);
		addObject.put("endDate", endDate);
		addObject.put("sbeginDate", sbeginDate);
		addObject.put("sendDate", sendDate);
		addObject.put("paytype", paytype);
		// 旅行社
		String biztype = 2+"";
		addObject.put("biztype", biztype);
		
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