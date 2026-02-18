package com.khapara.orderservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "last 4 Digits is required")
    @Size(min = 4, max = 10, message = "last 4 Digits exceed b/w 4 to 10 characters")
    private String last4Digits;

    @NotBlank(message = "card Holder Name is required")
    @Size(min = 3, max = 10, message = "card Holder Name exceed b/w 3 to 10 characters")
    private String cardHolderName;

    @Column(nullable = false)
    private LocalDate cardExpirationDate;

    private boolean isDefault;

    @Positive
    @NotNull(message = "User id is required")
    private Long userId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
