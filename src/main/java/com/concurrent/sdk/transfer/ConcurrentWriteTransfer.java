package com.concurrent.sdk.transfer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.concurrent.sdk.common.ConfigVariable;
import com.concurrent.sdk.common.PropertiesUtils;
import com.concurrent.sdk.container.MemoryPage;
import com.concurrent.sdk.write.IConcurrentWriter;

public class ConcurrentWriteTransfer<T> extends AbstractConcurrentWriteTransfer<T> {
	private static final Logger logger = LoggerFactory.getLogger(ConcurrentWriteTransfer.class);
	//存放数据的队列
	private BlockingQueue<T> queue;
	//线程名称
	private String name;
	private MemoryPage<T> page;
	private volatile boolean status = true;
	private IConcurrentWriter<T> writer;
	private int maxMemoryPageSize = ConfigVariable.getMemoryMaxSize();
	private int timeOut = ConfigVariable.getMemoryTimeout();
	
	@Override
	public void run() {
		init();
		while(status){
			write();
		}
	}

	@SuppressWarnings("unchecked")
	private void init() {
		Class<?> c = null;
		try {
			page = new MemoryPage<>(timeOut);
			c = Class.forName(PropertiesUtils.getValue("concurrent.writer.class"));
			writer = (IConcurrentWriter<T>) c.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.error("{} instance concurrent writer class error ",name, e);
		}
	}
	
	public boolean isNeedFlush(){
		boolean flag = false;
		//时间超时
		int seconds = Seconds.secondsBetween(new DateTime(page.getNextFlushTime()), 
				new DateTime()).getSeconds();
		if(page.size()>0 && seconds<=0){
			flag = true;
		}
		//超过max.size
		if(!flag && page.size() >= maxMemoryPageSize){
			flag = true;
		}
		return flag;
	}
	
	@Override
	public void flush(){
		if(writer!=null && page!=null){
			writer.write(page);
			page.reset();
			page.setNextFlushTime(new DateTime().plusSeconds(timeOut).toDate());
		}
	}
	
	@Override
	public void stop(){
		status = false;
	}

	@Override
	public void write() {
		try {
			if(isNeedFlush()){
				flush();
			}
			T data = queue.poll(100, TimeUnit.MICROSECONDS);
			page.put(data);
			logger.debug("memoryPage put data|{}, page size|{}",data, page.size());
		} catch (InterruptedException e) {
			logger.error("queue poll data error",e);
		}
	}

	public BlockingQueue<T> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<T> queue) {
		this.queue = queue;
	}

	public String getName() {
		return name;
	}

	public MemoryPage<T> getPage() {
		return page;
	}

	public void setPage(MemoryPage<T> page) {
		this.page = page;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
