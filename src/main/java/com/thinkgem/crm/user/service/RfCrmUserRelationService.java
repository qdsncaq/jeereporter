/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.service;

import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.user.entity.RfCrmUserRelation;
import com.thinkgem.crm.user.dao.RfCrmUserRelationDao;

/**
 * 用户关系维护Service
 * @author jiajianhong
 * @version 2016-03-30
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class RfCrmUserRelationService extends CrudService<RfCrmUserRelationDao, RfCrmUserRelation> {

	public RfCrmUserRelation get(String id) {
		return super.get(id);
	}
	
	public List<RfCrmUserRelation> findList(RfCrmUserRelation rfCrmUserRelation) {
		return super.findList(rfCrmUserRelation);
	}
	
	public Page<RfCrmUserRelation> findPage(Page<RfCrmUserRelation> page, RfCrmUserRelation rfCrmUserRelation) {
		return super.findPage(page, rfCrmUserRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(RfCrmUserRelation rfCrmUserRelation) {
		super.save(rfCrmUserRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(RfCrmUserRelation rfCrmUserRelation) {
		super.delete(rfCrmUserRelation);
	}

    public RfCrmUserRelation findByQzUserCode(String userCode) {
        return dao.findByQzUserCode(userCode);
    }

    public RfCrmUserRelation findByQjUserCode(String userCode) {
        return dao.findByQjUserCode(userCode);
    }

    public RfCrmUserRelation findBySalerUserCode(String userCode) {
        return dao.findBySalerUserCode(userCode);
    }

    public RfCrmUserRelation findByUserCode(String userCode) {
        return dao.findByUserCode(userCode);
    }
}