package com.khapara.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "full Name is required")
    @Size(min = 3, max = 200, message = "full Name must be between 3 and 200 characters")
    private String fullName;

    @NotBlank(message = "doorNumber is required")
    @Size(min = 1, max = 100, message = "doorNumber 100 characters limited")
    private String doorNumber;

    @NotBlank(message = "streetName is required")
    @Size(min = 1, max = 200, message = "streetName 200 characters limited")
    private String streetName;

    @NotBlank(message = "city is required")
    @Size(min = 1, max = 200, message = "city 200 characters limited")
    private String city;

    @NotBlank(message = "state is required")
    @Size(min = 1, max = 200, message = "state 200 characters limited")
    private String state;

    @NotBlank(message = "zipcode is required")
    @Size(max = 6, min = 6, message = "zipcode 6 characters limited")
    @Pattern(regexp = "\\d+")
    private String zipcode;

    @NotBlank(message = "nearByLandmark is required")
    @Size(min = 1, max = 200, message = "nearByLandmark 200 characters limited")
    private String nearByLandmark;

    @Column(nullable = true)
    @Email
    @Size(max = 200, message = "alternateEmail 200 characters limited")
    private String alternateEmail;

    @Column(nullable = true)
    @Size(min = 10, max = 15, message = "alternatePhoneNumber b/w 10 to 15 characters limited")
    @Pattern(regexp = "\\d+")
    private String alternatePhoneNumber;

    @NotBlank(message = "addressLabel is required")
    @Size(min = 1, max = 10, message = "addressLabel b/w 1 to 10 characters limited")
    private String addressLabel;

    @NotNull(message = "User id is required")
    private Long userId;

}
