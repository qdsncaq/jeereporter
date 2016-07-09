package com.thinkgem.jeesite.modules.rpt.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.rpt.entity.ZinccoatingWorkTeamReport;
import com.thinkgem.jeesite.modules.rpt.service.ZinccoatingWorkTeamReportService;

/**
 * 锌层测厚班组报表controller.
 * 
 * @author aiqing.chu
 * @version 2016-07-07
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/rpt/zinccoatingWorkTeamReport")
public class ZinccoatingWorkTeamReportController extends BaseController {

	@Autowired
	private ZinccoatingWorkTeamReportService zinccoatingWorkTeamReportService;
	
	@ModelAttribute
	public ZinccoatingWorkTeamReport get(@RequestParam(required=false) String id) {
		ZinccoatingWorkTeamReport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zinccoatingWorkTeamReportService.get(id);
		}
		if (entity == null){
			entity = new ZinccoatingWorkTeamReport();
		}
		return entity;
	}
	
	/**
	 * 
	 * @param zinccoatingWorkTeamReport
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("rpt:zinccoatingWorkTeamReport:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZinccoatingWorkTeamReport zinccoatingWorkTeamReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZinccoatingWorkTeamReport> page = new Page<ZinccoatingWorkTeamReport>(request, response);
		page.setOrderBy(" logtime asc ");
		zinccoatingWorkTeamReport.setPage(page);
		List<ZinccoatingWorkTeamReport> list = zinccoatingWorkTeamReportService.queryZinccoatingworkteamReport(zinccoatingWorkTeamReport);
		page.setList(list);
		model.addAttribute("page", page);
		return "modules/rpt/zinccoatingWorkTeamReportList";
	}
	
}
