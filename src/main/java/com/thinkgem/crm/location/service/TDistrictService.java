/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.location.service;

import java.util.List;

import com.thinkgem.crm.location.entity.AllAreas;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.crm.location.entity.TDistrict;
import com.thinkgem.crm.location.dao.TDistrictDao;

/**
 * CRM地理位置信息Service
 * @author 段文昌
 * @version 2016-03-15
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class TDistrictService extends CrudService<TDistrictDao, TDistrict> {

	public TDistrict get(String id) {
		return super.get(id);
	}
	
	public List<TDistrict> findList(TDistrict tDistrict) {
		return super.findList(tDistrict);
	}
	
	public Page<TDistrict> findPage(Page<TDistrict> page, TDistrict tDistrict) {
		return super.findPage(page, tDistrict);
	}
	
	@Transactional(readOnly = false)
	public void save(TDistrict tDistrict) {
		super.save(tDistrict);
	}
	
	@Transactional(readOnly = false)
	public void delete(TDistrict tDistrict) {
		super.delete(tDistrict);
	}

	/**
	 * 按父Id查询所有子节点
	 * @param parent 父对象
	 * @return TDistrict集合
	 */
	public List<TDistrict> getByParent(TDistrict parent){
        parent.getPage().setOrderBy("disname asc");
		return this.findList(parent);
	}

    public static void main(String[] args) {

    }

    public List<AllAreas> findAllToAllAreas(AllAreas allAreas) {
        return dao.findAllToAllAreas(allAreas);
    }
}