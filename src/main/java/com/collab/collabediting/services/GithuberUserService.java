package com.collab.collabediting.services;

import com.collab.collabediting.GithubModels.User;
import com.collab.collabediting.client.impl.GithubUserClient;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service

public class GithuberUserService {

    private final GithubUserClient githubUserClient;

    @Autowired
    GithuberUserService(GithubUserClient githubUserClient) {
        this.githubUserClient = githubUserClient;
    }

    public User getUser(String accessToken) throws IOException, InterruptedException {

        TypeReference<User> typeReference = new TypeReference<User>() {};
        return githubUserClient.get(accessToken, typeReference);
    }

}
