package com.concurrent.sdk.common;

public class ConfigVariable {
	private static int writeThreadSize = NumUtils.toInt(PropertiesUtils.getValue("write.thread.size"),0);
	private static int writerSize = NumUtils.toInt(PropertiesUtils.getValue("writer.size"),0);
	private static int memoryMaxSize = NumUtils.toInt(PropertiesUtils.getValue("memory.max.size"),0);
	private static int memoryTimeout = NumUtils.toInt(PropertiesUtils.getValue("memory.timeout"),0);
	
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
