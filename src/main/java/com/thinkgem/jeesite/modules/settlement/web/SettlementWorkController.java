package com.thinkgem.jeesite.modules.settlement.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.settlement.common.NetUtil;

@Controller
@RequestMapping(value = "${adminPath}/sc/work")
public class SettlementWorkController {
	
	@RequiresPermissions("sc:work:view")
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
		
		String settlementConUrl = "http://localhost:8081/sc/rpt/settlement/result";
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
	
	@RequiresPermissions("sc:result:exports")
	@RequestMapping(value = {"export", ""})
	public ModelAndView exportExcel(HttpServletRequest request, HttpServletResponse response, Model model){
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
		
		String settlementConUrl = "http://localhost:8081/sc/rpt/settlement/export";
		String result = null;
		try {
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject message = JSONObject.parseObject(result);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("modules/settlement/workList");
		
		modelAndView.addObject("params", addObject);
		
		return modelAndView;
	}
	
}