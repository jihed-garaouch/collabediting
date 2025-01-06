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
public class GithubTree {
    private String sha;
    private String path;
    private List<GithubTreeBranch> tree;
    private boolean  truncated;
}
