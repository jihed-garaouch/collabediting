package com.collab.collabediting.auth;

import com.collab.collabediting.config.JwtService;
import com.collab.collabediting.user.User;
import com.collab.collabediting.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;
    public AuthenticationResponse signUp(SignUpRequest request) {
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        var jwToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwToken).build();
    }

    public AuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        var user=repository.findByEmail(request.getEmail()).orElseThrow();
        System.out.println("HI signinnnnnnnn");
        var jwToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwToken).build();
    }
}
