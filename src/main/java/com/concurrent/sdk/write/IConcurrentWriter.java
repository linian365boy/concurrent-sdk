package com.concurrent.sdk.write;

import com.concurrent.sdk.common.IConcurrent;
import com.concurrent.sdk.container.MemoryPage;
import com.cuncurrent.sdk.test.Sku;

public interface IConcurrentWriter<T> {

	void write(MemoryPage<T> page);
	/**
	 * getWriter: 为了对同一个t，在相同的线程内处理，可以去重等操作
	 * @author tanfan 
	 * @param t
	 * @return 
	 * @since JDK 1.7
	 */
	IConcurrent<T> getWriter(T t);
	/**
	 * getWriters:获取所有的写线程 
	 * @author tanfan 
	 * @return 
	 * @since JDK 1.7
	 */
	public abstract IConcurrent<Sku>[] getWriters();
}
