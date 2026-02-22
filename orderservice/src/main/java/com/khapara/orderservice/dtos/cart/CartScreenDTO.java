package com.khapara.orderservice.dtos.cart;

import lombok.Data;

@Data
public class CartScreenDTO {

    private Long id;
    private Long sizeId;
    private Long userId;
    private Long productId;
    private String name;
    private long price;
    private String image;
    private String color;
    private String size;
    private Integer quantity;

}
