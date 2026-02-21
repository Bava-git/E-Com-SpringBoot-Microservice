package com.khapara.orderservice.dtos;

import lombok.Data;

@Data
public class ItemTrackingDTO {

    private String trackingStatus;
    private Long orderId;
    private Long productId;
    private Long sizeId;
    private Long userId;

}
