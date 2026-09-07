package com.globant.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Model representing a pet in the PerfDog catalog.
 * <p>
 * Maps to the Swagger Petstore {@code Pet} schema.
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {

    /** Unique pet identifier. */
    private Long id;

    /** Pet category. */
    private Category category;

    /** Pet name. */
    private String name;

    /** Pet photo URLs. */
    private List<String> photoUrls;

    /** Associated tags. */
    private List<Tag> tags;

    /**
     * Pet status in the store.
     * Possible values: {@code available}, {@code pending}, {@code sold}.
     */
    private String status;
}