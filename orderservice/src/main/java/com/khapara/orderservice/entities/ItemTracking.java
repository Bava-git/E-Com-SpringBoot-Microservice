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
public class ItemTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "uniqueItemTrackingId is required")
    @Size(max = 40, message = "uniqueItemTrackingId limited to 40 characters")
    private String uniqueItemTrackingId;

    @Enumerated(EnumType.STRING)
    private TrackingStatus trackingStatus;

    @Positive
    @NotNull(message = "orderId is required")
    private Long orderId;

    @NotNull(message = "eta must not be null")
    @FutureOrPresent(message = "eta must be in the present or future")
    private LocalDateTime eta;

    private LocalDateTime ata;

    @Positive
    @NotNull(message = "paymentTransactionsId is required")
    private Long paymentTransactionsId;

    @Positive
    @NotNull(message = "productId is required")
    private Long productId;

    @Positive(message = "quantity must be greater than 0")
    @NotNull(message = "quantity is required")
    private Integer quantity;

    @Positive
    @NotNull(message = "sizeId is required")
    private Long sizeId;

    @Positive
    @NotNull(message = "Userid is required")
    private Long userId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime orderDate;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (uniqueItemTrackingId == null) {
            uniqueItemTrackingId = UUID.randomUUID().toString();
        }
    }


}
