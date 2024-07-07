package com.collab.collabediting.client.impl;

import com.collab.collabediting.client.IGithubBaseClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public  class GithubBaseClient<T extends  Object> implements IGithubBaseClient<T> {
    private final HttpClient    httpClient;
    private final ObjectMapper jsonMapper;
    private final URI uri ;



    GithubBaseClient(HttpClient  httpClient, ObjectMapper jsonMapper, String path) {
        this.httpClient = httpClient;
        this.jsonMapper = new ObjectMapper();
        this.uri = URI.create(path);

    }


    public T get( String access_Token, TypeReference<T> typeReference) throws IOException, InterruptedException, RuntimeException {

        HttpRequest request= HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer "+access_Token)
                .GET()
                .build();

        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch data: " + response.body());
        }
        return jsonMapper.readValue(response.body(),typeReference);
    }
    public List<T> getList( String access_Token, TypeReference<List<T>> typeReference) throws IOException, InterruptedException, RuntimeException {

        HttpRequest request= HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer "+access_Token)
                .GET()
                .build();

        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch data: " + response.body());
        }
        return jsonMapper.readValue(response.body(),typeReference);
    }
    public boolean post( String access_Token,T body) throws IOException, InterruptedException {
        String jsonBody = jsonMapper.writeValueAsString(body);
        HttpRequest request =HttpRequest.newBuilder()
             .uri(uri)
             .header("Authorization","Bearer "+access_Token)
             .header("Content-Type", "application/json")
             .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
             .build();
        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200 && response.statusCode() != 201) {
            throw new RuntimeException("Failed to Post Data : " + response.body());
        }
        return  true;
    }
    public boolean put( String access_Token,T body) throws IOException, InterruptedException {
        String jsonBody = jsonMapper.writeValueAsString(body);
        HttpRequest request =HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer "+access_Token)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200 && response.statusCode() != 201) {
            throw new RuntimeException("Failed to Post Data : " + response.body());
        }
        return  true;
    }
}
