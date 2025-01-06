package com.collab.collabediting.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a user with name, email, login, and avatar URL.
 * <p>
 * This class is annotated with {@link lombok.Getter}, {@link lombok.Setter}, and {@link lombok.NoArgsConstructor}
 * to automatically generate getter, setter, and a no-args constructor.
 * It is also annotated with {@link com.fasterxml.jackson.annotation.JsonIgnoreProperties}
 * to ignore unknown JSON properties during deserialization.
 * </p>
 *
 * <p>Fields:</p>
 * <ul>
 *   <li>{@code name} - The user's name.</li>
 *   <li>{@code email} - The user's email address.</li>
 *   <li>{@code login} - The user's login username.</li>
 *   <li>{@code avatarUrl} - The URL of the user's avatar image. Mapped from {@code avatar_url} in JSON.</li>
 * </ul>
 *
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser {

    /**
     * The user's name.
     */
    private String name;

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The user's login username.
     */
    private String login;

    /**
     * The URL of the user's avatar image.
     * This field is mapped from the JSON property {@code avatar_url}.
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;
}