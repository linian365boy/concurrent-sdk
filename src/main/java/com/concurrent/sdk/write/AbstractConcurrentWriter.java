package com.concurrent.sdk.write;

import com.concurrent.sdk.common.IConcurrent;
import com.concurrent.sdk.container.MemoryPage;
import com.cuncurrent.sdk.test.Sku;

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
	public abstract IConcurrent<T> getWriter(T t);
	
	public IConcurrent<Sku>[] getWriters(){
		return null;
	}
}
