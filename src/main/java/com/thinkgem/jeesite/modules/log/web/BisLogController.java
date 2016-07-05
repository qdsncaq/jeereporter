/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.log.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lz.mongo.bislog.BisLogDelegate;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.log.entity.BisLog;
import com.thinkgem.jeesite.modules.log.service.BisLogService;

/**
 * 日志Controller
 *
 * @author 王智威
 * @version 2016-04-13
 */
@Controller
@RequestMapping(value = "${adminPath}/log/bisLog")
public class BisLogController extends BaseController {

	@Autowired
	private BisLogService bisLogService;
	@Autowired
	private BisLogDelegate bisLogDelegate;

	@ModelAttribute
	public com.lz.mongo.bislog.BisLog get(@RequestParam(required = false) String id) {
		// com.lz.mongo.bislog.BisLog entity = null;
		// if (org.apache.commons.lang3.StringUtils.isNotBlank(id)) {
		// entity = this.bisLogService.get(id);
		// }
		// if (entity == null) {
		// entity = new com.lz.mongo.bislog.BisLog();
		// }
		return new com.lz.mongo.bislog.BisLog();
	}

	@RequiresPermissions("log:bisLog:view")
	@RequestMapping(value = { "list", "" })
	public String list(com.lz.mongo.bislog.BisLog bisLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		// Page<BisLog> page = this.bisLogService.findPage(new
		// Page<BisLog>(request, response), bisLog);
		Page<com.lz.mongo.bislog.BisLog> page = new Page<com.lz.mongo.bislog.BisLog>(request, response);
		page.setList(this.queryBisLog(bisLog, page));
		model.addAttribute("page", page);
		return "modules/log/bisLogList";
	}

	@RequiresPermissions("log:bisLog:view")
	@RequestMapping(value = "form")
	public String form(BisLog bisLog, Model model) {
		model.addAttribute("bisLog", bisLog);
		return "modules/log/bisLogForm";
	}

	@RequiresPermissions("log:bisLog:edit")
	@RequestMapping(value = "save")
	public String save(BisLog bisLog, Model model, RedirectAttributes redirectAttributes) {
		if (!this.beanValidator(model, bisLog)) {
			return this.form(bisLog, model);
		}
		this.bisLogService.save(bisLog);
		this.addMessage(redirectAttributes, "保存日志成功");
		return "redirect:" + Global.getAdminPath() + "/log/bisLog/?repage";
	}

	@RequiresPermissions("log:bisLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BisLog bisLog, RedirectAttributes redirectAttributes) {
		this.bisLogService.delete(bisLog);
		this.addMessage(redirectAttributes, "删除日志成功");
		return "redirect:" + Global.getAdminPath() + "/log/bisLog/?repage";
	}

	/**
	 * 从mogo中查询数据，
	 *
	 * @param bisLog
	 * @return
	 */
	private List<com.lz.mongo.bislog.BisLog> queryBisLog(com.lz.mongo.bislog.BisLog bisLog, Page page) {
		List<BasicDBObject> dl = new ArrayList<BasicDBObject>();
		// 业务ID
		if (!StringUtils.isEmpty(bisLog.getBussinessId())) {
			dl.add(new BasicDBObject(com.lz.mongo.bislog.BisLog.CN_BUSSINESSID, bisLog.getBussinessId()));
		}
		// 系统
		if (!StringUtils.isEmpty(bisLog.getSystem()) && !bisLog.getSystem().equals("null")) {
			dl.add(new BasicDBObject(com.lz.mongo.bislog.BisLog.CN_SYSTEM, bisLog.getSystem()));
		}
		// 业务类型
		if (!StringUtils.isEmpty(bisLog.getBussinssType()) && !bisLog.getBussinssType().equals("null")) {
			dl.add(new BasicDBObject(com.lz.mongo.bislog.BisLog.CN_BUSSINESSTYPE, bisLog.getBussinssType()));
		}
		// 内容
		if (!StringUtils.isEmpty(bisLog.getContent())) {
			dl.add(new BasicDBObject(com.lz.mongo.bislog.BisLog.CN_CONTENT, bisLog.getContent()));
		}
		// 操作人
		if (!StringUtils.isEmpty(bisLog.getOperator())) {
			dl.add(new BasicDBObject(com.lz.mongo.bislog.BisLog.CN_OPERATOR, bisLog.getOperator()));
		}
		BasicDBObject[] basicDBObjects = dl.toArray(new BasicDBObject[dl.size()]);
		BasicDBObject basicDBObject = new BasicDBObject();
		if (basicDBObjects.length != 0) {
			basicDBObject = basicDBObject.append(QueryOperators.AND, basicDBObjects);
		} else {
			return new ArrayList<com.lz.mongo.bislog.BisLog>();
		}
		Bson sort = new Document(com.lz.mongo.bislog.BisLog.CN_CREATETIME, -1);
		List<com.lz.mongo.bislog.BisLog> list = this.bisLogDelegate.find(basicDBObject, sort, 0, 100);
		return list;
	}
}