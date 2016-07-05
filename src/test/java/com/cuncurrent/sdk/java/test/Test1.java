package com.cuncurrent.sdk.java.test;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

public class Test1 {
	public static void main(String[] args) {
		//后面时间-前面时间
		int seconds = Seconds.secondsBetween(new DateTime(2016,7,1,11,50,2,439), 
				new DateTime(2016,7,1,11,50,3,438)).getSeconds();
		
		System.out.println(seconds);
	}
}
