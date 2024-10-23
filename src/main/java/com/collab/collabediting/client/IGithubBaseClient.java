package com.collab.collabediting.client;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;
public interface IGithubBaseClient<T> {
    T get(TypeReference<T> typeReference) throws IOException, InterruptedException, RuntimeException;

    T get(TypeReference<T> typeReference, String path) throws IOException, InterruptedException, RuntimeException;

    List<T> getList(TypeReference<List<T>> typeReference, String path) throws IOException, InterruptedException, RuntimeException;

    List<T> getList(TypeReference<List<T>> typeReference) throws IOException, InterruptedException, RuntimeException;

    boolean post(T body) throws IOException, InterruptedException;

    boolean put(T body) throws IOException, InterruptedException;
}
