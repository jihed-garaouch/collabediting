package com.collab.collabediting.controllers;

import com.collab.collabediting.models.User;
import com.collab.collabediting.services.GithuberUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:5173")
/**
 *UserController to handle all user operation
 */
public class UserController {
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final GithuberUserService githuberUserService;

    @Autowired
    UserController(OAuth2AuthorizedClientService authorizedClientService, GithuberUserService githuberUserService) {
        this.authorizedClientService = authorizedClientService;
        this.githuberUserService = githuberUserService;

    }

    /**
     * Retrieves the connected user based on the OAuth2 authentication token.
     *
     * <p>This method fetches the authenticated user's details from an OAuth2 provider (e.g., GitHub)
     * using the access token from the {@link OAuth2AuthorizedClient}.</p>
     *
     * @param authentication the OAuth2 authentication token for the currently authenticated user.
     * @return a {@link User} containing the user details if found, or an appropriate HTTP status code:
     * <ul>
     *   <li>{@code 200 OK} if the user is successfully retrieved.</li>
     *   <li>{@code 404 Not Found} if no authorized client or user is found.</li>
     *   <li>{@code 400 Bad Request} if an error occurs while retrieving the user.</li>
     * </ul>
     */
    @GetMapping("/me")
    public ResponseEntity<User> getUser(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(), authentication.getName());

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        String accessToken = client.getAccessToken().getTokenValue();
        User user = null;
        try {
            user = githuberUserService.getUser(accessToken);
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
