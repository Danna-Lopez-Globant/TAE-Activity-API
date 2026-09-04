package com.globant.automation.test;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.Order;
import com.globant.automation.model.Pet;
import com.globant.automation.request.RequestBuilder;
import com.globant.automation.util.TestDataFactory;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Create an order (purchase) for a pet
 */
public class CreateOrderTests extends TestRunner {

    @Test(testName = "Create order - successful pet purchase")
    public void shouldCreateOrderForPet() {
        Pet petToCreate = TestDataFactory.buildAvailablePet();
        Response createPetResponse = RequestBuilder.postRequest(getBaseUrl(), "/pet", petToCreate);
        assertEquals(createPetResponse.getStatusCode(), 200, "Precondition: pet must be created successfully");

        Pet createdPet = createPetResponse.as(Pet.class);
        Long petId = createdPet.getId() != null ? createdPet.getId() : petToCreate.getId();

        Order order = TestDataFactory.buildOrderForPet(petId);
        Response orderResponse = RequestBuilder.postRequest(getBaseUrl(), "/store/order", order);
        Order createdOrder = orderResponse.as(Order.class);

        assertEquals(orderResponse.getStatusCode(), 200, "Order creation status code must be 200");
        assertNotNull(createdOrder.getId(), "Order id must not be null");
        assertEquals(createdOrder.getPetId(), petId, "Order petId must match the purchased pet");
        assertEquals(createdOrder.getStatus(), "placed", "Initial order status must be placed");
        assertEquals(createdOrder.getQuantity(), Integer.valueOf(1), "Order quantity must be 1");
        assertNotEquals(createdOrder.getComplete(), Boolean.TRUE, "New order must not be complete");
    }
}