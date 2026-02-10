package com.khapara.productservice.dtos;

import lombok.Data;

@Data
public class WishlistScreenDTO {

    private Long id;
    private Long productSizeId;
    private Long userId;
    private Long productId;
    private String name;
    private Double price;
    private String image;
    private String color;
    private String size;

}
