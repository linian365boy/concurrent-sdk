package com.cuncurrent.sdk.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.concurrent.sdk.container.MemoryPage;
import com.concurrent.sdk.write.AbstractConcurrentWriter;


public class SkuHandler extends AbstractConcurrentWriter<Sku> {
	private static final Logger logger = LoggerFactory.getLogger(SkuHandler.class);
	@Override
	public void write(MemoryPage<Sku> t) {
		List<Sku> skuList = t.getContent();
		if(skuList!=null && skuList.size()>0){
			logger.debug("skuList size()==t.size, size|{}",
					skuList.size()==t.size(),t.size());
			for(Sku sku : skuList){
				System.out.println(sku);
			}
		}
		logger.debug("writer skuList end");
	}

}
