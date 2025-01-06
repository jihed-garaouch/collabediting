package com.collab.collabediting.services;

import com.collab.collabediting.models.*;
import com.collab.collabediting.client.impl.GithubRepoClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GithubRepoService {
    private final GithubRepoClient githubRepoClient;

    @Autowired
    public GithubRepoService(GithubRepoClient githubRepoClient) {
        this.githubRepoClient = githubRepoClient;
    }
    public List<Repo> getUserOwnedReposByName( String Name ) throws IOException, InterruptedException {

        TypeReference<List<Repo> > typeReference = new TypeReference<List<Repo>>() {};
        return githubRepoClient.getList(typeReference,"https://api.github.com/users/"+Name+"/repos");
    }
    public Repo getUserOwnedRepoByName( String Name ,String repoName) throws IOException, InterruptedException {
        TypeReference<Repo> typeReference = new TypeReference<Repo>() {};
        return githubRepoClient.get( typeReference,"https://api.github.com/repos/"+Name+"/"+repoName);
    }

    public List<GithubBranch> getBranches(String userName, String repoName) throws IOException, InterruptedException {
        TypeReference<List<GithubBranch>> typeReference = new TypeReference<List<GithubBranch>>() {};
        return  githubRepoClient.getList(typeReference,"https://api.github.com/repos/"+userName+"/"+repoName+"/branches");

    }


    public List<Repo> getAllRepoOwnedByAuthenticatedUser() throws IOException, InterruptedException {
        TypeReference<List<Repo>> typeReference = new TypeReference<List<Repo>>() {};
        return githubRepoClient.getList(typeReference,"https://api.github.com/user/repos");
    }

    public boolean createRepo(Repo repo) throws IOException, InterruptedException {
        return githubRepoClient.post( repo);
    }

    public boolean updateOrCreateFileContent(String owner, String repoName ,  String ref  ,String path,String commitMessage ,String content,String sha  ) throws IOException, URISyntaxException, InterruptedException {
        return githubRepoClient.updateFileContent(owner,repoName,ref,path,commitMessage,content, sha);

    }
    public GithubFile getFileContent(String owner, String repoName ,  String ref  ,String path  ) throws IOException, URISyntaxException, InterruptedException {
       return githubRepoClient.getFileContent(owner,repoName,ref,path);

    }






}
