/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oms.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.crm.user.entity.SyOrgUser;
import com.thinkgem.crm.user.service.SyOrgUserService;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.db.DataSourceContextHolder;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oms.dao.OmsOrderDao;
import com.thinkgem.jeesite.modules.oms.dao.OmsOrderItemDao;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticImpObj;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticsDirect;
import com.thinkgem.jeesite.modules.oms.entity.OmsLogisticsGoods;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrder;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderItem;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderItemSplit;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderLogistic;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderLogisticExp;
import com.thinkgem.jeesite.modules.oms.entity.OmsOrderReport;
import com.thinkgem.jeesite.modules.oms.utils.LogisticUtils;

/**
 * OMS订单Service
 * @author jiajianhong
 * @version 2016-03-17
 */
@Service
@DataSourceName("oms")
@Transactional(readOnly = true)
public class OmsOrderService extends CrudService<OmsOrderDao, OmsOrder> {

    @Autowired
    private OmsOrderItemDao omsOrderItemDao;

    @Autowired
    private SyOrgUserService syOrgUserService;

    @Autowired
    private OmsOrderItemService omsOrderItemService;
    
    @Autowired
    private OmsOrderDao omsOrderDao;
    
    @Autowired
    private OmsOrderItemSplitService orderItemSplitService;
    
    

	public OmsOrder get(Long orderId) {
        OmsOrder temp = new OmsOrder();
        temp.setOrderId(orderId);
        OmsOrder omsOrder = super.get(temp);
        omsOrder.setOmsOrderItemList(omsOrderItemDao.findList(new OmsOrderItem(omsOrder)));
		return omsOrder;
	}

	public List<OmsOrder> findList(OmsOrder omsOrder) {
		List<OmsOrder> omsOrderList = super.findList(omsOrder);

        // 把销售id转换为销售名,跨库查询
       // getSalerNameById(omsOrderList);

        return omsOrderList;
	}

    private void getSalerNameById(List<OmsOrder> omsOrderList) {
        // 由于跨库,需要切换数据源
        String dbTarget = DataSourceContextHolder.getDbTarget();
//        DataSourceContextHolder.setDbTarget("crm");

        for (OmsOrder omsOrder : omsOrderList) {
            if (!StringUtils.isEmpty(omsOrder.getMarketid())) {
                SyOrgUser syOrgUser = syOrgUserService.get(omsOrder.getMarketid());
                if (null != syOrgUser) {
                    omsOrder.setMarketName(syOrgUser.getUserName());
                }
            }
        }

        // 查询完毕之后,还要回归期初数据源
//        DataSourceContextHolder.setDbTarget(dbTarget);
    }

    public Page<OmsOrderReport> findReportPage(Page<OmsOrderReport> page, OmsOrderReport omsOrder) {

    	omsOrder.setPage(page);
    	List<OmsOrderReport> list=omsOrderDao.findReport(omsOrder);
		page.setList(list);
		return page;
	}
    
    
    
    
    
    public List<OmsOrderReport> findReport( OmsOrderReport omsOrder) {

    	List<OmsOrderReport> list=omsOrderDao.findReport(omsOrder);
		return list;
	}
    
    /**
     * 
     * @param page 分页对象
     * @param orderLogistic 订单物流的对象 
     * @return 查询的列表
     */
    public Page<OmsOrderLogistic> findLogisticsReportPage(Page<OmsOrderLogistic> page, OmsOrderLogistic orderLogistic) {
    	orderLogistic.setPage(page);
    	List<OmsOrderLogistic> list = omsOrderDao.findLogisticsReportPage(orderLogistic);
    	addLogisticsForOrders(list);
    	
    	page.setList(list);
		
		return page;
	}
    
    /**
     * 
     * @param orderLogisticExp 订单物流的对象 
     * @return 查询的列表
     */
    public List<OmsOrderLogisticExp> findLogisticsReport(OmsOrderLogisticExp orderLogisticExp) {
    	List<OmsOrderLogisticExp> list = omsOrderDao.findLogisticsReportExp(orderLogisticExp);
    	addLogisticsExpForOrders(list);
		
		return list;
	}
    
    
	
	/**
	 * 导出物流信息 - 不分页
	 * @param omsOrder 
	 * @return 
	 */
    @Transactional(readOnly = false)
	public List<OmsOrderLogistic> findLogisticsReport(OmsOrderLogistic omsOrder) {
		if (StringUtils.isBlank(omsOrder.getInvoiceTime())) { //没有设置预发货时间 ， 默认给昨天
//			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.DATE, -1);
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String timeStamp = sdf.format(calendar.getTime());
//			
//			omsOrder.setInvoiceTime(timeStamp + " 23:59:59");
			
			//当前时间 
			omsOrder.setInvoiceTime(DateUtils.getDateTime());
		}
		
	  	List<OmsOrderLogistic> list = omsOrderDao.findLogisticsReport(omsOrder);
	  	
	  	//对这些记录拆分到表 oms_order_item_split 里面去， 
	  	List<OmsOrderLogistic> newList = splitItems(list);
	  	
	  	addLogisticsForOrders(newList);
	  	
		return newList;
	}
	
