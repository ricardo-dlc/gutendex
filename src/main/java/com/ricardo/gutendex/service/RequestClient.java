package com.ricardo.gutendex.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestClient {
	private String baseUrl;

	public RequestClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String get(String endpoint) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(this.baseUrl + endpoint))
				.build();
		HttpResponse<String> response = null;
		try {
			response = client
					.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		String json = response.body();
		return json;
	}
}
