package com.collab.collabediting.client;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;

public interface IGithubBaseClient<T> {

    /**
     * Sends a GET request to fetch a resource and map it to a specific type.
     *
     * @param typeReference The type reference to map the JSON response to.
     * @return The mapped resource.
     * @throws IOException If there is an issue with input/output during the operation.
     * @throws InterruptedException If the request is interrupted.
     * @throws RuntimeException If there is an error during the request.
     */
    T get(TypeReference<T> typeReference) throws IOException, InterruptedException, RuntimeException;

    /**
     * Sends a GET request to fetch a resource from a specific path and map it to a specific type.
     *
     * @param typeReference The type reference to map the JSON response to.
     * @param path The path to resolve the URI against.
     * @return The mapped resource.
     * @throws IOException If there is an issue with input/output during the operation.
     * @throws InterruptedException If the request is interrupted.
     * @throws RuntimeException If there is an error during the request.
     */
    <U> U get(TypeReference<U> typeReference, String path) throws IOException, InterruptedException, RuntimeException;

    /**
     * Sends a GET request to fetch a list of resources from a specific path and map it to a list of specific type.
     *
     * @param typeReference The type reference to map the JSON response to a list of resources.
     * @param path The path to resolve the URI against.
     * @return The list of mapped resources.
     * @throws IOException If there is an issue with input/output during the operation.
     * @throws InterruptedException If the request is interrupted.
     * @throws RuntimeException If there is an error during the request.
     */
    <U> List<U>  getList(TypeReference<List<U>> typeReference, String path) throws IOException, InterruptedException, RuntimeException;

    /**
     * Sends a GET request to fetch a list of resources and map it to a list of specific type.
     *
     * @param typeReference The type reference to map the JSON response to a list of resources.
     * @return The list of mapped resources.
     * @throws IOException If there is an issue with input/output during the operation.
     * @throws InterruptedException If the request is interrupted.
     * @throws RuntimeException If there is an error during the request.
     */
    List<T> getList(TypeReference<List<T>> typeReference) throws IOException, InterruptedException, RuntimeException;

    /**
     * Sends a POST request to create a resource.
     *
     * @param body The body of the request containing the resource to create.
     * @return A boolean indicating if the request was successful.
     * @throws IOException If there is an issue with input/output during the operation.
     * @throws InterruptedException If the request is interrupted.
     */
    boolean post(T body) throws IOException, InterruptedException;

    /**
     * Sends a POST request to create a resource at a specific path.
     *
     * @param body The body of the request containing the resource to create.
     * @param path The path to resolve the URI against.
     * @return A boolean indicating if the request was successful.
     * @throws IOException If there is an issue with input/output during the operation.
     * @throws InterruptedException If the request is interrupted.
     */
    boolean post(T body, String path) throws IOException, InterruptedException;

    /**
     * Sends a PUT request to update a resource.
     *
     * @param body The body of the request containing the resource to update.
     * @return A boolean indicating if the request was successful.
     * @throws IOException If there is an issue with input/output during the operation.
     * @throws InterruptedException If the request is interrupted.
     */
    boolean put(T body) throws IOException, InterruptedException;

    /**
     * Sends a PUT request to update a resource at a specific path.
     *
     * @param body The body of the request containing the resource to update.
     * @param path The path to resolve the URI against.
     * @return A boolean indicating if the request was successful.
     * @throws IOException If there is an issue with input/output during the operation.
     * @throws InterruptedException If the request is interrupted.
     */
    boolean put(T body, String path) throws IOException, InterruptedException;
}
