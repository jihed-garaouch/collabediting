package com.collab.collabediting.controllers;

import com.collab.collabediting.models.*;
import com.collab.collabediting.requests.UpdateFileRequest;
import com.collab.collabediting.services.GithubRepoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/repos")
public class GithubRepoController {

    private final GithubRepoService githubRepoService;

    @Autowired
    public GithubRepoController(GithubRepoService githubRepoService) {
        this.githubRepoService = githubRepoService;
    }



    @GetMapping("{name}/{repoName}")
    public ResponseEntity<Repo> getUserOwnedRepoByName(
            @PathVariable String name,
            @PathVariable String repoName) throws IOException, InterruptedException {
        Repo repo = githubRepoService.getUserOwnedRepoByName( name, repoName);
        return ResponseEntity.ok(repo);
    }

    @GetMapping()
    public ResponseEntity<List<Repo>> getAllRepoOwnedByAuthenticatedUser(
           ) throws IOException, InterruptedException {
        List<Repo> repos = githubRepoService.getAllRepoOwnedByAuthenticatedUser();
        return ResponseEntity.ok(repos);
    }


    @PostMapping("")
    public ResponseEntity<Boolean> createRepo(
            @RequestBody Repo repo) throws IOException, InterruptedException {
        boolean result = githubRepoService.createRepo( repo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("branches/{userName}/{repoName}")
    public ResponseEntity<List<String>> getBranches(@PathVariable String userName, @PathVariable String repoName) throws IOException, InterruptedException {
        try {
            List<GithubBranch> branches = githubRepoService.getBranches(userName, repoName);
            List<String> branchNames = branches.stream()
                    .map(GithubBranch::getName)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(branchNames);
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("structure/{userName}/{repoName}")
    public ResponseEntity<GithubTree> getRepoStructre(@PathVariable String userName, @PathVariable String repoName, @PathParam("ref") String ref) throws IOException, InterruptedException {
        try {
            GithubTree structure = githubRepoService.getStructure(userName, repoName, ref);
            return ResponseEntity.ok(structure);
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @GetMapping("file/{userName}/{repoName}")
    public ResponseEntity<GithubFile> getRepoFile(@PathVariable String userName, @PathVariable String repoName,  @RequestParam("path") String path, @RequestParam("ref") String ref) {
      try {
          GithubFile res = githubRepoService.getFileContent(userName,repoName,ref,path);
          return  ResponseEntity.ok(res);
      } catch (Exception e) {
          return ResponseEntity.badRequest().build();
      }
    }
    @PutMapping("file/{userName}/{repoName}")
    public ResponseEntity updateFile(@PathVariable String userName, @PathVariable String repoName,  @RequestParam("path") String path, @RequestParam("ref") String ref,  @RequestBody UpdateFileRequest updateFileRequest) {
        try {
            String sha = calculateSha(updateFileRequest.getContent());
            boolean res = githubRepoService.updateOrCreateFileContent(
                    userName,
                    repoName,
                    ref,
                    path,
                    updateFileRequest.getCommitMessage(),
                    updateFileRequest.getContent(),
                    sha
            );
            return  ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    private String calculateSha(String content) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] shaBytes = digest.digest(content.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : shaBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}

