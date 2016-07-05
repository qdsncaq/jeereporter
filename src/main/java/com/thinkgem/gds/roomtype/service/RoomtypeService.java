/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomtype.service;

import java.util.List;

import com.thinkgem.gds.roomtype.entity.RoomtypePrices;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.roomtype.entity.Roomtype;
import com.thinkgem.gds.roomtype.dao.RoomtypeDao;

/**
 * 房型Service
 * @author jiajianhong
 * @version 2016-05-24
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class RoomtypeService extends CrudService<RoomtypeDao, Roomtype> {

	public Roomtype get(String id) {
		return super.get(id);
	}
	
	public List<Roomtype> findList(Roomtype roomtype) {
		return super.findList(roomtype);
	}
	
	public Page<Roomtype> findPage(Page<Roomtype> page, Roomtype roomtype) {
		return super.findPage(page, roomtype);
	}
	
	@Transactional(readOnly = false)
	public void save(Roomtype roomtype) {
		super.save(roomtype);
	}
	
	@Transactional(readOnly = false)
	public void delete(Roomtype roomtype) {
		super.delete(roomtype);
	}

    /**
     * 查询房型特殊价
     * @param roomtype
     * @return
     */
    public List<RoomtypePrices> findDailyRate(Roomtype roomtype) {
        return dao.findDailyRate(roomtype);
    }

    /**
     * 查询房型门市价
     * @param roomtype
     * @return
     */
    public List<RoomtypePrices> findRackRate(Roomtype roomtype) {
        return dao.findRackRate(roomtype);
    }

    /**
     * 查询房型周末价
     * @param roomtype
     * @return
     */
    public List<RoomtypePrices> findWeekRate(Roomtype roomtype) {
        return dao.findWeekRate(roomtype);
    }
}