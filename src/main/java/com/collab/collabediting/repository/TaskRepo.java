package com.collab.collabediting.repository;

import com.collab.collabediting.enums.BoardTypes;
import com.collab.collabediting.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {

    List<Task> findAllByRepoName(String repoName);

    @Query("select T from Task T where T.assignee = :assignee and T.repoName = :repoName and T.status != 'BackLog'")
    List<Task> findAllByAssigneeAndRepoNameWhereStatus(@Param("assignee") String assignee, @Param("repoName") String repoName);

    List<Task> findAllByAssigneeAndStatusAndRepoName(String assignee, BoardTypes status,String repoName);

}
