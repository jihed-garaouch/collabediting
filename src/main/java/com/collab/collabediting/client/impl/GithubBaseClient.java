package com.collab.collabediting.client.impl;

import com.collab.collabediting.client.IGithubBaseClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public  class GithubBaseClient<T extends  Object> implements IGithubBaseClient<T> {
    protected final HttpClient    httpClient;
    protected  final ObjectMapper jsonMapper;
    protected  final URI uri ;
    protected  final OAuth2AuthorizedClientService authorizedClientService;



    GithubBaseClient(HttpClient  httpClient, ObjectMapper jsonMapper, String path ,OAuth2AuthorizedClientService authorizedClientService) {
        this.httpClient = httpClient;
        this.authorizedClientService=authorizedClientService;
        this.jsonMapper = new ObjectMapper();
        this.uri = URI.create(path);

    }
    protected  String getAccessToken() {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName());

        if (client == null) {
            throw new RuntimeException("User is not authenticated.");
        }

        return client.getAccessToken().getTokenValue();

    }

    public T get( TypeReference<T> typeReference) throws IOException, InterruptedException, RuntimeException {

        HttpRequest request= HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer "+getAccessToken())
                .GET()
                .build();

        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch data: " + response.body());
        }
        return jsonMapper.readValue(response.body(),typeReference);
    }
    public T get(TypeReference<T> typeReference,String path) throws IOException, InterruptedException, RuntimeException {
        URI newUri = uri.resolve(path);
        HttpRequest request= HttpRequest.newBuilder()
                .uri(newUri)
                .header("Authorization","Bearer "+getAccessToken())
                .GET()
                .build();

        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch data: " + response.body());
        }
        return jsonMapper.readValue(response.body(),typeReference);
    }
    public List<T> getList(  TypeReference<List<T>> typeReference,String path) throws IOException, InterruptedException, RuntimeException {
        URI newUri = uri.resolve(path);
        HttpRequest request= HttpRequest.newBuilder()
                .uri(newUri)
                .header("Authorization","Bearer "+getAccessToken())
                .GET()
                .build();

        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch data: " + response.body());
        }
        return jsonMapper.readValue(response.body(),typeReference);
    }

    public List<T> getList( TypeReference<List<T>> typeReference) throws IOException, InterruptedException, RuntimeException {

        HttpRequest request= HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer "+getAccessToken())
                .GET()
                .build();

        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch data: " + response.body());
        }
        return jsonMapper.readValue(response.body(),typeReference);
    }
    public boolean post( T body) throws IOException, InterruptedException {
        String jsonBody = jsonMapper.writeValueAsString(body);
        HttpRequest request =HttpRequest.newBuilder()
             .uri(uri)
                .header("Authorization","Bearer "+getAccessToken())
             .header("Content-Type", "application/json")
             .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
             .build();
        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200 && response.statusCode() != 201) {
            throw new RuntimeException("Failed to Post Data : " + response.body());
        }
        return  true;
    }
    public boolean put(T body) throws IOException, InterruptedException {
        String jsonBody = jsonMapper.writeValueAsString(body);
        HttpRequest request =HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer "+getAccessToken())
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
