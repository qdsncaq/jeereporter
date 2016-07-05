package com.thinkgem.jeesite.modules.settlement.common;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 地址管理工具
 * 
 * @author zzy
 *
 */
public class UrlUtils {

	static Map<String, String> urls = new HashMap<String, String>();
	static {
		reloadYml();
	}

	/**
	 * 获取url
	 * 
	 * @param key 格式newpms.url、ots.url
	 * @return
	 */
	public static String getUrl(String key) {
		return urls.get(key);
	}

	public static void reloadYml() {
		PropertiesUtil pro = new PropertiesUtil();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("common_urls.properties");
		if (is != null) {
			pro.load(is);
			urls = pro.getMap();
		}
	}
}
