/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbrulesrelation.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.crm.location.entity.TCity;
import com.thinkgem.crm.location.entity.TProvince;
import com.thinkgem.crm.location.service.TCityService;
import com.thinkgem.crm.location.service.TProvinceService;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.psb.psbrulesrelation.dao.SettlementPsbrulesrelationDao;
import com.thinkgem.psb.psbrulesrelation.entity.SettlementPsbrulesrelation;

/**
 * psbrulesrelationService
 * @author lm
 * @version 2016-04-08
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementPsbrulesrelationService extends CrudService<SettlementPsbrulesrelationDao, SettlementPsbrulesrelation> {
	
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/** 省服务 */
	@Autowired
	private TProvinceService provinceService;
	
	/** 市服务 */
	@Autowired
	private TCityService tCityService;
	
	@Autowired
	private SettlementPsbrulesrelationDao relationDao;

	public SettlementPsbrulesrelation get(String id) {
		return super.get(id);
	}
	
	public List<SettlementPsbrulesrelation> findList(SettlementPsbrulesrelation settlementPsbrulesrelation) {
		return super.findList(settlementPsbrulesrelation);
	}
	
	public Page<SettlementPsbrulesrelation> findPage(Page<SettlementPsbrulesrelation> page, SettlementPsbrulesrelation settlementPsbrulesrelation) {
		return super.findPage(page, settlementPsbrulesrelation);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementPsbrulesrelation settlementPsbrulesrelation) {
		super.save(settlementPsbrulesrelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementPsbrulesrelation settlementPsbrulesrelation) {
		super.delete(settlementPsbrulesrelation);
	}
	
	@Transactional(readOnly = false)
	public int returnUpdate(SettlementPsbrulesrelation settlementPsbrulesrelation) {
		return relationDao.update(settlementPsbrulesrelation);
	}

	public Map<String, Object> serBycode(String citycode, String procode) {
		return relationDao.getRulesByhotelid(procode, citycode);
	}

	/**
	 * 添加酒店规则
	 * @param settlementPsbrulesrelation
	 */
	@Transactional(readOnly = false)
	public void addrules(SettlementPsbrulesrelation settlementPsbrulesrelation) {
		if(settlementPsbrulesrelation.getId() == null || settlementPsbrulesrelation.getId().equalsIgnoreCase("")){
			// 判断如果酒店的规则设置已经存在，则更新
			String hotelid = settlementPsbrulesrelation.getHotelid();
			SettlementPsbrulesrelation relation = getByHotelid(hotelid);
			if(relation != null){
				String id = relation.getId();
				settlementPsbrulesrelation.setId(id);
			}
			settlementPsbrulesrelation.setAreatype(3);
			settlementPsbrulesrelation.setCreatetime(new Date());
		}
		settlementPsbrulesrelation.setUpdatetime(new Date());
		settlementPsbrulesrelation.setDelflag(1);
		save(settlementPsbrulesrelation);
	}
	
	/**
	 * 查询酒店规则是否存在
	 * @param hotelid
	 * @return
	 */
	public SettlementPsbrulesrelation getByHotelid(String hotelid) {
		return relationDao.getByHotelid(hotelid);
	}

	/**
	 * 添加区域规则
	 * @param settlementPsbrulesrelation
	 */
	@Transactional(readOnly = false)
	public void addDisRules(SettlementPsbrulesrelation settlementPsbrulesrelation) {
		if(settlementPsbrulesrelation.getId() == null || settlementPsbrulesrelation.getId().equalsIgnoreCase("")){
			// 判断如果区域规则已经存在，那么更新
			String procode = settlementPsbrulesrelation.getProcode();
			String citycode = settlementPsbrulesrelation.getCitycode();
			String discode = settlementPsbrulesrelation.getDiscode();
			SettlementPsbrulesrelation relation = relationDao.getByDiscode(procode, citycode, discode);
			if(relation != null){
				String id = relation.getId();
				settlementPsbrulesrelation.setId(id);
			}
			settlementPsbrulesrelation.setAreatype(2);
			settlementPsbrulesrelation.setCreatetime(new Date());
		}
		settlementPsbrulesrelation.setUpdatetime(new Date());
		settlementPsbrulesrelation.setDelflag(1);
		save(settlementPsbrulesrelation);
	}

	/**
	 * 添加城市规则
	 * @param settlementPsbrulesrelation
	 */
	@Transactional(readOnly = false)
	public void addCityRules(SettlementPsbrulesrelation settlementPsbrulesrelation) {
		String citycode = null;
		String procode = null;
		if(settlementPsbrulesrelation != null){
			citycode = settlementPsbrulesrelation.getCitycode();
			procode = settlementPsbrulesrelation.getProcode();
		}
		if(StringUtils.isBlank(citycode)){
			// 使用省编码, 查询所有城市.
			//市
			if(StringUtils.isNotBlank(procode)){
				//查询省ID
				TProvince province = provinceService.getByCode(settlementPsbrulesrelation.getProcode());
				//获取城市数据
				TCity city = new TCity();
				city.setProid(Integer.valueOf(String.valueOf(province.getProid())));
				List<TCity> cityList = tCityService.getByParent(city);
				SettlementPsbrulesrelation relation = new SettlementPsbrulesrelation();
				for (TCity tCity : cityList) {
					BeanUtils.copyProperties(settlementPsbrulesrelation, relation);
					relation.setCitycode(tCity.getCode());
					relation.setCityname(tCity.getCityname());
					SettlementPsbrulesrelation settlrRelation = relationDao.getByCitycode(tCity.getCode(), procode);
					if(settlrRelation != null){
						String id = settlrRelation.getId();
						relation.setId(id);
						relation.setAreatype(1);
						relation.setCreatetime(settlrRelation.getCreatetime());
						relation.setIsNewRecord(false);
					} else{
						relation.setAreatype(1);
						relation.setCreatetime(new Date());
						relation.setId(null);
					}
					relation.setUpdatetime(new Date());
					relation.setDelflag(1);
					save(relation);
				}
			}
		} else{
			if(citycode.startsWith(",")){
				citycode = citycode.substring(1);
			}
			settlementPsbrulesrelation.setCitycode(citycode);
			if(settlementPsbrulesrelation.getId() == null || settlementPsbrulesrelation.getId().equalsIgnoreCase("")){
				SettlementPsbrulesrelation settlrRelation = relationDao.getByCitycode(citycode, procode);
				if(settlrRelation != null){
					String id = settlrRelation.getId();
					settlementPsbrulesrelation.setId(id);
					settlementPsbrulesrelation.setAreatype(1);
					settlementPsbrulesrelation.setCreatetime(settlrRelation.getCreatetime());
					settlementPsbrulesrelation.setIsNewRecord(false);
				} else{
					settlementPsbrulesrelation.setAreatype(1);
					settlementPsbrulesrelation.setCreatetime(new Date());
				}
			}
			settlementPsbrulesrelation.setUpdatetime(new Date());
			settlementPsbrulesrelation.setDelflag(1);
			save(settlementPsbrulesrelation);
		}
	}

	/**
	 * 批量删除
	 * @param arrids
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteids(String[] arrids) {
		return relationDao.deleteids(arrids);
	}
	
}