package com.collab.collabediting.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizeTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    private String scope;
    @JsonProperty("token_type")
    private String tokenType;
}
