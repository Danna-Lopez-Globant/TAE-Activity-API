package com.globant.automation.util;

import com.globant.automation.model.Category;
import com.globant.automation.model.Order;
import com.globant.automation.model.Pet;
import com.globant.automation.model.Tag;
import com.globant.automation.model.User;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

/**
 * Test data factory that keeps tests independent and uses unique data
 * on each run (avoids collisions across executions).
 */
public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static User buildUniqueUser() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        return User.builder()
                .id(System.currentTimeMillis())
                .username("perfdog_user_" + suffix)
                .firstName("Perf")
                .lastName("Dog")
                .email("perfdog_" + suffix + "@example.com")
                .password("Pass_" + suffix)
                .phone("3001234567")
                .userStatus(1)
                .build();
    }

    public static Pet buildAvailablePet() {
        long id = System.currentTimeMillis();
        return Pet.builder()
                .id(id)
                .name("Buddy_" + id)
                .category(Category.builder().id(1L).name("dogs").build())
                .photoUrls(Collections.singletonList("https://example.com/pet/" + id + ".jpg"))
                .tags(Collections.singletonList(Tag.builder().id(1L).name("friendly").build()))
                .status("available")
                .build();
    }

    public static Order buildOrderForPet(Long petId) {
        return Order.builder()
                .id(System.currentTimeMillis())
                .petId(petId)
                .quantity(1)
                .shipDate(Instant.now().toString())
                .status("placed")
                .complete(false)
                .build();
    }
}