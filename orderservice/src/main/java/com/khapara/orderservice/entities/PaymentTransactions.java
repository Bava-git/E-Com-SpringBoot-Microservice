package com.khapara.orderservice.entities;

import com.khapara.orderservice.entities.erum.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "User id is required")
    private Long userId;

    @Positive
    @NotNull(message = "payment Method Id is required")
    private Long paymentMethodId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Positive
    @NotNull(message = "Amount is required")
    private long amount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
