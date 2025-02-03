package com.collab.collabediting.services.impl;

import com.collab.collabediting.client.impl.GithubStructureClient;
import com.collab.collabediting.models.GithubTree;
import com.collab.collabediting.models.GithubTreeBranch;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GithubStructureService {
    private  final GithubStructureClient githubStructureClient;

    public GithubStructureService(GithubStructureClient githubStructureClient) {
        this.githubStructureClient = githubStructureClient;
    }
    public GithubTree getStructure(String userName, String repoName, String branchName) throws IOException, InterruptedException {
        TypeReference<GithubTree> typeReference = new TypeReference<GithubTree>() {};
        GithubTree tree=   githubStructureClient.get(typeReference,"https://api.github.com/repos/"+userName+"/"+repoName+"/git/trees/"+branchName+"?recursive=1");
        tree.setTree(buildTree(tree.getTree()));
        return  tree;
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
