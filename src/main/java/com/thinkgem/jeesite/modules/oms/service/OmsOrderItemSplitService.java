/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oms.dao.OmsOrderItemSplitDao;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticImpObj;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderItemSplit;

/**
 * 物流单号Service
 * @author oms
 * @version 2016-05-20
 */
@Service
@DataSourceName("oms")
@Transactional(readOnly = true)
public class OmsOrderItemSplitService extends CrudService<OmsOrderItemSplitDao, OmsOrderItemSplit> {

    @Autowired
    private OmsOrderItemSplitDao omsOrderItemSplitDao;	
	
	public OmsOrderItemSplit get(String id) {
		return super.get(id);
	}
	
	public List<OmsOrderItemSplit> findList(OmsOrderItemSplit omsOrderItemSplit) {
		return super.findList(omsOrderItemSplit);
	}
	
	public Page<OmsOrderItemSplit> findPage(Page<OmsOrderItemSplit> page, OmsOrderItemSplit omsOrderItemSplit) {
		return super.findPage(page, omsOrderItemSplit);
	}
	
	@Transactional(readOnly = false)
	public void save(OmsOrderItemSplit omsOrderItemSplit) {
		super.save(omsOrderItemSplit);
	}
	
	@Transactional(readOnly = false)
	public void delete(OmsOrderItemSplit omsOrderItemSplit) {
		super.delete(omsOrderItemSplit);
	}

	/**
	 * 运单号的最大值
	 * @return
	 */
	public Integer findMaxYundan() {
		return omsOrderItemSplitDao.findMaxYundan();
	}

	
	@Transactional(readOnly = false)
	public Integer updateForImp(OmsLogisticImpObj rowObj) {
		return omsOrderItemSplitDao.updateForImp(rowObj);
	}
	
}