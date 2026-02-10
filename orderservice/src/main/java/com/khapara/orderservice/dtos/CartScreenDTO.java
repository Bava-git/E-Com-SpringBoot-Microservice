package com.khapara.orderservice.dtos;

import lombok.Data;

@Data
public class CartScreenDTO {

    private Long id;
    private Long sizeId;
    private Long userId;
    private Long productId;
    private String name;
    private Double price;
    private String image;
    private String color;
    private String size;
    private Integer quantity;
    
}
