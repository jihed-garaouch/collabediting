package com.collab.collabediting.services;

import com.collab.collabediting.enums.BoardTypes;
import com.collab.collabediting.repository.TaskRepo;
import com.collab.collabediting.models.Task;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    public TaskService(
            TaskRepo taskRepo
    ) {
        this.taskRepo = taskRepo;

    }
    public List<Task>  getTasksByRepoName(String repoName){
        return taskRepo.findAllByRepoName(repoName);
    }
    public boolean addOrUpdateTask(Task task){
      taskRepo.save(task);
      return true;
    }
    public void deleteTask(Integer id){
        taskRepo.deleteById(id);

    }
    public List<Task> getMyInProgressTasks(String assignee,String repoName){
        return taskRepo.findAllByAssigneeAndStatusAndRepoName(assignee, BoardTypes.Doing,repoName);
    }



}
