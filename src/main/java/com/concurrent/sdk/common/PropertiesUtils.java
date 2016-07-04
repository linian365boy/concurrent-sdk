package com.concurrent.sdk.common;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: PropertiesUtils  
 * @Description: 读取配置文件工具类 
 * @date: 2016年7月1日 下午4:46:59 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public class PropertiesUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	private static Properties prop = null;
	private static String fileName = "concurrentWrite";
	
	static {
		initProperties();
	}
	
	public static void initProperties(){
		prop = new Properties();
		try {
			prop.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName+".properties"));
			logger.debug("load prop value|{}",prop);
		} catch (IOException e) {
			logger.error("加载{}文件出错:{}",fileName,e);
		}
	}
	
	public static String getValue(String key, String defaultValue){
		if(prop==null){
			return defaultValue;
		}
		return prop.getProperty(key);
	}
	
	public static String getValue(String key){
		return getValue(key,null);
	}
	
}
