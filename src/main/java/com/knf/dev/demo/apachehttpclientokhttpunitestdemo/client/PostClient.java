package com.knf.dev.demo.apachehttpclientokhttpunitestdemo.client;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PostClient {

	private final String baseUrl;
    CloseableHttpClient httpClient = 
	                       HttpClients.createDefault();

	public PostClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<Post> fetchAllPosts() 
	     throws InterruptedException, IOException {

		HttpGet request = new HttpGet(baseUrl + "/posts");
		request.addHeader("content-type", "application/json");
		CloseableHttpResponse response = httpClient.execute(request);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(response.getEntity().getContent(),
				objectMapper.getTypeFactory().
				   constructCollectionType(List.class, Post.class));

	}

}
