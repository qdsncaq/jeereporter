package com.thinkgem.jeesite.modules.settlement.common;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

public class NetUtil {

    protected static Logger logger = LoggerFactory.getLogger(NetUtil.class);

    private static int timeout = 32 * 1000;
    
    /**
     * 
     * @param url
     * @param send
     * @return
     * @throws Exception
     */
    public static String httpPostjson(String url, String send) throws Exception {
        logger.info("请求地址：url:{},timeout:{}", url, timeout);
        return postJsonAsHttp(url, send, timeout, 3);
    }
    
    /**
     * 
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String httpPostParameters(String url, String send) throws Exception {
        logger.info("请求地址：url:{}", url);
        return postParametersAsHttp(url, send);
    }
    
    public static String httpGetjson(String url, String send) throws Exception {
        logger.info("GET请求地址：url:{},timeout:{}", url, timeout);
        return getJsonAsHttp(url, send, timeout, 3);
    }

    public static String httpsPostJson(String url, String send)
            throws Exception {
        logger.info("请求地址：url:{},timeout:{}", url, timeout);
        return postJsonAsHttps(url, send, timeout, 3);
    }

    /**
     * 
     * @param url
     * @param send
     * @param timeout
     * @param tryTime
     * @return
     * @throws Exception
     */
    public static String postJsonAsHttp(String url, String send, int timeout, int tryTime) throws Exception {
        String response = "";
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        try {
            HttpPost post = new HttpPost(url);
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            post.addHeader("Content-type","application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity(send, Charset.forName("UTF-8")));
            long startTime = System.currentTimeMillis();
            HttpResponse httpResponse = client.execute(post);
            long endTime = System.currentTimeMillis();
            // 响应状态
            StatusLine statusLine = httpResponse.getStatusLine();
            logger.info("statusLine: {}", statusLine); 
            int status = statusLine.getStatusCode();
            logger.info("statusCode:" + status);
            logger.info("调用API: {} 花费时间: {}(单位：毫秒)：", url, endTime - startTime);
            if (status == HttpStatus.SC_OK) {
                logger.info("访问url:{} successful: {}", url, status);
            } else {
                logger.info("访问url:{} failed: {}", url, status);
                throw new HTTPException(status);
            }
            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            // 判断响应实体是否为空  
            if (entity != null) {
                Header encoding = entity.getContentEncoding();
                response = EntityUtils.toString(entity);
                logger.info("contentEncoding: {}", encoding);
                logger.info("response content:{}", response);
                return response;
            }
        } catch (IOException e) {
            logger.error("postJsonAsHttp error: {}", e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e2) {
                logger.error("client close error: {}", e2.getLocalizedMessage());
                e2.printStackTrace();
            }
        }
        return response;
    }
    
    /**
     * 
     * @param url
     * @param send
     * @param timeout
     * @param tryTime
     * @return
     * @throws Exception
     */
    public static String postParametersAsHttp(String url, String send) throws Exception {
        String response = "";
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        try {
            JSONObject params = JSONObject.parseObject(send);
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                BasicNameValuePair pair = new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue()));
                pairList.add(pair);
            }
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            StringEntity postEntity = new UrlEncodedFormEntity(pairList, Charset.forName("utf-8"));
            post.setEntity(postEntity);
            HttpResponse httpResponse = client.execute(post);
            // 响应状态
            StatusLine statusLine = httpResponse.getStatusLine();
            logger.info("statusLine: {}", statusLine); 
            int status = statusLine.getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("访问url:{},返回状态:{}", url, status);
            } else {
                logger.info("访问url:{},返回状态:{}", url, status);
                throw new HTTPException(status);
            }
            
            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            // 判断响应实体是否为空  
            if (entity != null) {
                Header encoding = entity.getContentEncoding();
                response = EntityUtils.toString(entity);
                logger.info("contentEncoding: {}", encoding);
                logger.info("response content:{}", response);
                return response;
            }
        } catch (IOException e) {
            logger.error("postJsonAsHttp error: {}", e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e2) {
                logger.error("client close error: {}", e2.getLocalizedMessage());
                e2.printStackTrace();
            }
        }
        return response;
    }
    
    public static String getJsonAsHttp(String url, String send, int timeout, int tryTime) throws Exception {
        String response = "";
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        try {
            JSONObject params = JSONObject.parseObject(send);
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                BasicNameValuePair pair = new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue()));
                pairList.add(pair);
            }
            //要传递的参数.  
            url = url + URLEncodedUtils.format(pairList, "UTF-8");  
            HttpGet get = new HttpGet(url);
            HttpResponse httpResponse = client.execute(get);
            // 响应状态
            StatusLine statusLine = httpResponse.getStatusLine();
            logger.info("statusLine: {}", statusLine); 
            int status = statusLine.getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("访问url:{},返回状态:{}", url, status);
            } else {
                logger.info("访问url:{},返回状态:{}", url, status);
                throw new HTTPException(status);
            }
            
            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            // 判断响应实体是否为空  
            if (entity != null) {
                Header encoding = entity.getContentEncoding();
                response = EntityUtils.toString(entity);
                logger.info("contentEncoding: {}", encoding);
                logger.info("response content:{}", response);
                return response;
            }
        } catch (IOException e) {
            logger.error("postJsonAsHttp error: {}", e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e2) {
                logger.error("client close error: {}", e2.getLocalizedMessage());
                e2.printStackTrace();
            }
        }
        return response;
    }

    /**
     * @param url
     * @param sendParamStr
     * @param timeout
     * @param tryTime
     * @return
     * @throws Exception
     */
    public static String postJsonAsHttps(String url, String sendParamStr,
            int timeout, int tryTime) throws Exception {
        Transaction t = Cat.newTransaction("OtsHttpsPost", url);
        CloseableHttpClient httpclient = null;
        int code = 0;
        try {
            HttpPost post = new HttpPost(url);

            StringEntity entity = new StringEntity(sendParamStr,
                    ContentType.create("application/json", "UTF-8"));
            post.setEntity(entity);
            httpclient = createSSLClientDefault(null);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(timeout).setConnectTimeout(timeout)
                    .build();// 设置请求和传输超时时间
            post.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(post);
            code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                t.setStatus(Transaction.SUCCESS);
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new HTTPException(code);
            }
        } catch (Exception e) {
            t.setStatus(e);
            throw e;
        } finally {
            if (httpclient != null) {
                httpclient.close();
            }
            t.complete();
        }
    }

    public static CloseableHttpClient createSSLClientDefault(
            CookieStore cookieStore) throws KeyManagementException,
            NoSuchAlgorithmException, KeyStoreException {

        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
                new TrustStrategy() {
                    // 信任所有
                    public boolean isTrusted(X509Certificate[] chain,
                            String authType) throws CertificateException {
                        return true;
                    }
                }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext);
        if (cookieStore != null) {
            return HttpClients.custom().setSSLSocketFactory(sslsf)
                    .setDefaultCookieStore(cookieStore).build();
        } else {
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        }
    }

    public static void setTimeout(int _timeout) {
        timeout = _timeout;
    }
}
