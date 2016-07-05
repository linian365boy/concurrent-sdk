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
			for(Sku sku : skuList){
				logger.debug("write sku info|{}", sku);
			}
			logger.info("write {} skuList", t.size());
		}
	}
}
