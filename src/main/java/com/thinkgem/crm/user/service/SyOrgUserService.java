/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.service;

import java.util.List;
import java.util.Map;

import com.thinkgem.crm.location.entity.AllAreas;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.thinkgem.crm.user.dao.SyOrgUserDao;
import com.thinkgem.crm.user.entity.SyOrgUser;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * BMS用户表Service
 * @author 李雪楠
 * @version 2016-03-23
 */
@Service
@DataSourceName("crm")
@Transactional(readOnly = true)
public class SyOrgUserService extends CrudService<SyOrgUserDao, SyOrgUser> {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
	public SyOrgUser get(String id) {
		return super.get(id);
	}
	
	public List<SyOrgUser> findList(SyOrgUser syOrgUser) {
		return super.findList(syOrgUser);
	}
	
	public Page<SyOrgUser> findPage(Page<SyOrgUser> page, SyOrgUser syOrgUser) {
		return super.findPage(page, syOrgUser);
	}
	
	@Transactional(readOnly = false)
	public void save(SyOrgUser syOrgUser) {
		super.save(syOrgUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(SyOrgUser syOrgUser) {
		super.delete(syOrgUser);
	}
	
	/**
	 * 查询没有配置销售区域的剩余销售用户
	 */
	public List<SyOrgUser> findListNotExistUserArea(SyOrgUser syOrgUser) {
		return dao.findListNotExistUserArea(syOrgUser);
	}
	
	/**
	 * 获取列表查询基础信息(所有用户列表)
	 * @param rfCrmUserArea
	 * @param model
	 */
	public void getSyOrgUserList(Model model) {
		//销售
		SyOrgUser syOrgUser = new SyOrgUser();
		List<SyOrgUser> syOrgUserList = this.findList(syOrgUser);
		model.addAttribute("syOrgUserList",syOrgUserList);
	}

    public List<AllAreas> findUsersByCode(List<String> codes) {
        return dao.findUsersByCode(codes);
    }
	
	public List<Map<String,String>> getSellersByProvCode(Integer provCode){
		return dao.getSellersByProvCode(provCode);
	}
}