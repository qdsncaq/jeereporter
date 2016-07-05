/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * CRM酒店信息Entity
 * 
 * @author 李雪楠
 * @version 2016-03-28
 */
public class TRoomType extends DataEntity<TRoomType> {
	private static final long serialVersionUID = 1L;
	private String hotelid;
	private String name;// 房型name
	private String pms;
	private String roomtypeid;
	private String pics;// 图片
	private String bedtype;// 床类型
	private String bedsize;// 床尺寸
	private String maxarea;// 床尺寸
	private String pic;// 格式化之后的图片
	private String toilet;// 格式化之后的图片
	private String def;// 格式化之后的图片
	private String bed;// 格式化之后的图片

	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPms() {
		return pms;
	}

	public void setPms(String pms) {
		this.pms = pms;
	}

	public String getRoomtypeid() {
		return roomtypeid;
	}

	public void setRoomtypeid(String roomtypeid) {
		this.roomtypeid = roomtypeid;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		if (StringUtils.isNotBlank(pics)) {
            try {// 要进行解析json
                JSONArray jsonArray = JSONArray.parseArray(pics);

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.getString("name").equalsIgnoreCase("def")) {
                        this.def = jsonObject.getJSONArray("pic").getJSONObject(0).getString("url");
                    }
                    
                    if (jsonObject.getString("name").equalsIgnoreCase("toilet")) {
                        this.toilet = jsonObject.getJSONArray("pic").getJSONObject(0).getString("url");
                    }
                    
                    if (jsonObject.getString("name").equalsIgnoreCase("bed")) {
                        this.bed = jsonObject.getJSONArray("pic").getJSONObject(0).getString("url");
                    }
                }
            } catch (Exception e) {

            }
        }
		this.pics = pics;
	}

	public String getBedtype() {
		return bedtype;
	}

	public void setBedtype(String bedtype) {
		this.bedtype = bedtype;
	}

	public String getBedsize() {
		return bedsize;
	}

	public void setBedsize(String bedsize) {
		this.bedsize = bedsize;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getMaxarea() {
		return maxarea;
	}

	public void setMaxarea(String maxarea) {
		this.maxarea = maxarea;
	}

	public String getToilet() {
		return toilet;
	}

	public void setToilet(String toilet) {
		this.toilet = toilet;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

}