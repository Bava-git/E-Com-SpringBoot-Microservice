package com.khapara.orderservice.dtos.paymentTransactions;

import lombok.Data;

@Data
public class PaymentTransactionsDTO {

    // get the shipping options to calculate the total
    private Long shippingOptionsId;

    // pass the total and code to get discounted total
    private String promoCode;

    // store them
    private Long paymentMethodId;
    private Long userId;

}
