package com.collab.collabediting.services;

import com.collab.collabediting.models.GithubUser;
import com.collab.collabediting.client.impl.GithubUserClient;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service

public class GithubUserService {

    private final GithubUserClient githubUserClient;

    @Autowired
    GithubUserService(GithubUserClient githubUserClient) {
        this.githubUserClient = githubUserClient;
    }

    public GithubUser getUser() throws IOException, InterruptedException {

        TypeReference<GithubUser> typeReference = new TypeReference<GithubUser>() {};
        return githubUserClient.get(typeReference);
    }
    public List<GithubUser> getCollabortors(String owner , String repoName) throws IOException, InterruptedException {
        TypeReference<List<GithubUser>> typeReference = new TypeReference<List<GithubUser>>() {
        };
        return  githubUserClient.getList(typeReference,"https://api.github.com/repos/"+owner+"/"+repoName+"/collaborators");

    }



}
