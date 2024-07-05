package com.collab.collabediting.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final OAuth2Config oAuth2Config;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/github/generateToken")
    public ResponseEntity<AuthorizeTokenResponse> catchToken(@PathParam("code") String code){
        String url = "https://github.com/login/oauth/access_token" +
                "?client_id=" + oAuth2Config.getClient() +
                "&client_secret=" + oAuth2Config.getSecret() +
                "&code=" + code +
                "&redirect_uri=" + oAuth2Config.getRedirectUri();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response = responseFuture.join();
        try{
            AuthorizeTokenResponse tokenResponse = objectMapper.readValue(response.body(), AuthorizeTokenResponse.class);
            return ResponseEntity.ok(tokenResponse);
        }catch (Exception ex){
            ex.printStackTrace();

        }

    }


}
