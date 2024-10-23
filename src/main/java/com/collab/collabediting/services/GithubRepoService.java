package com.collab.collabediting.services;

import com.collab.collabediting.GithubModels.GithubFile;
import com.collab.collabediting.GithubModels.Repo;
import com.collab.collabediting.GithubModels.User;
import com.collab.collabediting.client.impl.GithubBaseClient;
import com.collab.collabediting.client.impl.GithubRepoClient;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
    public Repo getReposTree( String Name ,String repoName) throws IOException, InterruptedException {
        TypeReference<Repo> typeReference = new TypeReference<Repo>() {};
        return githubRepoClient.get( typeReference,"https://api.github.com/repos/"+Name+"/"+repoName);
    }
    public List<Repo> getAllRepoOwnedByAuthenticatedUser() throws IOException, InterruptedException {
        TypeReference<List<Repo>> typeReference = new TypeReference<List<Repo>>() {};
        return githubRepoClient.getList(typeReference);
    }
    public boolean createRepo(Repo repo) throws IOException, InterruptedException {
        return githubRepoClient.post( repo);
    }
    //TODO : implemnt getFileContentsByBranche
    //TODO : implements cretaet BranchInRepos

    public GithubFile getFileContent(String owner, String repoName , String path , String ref  ){

        return  getFileContent()

    }




}
