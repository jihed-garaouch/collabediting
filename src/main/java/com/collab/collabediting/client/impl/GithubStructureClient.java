package com.collab.collabediting.client.impl;

import com.collab.collabediting.models.GithubTree;
import com.collab.collabediting.models.GithubTreeBranch;
import com.collab.collabediting.models.ProjectStructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GithubStructureClient extends  GithubBaseClient<GithubTree> {

    private static final String BASE_URL = "https://api.github.com/repos";
    GithubStructureClient(HttpClient httpClient, ObjectMapper jsonMapper,  OAuth2AuthorizedClientService authorizedClientService) {
        super(httpClient, jsonMapper, BASE_URL, authorizedClientService);
    }


    public GithubTree get(String userName, String repoName, String branchName) throws IOException, InterruptedException {
        String url = constructTreeUrl(userName, repoName, branchName);
        TypeReference<GithubTree> typeReference = new TypeReference<GithubTree>() {};
        GithubTree tree = super.get(typeReference, url);
        tree.setTree(buildTree(tree.getTree()));

        return this.get(typeReference, url);
    }
    private String constructTreeUrl(String userName, String repoName, String branchName) {
        return BASE_URL + "/" + userName + "/" + repoName + "/git/trees/" + branchName + "?recursive=1";
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
