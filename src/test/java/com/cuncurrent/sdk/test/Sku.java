package com.cuncurrent.sdk.test;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Sku implements Serializable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 101844389705260999L;
	
	private Long id;
	private String name;
	private String productNo;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
