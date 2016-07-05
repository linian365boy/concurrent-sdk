package com.concurrent.sdk.write;

import com.concurrent.sdk.container.MemoryPage;

/**
 * @ClassName: AbstractConcurrentWriter  
 * @Description: 抽象落地的类 
 * @date: 2016年7月1日 下午4:54:04 
 * 
 * @author tanfan 
 * @version @param <T> 
 * @since JDK 1.7
 */
public abstract class AbstractConcurrentWriter<T> implements IConcurrentWriter<T> {
	public abstract void write(MemoryPage<T> page);
}
