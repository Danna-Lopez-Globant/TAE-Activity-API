package com.globant.automation.test;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.User;
import com.globant.automation.request.RequestBuilder;
import com.globant.automation.util.TestDataFactory;
import com.globant.automation.model.ApiResponse;


import org.testng.annotations.Test;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertNotNull;

/**
 * Tests for the functionality: <b>Create a user</b>.
 * <p>
 * Endpoint: {@code POST /user}
 * </p>
 */
public class CreateUserTests extends TestRunner {

    /**
     * Validates that a new user can be created in the PerfDog store.
     * <p>
     * The test is independent: it generates unique data on each run
     * and does not depend on other test cases.
     * </p>
     */
    @Test(testName = "Create user - successful response with status 200")
    public void shouldCreateUserSuccessfully() {
        User newUser = TestDataFactory.buildUniqueUser();

        Response response = RequestBuilder.postRequest(getBaseUrl(), "/user", newUser);
        ApiResponse apiResponse = response.as(ApiResponse.class);

        assertEquals(response.getStatusCode(), 200, "User creation status code must be 200");
        assertNotNull(apiResponse.getMessage(), "Response message must not be null");
        assertTrue(
                apiResponse.getCode() == null || apiResponse.getCode() == 200,
                "ApiResponse code must be 200 when present"
        );
    }
}
