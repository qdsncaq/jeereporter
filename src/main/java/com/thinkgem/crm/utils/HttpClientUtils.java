package com.thinkgem.crm.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http client 工具类
 *
 * @author hdy
 */
public class HttpClientUtils {

    // 超时间隔
    private static int connectTimeOut = 20000;
    // 返回数据编码格式
    private static String encoding = "UTF-8";

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * post请求
     *
     * @param address 请求地址
     * @param params  参数集合
     * @throws IOException 异常
     */
    public static String bodyPost(String address, String params) throws IOException {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(address);
        // 设置超时
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HttpClientUtils.connectTimeOut).setConnectTimeout(HttpClientUtils.connectTimeOut).build();
        httpPost.setConfig(requestConfig);

        String entityStr = null;
        CloseableHttpResponse response = null;
        try {
            // 创建参数队列
            StringEntity myEntity = new StringEntity(params, HttpClientUtils.encoding);
            httpPost.addHeader("Content-Type", "text/html");
            httpPost.setEntity(myEntity);

            response = httpclient.execute(httpPost);
//			if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                entityStr = EntityUtils.toString(entity, HttpClientUtils.encoding);
                HttpClientUtils.logger.info("Response status code: " + response.getStatusLine().getStatusCode() + ",Response content: " + entityStr);
            }
//			}
        } catch (UnsupportedEncodingException e) {
            HttpClientUtils.logger.error("http请求异常(UnsupportedEncodingException)：" + e.getMessage(), e);
        } catch (ClientProtocolException e) {
            HttpClientUtils.logger.error("http请求异常(ClientProtocolException)：" + e.getMessage(), e);
        } catch (ParseException e) {
            HttpClientUtils.logger.error("http请求异常(ParseException)：" + e.getMessage(), e);
        } catch (IOException e) {
            HttpClientUtils.logger.error("http请求异常(IOException)：" + e.getMessage(), e);
        } finally {
            if (null != response)
                response.close();
            if (null != httpclient)
                httpclient.close();
        }
        return entityStr;
    }

    /**
     * post请求
     *
     * @param address 请求地址
     * @param params  参数集合
     * @throws IOException 异常
     */
    public static String post(String address, Map<String, String> params) throws IOException {
        /*
		 * RestTemplate rest = new RestTemplate(); ResponseEntity<HashMap>
		 * responseEntity = rest.postForEntity(address, params, HashMap.class);
		 * System.out.println(responseEntity.getStatusCode());
		 * System.out.println(responseEntity.getBody().toString());
		 */
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(address);
        // 设置超时
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HttpClientUtils.connectTimeOut).setConnectTimeout(HttpClientUtils.connectTimeOut).build();
        httpPost.setConfig(requestConfig);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        // 追加参数
        for (String s : params.keySet()) {
            formparams.add(new BasicNameValuePair(s, params.get(s)));
        }
        UrlEncodedFormEntity uefEntity = null;
        CloseableHttpResponse response = null;
        String entityStr = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, HttpClientUtils.encoding);
            httpPost.setEntity(uefEntity);
            logger.info(getUrl(address,uefEntity));
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    entityStr = EntityUtils.toString(entity, HttpClientUtils.encoding);
                    HttpClientUtils.logger.info("Response content: " + entityStr);
                }
            }

        } catch (UnsupportedEncodingException e) {
            HttpClientUtils.logger.error("http请求异常(UnsupportedEncodingException)：" + e.getMessage(), e);
        } catch (ClientProtocolException e) {
            HttpClientUtils.logger.error("http请求异常(ClientProtocolException)：" + e.getMessage(), e);
        } catch (ParseException e) {
            HttpClientUtils.logger.error("http请求异常(ParseException)：" + e.getMessage(), e);
        } catch (IOException e) {
            HttpClientUtils.logger.error("http请求异常(IOException)：" + e.getMessage(), e);
        } finally {
            if (null != response)
                response.close();
            if (null != httpclient)
                httpclient.close();
        }
        return entityStr;
    }

    /**
     * https类型post请求
     *
     * @param address 请求地址
     * @param params  参数集合
     * @throws IOException 异常
     */
    public static String httpsPost(String address, Map<String, String> params) throws IOException {
		/*
		 * RestTemplate rest = new RestTemplate(); ResponseEntity<HashMap>
		 * responseEntity = rest.postForEntity(address, params, HashMap.class);
		 * System.out.println(responseEntity.getStatusCode());
		 * System.out.println(responseEntity.getBody().toString());
		 */
        // 创建默认的httpClient实例.
//		CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建https的httpClient实例.
        CloseableHttpClient httpclient = HttpClientUtils.createHttpClient();
        // 创建httppost
        HttpPost httpPost = new HttpPost(address);
        // 设置超时
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HttpClientUtils.connectTimeOut).setConnectTimeout(HttpClientUtils.connectTimeOut).build();
        httpPost.setConfig(requestConfig);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        // 追加参数
        for (String s : params.keySet()) {
            formparams.add(new BasicNameValuePair(s, params.get(s)));
        }
        UrlEncodedFormEntity uefEntity = null;
        CloseableHttpResponse response = null;
        String entityStr = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, HttpClientUtils.encoding);
            httpPost.setEntity(uefEntity);
            response = httpclient.execute(httpPost);
//			if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                entityStr = EntityUtils.toString(entity, HttpClientUtils.encoding);
                HttpClientUtils.logger.info("Response status code: " + response.getStatusLine().getStatusCode() + ",Response content: " + entityStr);
            }
//			}

        } catch (UnsupportedEncodingException e) {
            HttpClientUtils.logger.error("http请求异常(UnsupportedEncodingException)：" + e.getMessage(), e);
        } catch (ClientProtocolException e) {
            HttpClientUtils.logger.error("http请求异常(ClientProtocolException)：" + e.getMessage(), e);
        } catch (ParseException e) {
            HttpClientUtils.logger.error("http请求异常(ParseException)：" + e.getMessage(), e);
        } catch (IOException e) {
            HttpClientUtils.logger.error("http请求异常(IOException)：" + e.getMessage(), e);
        } finally {
            if (null != response)
                response.close();
            if (null != httpclient)
                httpclient.close();
        }
        return entityStr;
    }

    /**
     * 创建https连接的httpClient
     *
     * @return
     */
    private static CloseableHttpClient createHttpClient() {
        SSLContext sslcontext = createSSLContext();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new HostnameVerifier() {

            @Override
            public boolean verify(String paramString, SSLSession paramSSLSession) {
                return true;
            }
        });
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpclient;
    }

    /**
     * 创建https连接认证
     *
     * @return
     */
    private static SSLContext createSSLContext() {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("创建SSLContext失败");
        } catch (KeyManagementException e) {
            throw new RuntimeException("创建SSLContext失败");
        }
        return sslcontext;
    }

    /**
     * 自定义静态私有类
     */
    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }


    /**
     * 输出完整的url
     * @param url 请求的url
     * @param urlEntity UrlEncodedFormEntity对象
     * @return 编译完成的url
     */
    private static String getUrl(String url, UrlEncodedFormEntity urlEntity){
        try {
            String param = convertStreamToString(urlEntity.getContent());
            if(!StringUtils.isBlank(param)){
                url = url +"?"+param;
            }
        } catch (Exception e) {
            return null;
        }
        return url;
    }
    /**
     * InputStream 转换为String
     * @param is 输入流
     * @return String
     */
    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if(is != null){ is.close(); }
                if(reader != null){ reader.close(); }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
