package com.khapara.orderservice.dtos.paymentTransactions;

import lombok.Data;

@Data
public class ResPaymentTransactionsDTO {

    private Long id;
    private long subtotal;
    private long marketPlaceFee;
    private long deliveryFee;
    private long discountAmount;
    private long total;


}
