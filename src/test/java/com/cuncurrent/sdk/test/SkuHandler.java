package com.cuncurrent.sdk.test;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.concurrent.sdk.common.IConcurrent;
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
	
	@Override
	public IConcurrent<Sku> getWriter(Sku t) {
		int hashCode = t.getId().hashCode();
		IConcurrent<Sku>[] writers=this.getWriters();
		if(writers!=null && writers.length>0){
			int index=hashCode%writers.length;
			if(index<0){
				index*=-1;
			}
			return writers[index];
		}
		return null;
	}

}
