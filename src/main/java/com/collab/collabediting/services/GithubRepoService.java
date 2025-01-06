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
    public GithubTree getStructure(String userName, String repoName,String branchName) throws IOException, InterruptedException {
        TypeReference<GithubTree> typeReference = new TypeReference<GithubTree>() {};
        GithubTree tree=   githubRepoClient.get(typeReference,"https://api.github.com/repos/"+userName+"/"+repoName+"/git/trees/"+branchName+"?recursive=1");
         tree.setTree(buildTree(tree.getTree()));
        return  tree;
    }
    public List<GithubBranch> getBranches(String userName, String repoName) throws IOException, InterruptedException {
        TypeReference<List<GithubBranch>> typeReference = new TypeReference<List<GithubBranch>>() {};
        return  githubRepoClient.getList(typeReference,"https://api.github.com/repos/"+userName+"/"+repoName+"/branches");

    }

    public Repo getReposTree( String Name ,String repoName) throws IOException, InterruptedException {
        TypeReference<Repo> typeReference = new TypeReference<Repo>() {};
        return githubRepoClient.get( typeReference,"https://api.github.com/repos/"+Name+"/"+repoName);
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
    public List<GithubUser> getRepoCollabortors(String owner ,String repoName) throws IOException, InterruptedException {
        TypeReference<List<GithubUser>> typeReference = new TypeReference<List<GithubUser>>() {
        };
        return  githubRepoClient.getList(typeReference,"https://api.github.com/repos/"+owner+"/"+repoName+"/collaborators");

    }
    private  static List<GithubTreeBranch> buildTree(List<GithubTreeBranch> flatList) {
        Map<String, GithubTreeBranch> pathMap = new HashMap<>();
        List<GithubTreeBranch> rootNodes = new ArrayList<>();

        for (GithubTreeBranch item : flatList) {
            // Split the path into parts
            String[] parts = item.getPath().split("/");
            String currentPath = "";

            GithubTreeBranch currentNode = null;
            List<GithubTreeBranch> currentChildren = rootNodes;

            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                currentPath = currentPath.isEmpty() ? part : currentPath + "/" + part;

                if (!pathMap.containsKey(currentPath)) {
                    GithubTreeBranch newNode = new GithubTreeBranch();
                    newNode.setPath(item.getPath());
                    newNode.setName(part);
                    newNode.setChildren(new ArrayList<>());

                    // Only set properties on the last node in the path
                    if (i == parts.length - 1) {
                        newNode.setMode(item.getMode());
                        newNode.setType(item.getType());
                        newNode.setUrl(item.getUrl());
                        newNode.setSha(item.getSha());
                    }

                    pathMap.put(currentPath, newNode);
                    currentChildren.add(newNode);
                    currentNode = newNode;
                } else {
                    currentNode = pathMap.get(currentPath);
                }

                currentChildren = currentNode.getChildren();
            }
        }

        return rootNodes;
    }




}
