package com.concurrent.sdk.read;

/**
 * @ClassName: IConcurrentReader  
 * @Description: 读入数据类，单线程 
 * @date: 2016年7月1日 下午5:06:19 
 * 
 * @author tanfan 
 * @version @param <T> 
 * @since JDK 1.7
 */
public interface IConcurrentReader<T> {
	void read(T t);
	/**
	 * stop:停止并发写的任务 
	 * @author tanfan  
	 * @since JDK 1.7
	 */
	void stop();
}
