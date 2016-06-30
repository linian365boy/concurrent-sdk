package com.concurrent.sdk.transfer;

import java.util.concurrent.BlockingQueue;

import com.concurrent.sdk.common.IConcurrent;

public abstract class AbstractConcurrentWriteTransfer<T> implements IConcurrent<T> {
	public abstract void write();
	public abstract void setQueue(BlockingQueue<T> queue);
	//设置线程名称
	public abstract void setName(String name) ;
	public abstract void flush();
	public abstract void stop() ;
}
