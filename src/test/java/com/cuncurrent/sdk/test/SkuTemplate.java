package com.cuncurrent.sdk.test;

import com.concurrent.sdk.read.IConcurrentReader;
import com.concurrent.sdk.template.AbstractConcurrentTemplate;

public class SkuTemplate extends AbstractConcurrentTemplate<Sku> {
	
	public SkuTemplate(int queueSize) {
		super(queueSize);
	}

	@Override
	public IConcurrentReader<Sku> getReader(Sku t) {
		int hashCode = t.getId().hashCode();
		IConcurrentReader<Sku>[] readers =  this.getReaders();
		IConcurrentReader<Sku> reader = readers[hashCode%this.getQueueSize()];
		if(reader!=null){
			return reader;
		}
		return null;
	}
	
}
