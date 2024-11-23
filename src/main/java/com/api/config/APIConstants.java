package com.api.config;

/**
 * This class contains the constants for the API endpoints and email regex.
 */
public class APIConstants {
    //The base URL for the API.
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    //The endpoint for users in the API.
    public static final String USERS_ENDPOINT = "/users";
    //The endpoint for posts in the API.
    public static final String POSTS_ENDPOINT = "/posts";
    //The endpoint for posts in the API.
    public static final String COMMENTS_ENDPOINT = "/comments";
    //The regular expression for validating email addresses.
    //public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,6}$";
    // The endpoint for users in the WireMock server.
    public static final String WIREMOCK_USERS_ENDPOINT = "/users";
    // The endpoint for posts in the WireMock server.
    public static final String WIREMOCK_POSTS_ENDPOINT = "/posts";
    // The endpoint for posts in the WireMock server.
    public static final String WIREMOCK_COMMENTS_ENDPOINT = "/comments";
}
