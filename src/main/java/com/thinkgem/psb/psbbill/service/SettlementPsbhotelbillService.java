/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbbill.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.psb.common.ExportPsbReceipt;
import com.thinkgem.psb.psbbill.dao.SettlementPsbhotelbillDao;
import com.thinkgem.psb.psbbill.entity.SettlementPsbhotelbill;
import com.thinkgem.psb.psbrules.dao.SettlementPsbrulesDao;
import com.thinkgem.psb.psbrules.entity.SettlementPsbrules;

/**
 * psbbillService
 * @author lm
 * @version 2016-04-21
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementPsbhotelbillService extends CrudService<SettlementPsbhotelbillDao, SettlementPsbhotelbill> {

	/**
	 * 结算账单dao
	 */
	@Autowired
	private SettlementPsbhotelbillDao hotelbillDao;
	
	/**
	 * 科目 Dao
	 */
	@Autowired
	private SettlementPsbrulesDao rulesDao;
	
	public SettlementPsbhotelbill get(String id) {
		return super.get(id);
	}
	
	public List<SettlementPsbhotelbill> findList(SettlementPsbhotelbill settlementPsbhotelbill) {
		return super.findList(settlementPsbhotelbill);
	}
	
	public Page<SettlementPsbhotelbill> findPage(Page<SettlementPsbhotelbill> page, SettlementPsbhotelbill settlementPsbhotelbill) {
		return super.findPage(page, settlementPsbhotelbill);
	}
	
	@Transactional(readOnly = false)
	public void save(SettlementPsbhotelbill settlementPsbhotelbill) {
		super.save(settlementPsbhotelbill);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementPsbhotelbill settlementPsbhotelbill) {
		super.delete(settlementPsbhotelbill);
	}
	
	/**
	 * 导出
	 * @param settlementPsbhotelbill
	 * @param response
	 */
	public void exportexcel(SettlementPsbhotelbill settlementPsbhotelbill,HttpServletResponse response) {
		 String fileName = "结算签收单.xlsx";
		 List<SettlementPsbhotelbill> list = super.findList(settlementPsbhotelbill);
		 List<SettlementPsbrules> prlist=null;
		 //默认有个产品费
		 String[] columnName2={"年费"};
		 //如果有金盾,则显示金盾所有列，否则只显示一列  年费
		 Integer psbid=getJinDunId(list);
		 if(psbid!=null){ //如果有金盾
			 prlist=rulesDao.findRulesByPsbid(psbid);
			 columnName2=new String[prlist.size()];  
		     for (int i = 0; i < prlist.size(); i++) {
		    	 columnName2[i]=prlist.get(i).getRulename();
			 }
		 }
		try {
			new ExportPsbReceipt(columnName2,list,prlist).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**判断是否有金盾 - 导出功能调用方法*/
	private Integer getJinDunId(List<SettlementPsbhotelbill> list){
		Integer psbid=null;
		for (SettlementPsbhotelbill sphb : list) {
			 if(sphb.getPsbname().indexOf("金盾")>=0){
				 psbid= sphb.getPsbid();
				 break;
			 }
		}
		return psbid;
	}

	// 批量 修改账单状态为: 已结算
	@Transactional(readOnly = false)
	public int settlebill(String[] arrids) {
		return hotelbillDao.settlebill(arrids);
	}

	// 批量 修改账单状态为: 本期不结算
	@Transactional(readOnly = false)
	public int nobill(String[] arrids) {
		return hotelbillDao.nobill(arrids);
	}
	
}