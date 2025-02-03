package com.collab.collabediting.controllers;

import com.collab.collabediting.models.GithubUser;
import com.collab.collabediting.services.impl.GithubUserService;


import com.collab.collabediting.services.ITaskService;
import com.collab.collabediting.models.Task ;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "https://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("task")
public class TaskController {

    private final ITaskService taskService;
    private final GithubUserService githubUserService;
    public TaskController(ITaskService taskService , GithubUserService githubUserService) {
        this.taskService = taskService;
        this.githubUserService = githubUserService;

    }
    @GetMapping("{repoName}/{owner}")
    ResponseEntity<List<Task>> getTasks(@PathVariable String repoName,@PathVariable String owner) {
        try{
        if(HasAccessToRepo(owner, repoName)) {
        return  ResponseEntity.badRequest().build();
        }
        List<Task> tasks = this.taskService.getTasksByRepoName(repoName);

        return ResponseEntity.ok(tasks);}
        catch(Exception e){

            return  ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("{repoName}/{owner}/myTasks")
    ResponseEntity<List<Task>> getMyTasks(@PathVariable String repoName,@PathVariable String owner) {
        try{
            if(HasAccessToRepo(owner, repoName)) {
                return  ResponseEntity.badRequest().build();
            }
            List<Task> tasks = this.taskService.getMyTasks( getUserConnectedName(),repoName);

            return ResponseEntity.ok(tasks);}
        catch(Exception e){

            return  ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("{repoName}/{owner}")
    ResponseEntity<Task> createTask(@PathVariable String repoName,@PathVariable String owner, @RequestBody Task task) {
        try{
            if(HasAccessToRepo(owner, repoName)) {
                return  ResponseEntity.badRequest().build();
            }
            boolean res =this.taskService.addOrUpdateTask(task);
            if(!res){
                return  ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();}
        catch(Exception e){

            return  ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("{repoName}/{owner}")
    ResponseEntity<Task> updateTask(@PathVariable String repoName,@PathVariable String owner, @RequestBody Task task) {
        try{
            if(HasAccessToRepo(owner, repoName)) {
                return  ResponseEntity.badRequest().build();
            }
            boolean res =this.taskService.addOrUpdateTask(task);
            if(!res){
                return  ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();}
        catch(Exception e){

            return  ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("{repoName}/{owner}/collaborators")
    ResponseEntity<List<GithubUser>> getRepoCollaborators(@PathVariable String repoName,@PathVariable String owner) {
        try {
            List<GithubUser>  users = this.githubUserService.getCollabortors(owner ,repoName);
            return    ResponseEntity.ok(users);
        }catch(Exception e){

            return  ResponseEntity.badRequest().build();
        }

    }
   @DeleteMapping("{repoName}/{owner}/{id}")
   ResponseEntity<Void> deleteTask(@PathVariable String repoName,@PathVariable String owner,@PathVariable  Integer id) {
     try {
         if (HasAccessToRepo(owner, repoName)) {
             return ResponseEntity.badRequest().build();
         }
         this.taskService.deleteTask(id);
         return ResponseEntity.ok().build();
     }
     catch(Exception e){
         return  ResponseEntity.badRequest().build();
     }
   }

    private boolean HasAccessToRepo( String owner,String repoName) throws IOException, InterruptedException {

        String loginName =getUserConnectedName();
        List<GithubUser>  users = this.githubUserService.getCollabortors(owner ,repoName);
        return users.stream().noneMatch(user -> user.getLogin().equals(loginName));
    }
    private String getUserConnectedName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof OAuth2User oAuth2User) {


                return oAuth2User.getAttribute("login");
            }
        }
        return null;
    }
}
