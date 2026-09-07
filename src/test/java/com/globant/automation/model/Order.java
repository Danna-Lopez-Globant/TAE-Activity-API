package com.globant.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for a pet purchase order.
 * <p>
 * Maps to the Swagger Petstore {@code Order} schema.
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    /** Order identifier. */
    private Long id;

    /** Identifier of the purchased pet. */
    private Long petId;

    /** Identifier of the purchased pet. */
    private Integer quantity;

    /** Ship date in ISO-8601 format. */
    private String shipDate;

    /**
     * Order status.
     * Possible values: {@code placed}, {@code approved}, {@code delivered}.
     */
    private String status;

    /** Whether the order is complete. */
    private Boolean complete;
}
