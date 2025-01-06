package com.collab.collabediting.services;

import com.collab.collabediting.client.impl.GithubStructureClient;
import com.collab.collabediting.models.ProjectStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GithubStructureService {
    private  final GithubStructureClient githubStructureClient;

    public GithubStructureService(GithubStructureClient githubStructureClient) {
        this.githubStructureClient = githubStructureClient;
    }

    public List<ProjectStructure> getProjectStructure(String owner , String repoName) throws Exception {
        List<ProjectStructure> roots= githubStructureClient.getProjectStructure(owner,repoName) ;
        return roots;

    }
    public List<ProjectStructure> getProjectStructurev2(String owner , String repoName) throws Exception {
        List<ProjectStructure> roots= githubStructureClient.getProjectStructure(owner,repoName) ;
        return roots;

    }

}
