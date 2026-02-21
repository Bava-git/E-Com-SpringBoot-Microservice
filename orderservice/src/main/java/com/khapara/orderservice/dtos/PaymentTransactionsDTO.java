package com.khapara.orderservice.dtos;

import lombok.Data;

@Data
public class PaymentTransactionsDTO {

    private Long userId;
    private Long paymentMethodId;
    private String paymentStatus;
    private long amount;

}
