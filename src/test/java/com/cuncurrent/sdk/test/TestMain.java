package com.cuncurrent.sdk.test;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain extends SkuTemplate {
	
	public TestMain(int queueSize) {
		super(queueSize);
	}
	
	private final static Logger logger = LoggerFactory.getLogger(TestMain.class);
	
	public void testRead(){
		logger.debug("start read!");
		Sku sku = null;
		for(int i=0;i<10;i++){
			sku = new Sku();
			sku.setId(new Long(i));
			sku.setName("name"+i);
			sku.setProductNo("productNo"+i);
			this.read(sku);
			logger.debug("read {} data",i+1);
		}
		try {
			TimeUnit.SECONDS.sleep(10);
			this.stop();
			logger.debug("write end");
		} catch (InterruptedException e) {
			logger.error("sleep error",e);
		}
	}
	
	public static void main(String[] args) {
		logger.info("read start..........");
		TestMain main = new TestMain(2);
		main.testRead();
	}
	
}
