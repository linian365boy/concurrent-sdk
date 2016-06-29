package com.concurrent.sdk.common;

import org.apache.commons.lang3.StringUtils;

public class NumUtils {

	public static int toInt(String value, int defalutValue) {
		if(StringUtils.isBlank(value)){
			return defalutValue;
		}
		try{
			return Integer.valueOf(value);
		}catch(Exception e){
			return defalutValue;
		}
	}
	
}
