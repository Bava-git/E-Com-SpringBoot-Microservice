package com.khapara.orderservice.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemTrackingDTO {

    private String trackingStatus;
    private Long orderId;
    private LocalDateTime eta;
    private Long paymentTransactionsId;
    private Long productId;
    private Integer quantity;
    private Long sizeId;
    private Long userId;

}
