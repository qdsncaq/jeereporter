package com.thinkgem.jeesite.modules.settlement.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.settlement.common.NetUtil;
import com.thinkgem.jeesite.modules.settlement.common.UrlUtils;

@Controller
@RequestMapping(value = "${adminPath}/scadmin/washdetail")
public class WashDetailBillController {
	
	@RequestMapping(value = {"list", ""})
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Model model) {
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
		
		String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
		String settlementConUrl = stlmt_server_url + "/sc/caiwu/details";
		String result = null;
		try {
//			result = NetUtil.httpPostjson(settlementConUrl, addObject.toString());
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
		modelAndView.setViewName("modules/settlement/washDetailList");
		return modelAndView;
	}
	
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
//			result = NetUtil.httpPostjson(settlementConUrl, addObject.toString());
			result = NetUtil.httpPostParameters(settlementConUrl, addObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(result);
	}
}