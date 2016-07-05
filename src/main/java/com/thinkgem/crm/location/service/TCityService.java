/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.location.service;

import com.thinkgem.crm.location.dao.TCityDao;
import com.thinkgem.crm.location.entity.AllAreas;
import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CRM地理位置信息Service
 * @author 段文昌
 * @version 2016-03-15
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class TCityService extends CrudService<TCityDao, TCity> {

	public TCity get(String id) {
		return super.get(id);
	}
	
	public List<TCity> findList(TCity tCity) {
		return super.findList(tCity);
	}
	
	public Page<TCity> findPage(Page<TCity> page, TCity tCity) {
		return super.findPage(page, tCity);
	}
	
	@Transactional(readOnly = false)
	public void save(TCity tCity) {
		super.save(tCity);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCity tCity) {
		super.delete(tCity);
	}

	/**
	 * 按父Id查询所有子节点
	 * @param parent 父对象
	 * @return TCity集合
	 */
	public List<TCity> getByParent(TCity parent){
        parent.getPage().setOrderBy("cityname asc");
		return this.findList(parent);
	}

	public TCity getByCode(String code) {
		return dao.getByCode(code);
	}

    public List<AllAreas> findAllToAllAreas(AllAreas allAreas) {
        return dao.findAllToAllAreas(allAreas);
    }
}