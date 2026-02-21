package com.khapara.userservice.dto.payment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentMethodDTO {

    private String cardLast4Digits;
    private String cardHolderName;
    private LocalDateTime cardExpirationDate;
    private String upiId;
    private String paymentType;
    private Long userId;

}
