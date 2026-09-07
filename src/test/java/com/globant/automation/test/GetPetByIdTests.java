package com.globant.automation.test;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.Pet;
import com.globant.automation.request.RequestBuilder;
import com.globant.automation.util.TestDataFactory;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/** Tests for the functionality: Get details of a specific pet</b>.
 * <p>
 *     Endpoint: {@code GET /pet/{petId}}
 * </p>
 */
public class GetPetByIdTests extends TestRunner {

    /**
     * Creates a pet as internal setup and retrieves its details by id.
     */
    @Test(testName = "get pet details by id")
    public void shouldGetPetDetailsById() {
        Pet petToCreate = TestDataFactory.buildAvailablePet();
        Response createResponse = RequestBuilder.postRequest(getBaseUrl(), "/pet", petToCreate);
        assertEquals(createResponse.getStatusCode(), 200, "Precondition: pet must be created successfully");

        Pet createdPet = createResponse.as(Pet.class);
        Long petId = createdPet.getId() != null ? createdPet.getId() : petToCreate.getId();

        Response getResponse = RequestBuilder.getRequestWithApiKey(
                getBaseUrl(),
                "/pet/" + petId,
                getApiKey()
        );
        Pet retrievedPet = getResponse.as(Pet.class);

        assertEquals(getResponse.getStatusCode(), 200, "Get pet status code must be 200");
        assertEquals(retrievedPet.getId(), petId, "Retrieved pet id must match");
        assertEquals(retrievedPet.getName(), petToCreate.getName(), "Pet name must match");
        assertNotNull(retrievedPet.getStatus(), "Pet status must not be null");
        assertEquals(retrievedPet.getStatus(), "available", "Retrieved pet must be available");
    }
}