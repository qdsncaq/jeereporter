/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.datacenter.service;

import java.math.BigDecimal;
import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.gds.roomprice.entity.Roomprice;
import com.thinkgem.gds.roomprice.service.RoompriceService;
import com.thinkgem.jeesite.common.utils.Collections3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.gds.datacenter.dao.OtaOrderdetailDao;
import com.thinkgem.gds.datacenter.entity.OtaOrderdetail;

/**
 * 订单详情Service
 * @author LYN
 * @version 2016-03-29
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class OtaOrderdetailService extends CrudService<OtaOrderdetailDao, OtaOrderdetail> {

    @Autowired
    private RoompriceService roompriceService;

	public OtaOrderdetail get(String id) {
		return super.get(id);
	}
	
	public List<OtaOrderdetail> findList(OtaOrderdetail otaOrderdetail) {

        List<OtaOrderdetail> resultList = super.findList(otaOrderdetail);

        if (!Collections3.isEmpty(resultList)) {
            for (OtaOrderdetail otaOrderdetail1 : resultList) {
                Roomprice queryRoomPrice = new Roomprice();
                queryRoomPrice.setOrderid(otaOrderdetail1.getOrderid());
                queryRoomPrice.setOrderdetailid(Long.parseLong(otaOrderdetail1.getId()));
                List<Roomprice> roompriceList = roompriceService.findList(queryRoomPrice);

                if (!Collections3.isEmpty(roompriceList)) {
                    for (Roomprice roomprice : roompriceList) {
                        if (roomprice.getType().equalsIgnoreCase("1")) {
                            otaOrderdetail1.setTotalOTAPrice(otaOrderdetail1.getTotalOTAPrice()
                                    .add(roomprice.getPrice().multiply(new BigDecimal(otaOrderdetail1.getBooknum()))));
                        } else {
                            otaOrderdetail1.setTotalClearingPrice(otaOrderdetail1.getTotalClearingPrice()
                                    .add(roomprice.getPrice().multiply(new BigDecimal(otaOrderdetail1.getBooknum()))));
                        }
                    }
                }

                otaOrderdetail1.setRoompriceList(roompriceService.findList(queryRoomPrice));
            }
        }

		return super.findList(otaOrderdetail);
	}
	
	public Page<OtaOrderdetail> findPage(Page<OtaOrderdetail> page, OtaOrderdetail otaOrderdetail) {
		return super.findPage(page, otaOrderdetail);
	}
	
	@Transactional(readOnly = false)
	public void save(OtaOrderdetail otaOrderdetail) {
		super.save(otaOrderdetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(OtaOrderdetail otaOrderdetail) {
		super.delete(otaOrderdetail);
	}
	
}