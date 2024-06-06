package com.ricardo.gutendex.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
		return objectMapper.readValue(json, clazz);
	}

	public static <T> T fromJson(String json, TypeReference<T> typeReference) throws JsonProcessingException {
		return objectMapper.readValue(json, typeReference);
	}

	public static String toJson(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	public static <T> List<T> extractListFromJson(String fromJson, Class<T> toClass, String propertyName)
			throws JsonProcessingException {
		JsonNode rootNode = objectMapper.readTree(fromJson);
		JsonNode resultsNode = rootNode.get(propertyName);
		return objectMapper.readValue(resultsNode.toString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, toClass));
	}
}
