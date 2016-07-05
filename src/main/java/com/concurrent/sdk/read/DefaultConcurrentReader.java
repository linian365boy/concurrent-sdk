package com.concurrent.sdk.read;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.concurrent.sdk.common.ConfigVariable;
import com.concurrent.sdk.transfer.AbstractConcurrentWriteTransfer;
import com.concurrent.sdk.transfer.ConcurrentWriteTransfer;

/**
 * @ClassName: DefaultConcurrentReader  
 * @Description: 默认读入数据的类 
 * @date: 2016年7月1日 下午5:25:37 
 * 
 * @author tanfan 
 * @version @param <T> 
 * @since JDK 1.7
 */
public class DefaultConcurrentReader<T> implements IConcurrentReader<T> {
	private static final Logger logger = LoggerFactory.getLogger(DefaultConcurrentReader.class);
	private BlockingQueue<T> queue;
	private ExecutorService service;
	private static int writerSize = ConfigVariable.getWriterSize();
	private AbstractConcurrentWriteTransfer<T>[] transfer;
	private Future<?>[] future;
	
	@SuppressWarnings("unchecked")
	public DefaultConcurrentReader(){
		service = Executors.newFixedThreadPool(ConfigVariable.getWriteThreadSize());
		logger.debug("service hashcode|{}",service.hashCode());
		queue = new LinkedBlockingQueue<>();
		transfer = new AbstractConcurrentWriteTransfer[writerSize];
		future = new Future[writerSize];
		for(int i=0;i<writerSize;i++){
			transfer[i] = new ConcurrentWriteTransfer<T>();
			transfer[i].setQueue(queue);
			transfer[i].setName("transfer"+i);
			future[i] = service.submit(transfer[i]);
		}
	}
	
	@Override
	public void read(T t) {
		queue.add(t);
		logger.debug("t|{},queue hashCode|{}, queue size|{}",t,queue.hashCode(),queue.size());
	}

	@Override
	public void stop() {
		for(int i=0;i<writerSize;i++){
			transfer[i].flush();
			transfer[i].stop();
			future[i].cancel(true);
		}
		if(service!=null){
			logger.debug("stop service hashcode|{}",service.hashCode());
			service.shutdown();
		}
	}

}
