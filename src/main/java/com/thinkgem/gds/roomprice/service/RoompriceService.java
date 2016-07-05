/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomprice.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.roomprice.entity.Roomprice;
import com.thinkgem.gds.roomprice.dao.RoompriceDao;

/**
 * 房型价格Service
 * @author jiajianhong
 * @version 2016-04-01
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class RoompriceService extends CrudService<RoompriceDao, Roomprice> {

	public Roomprice get(String id) {
		return super.get(id);
	}
	
	public List<Roomprice> findList(Roomprice roomprice) {
		return super.findList(roomprice);
	}
	
	public Page<Roomprice> findPage(Page<Roomprice> page, Roomprice roomprice) {
		return super.findPage(page, roomprice);
	}
	
	@Transactional(readOnly = false)
	public void save(Roomprice roomprice) {
		super.save(roomprice);
	}
	
	@Transactional(readOnly = false)
	public void delete(Roomprice roomprice) {
		super.delete(roomprice);
	}
	
}