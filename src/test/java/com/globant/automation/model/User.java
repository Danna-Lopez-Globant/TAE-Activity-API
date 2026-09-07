package com.globant.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model representing a PerfDog (Petstore) user.
 * <p>
 * Maps to the Swagger {@code User} schema.
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    /** Unique user identifier. */
    private Long id;

    /** Username used for login. */
    private String username;

    /** First name of the user. */
    private String firstName;

    /** Last name of the user. */
    private String lastName;

    /** Email address of the user. */
    private String email;

    /** Plain-text password (required by the demo API). */
    private String password;

    /** Contact phone number. */
    private String phone;

    /** User status (numeric value defined by the API). */
    private Integer userStatus;
}
