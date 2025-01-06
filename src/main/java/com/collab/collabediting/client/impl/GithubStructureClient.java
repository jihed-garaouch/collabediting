package com.collab.collabediting.client.impl;

import com.collab.collabediting.models.ProjectStructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.List;

@Component
public class GithubStructureClient extends  GithubBaseClient<ProjectStructure> {

    private static final String BASE_URL = "https://api.github.com/repos";
    GithubStructureClient(HttpClient httpClient, ObjectMapper jsonMapper,  OAuth2AuthorizedClientService authorizedClientService) {
        super(httpClient, jsonMapper, BASE_URL, authorizedClientService);
    }


    public List<ProjectStructure> getProjectStructure(String owner, String repo ,String ref) throws Exception{


        List<ProjectStructure>  root =super.getList(getListTypeReferenceStructure(),getPath(owner, repo,ref));
        root.stream().filter( x -> "dir".equals(x.getType())).forEach(element ->{

            try {
                List<ProjectStructure>  childs = getChildren(element.getUrl());
                element.setChildren(childs);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        return  root;

    }
    public List<ProjectStructure> getProjectStructure(String owner, String repo ) throws Exception{

        List<ProjectStructure>  root =super.getList(getListTypeReferenceStructure(),getPath(owner, repo));
        root.stream().filter( x -> "dir".equals(x.getType())).forEach(element ->{

            try {
                List<ProjectStructure>  childs = getChildren(element.getUrl());
                element.setChildren(childs);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        return  root;

    }
    private List<ProjectStructure> getChildren(String path) throws Exception{
        List<ProjectStructure>  root =super.getList(getListTypeReferenceStructure(),path);
        root.stream().filter( x -> "dir".equals(x.getType())).forEach(element ->{

            try {
                List<ProjectStructure>  childs = getChildren(element.getUrl());
                element.setChildren(childs);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        return root;

    }

    private String getPath(String owner, String repo) {
        return  BASE_URL+"/"+owner+"/"+repo+"/contents";

    }
    private String getPath(String owner, String repo,String ref) {
        return  BASE_URL+"/"+owner+"/"+repo+"/contents?ref="+ref;

    }
    private TypeReference<ProjectStructure> getTypeReferenceStructure(){
      return new TypeReference<ProjectStructure>() {};
    }
    private TypeReference<List<ProjectStructure>> getListTypeReferenceStructure(){
        return new TypeReference<List<ProjectStructure>>() {};
    }



}
