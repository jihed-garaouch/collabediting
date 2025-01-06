package com.collab.collabediting.services;

import com.collab.collabediting.models.GithubUser;
import com.collab.collabediting.models.GithubUser;
import com.collab.collabediting.client.impl.GithubUserClient;
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

    public GithubUser getUser() throws IOException, InterruptedException {

        TypeReference<GithubUser> typeReference = new TypeReference<GithubUser>() {};
        return githubUserClient.get(typeReference);
    }


}
