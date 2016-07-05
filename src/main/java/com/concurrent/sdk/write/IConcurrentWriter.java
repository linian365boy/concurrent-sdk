package com.concurrent.sdk.write;

import com.concurrent.sdk.container.MemoryPage;

public interface IConcurrentWriter<T> {
	void write(MemoryPage<T> page);
}
