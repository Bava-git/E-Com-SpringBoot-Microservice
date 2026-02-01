package com.khapara.productservice.dtos;

import lombok.Data;

@Data
public class ProductReviewDTO {

    private Long id;
    private Double productStarRating;
    private String productFeedback;

}