	/**
	 * 
	 * @param list
	 */
	@Transactional(readOnly = false)
	private List<OmsOrderLogistic> splitItems(List<OmsOrderLogistic> list) {
	  	//查询到的运单号的最大值
	  	Integer maxYundan = orderItemSplitService.findMaxYundan();
	  	if (null == maxYundan) {
	  		maxYundan = 100000;
	  	}
		
	  	List<OmsOrderLogistic> newList = new ArrayList<OmsOrderLogistic>();
	  	//查询完成之后，处理数据， 商品 按条数 分拆
	  	for (OmsOrderLogistic oriData: list) {
	  		int num = oriData.getNum();
	  		
	  		for (int i = 0; i < num; i++) {
	  			OmsOrderLogistic destData = new OmsOrderLogistic();
	  			try {
					BeanUtils.copyProperties(destData, oriData);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
	  			
	  			++maxYundan; //先加上1
	  			destData.setNum(1);
	  			destData.setYundanCode(maxYundan);  //设置运单号
	  			
	  			OmsOrderItemSplit splitObj = new OmsOrderItemSplit();
	  			splitObj.setItemId(oriData.getItemId());
	  			splitObj.setYundanCode(maxYundan);
	  			splitObj.setOrderId(oriData.getOrderId());
	  			orderItemSplitService.save(splitObj);
	  			
	  			newList.add(destData);
	  		}
	  	}
		
		return newList;
	}

	/**
	 * 
	 * @param logistics 查询出来的结果
	 * @return 添加上处理信息的结果
	 */
	private void addLogisticsForOrders(List<OmsOrderLogistic> logistics) {
		for (OmsOrderLogistic logistic: logistics) {
			String goodCode = logistic.getOuterId();
			String state = logistic.getReceiverState();
			
			//判断商品是否符合直送， 是 则设置  直配送厂家 , 这里缓存获取
			OmsLogisticsDirect direct = LogisticUtils.getLogisticsDirect(goodCode, state);
			if (null != direct) {
				logistic.setVendorName(direct.getVendorName());
			} else {
				//查询 配送公司 
				OmsLogisticsGoods good = LogisticUtils.getLogisticsGoods(goodCode, state);
				if (null != good) {
					logistic.setPeiSongCmpy(good.getLogisticsName());
					
					logistic.setTiHuoCangku(good.getWarehouseName());
					logistic.setWareHouseName(good.getWarehouseName());
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @param logistics 查询出来的结果
	 * @return 添加上处理信息的结果
	 */
	private void addLogisticsExpForOrders(List<OmsOrderLogisticExp> logistics) {
		for (OmsOrderLogisticExp logistic: logistics) {
			String goodCode = logistic.getOuterId();
			String state = logistic.getReceiverState();
			
			//判断商品是否符合直送， 是 则设置  直配送厂家 , 这里缓存获取
			OmsLogisticsDirect direct = LogisticUtils.getLogisticsDirect(goodCode, state);
			if (null != direct) {
				logistic.setVendorName(direct.getVendorName());
			} else {
				//查询 配送公司 
				OmsLogisticsGoods good = LogisticUtils.getLogisticsGoods(goodCode, state);
				if (null != good) {
					logistic.setPeiSongCmpy(good.getLogisticsName());
					
					logistic.setTiHuoCangku(good.getWarehouseName());
					logistic.setWareHouseName(good.getWarehouseName());
				}
			}
		}
	}
    
    
    public Page<OmsOrder> findPage(Page<OmsOrder> page, OmsOrder omsOrder) {

		Page<OmsOrder> omsOrderPage = super.findPage(page, omsOrder);

        // 获取销售名称
       // getSalerNameById(omsOrderPage.getList());

        return omsOrderPage;
	}
	
	@Transactional(readOnly = false)
	public void save(OmsOrder omsOrder) {
		super.save(omsOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(OmsOrder omsOrder) {
		super.delete(omsOrder);
	}
	
	@Transactional(readOnly = false)
	public int updateReceiver(OmsOrder order){
		return omsOrderDao.updateReceiver(order);
	}
	
	/**
	 * 查询发票关联的订单列表
	 * @param invoiceid
	 * 参数：发票id
	 * @return
	 */
	public List<OmsOrder> findInvoiceOrders(String invoiceid) {
	    return omsOrderDao.findInvoiceOrders(invoiceid);
	}
	
	
	/**
	 * 
	 * @param logistics 需要修改的订单
	 */
	@Transactional(readOnly = false)
	public void addPiciForExp(List<OmsOrderLogistic> logistics) {
		if (logistics.size() == 0) {
			return;
		}
		
		//修改订单的    导出时间， 
		Set<Long> orderIdSet = new HashSet<Long>();
		Set<Long> orderItemIdSet = new HashSet<Long>();
		for (OmsOrderLogistic orderLogistics: logistics) {
			orderIdSet.add(orderLogistics.getOrderId());
			
			long itemId = orderLogistics.getItemId();
			if (!orderIdSet.contains(itemId)) {
				orderItemIdSet.add(orderLogistics.getItemId());
			}
		}
		
		addPiciForExp(orderIdSet);
	}
	
	
	/**
	 * 
	 * @param orderIdSet 需要修改的订单
	 */
	@Transactional(readOnly = false)
	public void addPiciForExp(Set<Long> orderIdSet) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = sdf.format(calendar.getTime());

		omsOrderDao.updateOrdersExpBatch(timeStamp, orderIdSet);
	}
	
	
	/**
	 * 
	 * @param orderIdSet 需要修改的订单
	 */
	@Transactional(readOnly = false)
	public Integer updateForImp(OmsLogisticImpObj rowObj) {
		
		
		
		//将运单号，签收状态 保存到 oms_order_item表
		return orderItemSplitService.updateForImp(rowObj);
		
		
//		//将签收状态 保存到 oms_order表
//		
//		omsOrderDao.updateForImp(rowObj);
	}
}