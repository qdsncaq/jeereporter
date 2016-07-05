/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.channel.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fangbaba.basic.face.enums.HotelBusinessEnum;
import com.fangbaba.basic.face.enums.HotelBusinessStatusEnum;
import com.fangbaba.gds.enums.ChannelEnum;
import com.fangbaba.gds.enums.ChannelStateEnum;
import com.fangbaba.gds.face.service.IGdsChannelService;
import com.fangbaba.gds.face.service.IHotelChannelSettingService;
import com.thinkgem.gds.channel.dao.GdsChannelDao;
import com.thinkgem.gds.channel.entity.GdsChannel;
import com.thinkgem.gds.hotel.entity.Hotel;
import com.thinkgem.gds.hotel.service.HotelService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 渠道管理Service
 * @author tankai
 * @version 2016-03-18
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class GdsChannelService extends CrudService<GdsChannelDao, GdsChannel> {

	
	@Autowired
	private IGdsChannelService iGdsChannelService;
	@Autowired
	private HotelService hotelService;
	@Autowired
	private IHotelChannelSettingService hotelChannelSettingService;
	
	public GdsChannel get(String id) {
		return super.get(id);
	}
	
	public List<GdsChannel> findList(GdsChannel gdsChannel) {
		return super.findList(gdsChannel);
	}
	
	public Page<GdsChannel> findPage(Page<GdsChannel> page, GdsChannel gdsChannel) {
		return super.findPage(page, gdsChannel);
	}
	
	@Transactional(readOnly = false)
	public void save(GdsChannel gdsChannel) {
		super.save(gdsChannel);
	}
	@Transactional(readOnly = false)
	public void openOrClose(GdsChannel gdsChannel) {
		gdsChannel.setIsNewRecord(false);
		super.save(gdsChannel);

		//遍历已经开通分销的酒店，然后开通
		this.openOrCloseHotelChannel(Integer.valueOf(gdsChannel.getChannelid()),ChannelStateEnum.getById(Integer.valueOf(gdsChannel.getState())));
	}
	
	/**
	 * 初始化酒店
	 * @param channelId
	 */
	public void openOrCloseHotelChannel(Integer channelId,ChannelStateEnum channelStateEnum) {
		//分页查询开通分销酒店
		Hotel hotelquery = new Hotel();
		hotelquery.setSwitchOpen(HotelBusinessStatusEnum.BUSINESS_OK.getId()+"");
		List<Hotel> list = hotelService.findList(hotelquery);
		if(CollectionUtils.isNotEmpty(list)){
			logger.info("初始化分销渠道的酒店size：{},channelId={}",list.size(),channelId);
			StringBuilder sf = new StringBuilder();
			for (Hotel hotel : list) {
				//将酒店发给开通渠道分销mq
				sf.append(hotel.getId()).append(",");
				hotelChannelSettingService.openOrCloseSwitch(hotel.getId(), ChannelEnum.getById(channelId), channelStateEnum, "system");
			}
			logger.info("初始化分销渠道的酒店list：{}",sf.toString());
		}else{
			logger.info("初始化酒店为空");
		}
	}
	
	
	
	@Transactional(readOnly = false)
	public void delete(GdsChannel gdsChannel) {
		super.delete(gdsChannel);
	}
	
}