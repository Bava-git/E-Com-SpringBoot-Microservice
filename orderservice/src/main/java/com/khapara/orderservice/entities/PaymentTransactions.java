package com.khapara.orderservice.entities;

import com.khapara.orderservice.entities.erum.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class PaymentTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull(message = "Userid is required")
    private Long userId;

    @Positive
    @NotNull(message = "paymentMethodId is required")
    private Long paymentMethodId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Positive
    @NotNull(message = "subtotal is required")
    private long subtotal;

    @Positive
    @NotNull(message = "marketPlaceFee is required")
    private long marketPlaceFee;

    @PositiveOrZero
    @NotNull(message = "deliveryFee is required")
    private long deliveryFee;

    @NegativeOrZero
    @NotNull(message = "discountAmount is required")
    private long discountAmount;

    @Positive
    @NotNull(message = "total is required")
    private long total;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
