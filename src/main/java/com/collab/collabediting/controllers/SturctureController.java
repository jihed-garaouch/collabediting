package com.collab.collabediting.controllers;

import com.collab.collabediting.client.impl.GithubStructureClient;
import com.collab.collabediting.models.GithubTree;
import com.collab.collabediting.models.ProjectStructure;
import com.collab.collabediting.services.GithubStructureService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("structure")
public class SturctureController {

    private final GithubStructureService githubStructureService;
    @Autowired
    SturctureController(GithubStructureService githubStructureService) {
        this.githubStructureService = githubStructureService;
    }
    @GetMapping("{userName}/{repoName}")
    public ResponseEntity<GithubTree> getRepoStructre(@PathVariable String userName, @PathVariable String repoName, @PathParam("ref") String ref)  {
        try {
            GithubTree structure =  githubStructureService.getStructure(userName, repoName, ref);
            return ResponseEntity.ok(structure);
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
