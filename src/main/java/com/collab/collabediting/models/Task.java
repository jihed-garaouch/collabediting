package com.collab.collabediting.models;

import com.collab.collabediting.enums.BoardTypes;
import com.collab.collabediting.enums.TaskPriority;
import com.collab.collabediting.enums.TaskTags;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String repoName;
    private String title;
    private String assignee;
    private TaskTags type ;
    private TaskPriority priority;
    @JsonProperty("column")
    @Enumerated(EnumType.STRING)
    private BoardTypes status ;

}
