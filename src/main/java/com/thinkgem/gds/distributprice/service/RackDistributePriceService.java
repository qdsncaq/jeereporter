/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.distributprice.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.distributprice.entity.RackDistributePrice;
import com.thinkgem.gds.distributprice.dao.RackDistributePriceDao;

/**
 * 酒店分销价Service
 * @author zhaochuanbin
 * @version 2016-03-23
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class RackDistributePriceService extends CrudService<RackDistributePriceDao, RackDistributePrice> {

    @Autowired
    private RackDistributePriceDao rackDistributePriceDao;
    
	public RackDistributePrice get(String id) {
		return super.get(id);
	}
	
	
	  public RackDistributePrice getbyhotelandroomtype(Long hotelid,Long roomtypeid) {
        return rackDistributePriceDao.getbyhotelandroomtype( hotelid, roomtypeid);
    }
	
	public List<RackDistributePrice> findList(RackDistributePrice rackDistributePrice) {
		return super.findList(rackDistributePrice);
	}
	
	public Page<RackDistributePrice> findPage(Page<RackDistributePrice> page, RackDistributePrice rackDistributePrice) {
		return super.findPage(page, rackDistributePrice);
	}
	
	@Transactional(readOnly = false)
	public void save(RackDistributePrice rackDistributePrice) {
	    if(rackDistributePrice.getIsNewRecord()){
	        rackDistributePrice.setCreatetime(new Date());
	    }else{
	        rackDistributePrice.setUpdatetime(new Date());
	        rackDistributePrice.setUpdateuser(rackDistributePrice.getCreateuser());
	    }
		super.save(rackDistributePrice);
	}
	
	@Transactional(readOnly = false)
	public void delete(RackDistributePrice rackDistributePrice) {
		super.delete(rackDistributePrice);
	}
	
}