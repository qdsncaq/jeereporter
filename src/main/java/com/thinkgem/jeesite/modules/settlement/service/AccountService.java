package com.thinkgem.jeesite.modules.settlement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.settlement.common.NetUtil;
import com.thinkgem.jeesite.modules.settlement.common.UrlUtils;
import com.thinkgem.jeesite.modules.settlement.entity.AccountLedger;
import com.thinkgem.jeesite.modules.settlement.entity.AccountRunning;
import com.thinkgem.jeesite.modules.settlement.entity.HotelWeeklyBill;
import com.thinkgem.jeesite.modules.settlement.entity.ResponseData;

/**
 * 
 * @author chuaiqing.
 *
 */
@Service
public class AccountService {
    /**
     * 日志对象
     */
    private Logger log = LoggerFactory.getLogger(AccountService.class);
    
    /**
     * 
     * @param page
     * @param accountRunning
     * @return
     */
    public Page<AccountRunning> requestAccountRunningList(Page<AccountRunning> page, AccountRunning accountRunning) {
        List<AccountRunning> list = Lists.newArrayList();
        try {
            String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
            String settlementConUrl = stlmt_server_url + "/fi/ac/details";
            String result = "";
            accountRunning.setPage(page);
            String paramsData = new JsonMapper().toJson(accountRunning);
            result = NetUtil.httpPostjson(settlementConUrl, paramsData);
            if (result != null && result.trim().length() > 0) {
                ResponseData responseData = JSONObject.parseObject(result, ResponseData.class);
                if (responseData != null) {
                    JSONObject resultData = (JSONObject) responseData.getResultData();
                    JSONObject pageData = resultData.getJSONObject("page");
                    long count = pageData.getLongValue("count");
                    JSONArray details = pageData.getJSONArray("list");
                    for (int i = 0; i < details.size(); i++) {
                        JSON json = details.getJSONObject(i);
                        list.add(JSONObject.toJavaObject(json, AccountRunning.class));
                    }
                    page.setCount(count);
                    page.setList(list);
                }
            } else {
                log.info("request url: {}, parameters: {} failure.", settlementConUrl, paramsData);
            }
        } catch (Exception e) {
            log.error("AccountService::requestAccountRunningList method error: {}", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return page;
    }
    
    /**
     * 查询账户
     * @param page
     * @param accountLedger
     * @return
     */
    public Page<AccountLedger> requestAccountLedgerList(Page<AccountLedger> page, AccountLedger accountLedger) {
        List<AccountLedger> list = Lists.newArrayList();
        try {
            String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
            String settlementConUrl = stlmt_server_url + "/fi/ac/ledgers";
            String result = "";
            JSONObject params = new JSONObject();
            int pageNo = page.getPageNo();
            int pageSize = 10;
            int limitStart = (pageNo - 1) * pageSize + 1;
            int limitEnd = pageNo * pageSize;
            params.put("userid", null);
            params.put("start", limitStart);
            params.put("end", 300);
            result = NetUtil.httpPostjson(settlementConUrl, params.toString());
            if (result != null && result.trim().length() > 0) {
                ResponseData responseData = JSONObject.parseObject(result, ResponseData.class);
                if (responseData != null) {
                    JSONObject resultData = (JSONObject) responseData.getResultData();
                    JSONArray ledgers = resultData.getJSONArray("ledgers");
                    for (int i = 0; i < ledgers.size(); i++) {
                        JSON json = ledgers.getJSONObject(i);
                        list.add(JSONObject.toJavaObject(json, AccountLedger.class));
                    }
                }
            } else {
                log.info("request url: {}, parameters: {} failure.", settlementConUrl, params);
            }
            page.setList(list);
            page.setCount(list.size());
            
//    		page.setPageNo(pageObj.getIntValue("pageNo"));
//    		page.setPageSize(pageObj.getIntValue("pageSize"));
//    		page.setCount(pageObj.getLongValue("totalRecord"));
//    		page.setList(pageObj.getJSONArray("result"));
            
        } catch (Exception e) {
            log.error("AccountService::requestAccountLedgerList method error: {}", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return page;
    }
    
    public Page<HotelWeeklyBill> requestHotelWeeklyBill(Page<HotelWeeklyBill> page, HotelWeeklyBill entity) {
        List<HotelWeeklyBill> list = Lists.newArrayList();
        try {
            String stlmt_server_url = UrlUtils.getUrl("stlmt.server.url");
            String settlementConUrl = stlmt_server_url + "/fi/ac/hotelweeklybill";
            entity.setPage(page);
            String paramsData = new JsonMapper().toJson(entity);
            String result = null;
            //result = NetUtil.httpPostParameters(settlementConUrl, paramsData);
            result = NetUtil.httpPostjson(settlementConUrl, paramsData);
            if (result != null && result.trim().length() > 0) {
                ResponseData responseData = JSONObject.parseObject(result, ResponseData.class);
                if (responseData != null) {
                    JSONObject resultData = (JSONObject) responseData.getResultData();
                    JSONArray billList = resultData.getJSONArray("list");
                    for (int i = 0; i < billList.size(); i++) {
                        JSON json = billList.getJSONObject(i);
                        list.add(JSONObject.toJavaObject(json, HotelWeeklyBill.class));
                    }
                }
            } else {
                log.info("request url: {}, parameters: {} failure.", settlementConUrl, paramsData);
            }
            page.setList(list);
            page.setCount(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }
}
