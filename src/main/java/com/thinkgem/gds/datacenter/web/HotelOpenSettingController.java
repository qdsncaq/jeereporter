/*
package com.thinkgem.gds.datacenter.web;

import com.fangbaba.gds.po.HotelOpenSetting;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.gds.web.hotel.service.HotelOpensettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * Created by nolan on 16/4/18.
 * description:
 *//*

@Controller
@RequestMapping("hotel/opensetting")
public class HotelOpenSettingController extends BaseController {

    @Autowired
    private HotelOpensettingService hotelOpenSettingService;

    @ModelAttribute("hotelOpenSetting")
    public HotelOpenSetting get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return hotelOpenSettingService.get(id);
        } else {
            return new HotelOpenSetting();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(HotelOpenSetting hotelOpenSetting, String hotelid, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (hotelOpenSetting.getHotelid() == null) {
            throw new ServiceException("缺少必填参数: hotelid");
        }
        Page<HotelOpenSetting> page = new Page<HotelOpenSetting>(request, response);
        page = hotelOpenSettingService.findPage(page, hotelOpenSetting);

        model.addAttribute("page", page);
        model.addAttribute("hotelOpenSetting", hotelOpenSetting);
        return "modules/gds/opensetting/list";
    }

    @RequestMapping("form")
    public String form(HotelOpenSetting hotelOpenSetting, Model model) {
        if (hotelOpenSetting.getId() != null) {
            hotelOpenSetting = hotelOpenSettingService.get(String.valueOf(hotelOpenSetting.getId()));
        }
        if (hotelOpenSetting.getHotelid() == null) {
            throw new ServiceException("缺少必填参数: hotelid");
        }

        model.addAttribute("hotelOpenSetting", hotelOpenSetting);
        return "modules/gds/opensetting/form";
    }

    @RequestMapping("save")
    public String save(HotelOpenSetting hotelOpenSetting, Model model, RedirectAttributes redirectAttributes) {
        if (hotelOpenSetting.getHotelid() == null) {
            throw new ServiceException("缺少必填参数: hotelid");
        }
        hotelOpenSettingService.save(hotelOpenSetting);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/hotel/opensetting/?repage";
    }

    @RequestMapping("delete")
    public String delete(HotelOpenSetting hotelOpenSetting, RedirectAttributes redirectAttributes) {
        hotelOpenSettingService.delete(hotelOpenSetting);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/hotel/opensetting/?repage";
    }
}
*/
