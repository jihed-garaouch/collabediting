package com.collab.collabediting.controllers;

import com.collab.collabediting.models.GithubTree;
import com.collab.collabediting.services.impl.GithubStructureService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "https://localhost:5173", allowCredentials = "true")
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
