package com.khapara.orderservice.dtos.cart;

import lombok.Data;

@Data
public class CartDTO {

    private Long id;
    private Long sizeId;
    private Long userId;
    private Long productId;
    private Integer quantity;
}
