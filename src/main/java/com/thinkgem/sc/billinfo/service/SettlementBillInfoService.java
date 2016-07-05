/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.sc.billinfo.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mk.office.service.IOfficeService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.sc.billinfo.entity.SettlementBillInfo;
import com.thinkgem.sc.billinfo.dao.SettlementBillInfoDao;

/**
 * billinfoService
 * @author lm
 * @version 2016-03-28
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementBillInfoService extends CrudService<SettlementBillInfoDao, SettlementBillInfo> {
	
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SettlementBillInfoDao settlementBillInfoDao;
	
	@Autowired
	private IOfficeService iOfficeService;

	public SettlementBillInfo get(String id) {
		return super.get(id);
	}
	
	public List<SettlementBillInfo> findList(SettlementBillInfo settlementBillInfo) {
		return super.findList(settlementBillInfo);
	}
	
	public Page<SettlementBillInfo> findPage(Page<SettlementBillInfo> page, SettlementBillInfo settlementBillInfo) {
		return super.findPage(page, settlementBillInfo);
	}
	
	/**
	 * 财务用户点击收款 
	 * 		向收款明细表中添加一条数据
	 * 		调用接口：：：
	 * 			接收页面传过来的状态，同时更新账单表账单状态
	 * 			只有状态是已付款 或超额付款的时候 ， 更新明细状态-旅行社后付费账户 额度增加
	 * 			如果状态是部分付款 则在更新为已付款 或超额付款时， 更新明细状态-旅行社后付费账户 额度增加
	 */
	@Transactional(readOnly = false)
	public void save(SettlementBillInfo settlementBillInfo) {
		// super.save(settlementBillInfo);
		Long billid = settlementBillInfo.getBillid();
		Integer status = settlementBillInfo.getStatus();
		BigDecimal settlesum = settlementBillInfo.getSettlesum();
		if(status >= 11){
			log.info("账单{} 已收款11或超额收款12:{},修改明细和账户余额,收款金额为:{}", billid, status, settlesum);
			int settlementCount = iOfficeService.settlement(billid.toString(), status);
			log.info("账单完成收款,记录 {} 条", settlementCount);
		} else{
			log.info("账单{} 部分部分收款,不修改明细和账户余额,收款金额为:{},修改账单状态为部分收款.", billid, settlesum);
			int settlementCount = iOfficeService.settlement(billid.toString(), status);
			log.info("账单部分收款,记录 {} 条", settlementCount);
		}
		settlementBillInfo.setCreatetime(new Date());
		settlementBillInfo.setUpdatetime(new Date());
		settlementBillInfoDao.insert(settlementBillInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(SettlementBillInfo settlementBillInfo) {
		super.delete(settlementBillInfo);
	}
	
}