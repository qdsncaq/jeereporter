/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.psb.psbhotelbillinit.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.psb.common.DateUtils;
import com.thinkgem.psb.enums.HotelOnlineState;
import com.thinkgem.psb.onoffdetail.dao.OnOffLineDetailDao;
import com.thinkgem.psb.onoffdetail.entity.OnOffLineDetail;
import com.thinkgem.psb.psbbill.dao.SettlementPsbhotelbillDao;
import com.thinkgem.psb.psbbill.entity.SettlementPsbhotelbill;
import com.thinkgem.psb.psbhotelbillinit.dao.SettlementPsbhotelbillinitDao;
import com.thinkgem.psb.psbhotelbillinit.entity.SettlementPsbhotelbillinit;
import com.thinkgem.psb.psbrules.dao.SettlementPsbrulesDao;
import com.thinkgem.psb.psbrules.entity.SettlementPsbrules;

/**
 * psbhotelbillService
 * @author lm
 * @version 2016-04-12
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class SettlementPsbhotelbillinitService extends CrudService<SettlementPsbhotelbillinitDao, SettlementPsbhotelbillinit> {
	
	/**
	 * 日志对象
	 */
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 科目 Dao
	 */
	@Autowired
	private SettlementPsbrulesDao rulesDao;
	
	/**
	 * psb酒店账单初始化信息
	 */
	@Autowired
	private SettlementPsbhotelbillinitDao billinitdao;
	
	/**
	 * 酒店上下线记录明细dao
	 */
	@Autowired
	private OnOffLineDetailDao onOffDao;
	
	/**
	 * 结算账单dao
	 */
	@Autowired
	private SettlementPsbhotelbillDao billDao;
	
    /**
     * 小数位数
     */
    private final int ACCOUNT_LEDGER_SCALE = 2;
	
	/**
	 * 导出
	 * @param settlementPsbhotelbillinit
	 * @param response
	 */
	public void exportexcel(SettlementPsbhotelbillinit settlementPsbhotelbillinit,HttpServletResponse response) {
		
//		 String fileName = "结算签收单.xlsx";
//		 List<SettlementPsbhotelbillinit> list = super.findList(settlementPsbhotelbillinit);
//		 List<SettlementPsbrules> prlist=null;
//		 //默认有个产品费
//		 String[] columnName2={"年费"};
//		 //如果有金盾,则显示金盾所有列，否则只显示一列  年费
//		 Integer psbid=getJinDunId(list);
//		 if(psbid!=null){ //如果有金盾
//			 prlist=rulesDao.findRulesByPsbid(psbid);
//			 columnName2=new String[prlist.size()];  
//		     for (int i = 0; i < prlist.size(); i++) {
//		    	 columnName2[i]=prlist.get(i).getRulename();
//			 }
//		 }
//		try {
//			new ExportPsbReceipt(columnName2,list,prlist).write(response, fileName).dispose();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
//	/**判断是否有金盾 - 导出功能调用方法*/
//	private Integer getJinDunId(List<SettlementPsbhotelbillinit> list){
//		Integer psbid=null;
//		for (SettlementPsbhotelbillinit  sphb : list) {
//			 if(sphb.getPsbname().indexOf("金盾")>=0){
//				 psbid= sphb.getPsbid();
//				 break;
//			 }
//		}
//		return psbid;
//	}

	/**
	 * 酒店账单初始化
	 * 		查询所有有上线记录的酒店,生成一条账单明细
	 * 		状态: 1初始化
	 */
	@Transactional(readOnly = false)
	public List<SettlementPsbhotelbillinit> psbbillinit(SettlementPsbhotelbillinit billinit, int typeParam) {
		log.info("查询所有有上线记录的酒店,生成一条账单明细.开始...");
		List<Map<String, Object>> lists = billinitdao.getAllFirstOnHotel(billinit);
		List<SettlementPsbhotelbillinit> billinits = new ArrayList<SettlementPsbhotelbillinit>();
		for (Map<String, Object> map : lists) {
			String procode = (String) map.get("procode");
			String proname = (String) map.get("proname");
			String citycode = (String) map.get("citycode");
			String cityname = (String) map.get("cityname");
			String discode = (String) map.get("discode");
			String disname = (String) map.get("disname");
			String hotelid = map.get("hotelid")+"";
			String hotelname = (String) map.get("hotelname");
			String hotelpms = (String) map.get("hotelpms");
			Date minontime = (Date) map.get("minontime");
			// 获取酒店房间数 根据hotelid
			int roomnums = billinitdao.selectroomNums(hotelid);
			SettlementPsbhotelbillinit billroomnums = new SettlementPsbhotelbillinit();
			billroomnums.setRoomnums(roomnums);
			// 查询酒店规则 年费 
			Map<String, Object> rulesmap = billinitdao.selectrules(procode, citycode, discode, hotelid);
			String rules = null;
			if(rulesmap != null){
				String hotelrules = (String) rulesmap.get("hotelrules");
				String disrules = (String) rulesmap.get("disrules");
				String cityrules = (String) rulesmap.get("cityrules");
				if(hotelrules != null){
					rules = hotelrules;
				} else if(disrules != null){
					rules = disrules;
				} else{
					rules = cityrules;
				}
			} else{
				log.info("省:{}, 市:{}, 区:{}, 酒店id:{}, 酒店名称:{}, 没有对应规则.", proname, cityname, disname, hotelid, hotelname);
			}
			// 根据rules 查询规则
			List<SettlementPsbrules> billrules = new ArrayList<SettlementPsbrules>();
			String[] ids = null;
			if(rules != null){
				ids = rules.split(",");
				billrules = rulesDao.selectRulesByids(ids);
			}
			Integer psbid = null;
			String psbname = null;
			BigDecimal fee = new BigDecimal(0);
			if(billrules.size() > 0){
				// 查询psb信息
				psbid = billrules.get(0).getPsbid();
				psbname = billrules.get(0).getPsbname();
				if(roomnums >= 10){
					for (SettlementPsbrules psbrule : billrules) {  
						BigDecimal fee20 = new BigDecimal(psbrule.getFee20());
						fee = fee.add(fee20);
					}
				} else{
					for (SettlementPsbrules psbrule : billrules) {
						String fee10 = psbrule.getFee10();
						if(fee10.equalsIgnoreCase("12*roomnums*1/7")){
							fee10 = "12*roomnums*1.0/7";
						}
						ExpressionParser parser = new SpelExpressionParser();
						Expression exp = parser.parseExpression(fee10);
						EvaluationContext context = new StandardEvaluationContext(billroomnums);
						BigDecimal bigDecimal = exp.getValue(context, BigDecimal.class).setScale(ACCOUNT_LEDGER_SCALE, BigDecimal.ROUND_CEILING);
						fee = fee.add(bigDecimal);
					}
				}
			} else{
				log.info("酒店未查询到规则.procode:{}, citycode:{}, discode:{}, hotelid:{}.", procode, citycode, discode, hotelid);
			}

			// 如果是新酒店 需要信息: 酒店省市区, 酒店科目, 年费, PSB对应关系, 科目对应金额, 酒店房间数
			SettlementPsbhotelbillinit bill = new SettlementPsbhotelbillinit();
			bill.setCreatetime(new Date());
			bill.setUpdatetime(new Date());
			bill.setHotelid(hotelid);
			bill.setOnlinestate(HotelOnlineState.ONLINE.getIndex());
			bill.setRoomnums(roomnums);
			bill.setFeetypeids(rules);
			bill.setFee(fee);
			bill.setAmount(fee);
			bill.setRecoveramount(fee);
			bill.setBegintime(minontime);
			if(minontime != null){
				bill.setEndtime(DateUtils.addDays(minontime, 360));
				bill.setRealendtime(DateUtils.addDays(minontime, 360));
			}
			bill.setOnlinedays(0); //设置在线天数
			bill.setStatus(1);	//状态默认为1
			
			bill.setPsbid(psbid);
			bill.setPsbname(psbname);
			bill.setHotelpms(hotelpms);
			bill.setHotelname(hotelname);
			bill.setProcode(procode);
			bill.setProname(proname);
			bill.setCitycode(citycode);
			bill.setCityname(cityname);
			bill.setDiscode(discode);
			bill.setDisname(disname);
			bill.setBilltype(1);
			bill.setBilltypedesc("新上线酒店");
			
			billinits.add(bill);			
		}
		int count = 0;
		if(typeParam == 2){
			if(billinits != null && billinits.size() > 0){
				int pageTotal = 0;
				int record = 0 ;
				pageTotal =billinits.size()/500;
				if (billinits.size()%500!=0) {
					record = billinits.size() -  pageTotal*500 ;
					pageTotal = pageTotal+1;
				}
				List<SettlementPsbhotelbillinit> subList = null;
				for (int i = 0; i <pageTotal; i++) {
					if (i==(pageTotal -1)&&record!=0) {
						subList = billinits.subList(i*500,i*500+record);
					}else{
						subList = billinits.subList(i*500, (i+1)*500);//i*500+record
						
					}
					count = billinitdao.batchInsert(subList);
				}
			}
		}
		log.info("本次结算共插入新上线酒店数据 {} 条.时间: {}", count, new Date());
		return billinits;
	}
	
	/**
	 * 根据输入条件生成账单
	 * 	psbid, procode, citycode, discode, hotelid, hotelpms, begintime, endtime
	 * @param billinit
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<SettlementPsbhotelbillinit> generatePsbBill(SettlementPsbhotelbillinit billinit, int typeParam) {
		Date begintime = billinit.getBegintime();
		Date endtime = billinit.getEndtime();
		
		List<SettlementPsbhotelbillinit> genbills = new ArrayList<SettlementPsbhotelbillinit>();
		
		// 调用初始化账单方法.
		List<SettlementPsbhotelbillinit> psbbillinit = psbbillinit(billinit, typeParam);
		
		// 按照查询条件查询账单初始化表-所有符合结算条件的酒店
		billinit.setOnlinestate(HotelOnlineState.ONLINE.getIndex());
		List<SettlementPsbhotelbillinit> billinitList = billinitdao.findList(billinit);
		if(typeParam == 1){
		    for (SettlementPsbhotelbillinit settlementPsbhotelbillinit : psbbillinit) {
		    	billinitList.add(settlementPsbhotelbillinit);
		    }
		}
		for (SettlementPsbhotelbillinit bill : billinitList) {
			
			try {
				
			
			String hotelid = bill.getHotelid();
			if(bill.getBilltype() == 2){
				log.info("在线360天酒店数据不处理, 酒店ID: ", hotelid);
				continue ;
			}
			// 离线天数 当前状态
			Map<String, Object> map = getOffdays(hotelid, begintime, endtime);  
			String stype = (String) map.get("type");		// 当前状态 1,在线 2,离线
			int type;
			if(stype == null){
				type = Integer.parseInt("1"); 				// 未查到上线下记录, 按照在线处理
			} else{
				type = Integer.parseInt(stype);
			}
			int offdays = 0;
			if(map.get("offdays") != null){
				offdays = (int) map.get("offdays");	// 离线天数
			}
			Date onOffTime = null;
			if(map.get("onOffTime") != null){
				onOffTime = (Date) map.get("onOffTime");	// 下线时间
			}
			
			Date billBegintime = null;
			Date mapBegintime = (Date) map.get("begintime");
			if(mapBegintime != null){
				billBegintime = mapBegintime;
			} else {
				billBegintime = bill.getBegintime();
			}
			if(billBegintime!=null && billBegintime.getTime() >= begintime.getTime() && billBegintime.getTime() < endtime.getTime()){
				log.info("开始时间:{}-结束时间:{},新上线酒店统计.酒店id:{}.", begintime, endtime, hotelid);
				// 新上线酒店
				// 在线天数
				int onlinedays = DateUtils.selectDateDiff(endtime, billBegintime);
				int days = onlinedays - offdays;
				if (days < 0) {
					days = 0;
				}
				// 设置实际结算日期bill.
				Date realendtime = DateUtils.addDays(billBegintime, 360+offdays);
				bill.setBegintime(billBegintime);
				bill.setEndtime(DateUtils.addDays(billBegintime, 360));
				bill.setRealendtime(realendtime);
				bill.setOnlinedays(days);
				bill.setRecoveramount(null);
				// 当前状态
				bill.setOnlinestate(type);
				// 如果是离店的话, 设置最后一次离线时间
				if(onOffTime != null){
					bill.setOfflinetime(onOffTime);
				}
				if(type == 1){
					if(realendtime.getTime() >= begintime.getTime() && realendtime.getTime() <= endtime.getTime()){
						// 判断realendtime 是否在这个时间 如果在,则需要按照360天,然后生成账单 360在线
						bill.setOnlinedays(360);
						bill.setBilltype(2);
						bill.setBilltypedesc("在线360天完成");
						update(bill);
						
						// 然后重新生成一条新的账单
						SettlementPsbhotelbillinit newbillinit = new SettlementPsbhotelbillinit();
						BeanUtils.copyProperties(bill, newbillinit);
						newbillinit.setBegintime(DateUtils.addDays(bill.getRealendtime(), 1));
						newbillinit.setEndtime(DateUtils.addDays(bill.getRealendtime(), 361));
						newbillinit.setRealendtime(DateUtils.addDays(bill.getRealendtime(), 361));
						int newonlinedays = DateUtils.selectDateDiff(endtime, bill.getRealendtime())+1;
						newbillinit.setOnlinedays(newonlinedays);
						// double newrate = 1 - (double)onlinedays/360;
						// newbillinit.setRecoveramount(newbillinit.getFee().multiply(new BigDecimal(newrate)).setScale(ACCOUNT_LEDGER_SCALE, BigDecimal.ROUND_CEILING));
						newbillinit.setOnlinetime(DateUtils.addDays(bill.getRealendtime(), 1));
						newbillinit.setStatus(1);
						newbillinit.setOfflinetime(null);
						newbillinit.setCreatetime(new Date());
						newbillinit.setUpdatetime(new Date());
						newbillinit.setBilltype(3);
						newbillinit.setBilltypedesc("第二年预付费用");
						
						genbills.add(newbillinit);
						billinitdao.insert(newbillinit);
					}
				}
				if(type == 2){
					// bill.setBilltype(4);
					// bill.setBilltypedesc("下线酒店");
					double newrate = 1 - (double)onlinedays/360;
					bill.setRecoveramount(bill.getFee().multiply(new BigDecimal(newrate)).setScale(ACCOUNT_LEDGER_SCALE, BigDecimal.ROUND_CEILING));
				}
				
				update(bill);
				genbills.add(bill);
			} else{
				if(type == 1){
                    log.info("开始时间:{}-结束时间:{},在线酒店统计.酒店id:{}.", begintime, endtime, hotelid);
					Date realendtime = DateUtils.addDays(bill.getRealendtime(), offdays);
					// DateUtils.selectDateDiff(onchecktime, offchecktime);
					int days = DateUtils.selectDateDiff(endtime, bill.getBegintime()) - offdays;
					if(days < 0){
						days = 0;
					}
					bill.setOnlinedays(days);
					bill.setRecoveramount(null);
					bill.setRealendtime(realendtime);
					update(bill);
					if(realendtime.getTime() >= begintime.getTime() && realendtime.getTime() <= endtime.getTime()){
						// 判断realendtime 是否在这个时间 如果在,则需要按照360天,然后生成账单 360在线
						bill.setOnlinedays(360);
						bill.setBilltype(2);
						bill.setBilltypedesc("在线360天完成");
						update(bill);
						
						// 然后重新生成一条新的账单
						SettlementPsbhotelbillinit newbillinit = new SettlementPsbhotelbillinit();
						BeanUtils.copyProperties(bill, newbillinit);
						newbillinit.setBegintime(DateUtils.addDays(bill.getRealendtime(), 1));
						newbillinit.setEndtime(DateUtils.addDays(bill.getRealendtime(), 361));
						newbillinit.setRealendtime(DateUtils.addDays(bill.getRealendtime(), 361));
						int onlinedays = DateUtils.selectDateDiff(endtime, bill.getRealendtime())+1;
						newbillinit.setOnlinedays(onlinedays);
						// double newrate = 1 - (double)onlinedays/360;
						// newbillinit.setRecoveramount(newbillinit.getFee().multiply(new BigDecimal(newrate)).setScale(ACCOUNT_LEDGER_SCALE, BigDecimal.ROUND_CEILING));
						newbillinit.setOnlinetime(DateUtils.addDays(bill.getRealendtime(), 1));
						newbillinit.setStatus(1);
						newbillinit.setOfflinetime(null);
						newbillinit.setCreatetime(new Date());
						newbillinit.setUpdatetime(new Date());
						newbillinit.setBilltype(3);
						newbillinit.setBilltypedesc("第二年预付费用");
						
						genbills.add(newbillinit);
						billinitdao.insert(newbillinit);
					}
				} else if(type == 2){
                    log.info("开始时间:{}-结束时间:{},下线酒店统计.酒店id:{}.", begintime, endtime, hotelid);
					//下线酒店
					int onlinedays = DateUtils.selectDateDiff(endtime, bill.getBegintime())-offdays;
					if(onlinedays < 0){
						onlinedays = 0;
					}
					bill.setOnlinedays(onlinedays);
					bill.setOfflinetime(onOffTime);
					bill.setOnlinestate(type);
					double newrate = 1 - (double)onlinedays/360;
					bill.setRecoveramount(bill.getFee().multiply(new BigDecimal(newrate)).setScale(ACCOUNT_LEDGER_SCALE, BigDecimal.ROUND_CEILING));
					
					bill.setBilltype(4);
					bill.setBilltypedesc("下线酒店");
					if(typeParam == 2){
						update(bill);
					}
					if(onOffTime.getTime() >= begintime.getTime() && onOffTime.getTime() <= endtime.getTime()){
						genbills.add(bill);
					}
				}
			}
		} catch (Exception e) {
		 log.error("预览账单出错"+e);
		}
			
		}
		if(typeParam == 2){
			int count = 0;
			if(genbills != null && genbills.size() > 0){
				List<SettlementPsbhotelbill> saveHotelbill = new ArrayList<SettlementPsbhotelbill>();
				List<SettlementPsbhotelbill> hotelbillList = getbillBybillinit(genbills);
				for (SettlementPsbhotelbill genhotelbill : hotelbillList) {
					genhotelbill.setBillstarttime(begintime);
					genhotelbill.setBillendtime(endtime);
					genhotelbill.setCreatetime(new Date());
					genhotelbill.setUpdatetime(new Date());
					
					// 去重判断, 如果在该段时间内有未结算的账单 则不生成新的未结算的账单
					SettlementPsbhotelbill existedHotelBill = billDao.getPeriodBillByhotelid(genhotelbill);
					if(existedHotelBill != null){
						log.info("开始时间:{}-结束时间:{},有未完成账单的酒店不用生成账单.酒店id:{}.", begintime, endtime, genhotelbill.getHotelid());
					} else{
						saveHotelbill.add(genhotelbill);
					}
				}
				if(saveHotelbill != null && saveHotelbill.size() > 0){
					
					int pageTotal = 0;
					int record = 0 ;
					pageTotal =saveHotelbill.size()/500;
					if (saveHotelbill.size()%500!=0) {
						record = saveHotelbill.size() -  pageTotal*500 ;
						pageTotal = pageTotal+1;
					}
					List<SettlementPsbhotelbill> subList = null;
					for (int i = 0; i <pageTotal; i++) {
						if (i==(pageTotal -1)&&record!=0) {
							subList = saveHotelbill.subList(i*500,i*500+record);
						}else{
							subList = saveHotelbill.subList(i*500, (i+1)*500);//i*500+record
						}
						count = billDao.batchInsert(subList);
					}
				}
			}
			log.info("本次结算共插入需要结算酒店账单数据 {} 条.时间: {}", count, new Date());
		}
		return genbills;
	}
	
	public List<SettlementPsbhotelbill> getbillBybillinit(List<SettlementPsbhotelbillinit> billinitList){
		List<SettlementPsbhotelbill> hotelbillList = new ArrayList<SettlementPsbhotelbill>();
		for (SettlementPsbhotelbillinit billinit : billinitList) {
			SettlementPsbhotelbill hotelbill = new SettlementPsbhotelbill();
			BigDecimal amount = billinit.getAmount();
			String areamanager = billinit.getAreamanager();
			Date begintime = billinit.getBegintime();
			Integer billtype = billinit.getBilltype();
			String billtypedesc = billinit.getBilltypedesc();
			String citycode = billinit.getCitycode();
			String cityname = billinit.getCityname();
			String discode = billinit.getDiscode();
			String disname = billinit.getDisname();
			BigDecimal fee = billinit.getFee();
			String feetypeids = billinit.getFeetypeids();
			String hotelid = billinit.getHotelid();
			String hotelname = billinit.getHotelname();
			String hotelpms = billinit.getHotelpms();
			Date offlinetime = billinit.getOfflinetime();
			Integer onlinedays = billinit.getOnlinedays();
			Integer onlinestate = billinit.getOnlinestate();
			Date onlinetime = billinit.getOnlinetime();
			String operator = billinit.getOperator();
			String procode = billinit.getProcode();
			String proname = billinit.getProname();
			Integer psbid = billinit.getPsbid();
			String psbname = billinit.getPsbname();
			Date realendtime = billinit.getRealendtime();
			BigDecimal recoveramount = billinit.getRecoveramount();
			Integer roomnums = billinit.getRoomnums();
			Integer status = billinit.getStatus();
			Date endtime = billinit.getEndtime();
			
			hotelbill.setHotelid(hotelid);
			hotelbill.setHotelname(hotelname);
			hotelbill.setHotelpms(hotelpms);
			hotelbill.setProcode(procode);
			hotelbill.setProname(proname);
			hotelbill.setCitycode(citycode);
			hotelbill.setCityname(cityname);
			hotelbill.setDiscode(discode);
			hotelbill.setDisname(disname);
			hotelbill.setPsbid(psbid);
			hotelbill.setPsbname(psbname);
			
			hotelbill.setRoomnums(roomnums);
			hotelbill.setAmount(amount);
			hotelbill.setRecoveramount(recoveramount);
			hotelbill.setOperator(operator);
			hotelbill.setAreamanager(areamanager);
			hotelbill.setStatus(status);
			hotelbill.setOnlinestate(onlinestate);
			hotelbill.setOnlinedays(onlinedays);
			hotelbill.setBegintime(begintime);
			hotelbill.setEndtime(endtime);
			hotelbill.setRealendtime(realendtime);
			hotelbill.setOnlinetime(onlinetime);
			hotelbill.setOfflinetime(offlinetime);
			hotelbill.setFeetypeids(feetypeids);
			hotelbill.setFee(fee);
			hotelbill.setBilltype(billtype);
			hotelbill.setBilltypedesc(billtypedesc);
			
			hotelbillList.add(hotelbill);
		}
		return hotelbillList;
	}
	
	/**
	 * 根据酒店ID 起始时间查询酒店下线天数
	 * @param hotelid
	 * @param begintime
	 * @param endtime
	 * @return
	 */    
	// 下线天数
	public Map<String, Object> getOffdays(String hotelid, Date begintime, Date endtime ){
		Map<String, Object> map = new HashMap<String, Object>();
		int offdays = 0;
		// 加判断，如果已经统计过一次，则查询开始时间和结束时间之间的 上下线记录
		// 根据酒店ID查询billinit表
		SettlementPsbhotelbillinit billinit = billinitdao.getByHotelid(hotelid);
		List<OnOffLineDetail> onofflist = null;
		if(billinit != null){
			onofflist = onOffDao.selectHotelOfflineByConditionsTime(hotelid, begintime, endtime);
		} else{
			onofflist = onOffDao.selectHotelOfflineByConditions(hotelid, begintime, endtime);
		}
		String type = null;
		if(onofflist != null){
			if(onofflist.size() == 0){
				map.put("offdays", offdays);
				map.put("type", type);
				return map;
			}
			String type0 = onofflist.get(0).getType();
			if(type0.equalsIgnoreCase("1")){
				map.put("begintime", onofflist.get(0).getOnOffTime());
			}
			type = onofflist.get(onofflist.size() - 1).getType();
		}
		if(type.equalsIgnoreCase("1")){ // 最后一条上线状态
			if(onofflist.size() == 1){
				map.put("offdays", offdays);
				map.put("type", type);
				return map;
			}
			for(int i = onofflist.size()-1; i > 1; i--){
				if(i%2==0){
					Date onchecktime = onofflist.get(i).getOnOffTime();
					Date offchecktime = onofflist.get(i-1).getOnOffTime();
					int onlinedays = DateUtils.selectDateDiff(onchecktime, offchecktime);
					if(onlinedays == 0){
						onlinedays = 1;
					}
					offdays = offdays + onlinedays;
					if(map.get("onOffTime") == null){
						map.put("onOffTime", offchecktime);
					}
				}
			}
			map.put("offdays", offdays);
		} else if(type.equalsIgnoreCase("2")){ // 最后一条下线状态
			OnOffLineDetail onOffDetail = onofflist.get(onofflist.size() - 1);
			Date onOffTime = onOffDetail.getOnOffTime();
			int daysnum = DateUtils.selectDateDiff(endtime, DateUtils.addDays(onOffTime, -1));
			offdays = daysnum;
			for (int i = 0; i < onofflist.size() - 1; i++) {
				if(onofflist.size() > 3){
					if(i%2 == 0){
						Date offchecktime = onofflist.get(i+1).getOnOffTime();
						Date onchecktime = onofflist.get(i+2).getOnOffTime();
						int onlinedays = DateUtils.selectDateDiff(onchecktime, offchecktime);
						if(onlinedays == 0){
							onlinedays = 1;
						}
						offdays = offdays + onlinedays;
					}
				}
			}
			map.put("offdays", offdays);
			map.put("onOffTime", onOffTime);
		}
		map.put("type", type);
		return map;
	}

}