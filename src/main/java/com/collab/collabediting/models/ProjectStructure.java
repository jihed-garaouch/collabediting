package com.collab.collabediting.models;

import com.collab.collabediting.customDeserializer.TypeToIsFileDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProjectStructure {
    private String name;
    private String type;
    private String size;
    private String path;
    private String url;
    private List<ProjectStructure> children;


}
