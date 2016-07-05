package com.thinkgem.jeesite.tools;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class APIHttpClient {
    // 接口地址
    private static String apiURL = "http://192.168.3.67:8080/lkgst_manager/order/order";
    private Log logger = LogFactory.getLog(this.getClass());
    private static final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
    private CloseableHttpClient httpClient = null;
    private HttpPost method = null;
    private long startTime = 0L;
    private long endTime = 0L;
    private int status = 0;

    /**
     * 接口地址
     * 
     * @param url
     */
    public APIHttpClient(String url) {
        if (url != null && url.trim().length() > 0) {
            apiURL = url;
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            httpClient = httpClientBuilder.build();
            method = new HttpPost(apiURL);
        }
    }

    /**
     * 调用 API
     * @param parameters
     * @return
     */
    public String post(String parameters) {
        String body = null;
        logger.info("parameters:" + parameters);
        if (method != null & parameters != null && !"".equals(parameters.trim())) {
            try {
                // 建立一个NameValuePair数组，用于存储欲传送的参数
                method.addHeader("Content-type","application/json; charset=utf-8");
                method.setHeader("Accept", "application/json");
                method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
                startTime = System.currentTimeMillis();
                HttpResponse response = httpClient.execute(method);
                endTime = System.currentTimeMillis();
                int statusCode = response.getStatusLine().getStatusCode();
                logger.info("statusCode:" + statusCode);
                logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
                if (statusCode != HttpStatus.SC_OK) {
                    logger.error("Method failed:" + response.getStatusLine());
                    status = 1;
                }
                // Read the response body
                body = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                // 网络错误
                status = 3;
            } finally {
                logger.info("调用接口状态：" + status);
            }
        }
        return body;
    }

    public static void main(String[] args) {
        APIHttpClient.apiURL = "http://localhost:8081/fi/ac/test";
        APIHttpClient ac = new APIHttpClient(apiURL);
//        JsonArray arry = new JsonArray();
//        JsonObject j = new JsonObject();
//        j.addProperty("orderId", "中文");
//        j.addProperty("createTimeOrder", "2015-08-11");
//        arry.add(j);
//        System.out.println(ac.post(arry.toString()));
        JSONObject json = new JSONObject();
        json.put("id", 123);
        json.put("hotelid", 999);
        json.put("hotelname", "测试酒店");
        
        JSONObject page = new JSONObject();
        page.put("paegNo", 1);
        page.put("pageSize", 10);
        json.put("page", page);
        System.out.println(ac.post(json.toJSONString()));
    }

    /**
     * 0.成功 1.执行方法失败 2.协议错误 3.网络错误
     * 
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }
}
