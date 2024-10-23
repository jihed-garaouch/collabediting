package com.collab.collabediting.controllers;

import com.collab.collabediting.GithubModels.User;
import com.collab.collabediting.services.GithuberUserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.security.Principal;

@RestController
public class GithubController {

    private final GithuberUserService githuberUserService;

    @Autowired
    public GithubController( GithuberUserService githuberUserService) {
        ;
        this.githuberUserService = githuberUserService;

    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(OAuth2AuthenticationToken authentication, Principal principal) {

        User  user ;
        try {
            user = githuberUserService.getUser();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        } catch (InterruptedException e) {
            return ResponseEntity.badRequest().build();
        }
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}
