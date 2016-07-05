/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.settlementbaseinterest.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.settlement.common.NetUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.settlement.common.UrlUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.sc.settlementbaseinterest.entity.SettlementBaseInterest;
import com.thinkgem.sc.settlementbaseinterest.service.SettlementBaseInterestService;

/**
 * SettlementBaseInterestController
 * @author ft
 * @version 2016-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/settlementbaseinterest/settlementBaseInterest")
public class SettlementBaseInterestController extends BaseController {

	@Autowired
	private SettlementBaseInterestService settlementBaseInterestService;
	
	@ModelAttribute
	public SettlementBaseInterest get(@RequestParam(required=false) String id) {
		SettlementBaseInterest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = settlementBaseInterestService.get(id);
		}
		if (entity == null){
			entity = new SettlementBaseInterest();
		}
		return entity;
	}
	
	@RequiresPermissions("settlementbaseinterest:settlementBaseInterest:view")
	@RequestMapping(value = {"list", ""})
	public String list(SettlementBaseInterest settlementBaseInterest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SettlementBaseInterest> page = settlementBaseInterestService.findPage(new Page<SettlementBaseInterest>(request, response), settlementBaseInterest); 
		model.addAttribute("page", page);
		return "sc/settlementbaseinterest/settlementBaseInterestList";
	}

	@RequiresPermissions("settlementbaseinterest:settlementBaseInterest:view")
	@RequestMapping(value = "form")
	public String form(SettlementBaseInterest settlementBaseInterest, Model model) {
		model.addAttribute("settlementBaseInterest", settlementBaseInterest);
		return "sc/settlementbaseinterest/settlementBaseInterestForm";
	}

	@RequiresPermissions("settlementbaseinterest:settlementBaseInterest:edit")
	@RequestMapping(value = "save")
	public String save(SettlementBaseInterest settlementBaseInterest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, settlementBaseInterest)){
			return form(settlementBaseInterest, model);
		}
		//根据hotelid 去PMS  查 hotelName hotelPms
		if(settlementBaseInterest!=null  //只是添加的
				&&(settlementBaseInterest.getId()==null || settlementBaseInterest.getId().trim().length()==0) ){
			String hotelid=settlementBaseInterest.getHotelid();
			if(hotelid==null || hotelid.trim().length()==0){
				//hotelid为必填
				addMessage(redirectAttributes, "酒店ID不能为空.");
				model.addAttribute("settlementBaseInterest", settlementBaseInterest);
				return "redirect:"+Global.getAdminPath()+"/settlementbaseinterest/settlementBaseInterest/form";
			
			}else{
				SettlementBaseInterest sbi=new SettlementBaseInterest();
				sbi.setHotelid(hotelid);
				if(settlementBaseInterestService.findList(sbi).size()>0){
					addMessage(redirectAttributes, "酒店已经存在不能重复添加.");
					model.addAttribute("settlementBaseInterest", settlementBaseInterest);
					return "redirect:"+Global.getAdminPath()+"/settlementbaseinterest/settlementBaseInterest/form";
				}else{
				JSONObject addObject = new JSONObject();
				addObject.put("hotelId", hotelid);
				String crmUrl = UrlUtils.getUrl("crm.url") + "/getSellerAndBossInfoByRealHotelId/" + hotelid;
				JSONObject resultObject = null;
				try {
					String result = doPostJson(crmUrl, addObject.toJSONString());
					resultObject = JSONObject.parseObject(result);
				} catch (Exception e) {
					System.out.println(e);
				}
				if(resultObject==null){
					addMessage(redirectAttributes, "通过酒店ID未能查询到酒店基础信息异常.");
					return "redirect:"+Global.getAdminPath()+"/settlementbaseinterest/settlementBaseInterest/form";
				}
				if ("OK".equals(resultObject.getString("_MSG_"))) {
					JSONObject data = new JSONObject();
					data = resultObject.getJSONObject("_DATA_");
					String hotelpms = data.getString("hotelPms");
					String hotelname = data.getString("hotelName");
					settlementBaseInterest.setHotelname(hotelname);
					settlementBaseInterest.setHotelpms(hotelpms);
				}else{
					addMessage(redirectAttributes, "添加失败！通过酒店ID未能查询到酒店基础信息.");
					model.addAttribute("settlementBaseInterest", settlementBaseInterest);
					return "redirect:"+Global.getAdminPath()+"/settlementbaseinterest/settlementBaseInterest/form";
				}
			  }
			}
		}
		settlementBaseInterestService.save(settlementBaseInterest);
		addMessage(redirectAttributes, "保存SettlementBaseInterest成功");
		return "redirect:"+Global.getAdminPath()+"/settlementbaseinterest/settlementBaseInterest/?repage";
	}
	
	
	
    private String doPostJson(String url, String json)throws Exception {
        JSONObject back = new JSONObject();
        try {
            String jsonBack = "{}";
            if (url.toLowerCase().startsWith("https")) {
                jsonBack = NetUtil.httpsPostJson(url, json);
            } else if (url.toLowerCase().startsWith("http")) {
                jsonBack = NetUtil.httpPostjson(url, json);
            }
            return jsonBack;
        } catch (Exception e) {
            e.printStackTrace();
            back.put("success", false);
            if (e instanceof HTTPException) {
                back.put("errorcode", "101");
                back.put("errormsg", "http请求异常：错误" + ((HTTPException) e).getStatusCode());
            } else {
                back.put("errorcode", "-1");
                back.put("errormsg", e.getLocalizedMessage());
            }
        }
        return back.toJSONString();
    }
	
	@RequiresPermissions("settlementbaseinterest:settlementBaseInterest:edit")
	@RequestMapping(value = "delete")
	public String delete(SettlementBaseInterest settlementBaseInterest, RedirectAttributes redirectAttributes) {
		settlementBaseInterestService.delete(settlementBaseInterest);
		addMessage(redirectAttributes, "删除SettlementBaseInterest成功");
		return "redirect:"+Global.getAdminPath()+"/settlementbaseinterest/settlementBaseInterest/?repage";
	}

}