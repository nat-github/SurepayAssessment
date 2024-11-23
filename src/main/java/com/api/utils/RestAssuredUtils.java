package com.api.utils;

import java.util.Map;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredUtils {
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
    private static RequestSpecification buildRequestSpecification(String baseUrl,Map<String, Integer> parameters) {

        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder().setRelaxedHTTPSValidation()
                .setBaseUri(baseUrl);
        if (parameters != null && !parameters.isEmpty()) {
            reqSpecBuilder.addQueryParams(parameters);
        }
        return reqSpecBuilder.build();
    }

    // GET request
    public static Response sendGetRequest(String baseUrl, String endpoint, Map<String, String> headers, Map<String, Integer> parameters) {
        return given().spec(buildRequestSpecification(baseUrl, headers,parameters, null))
                .when()
                .contentType(ContentType.JSON)
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
    public static Response sendGetRequest(String baseUrl, String endpoint, Map<String, Integer> parameters) {
        return given().spec(buildRequestSpecification(baseUrl,parameters))
                .when()
                .contentType(ContentType.JSON)
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // POST request with body
    public static Response sendPostRequest(String baseUrl, String endpoint, Map<String, String> headers, Map<String, Integer> parameters, String body) {
        return given().spec(buildRequestSpecification(baseUrl, headers, parameters, body))
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    // PUT request with body
    public static Response sendPutRequest(String baseUrl, String endpoint, Map<String, String> headers, Map<String, Integer> parameters, String body) {
        return given().spec(buildRequestSpecification(baseUrl, headers, parameters, body))
                .contentType(ContentType.JSON)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    // DELETE request
    public static Response sendDeleteRequest(String baseUrl, String endpoint, Map<String, String> headers, Map<String, Integer> parameters) {
        return given().spec(buildRequestSpecification(baseUrl, null, parameters, null))
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    // Validate response status code
    public static boolean validateStatusCode(Response response, int expectedStatusCode) {
        return response.getStatusCode() == expectedStatusCode;
    }

    // Get value from response using JsonPath
    public static String getValueFromResponse(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }
}
