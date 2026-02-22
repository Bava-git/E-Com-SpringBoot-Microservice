package com.khapara.orderservice.entities;

import com.khapara.orderservice.entities.erum.TrackingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "uniqueOrderId is required")
    @Size(max = 40, message = "uniqueOrderId limited to 40 characters")
    private String uniqueOrderId;

    @Positive
    @NotNull(message = "Userid is required")
    private Long userId;

    @Positive
    @NotNull(message = "shippingOptionsId is required")
    private Long shippingOptionsId;

    @Positive
    @NotNull(message = "paymentTransactionsId is required")
    private Long paymentTransactionsId;

    @PositiveOrZero
    @NotNull(message = "paymentTransactionsId is required")
    private Long promoCodeId;

    @Enumerated(EnumType.STRING)
    private TrackingStatus trackingStatus;

    @NotNull(message = "eta must not be null")
    @FutureOrPresent(message = "eta must be in the present or future")
    private LocalDateTime eta;

    private LocalDateTime ata;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime orderDate;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (uniqueOrderId == null) {
            uniqueOrderId = UUID.randomUUID().toString();
        }
    }


}
