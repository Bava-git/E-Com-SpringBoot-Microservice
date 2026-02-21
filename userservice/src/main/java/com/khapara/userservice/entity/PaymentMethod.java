package com.khapara.userservice.entity;

import com.khapara.userservice.entity.erum.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Card details
    @Size(max = 4, message = "card Last 4 Digits limited b/w 4 to 10 characters")
    private String cardLast4Digits;

    @Size(min = 3, max = 20, message = "card Holder Name limited b/w 3 to 20 characters")
    private String cardHolderName;

    @Column(nullable = false)
    private LocalDateTime cardExpirationDate;

    //UPI id
    @Size(max = 30, message = "upi ID limited 30 characters")
    private String upiId;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Positive
    @NotNull(message = "User id is required")
    private Long userId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
