package com.collab.collabediting.controllers;


import com.collab.collabediting.models.GithubUser;
import com.collab.collabediting.services.GithuberUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GithubUserController {

    private final GithuberUserService githuberUserService;

    @Autowired
    public GithubUserController(GithuberUserService githuberUserService) {
        ;
        this.githuberUserService = githuberUserService;

    }

    @GetMapping("/user")
    public ResponseEntity<GithubUser> getUser() {

        GithubUser  user ;
        try {
            user = githuberUserService.getUser();
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.badRequest().build();
        }
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}
