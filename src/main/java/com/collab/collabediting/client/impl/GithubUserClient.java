package com.collab.collabediting.client.impl;

import com.collab.collabediting.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;

@Component
public class GithubUserClient extends GithubBaseClient<User> {


    GithubUserClient(HttpClient httpClient, ObjectMapper jsonMapper) {
        super(httpClient,jsonMapper, "https://api.github.com/user");
    }

}
