package com.globant.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic Petstore API response for operations such as user creation.
 * <p>
 * Maps to the {@code ApiResponse} schema.
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    /** Result code reported by the API. */
    private Integer code;

    /** Response type. */
    private String type;

    /** Descriptive message of the response*/
    private String message;
}
