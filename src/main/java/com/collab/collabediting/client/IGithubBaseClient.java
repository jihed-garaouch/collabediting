package com.collab.collabediting.client;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;
public interface IGithubBaseClient<T> {
    T get(String accessToken, TypeReference<T> typeReference) throws IOException, InterruptedException;

    List<T> getList(String accessToken, TypeReference<List<T>> typeReference) throws IOException, InterruptedException;

    boolean post(String accessToken, T body) throws IOException, InterruptedException;

    boolean put(String accessToken, T body) throws IOException, InterruptedException;
}
