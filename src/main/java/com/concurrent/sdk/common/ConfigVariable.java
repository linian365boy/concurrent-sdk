package com.concurrent.sdk.common;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @ClassName: ConfigVariable  
 * @Description: 变量管理类
 * @date: 2016年7月1日 下午4:16:00 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public class ConfigVariable {
	private static int writeThreadSize = NumberUtils.toInt(PropertiesUtils.getValue("write.thread.size"),0);
	private static int writerSize = NumberUtils.toInt(PropertiesUtils.getValue("writer.size"),0);
	private static int memoryMaxSize = NumberUtils.toInt(PropertiesUtils.getValue("memory.max.size"),0);
	private static int memoryTimeout = NumberUtils.toInt(PropertiesUtils.getValue("memory.timeout"),0);
	
	public static int getWriteThreadSize() {
		return writeThreadSize;
	}
	public static int getMemoryMaxSize() {
		return memoryMaxSize;
	}
	public static int getMemoryTimeout() {
		return memoryTimeout;
	}
	public static void setWriteThreadSize(int writeThreadSize) {
		ConfigVariable.writeThreadSize = writeThreadSize;
	}
	public static void setMemoryMaxSize(int memoryMaxSize) {
		ConfigVariable.memoryMaxSize = memoryMaxSize;
	}
	public static void setMemoryTimeout(int memoryTimeout) {
		ConfigVariable.memoryTimeout = memoryTimeout;
	}
	public static int getWriterSize() {
		return writerSize;
	}
	public static void setWriterSize(int writerSize) {
		ConfigVariable.writerSize = writerSize;
	}
}
