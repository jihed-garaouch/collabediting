package com.collab.collabediting.controllers;

import com.collab.collabediting.GithubModels.Repo;
import com.collab.collabediting.services.GithubRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/github/repos")
public class GithubRepoController {

    private final GithubRepoService githubRepoService;

    @Autowired
    public GithubRepoController(GithubRepoService githubRepoService) {
        this.githubRepoService = githubRepoService;
    }

    @GetMapping("/repos/{name}")
    public ResponseEntity<List<Repo>> getUserOwnedReposByName(
            @PathVariable String name) throws IOException, InterruptedException {
        List<Repo> repos = githubRepoService.getUserOwnedReposByName( name);
        return ResponseEntity.ok(repos);
    }

    @GetMapping("/repos/{name}/{repoName}")
    public ResponseEntity<Repo> getUserOwnedRepoByName(
            @PathVariable String name,
            @PathVariable String repoName) throws IOException, InterruptedException {
        Repo repo = githubRepoService.getUserOwnedRepoByName( name, repoName);
        return ResponseEntity.ok(repo);
    }

    @GetMapping("/user/repos")
    public ResponseEntity<List<Repo>> getAllRepoOwnedByAuthenticatedUser(
           ) throws IOException, InterruptedException {
        List<Repo> repos = githubRepoService.getAllRepoOwnedByAuthenticatedUser();
        return ResponseEntity.ok(repos);
    }

    @PostMapping("/repos")
    public ResponseEntity<Boolean> createRepo(
            @RequestBody Repo repo) throws IOException, InterruptedException {
        boolean result = githubRepoService.createRepo( repo);
        return ResponseEntity.ok(result);
    }

}