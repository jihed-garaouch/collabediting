package com.collab.collabediting.services;

import com.collab.collabediting.models.Task;

import java.util.List;

public interface ITaskService {
    List<Task> getTasksByRepoName(String repoName);
    void deleteTask(Integer id);
     List<Task> getMyTasks(String assignee,String repoName);
    boolean addOrUpdateTask(Task task);
}
