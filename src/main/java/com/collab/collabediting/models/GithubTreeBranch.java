package com.collab.collabediting.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class GithubTreeBranch {
    private String path;
    private String mode;
    private String name;
    private String type;
    private String url;
    private String sha;
    private List<GithubTreeBranch> children;
}
