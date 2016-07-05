/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.hotel.entity.EHotel;
import com.thinkgem.crm.hotel.entity.TRoomType;
import com.thinkgem.crm.hotel.dao.EHotelDao;

/**
 * CRM酒店信息Service
 * @author 李雪楠
 * @version 2016-03-28
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class EHotelService extends CrudService<EHotelDao, EHotel> {

	public EHotel get(String id) {
		return super.get(id);
	}
	
	public List<EHotel> findList(EHotel eHotel) {
		return super.findList(eHotel);
	}
	
	public Page<EHotel> findPage(Page<EHotel> page, EHotel eHotel) {
		return super.findPage(page, eHotel);
	}
	
	@Transactional(readOnly = false)
	public void save(EHotel eHotel) {
		super.save(eHotel);
	}
	
	@Transactional(readOnly = false)
	public void delete(EHotel eHotel) {
		super.delete(eHotel);
	}

    public Page<EHotel> findSelfBuildHotelPage(Page<EHotel> eHotelPage, EHotel eHotel) {
    	eHotelPage.setOrderBy(" a.id desc");
        eHotel.setPage(eHotelPage);
        eHotelPage.setList(dao.findSelfBuildHotelPage(eHotel));
        return eHotelPage;
    }

	public List<EHotel> findSelfBuildHotelList(EHotel eHotel) {
		List<EHotel> list = dao.findSelfBuildHotelPage(eHotel);
		return list;
	}

    public EHotel getSelfBuildHotel(EHotel eHotel) {
        return dao.getSelfBuildHotel(eHotel);
    }
    public EHotel getCheckHotel(EHotel eHotel) {
        return dao.getCheckHotel(eHotel);
    }
    public List<TRoomType> findTHotelTypeByHotelId(TRoomType tHotelType) {
    	return dao.findTHotelTypeByHotelId(tHotelType);
    }
}