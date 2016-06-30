package com.concurrent.sdk.read;


public interface IConcurrentReader<T> {
	void read(T t);
	void stop();
}
