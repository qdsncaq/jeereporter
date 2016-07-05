package com.thinkgem.jeesite.modules.settlement.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.thinkgem.jeesite.modules.settlement.common.UrlUtils;

@Controller
@RequestMapping(value = "${adminPath}/sc/order")
public class OrderListController {
	
	@RequiresPermissions("sc:order:ruletest")
	@RequestMapping(value = {"ruletest", ""})
	public ModelAndView ruleTest(HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("modules/settlement/ruletest");
		return modelAndView;
	}
	
	@RequiresPermissions("sc:order:view")
	@RequestMapping(value = {"list", ""})
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject addObject = new JSONObject();
		/** 封装数据 */
		String sorderId = request.getParameter("orderId");
		String settlementTargetName = request.getParameter("settlementTargetName");
		String settlementCategory = request.getParameter("settlementCategory");
		String sbeginDate = request.getParameter("beginDate");
		String sendDate = request.getParameter("endDate");
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;
		if(sbeginDate != null  && sbeginDate.trim().length() > 0){
			try {
				beginDate = sf.parse(sbeginDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(sendDate != null && sendDate.trim().length() > 0){
			try {
				endDate = sf.parse(sendDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		addObject.put("orderId", sorderId);
		addObject.put("settlementTargetName", settlementTargetName);
		addObject.put("settlementCategory", settlementCategory);
		
		addObject.put("beginTime", sbeginDate);
		addObject.put("endTime", sendDate);
		addObject.put("beginDate", beginDate);
		addObject.put("endDate", endDate);
		
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
		
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/rpt/order/statistica";
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
		
		modelAndView.addObject("params", addObject);
		modelAndView.setViewName("modules/settlement/orderList");
		return modelAndView;
	}

	@RequestMapping(value = "save")
	public ModelAndView save(HttpServletRequest request) {
		JSONObject addObject = new JSONObject();
		String id = request.getParameter("id");
		String orderId = request.getParameter("orderId");
		String settlementSum = request.getParameter("changeSettlementSum");
		addObject.put("orderId", orderId);
		addObject.put("id", id);
		addObject.put("settlementSum", settlementSum);
		
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/rpt/order/editSettlementSum";
		String result = null;
		try {
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//{"result":1}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(result);
		modelAndView.setViewName("modules/settlement/orderList");
		return modelAndView;
	}
	
}