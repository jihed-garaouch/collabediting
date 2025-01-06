package com.collab.collabediting.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubFile {
    private String path;
    private String name;
    private String content ;
    private String encoding ;
    private String  size ;
    private String sha ;

}
