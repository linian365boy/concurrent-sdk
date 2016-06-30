package com.concurrent.sdk.read;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import com.concurrent.sdk.common.ConfigVariable;
import com.concurrent.sdk.transfer.AbstractConcurrentWriteTransfer;
import com.concurrent.sdk.transfer.ConcurrentWriteTransfer;

public class DefaultConcurrentReader<T> implements IConcurrentReader<T> {
	private BlockingQueue<T> queue;
	private static ExecutorService service = Executors.newFixedThreadPool(ConfigVariable.getWriteThreadSize());
	private static int writerSize = ConfigVariable.getWriterSize();
	private AbstractConcurrentWriteTransfer<T>[] transfer;
	private Future<?>[] future;
	
	@SuppressWarnings("unchecked")
	public DefaultConcurrentReader(){
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
	}

	@Override
	public void stop() {
		for(int i=0;i<writerSize;i++){
			transfer[i].flush();
			transfer[i].stop();
			future[i].cancel(true);
		}
		if(service!=null){
			service.shutdown();
		}
	}

}
