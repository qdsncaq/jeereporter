/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.contract.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.contract.entity.HotelContract;
import com.thinkgem.gds.contract.dao.HotelContractDao;

/**
 * 合同Service
 * @author zhaochuanbin
 * @version 2016-03-15
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class HotelContractService extends CrudService<HotelContractDao, HotelContract> {

	public HotelContract get(String id) {
		return super.get(id);
	}
	
	public List<HotelContract> findList(HotelContract hotelContract) {
		return super.findList(hotelContract);
	}
	
	public Page<HotelContract> findPage(Page<HotelContract> page, HotelContract hotelContract) {
		return super.findPage(page, hotelContract);
	}
	
	@Transactional(readOnly = false)
	public void save(HotelContract hotelContract) {
		super.save(hotelContract);
	}
	
	@Transactional(readOnly = false)
	public void delete(HotelContract hotelContract) {
		super.delete(hotelContract);
	}
	
}