package com.concurrent.sdk.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.concurrent.sdk.read.DefaultConcurrentReader;
import com.concurrent.sdk.read.IConcurrentReader;

/**
 * @ClassName: AbstractConcurrentTemplate  
 * @Description: 默认实现读写的一个模板 ，客户端继承该抽象类，实现自己的getReader方法即可
 * @date: 2016年7月5日 下午3:04:44 
 * 
 * @author tanfan 
 * @version @param <T> 
 * @since JDK 1.7
 */
public abstract class AbstractConcurrentTemplate<T> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractConcurrentTemplate.class);
	private int queueSize;
	private IConcurrentReader<T>[] readers;
	
	@SuppressWarnings("unchecked")
	public AbstractConcurrentTemplate(int queueSize) {
		this.queueSize = queueSize;
		readers = new IConcurrentReader[queueSize];
		for(int i=0;i<queueSize;i++){
			readers[i] = new DefaultConcurrentReader<>();
			logger.debug("init readers[i] hashcode |{}",readers[i].hashCode());
		}
	}
	
	public int getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
	
	/**
	 * getReader:相同的t，获取同一个reader
	 * @author tanfan 
	 * @param t
	 * @return 
	 * @since JDK 1.7
	 */
	public abstract IConcurrentReader<T> getReader(T t);
	
	public void read(T t) {
		IConcurrentReader<T> reader = this.getReader(t);
		reader.read(t);
	}
	
	public void stop() {
		for(int i=0;i<readers.length;i++){
			logger.debug("stop readers[i] hashcode |{}",readers[i].hashCode());
			readers[i].stop();
		}
	}
	
	public IConcurrentReader<T>[] getReaders() {
		return readers;
	}
	
	public void setReaders(IConcurrentReader<T>[] readers) {
		this.readers = readers;
	}
}
