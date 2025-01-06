package com.collab.collabediting.repository;

import com.collab.collabediting.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {

    List<Task> findAllByRepoName(String repoName);

}
