package com.khapara.orderservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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

    @Email
    @Size(max = 200, message = "email 200 characters limited")
    private String email;

    @Size(min = 10, max = 15, message = "phoneNumber b/w 10 to 15 characters limited")
    @Pattern(regexp = "\\d+")
    private String phoneNumber;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
