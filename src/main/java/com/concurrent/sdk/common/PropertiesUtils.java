package com.concurrent.sdk.common;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			prop.load(PropertiesUtils.class.getResourceAsStream(fileName+".properties"));
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
