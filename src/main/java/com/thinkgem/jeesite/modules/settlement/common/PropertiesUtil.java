package com.thinkgem.jeesite.modules.settlement.common;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


public class PropertiesUtil {
	
	private Properties properties;
	
	public Properties getProperties() {
		return properties;
	}


	public void setProperties(Properties properties) {
		this.properties = properties;
	}


	public PropertiesUtil() {
		properties = new Properties();
	}
	
		
	/**
	 * 初始化Configuration类
	 * @param filePath: 要读取的配置文件的路径+名称
	 * @throws IOException 
	 */
	public PropertiesUtil(String filePath) throws IOException {
		load(filePath);
	}
	
	public void loadResourceAsStream(String path) throws IOException{
		properties = new Properties();
		InputStream inputFile=null;
		try {
			inputFile = getClass().getResourceAsStream(path);
			properties.load(inputFile);
			inputFile.close();
		} finally{
			closeQuietly(inputFile);
		}
	}


	private void closeQuietly(Closeable inputFile) {
		if (inputFile != null) {
			try {
				inputFile.close();
			} catch (Exception e) {
			}
		}
	}
	
	public void load(String filePath)throws IOException{
		properties = new Properties();
		InputStream inputFile=null;
		try {
			File f=new File(filePath);
			if(!f.exists())f.createNewFile();
			inputFile = new FileInputStream(filePath);
			properties.load(inputFile);
			inputFile.close();
		} finally{
			closeQuietly(inputFile);
		}
	}
	
	public void load(InputStream is){
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			closeQuietly(is);
		}
	}
	
	/**
	 * 初始化Configuration类
	 * @param file: 要读取的配置文件的file
	 * @throws IOException 
	 */
	public PropertiesUtil(File file) throws IOException {
		this(file.getAbsolutePath());
	}

	/**
	 * 重载函数，得到key的值
	 * @param key:取得其值的键
	 * @return key的值
	 */
	public String getValue(String key) {
		if (properties.containsKey(key)) {
			String value = properties.getProperty(key);// 得到某一属性的值
			return value;
		} else
			return "";
	}
	
	public void deleteKey(String key){
		properties.remove(key);
	}

	/**
	 * 重载函数，得到key的值
	 * @param filePath:properties文件的路径+文件名
	 * @param key:取得其值的键
	 * @return key的值
	 * @throws IOException 
	 */
	public String getValue(String filePath, String key) throws IOException {
		load(filePath);
		if(properties.containsKey(key)){
			return properties.getProperty(key);
		}else{
			return "";
		}
	}

	/**
	 * 将能匹配字符串的信息写入Map，保存在内存中
	 * @param filePath:文件路径+文件名称
	 * @param MatchStrKey:要匹配的字符串
	 * @return 一个Map(*.png,10)
	 * @throws Exception
	 */
	public Map<String, String> getMatchStrValue(String filePath, String MatchStrKey) throws Exception {
		load(filePath);
		Map<String, String> result = new HashMap<String, String>();
		Iterator<Object> iter=properties.keySet().iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			if(key!=null && key.startsWith(MatchStrKey)){
				result.put(key, properties.getProperty(key));
			}
		}
		return result;
	}
	
	
	public Map<String,String> getMap(){
		Map<String, String> result = new HashMap<String, String>();
		Iterator<Object> iter=properties.keySet().iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			result.put(key, properties.getProperty(key));
		}
		return result;
	}

	/**
	 * 判断文件中是否已经含有子字符串
	 * @param filePath:文件路径+文件名称
	 * @param MatchStrKey:子字符串
	 * @return 布尔型数据(true:含有，false:不含有)
	 */
	public boolean isHaveValue(String filePath, String MatchStrKey) throws Exception {
		load(filePath);
		Iterator<Object> iter=properties.keySet().iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			if(key!=null && key.startsWith(MatchStrKey)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除一个属性
	 * 
	 * @param filePath:文件路径+文件名称
	 * @param key:属性名
	 * @throws Exception
	 */
	public void deleteRow(String filePath, String key) throws Exception {
		load(filePath);
		properties.remove(key);
		save(filePath,"");
	}
	
	private void save(String filePath,String node) throws IOException{
		OutputStream out=null;
		try {
			File f=new File(filePath);
			if(!f.exists())f.createNewFile();
			out=new FileOutputStream(f);
			properties.store(out, node);
		}finally{
			closeQuietly(out);
		}
	}
	
	/**
	 * 删除一个属性
	 * 
	 * @param filePath:文件路径+文件名称
	 * @param key:属性名
	 * @throws Exception
	 */
	public void deleteRow(File file, String key) throws Exception {
		deleteRow(file.getAbsolutePath(), key);
	}

	/**
	 * 清除properties文件中所有的key和其值
	 */
	public void clear() {
		properties.clear();
	}

	/**
	 * 改变或添加一个key的值，当key存在于properties文件中时该key的值被value所代替， 当key不存在时，该key的值是value
	 * @param key:要存入的键
	 * @param value:要存入的值
	 */
	public void setValue(String key, String value) {
		properties.setProperty(key, value);
	}

	/**
	 * 将更改后的文件数据存入指定的文件中，该文件可以事先不存在。
	 * @param filePath:文件路径+文件名称
	 * @param description:对该文件的描述
	 * @throws IOException 
	 */
	public void saveFile(String filePath, String description) throws IOException {
		save(filePath, description);
	}
	
	public void saveFile(File file,String description) throws IOException{
		save(file.getAbsolutePath(), description);
	}

//	public static void main(String[] args) {
//		FileOperate fileOperate = new FileOperate(FilePath.FILE_PATH);
//		fileOperate.setValue("upload", "C:\\Documents and Settings\\j2ee\\MyDocuments\\新建文件夹");
//		fileOperate.saveFile(FilePath.FILE_PATH, "iFile用户路径");
//		String value = fileOperate.getValue(FilePath.FILE_PATH, "upload");
//	}
}