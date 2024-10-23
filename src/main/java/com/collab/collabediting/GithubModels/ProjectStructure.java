package com.collab.collabediting.GithubModels;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectStructure {
    private String name;
    private boolean isFile ;
    private List<ProjectStructure> children;
}
