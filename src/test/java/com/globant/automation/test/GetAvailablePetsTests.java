package com.globant.automation.test;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.Pet;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class GetAvailablePetsTests extends TestRunner {

    /**
     * Validates that the API returns the list of pets with status available.
     */
    @Test(testName = "List pets - all with available status")
    public void shouldListOnlyAvailablePets() {
        Map<String, String> queryParams = Collections.singletonMap("status", "available");

        Response response = RequestBuilder.getRequest(getBaseUrl(), "/pet/findByStatus", queryParams);
        List<Pet> pets = response.jsonPath().getList(".", Pet.class);

        assertEquals(response.getStatusCode(), 200, "List status code must be 200");
        assertNotNull(pets, "Pet list must not be null");
        assertFalse(pets.isEmpty(), "There must be at least one available pet");
        assertTrue(
                pets.stream().allMatch(pet -> "available".equalsIgnoreCase(pet.getStatus())),
                "All returned pets must have available status"
        );
    }
}