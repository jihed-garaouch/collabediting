package com.collab.collabediting.auth;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.oauth2")
public class OAuth2Config {
    private String client;
    private String secret;
    private String generateTokenUrl;
    private String redirectUri;
    private String scopes;
}