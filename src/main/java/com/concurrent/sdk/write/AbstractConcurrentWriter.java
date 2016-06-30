package com.concurrent.sdk.write;

import com.concurrent.sdk.container.MemoryPage;

public abstract class AbstractConcurrentWriter<T> implements IConcurrentWriter<T> {

	public abstract void write(MemoryPage<T> page);
	
}
