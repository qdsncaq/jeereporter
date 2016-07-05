package com.thinkgem.gds.order.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fangbaba.datacenter.bean.QunarOrderLog;
import com.fangbaba.datacenter.service.DatacenterService;
import com.thinkgem.gds.order.entity.QunarOrderLogParam;
import com.thinkgem.gds.util.DateUtils;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/qunar/orderlog")
public class QunarOrderLogController extends BaseController {
	
	@Autowired
	DatacenterService datacenterService;

	@RequestMapping(value = {"list", ""})
	public String list(QunarOrderLogParam orders, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (request.getParameter("pageNo") == null) {
			List<QunarOrderLog> logs = new ArrayList<QunarOrderLog>();
			Page<QunarOrderLog> page = new Page<QunarOrderLog>(request, response);
			page.setPageNo(1);
			page.setPageSize(100);
			page.setCount(0);
			page.setList(logs);
			model.addAttribute("page", page);
			model.addAttribute("orders", orders);
			return "gds/qunar/orderloglist";
		}
		int pageNum = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		JSONObject j = new JSONObject();
		String[] keys = "ordertype,qunarStatus".split(",");
		for (String k : keys) {
			if (StringUtils.isNoneBlank(request.getParameter(k))) {
				j.put(k, Integer.parseInt(request.getParameter(k)));
			}
		}
		if (StringUtils.isNoneBlank(request.getParameter("hotelid"))) {
			j.put("hotelid", Long.parseLong(request.getParameter("hotelid")));
		}
		if (StringUtils.isNoneBlank(request.getParameter("orderno"))) {
			j.put("orderno", request.getParameter("orderno"));
		}
		if (StringUtils.isNoneBlank(request.getParameter("hotelname"))) {
			JSONObject condition = new JSONObject();
			condition.put("$regex", request.getParameter("hotelname"));
			j.put("hotelname", condition);
		}
		if (StringUtils.isNoneBlank(request.getParameter("createtime1")) || StringUtils.isNoneBlank(request.getParameter("createtime2"))) {
			JSONObject condition = new JSONObject();
			if (StringUtils.isNoneBlank(request.getParameter("createtime1"))) {
				JSONObject d = new JSONObject();
				d.put("$date", DateUtils.getDateFromString(request.getParameter("createtime1")));
				condition.put("$gte", d);
			}
			
			if (StringUtils.isNoneBlank(request.getParameter("createtime2"))) {
				JSONObject d = new JSONObject();
				d.put("$date", DateUtils.getDateFromString(request.getParameter("createtime2")));
				condition.put("$lt", d);
			}
			j.put("createtime", condition);
		}
		
		String data = datacenterService.getQunarOrderLog(j.toJSONString(), pageNum, pageSize);
		long count = datacenterService.countQunarOrderLog(j.toJSONString());
		List<QunarOrderLog> logs = JSONArray.parseArray(data, QunarOrderLog.class);
		
		Page<QunarOrderLog> page = new Page<QunarOrderLog>(request, response);
		page.setPageNo(pageNum);
		page.setPageSize(pageSize);
		page.setCount(count);
		page.setList(logs);
		model.addAttribute("page", page);
		model.addAttribute("orders", orders);
		return "gds/qunar/orderloglist";
	}

}
