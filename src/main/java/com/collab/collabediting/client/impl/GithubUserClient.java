package com.collab.collabediting.client.impl;

import com.collab.collabediting.GithubModels.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;

@Component
public class GithubUserClient extends GithubBaseClient<User> {


    GithubUserClient(HttpClient httpClient, ObjectMapper jsonMapper) {
        super(httpClient,jsonMapper, "https://api.github.com/user");
    }

}
