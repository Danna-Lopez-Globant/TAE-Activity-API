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
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class LoginUserTests extends TestRunner {

    /**
     * Creates a user within the same test and then logs in with those credentials.
     */
    @Test(testName = "Login - successful authentication with newly created user")
    public void shouldLoginWithNewlyCreatedUser() {
        User newUser = TestDataFactory.buildUniqueUser();
        Response createResponse = RequestBuilder.postRequest(getBaseUrl(), "/user", newUser);
        assertEquals(createResponse.getStatusCode(), 200, "Precondition: user must be created successfully");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("username", newUser.getUsername());
        queryParams.put("password", newUser.getPassword());

        Response loginResponse = RequestBuilder.getRequest(getBaseUrl(), "/user/login", queryParams);
        String sessionMessage = loginResponse.asString();

        assertEquals(loginResponse.getStatusCode(), 200, "Login status code must be 200");
        assertFalse(sessionMessage.isEmpty(), "Login response must not be empty");
        assertTrue(
                sessionMessage.toLowerCase().contains("logged") || sessionMessage.contains("session"),
                "Login response must indicate an active session"
        );
    }
}