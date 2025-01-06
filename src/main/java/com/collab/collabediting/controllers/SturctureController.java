package com.collab.collabediting.controllers;

import com.collab.collabediting.client.impl.GithubStructureClient;
import com.collab.collabediting.models.ProjectStructure;
import com.collab.collabediting.services.GithubStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/structure")
public class SturctureController {

    private final GithubStructureService githubStructureService;
    @Autowired
    SturctureController(GithubStructureService githubStructureService) {
        this.githubStructureService = githubStructureService;
    }
    @GetMapping("{repo}/{user}")
    public ResponseEntity<List<ProjectStructure>> getMyReposStructre(@PathVariable String repo,@PathVariable String user) {

        try{
            List<ProjectStructure> structureList= githubStructureService.getProjectStructure(user,repo );

            return ResponseEntity.ok(structureList) ;
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().build() ;
        }

    }
    @GetMapping("{repo}/{user}/v2")
    public ResponseEntity<List<ProjectStructure>> getMyReposStructreV2(@PathVariable String repo,@PathVariable String user) {

        try{
            List<ProjectStructure> structureList= githubStructureService.getProjectStructure(user,repo );

            return ResponseEntity.ok(structureList) ;
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().build() ;
        }

    }

}
