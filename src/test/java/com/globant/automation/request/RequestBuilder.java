package com.globant.automation.request;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public final class RequestBuilder {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String API_KEY_HEADER = "api_key";

    private RequestBuilder() {
        // Utility class
    }

    /**
     * Sends a GET request to the given path.
     *
     * @param baseUrl API base URL
     * @param path    relative resource
     * @return HTTP response
     */
    public static Response getRequest(String baseUrl, String path) {
        return baseRequest(baseUrl).get(path);
    }

    /**
     * Sends a GET request with query parameters.
     *
     * @param baseUrl     API base URL
     * @param path        relative resource
     * @param queryParams query parameter map (e.g. status=available)
     * @return HTTP response
     */
    public static Response getRequest(String baseUrl, String path, Map<String, ?> queryParams) {
        return baseRequest(baseUrl)
                .queryParams(queryParams)
                .get(path);
    }

    /**
     * Sends a GET request including the API key header.
     *
     * @param baseUrl API base URL
     * @param path    relative resource
     * @param apiKey  value for the {@code api_key} header
     * @return HTTP response
     */
    public static Response getRequestWithApiKey(String baseUrl, String path, String apiKey) {
        return baseRequest(baseUrl)
                .header(API_KEY_HEADER, apiKey)
                .get(path);
    }

    /**
     * Sends a POST request with a JSON body.
     *
     * @param baseUrl API base URL
     * @param path    relative resource
     * @param body    object to serialize as JSON
     * @return HTTP response
     */
    public static Response postRequest(String baseUrl, String path, Object body) {
        return baseRequest(baseUrl)
                .body(body)
                .post(path);
    }

    /**
     * Builds the shared base request specification for all calls.
     *
     * @param baseUrl API base URL
     * @return configured RequestSpecification
     */
    private static RequestSpecification baseRequest(String baseUrl) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
    }
}