package com.globant.automation.test;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.User;
import com.globant.automation.request.RequestBuilder;
import com.globant.automation.util.TestDataFactory;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LogoutUserTests extends TestRunner {

    /**
     * Logs in with a user owned by this test and then closes the session.
     */
    @Test(testName = "Logout - successful session close")
    public void shouldLogoutSuccessfully() {
        User newUser = TestDataFactory.buildUniqueUser();
        Response createResponse = RequestBuilder.postRequest(getBaseUrl(), "/user", newUser);
        assertEquals(createResponse.getStatusCode(), 200, "Precondition: user must be created successfully");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("username", newUser.getUsername());
        queryParams.put("password", newUser.getPassword());
        Response loginResponse = RequestBuilder.getRequest(getBaseUrl(), "/user/login", queryParams);
        assertEquals(loginResponse.getStatusCode(), 200, "Precondition: login must be successful");

        Response logoutResponse = RequestBuilder.getRequest(getBaseUrl(), "/user/logout");

        // Petstore documents a successful "default" response; in practice it returns 200 with body "ok".
        assertEquals(logoutResponse.getStatusCode(), 200, "Logout status code must be 200");
        assertTrue(
                logoutResponse.asString().toLowerCase().contains("ok"),
                "Logout response must contain 'ok'"
        );
    }
}