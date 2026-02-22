package com.khapara.orderservice.dtos.order;

import lombok.Data;

@Data
public class OrderDTO {

    private Long userId;
    private Long shippingOptionsId;
    private Long paymentTransactionsId;
    private Long promoCodeId;
    private String trackingStatus;

}
