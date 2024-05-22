package com.ricardo.gutendex.service;

public interface IDataConverter {
	<T> T getData(String json, Class<T> className);
}
