package com.collab.collabediting.client.impl;

import com.collab.collabediting.GithubModels.GithubFile;
import com.collab.collabediting.GithubModels.Repo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class GithubRepoClient extends  GithubBaseClient<Repo>{
    private static final String BASE_URL = "https://api.github.com/repos";
    public GithubRepoClient(HttpClient  httpClient, ObjectMapper jsonMapper, OAuth2AuthorizedClientService authorizedClientService) {
        super( httpClient,  jsonMapper, BASE_URL,authorizedClientService);
    }

    public GithubFile getFileContent(String owner , String repo ,String path  ) throws IOException, InterruptedException, URISyntaxException {

            URI uri = new URI(BASE_URL + "/" + owner + "/" + repo + "/contents/" + path );
            HttpRequest request= HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Authorization","Bearer "+getAccessToken())
                    .header("Accept","application/vnd.github.object+json")
                    .GET()
                    .build();

            HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to fetch data: " + response.body());
            }
            return jsonMapper.readValue(response.body(),GithubFile.class);

    };
    public GithubFile getFileContent(String owner , String repo ,String ref ,String path  ) throws IOException, InterruptedException, URISyntaxException {

        URI uri = new URI(BASE_URL + "/" + owner + "/" + repo + "/contents/" + path +"?ref="+ref);
        HttpRequest request= HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer "+getAccessToken())
                .header("Accept","application/vnd.github.object+json")
                .GET()
                .build();

        HttpResponse<String> response =httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch data: " + response.body());
        }
        return jsonMapper.readValue(response.body(),GithubFile.class);

    };
}
