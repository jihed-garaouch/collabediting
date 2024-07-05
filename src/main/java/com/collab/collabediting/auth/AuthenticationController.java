package com.collab.collabediting.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final OAuth2Config oAuth2Config;
    @GetMapping("/github/authorize")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(service.signUp(request));
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody SignInRequest request){

        return ResponseEntity.ok(service.signIn(request));
    }
}
