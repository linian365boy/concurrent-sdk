package com.concurrent.sdk.container;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

/**
 * 内存页，临时存放数据
 * @author linian
 *
 * @param <T>
 */
public class MemoryPage<T> {
	private List<T> content;
	private Date nextFlushTime;
	
	public int size(){
		if(content==null){
			return 0;
		}
		return content.size();
	}
	
	public MemoryPage(int timeOut) {
		content = new ArrayList<>();
		DateTime dt = new DateTime().plusSeconds(timeOut);
		this.nextFlushTime = dt.toDate();
	}

	public List<T> getContent() {
		return content;
	}
	
	public void put(T t){
		if(content!=null){
			content.add(t);
		}
	}
	
	public void reset(){
		content.clear();
	}
	
	public void setContent(List<T> content) {
		this.content = content;
	}

	public Date getNextFlushTime() {
		return nextFlushTime;
	}

	public void setNextFlushTime(Date nextFlushTime) {
		this.nextFlushTime = nextFlushTime;
	}
	
}
