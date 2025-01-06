package com.collab.collabediting.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo {
    private String id;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private   GithubUser owner;
    @JsonProperty("private")
    private Boolean _private;
}
