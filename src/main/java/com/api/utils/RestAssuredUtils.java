package com.api.utils;

import java.util.Map;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredUtils {

    /**
     Builds a {@link RequestSpecification} with the provided base URL and optional query parameters.
     @param baseUrl The base URL for the request.
     @param headers A map of headers to be added to the request.
     @param parameters A map of query parameters to be added to the request.
     @param body The request body to be sent with the request.
     @return A {@link RequestSpecification} instance with the specified base URL, headers, query parameters, and request body.
     */
    private static RequestSpecification buildRequestSpecification(String baseUrl, Map<String, String> headers,
                                                                  Map<String, Integer> parameters, String body) {

        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder().setRelaxedHTTPSValidation()
                .setBaseUri(baseUrl);
        if (headers != null && !headers.isEmpty()) {
            reqSpecBuilder.addHeaders(headers);
        }
        if (parameters != null && !parameters.isEmpty()) {
            reqSpecBuilder.addQueryParams(parameters);
        }
        if (body != null) {
            reqSpecBuilder.setBody(body);
        }
        return reqSpecBuilder.build();
    }
    /**
    Builds a {@link RequestSpecification} with the provided base URL and optional query parameters.
    @param baseUrl The base URL for the request.
    @param parameters A map of query parameters to be added to the request.
     @return A {@link RequestSpecification} instance with the specified base URL and query parameters.
     */
    private static RequestSpecification buildRequestSpecification(String baseUrl,Map<String, Integer> parameters) {

        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder().setRelaxedHTTPSValidation()
                .setBaseUri(baseUrl);
        if (parameters != null && !parameters.isEmpty()) {
            reqSpecBuilder.addQueryParams(parameters);
        }
        return reqSpecBuilder.build();
    }

    /**
     * Sends a GET request to the specified endpoint using the provided base URL, headers, and parameters.
     *
     * @param baseUrl The base URL for the request.
     * @param endpoint The endpoint to which the request is sent.
     * @param headers A map of headers to be added to the request.
     * @param parameters A map of query parameters to be added to the request.
     * @return The response object containing the response body, headers, and status code.
     */
    public static Response sendGetRequest(String baseUrl, String endpoint, Map<String, String> headers, Map<String, Integer> parameters) {
        return given().spec(buildRequestSpecification(baseUrl, headers,parameters, null))
                .when()
                .contentType(ContentType.JSON)
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
    /**
     * Sends a GET request to the specified endpoint using the provided base URL, headers, and parameters.
     *
     * @param baseUrl The base URL for the request.
     * @param endpoint The endpoint to which the request is sent.
     * @param parameters A map of query parameters to be added to the request.
     * @return The response object containing the response body, headers, and status code.
     */
    public static Response sendGetRequest(String baseUrl, String endpoint, Map<String, Integer> parameters) {
        return given().spec(buildRequestSpecification(baseUrl,parameters))
                .when()
                .contentType(ContentType.JSON)
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
    /**
     * Sends a POST request to the specified endpoint using the provided base URL, headers, parameters, and request body.
     *
     * @param baseUrl The base URL for the request.
     * @param endpoint The endpoint to which the request is sent.
     * @param headers A map of headers to be added to the request.
     * @param parameters A map of query parameters to be added to the request.
     * @param body The request body to be sent with the request.
     * @return The response object containing the response body, headers, and status code.
     */
    public static Response sendPostRequest(String baseUrl, String endpoint, Map<String, String> headers,
                                           Map<String, Integer> parameters, String body) {
        return given().spec(buildRequestSpecification(baseUrl, headers, parameters, body))
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a PUT request to the specified endpoint using the provided base URL, headers, parameters, and request body.
     *
     * @param baseUrl The base URL for the request.
     * @param endpoint The endpoint to which the request is sent.
     * @param headers A map of headers to be added to the request.
     * @param parameters A map of query parameters to be added to the request.
     * @param body The request body to be sent with the request.
     * @return The response object containing the response body, headers, and status code.
     */
    public static Response sendPutRequest(String baseUrl, String endpoint, Map<String, String> headers,
                                          Map<String, Integer> parameters, String body) {
        return given().spec(buildRequestSpecification(baseUrl, headers, parameters, body))
                .contentType(ContentType.JSON)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a DELETE request to the specified endpoint using the provided base URL, headers, parameters, and request body.
     * @param baseUrl The base URL for the request.
     * @param endpoint The endpoint to which the request is sent.
     * @param headers A map of headers to be added to the request.
     * @param parameters A map of query parameters to be added to the request.
     * @return The response object containing the response body, headers, and status code.
     */
    public static Response sendDeleteRequest(String baseUrl, String endpoint, Map<String, String> headers,
                                             Map<String, Integer> parameters) {
        return given().spec(buildRequestSpecification(baseUrl, null, parameters, null))
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Validates if the received response's status code matches the expected status code.
     *
     * @param response The response object containing the response body, headers, and status code.
     * @param expectedStatusCode The expected status code to compare with the received response's status code.
     * @return True if the received response's status code matches the expected status code, otherwise false.
     */
    public static boolean validateStatusCode(Response response, int expectedStatusCode) {
        return response.getStatusCode() == expectedStatusCode;
    }

    /**
     * Retrieves the value from the JSON response using the specified JSON path.
     *
     * @param response The response object containing the response body, headers, and status code.
     * @param jsonPath The JSON path to extract the value from the response.
     * @return The value extracted from the JSON response using the specified JSON path.
     */
    public static String getValueFromResponse(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }
}
