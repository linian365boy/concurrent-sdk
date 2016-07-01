package com.cuncurrent.sdk.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.concurrent.sdk.read.IConcurrentReader;

public class TestMain {
	private final static Logger logger = LoggerFactory.getLogger(TestMain.class);
	private IConcurrentReader<Sku> reader; 
	@Test
	public void testRead(){
		logger.debug("start read!");
		reader = new SkuReader();
		Sku sku = null;
		for(int i=0;i<425;i++){
			sku = new Sku();
			sku.setId(new Long(i));
			sku.setName("name"+i);
			sku.setProductNo("productNo"+i);
			reader.read(sku);
			logger.debug("read {} data",i+1);
		}
		try {
			TimeUnit.SECONDS.sleep(7);
			reader.stop();
			logger.debug("write end");
		} catch (InterruptedException e) {
			logger.error("sleep error",e);
		}
	}
	
}
