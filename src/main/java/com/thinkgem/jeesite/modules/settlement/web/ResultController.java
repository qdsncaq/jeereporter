package com.thinkgem.jeesite.modules.settlement.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.settlement.common.ExcelExport;
import com.thinkgem.jeesite.modules.settlement.common.NetUtil;
import com.thinkgem.jeesite.modules.settlement.common.UrlUtils;

@Controller
@RequestMapping(value = "${adminPath}/sc/result")
public class ResultController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@RequiresPermissions("sc:result:view")
	@RequestMapping(value = {"list", ""})
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject addObject = new JSONObject();
		
		/** 封装数据对象 */
		String id = request.getParameter("id");
		String settlementTargetName = request.getParameter("settlementTargetName");
		String settlementStatus = request.getParameter("settlementStatus");
		String settlementCategory = request.getParameter("settlementCategory");
		String saleType = request.getParameter("saleType");
		
		addObject.put("id", id);
		addObject.put("settlementTargetName", settlementTargetName);
		addObject.put("settlementStatus", settlementStatus);
		addObject.put("settlementCategory", settlementCategory);
		addObject.put("saleType", saleType);
		
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
		//当前页
		addObject.put("pageNo", pageNo);
		//添加开始行
		int start = 0;
		if(pageNo != 1){
			start = (pageNo - 1) * pageSize + 1;
		}
		addObject.put("start", start);
		//每页显示行数
		addObject.put("pageSize", pageSize);
		//是否分页
		addObject.put("currentPage", true);
		
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
        String settlementConUrl = stlmt_server_url + "/sc/rpt/settlement/result";
		String result = null;
		try {
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toJSONString());
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
		modelAndView.setViewName("modules/settlement/resultList");
		
		modelAndView.addObject("params", addObject);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "export")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response){

		JSONObject addObject = new JSONObject();
		/** 封装数据对象 */
		String settlementTargetName = request.getParameter("settlementTargetName");
		String settlementStatus = request.getParameter("settlementStatus");
		String settlementCategory = request.getParameter("settlementCategory");
		String saleType = request.getParameter("saleType");
		
		addObject.put("settlementTargetName", settlementTargetName);
		addObject.put("settlementStatus", settlementStatus);
		addObject.put("settlementCategory", settlementCategory);
		addObject.put("saleType", saleType);
		
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
		//当前页
		addObject.put("pageNo", pageNo);
		//添加开始行
		int start = 0;
		if(pageNo != 1){
			start = (pageNo - 1) * pageSize + 1;
		}
		addObject.put("start", start);
		//每页显示行数
		addObject.put("pageSize", pageSize);
		//是否分页
		addObject.put("currentPage", false);
		
		/**  */
		OutputStream fOut = null;
		try {
			String[] titile={"结算方名称","费用类型","结算总金额","折扣后总金额","状态","结算类型","收/支","销售类型","起始日期","结账日期","创建时间",};
			addObject.put("length", titile.length);
			
			String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
			String settlementConUrl = stlmt_server_url + "/sc/rpt/settlement/export1";
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
			String excelName = "结算数据导出";
			ExcelExport.exprotBefore(request,response,excelName);
			Boolean[] isdigit={null,null,true,true,null,null,null,null,null,null,null,null};
			int[] columnWidth={35,28,28,30,35,28,35,45,55,55,55 };
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
	
//	@RequiresPermissions("sc:result:exports")
	@RequestMapping(value = {"resettlement", ""})
	public String resettlement(HttpServletRequest request, HttpServletResponse response, Model model){
		JSONObject addObject = new JSONObject();
		/** 封装数据对象 */
		String id = request.getParameter("id");
		String settlementBeginTime = request.getParameter("sbt");
		String settlementEndTime = request.getParameter("set");
		String settlementCreateTime = request.getParameter("ct");
		addObject.put("id", id);
		addObject.put("settlementBeginTime", settlementBeginTime);
		addObject.put("settlementEndTime", settlementEndTime);
		addObject.put("createTime", settlementCreateTime);
		
		/** POST请求 */
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/rpt/settlement/reSettlement";
		String result = null;
		try {
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject message = JSONObject.parseObject(result);

//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("modules/settlement/resultList");
//		modelAndView.addObject("message", message);
//		return modelAndView;
		return "modules/settlement/resultList";
		
	}
	
	@RequestMapping(value = "settlement")
	@ResponseBody
	public int settlement(HttpServletRequest request ){
		JSONObject addObject = new JSONObject();
		//接收参数
		String id = request.getParameter("id");
		log.info("统一后台结算账单接收参数：" + id);
		addObject.put("ids", id);
		//http请求settlement系统
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/rpt/settlement/statements";
		log.info("统一后台结算账单请求结算系统，路径：" + settlementConUrl);
		String result = null;
		try {
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toJSONString());
		} catch (Exception e) {
			log.info("统一后台结算账单请求结算系统异常。请求路径为：" + settlementConUrl);
			e.printStackTrace();
		}
		log.info("统一后台结算账单请求结算系统，返回参数：" + result);
		JSONObject rs = (JSONObject) JSONObject.parse(result);
		Integer data = (Integer) rs.get("result");
		return data;
	}
	
}