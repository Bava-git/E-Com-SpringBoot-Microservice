package com.khapara.orderservice.dtos.cart;

import lombok.Data;

@Data
public class UpdateQuantityDTO {

    private Long id;
    private Long userId;
    private Integer quantity;

}
