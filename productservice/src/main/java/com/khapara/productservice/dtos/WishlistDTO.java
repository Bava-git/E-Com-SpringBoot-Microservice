package com.khapara.productservice.dtos;

import lombok.Data;

@Data
public class WishlistDTO {

    private Long id;
    private Long productSizeId;
    private Long userId;
    private Long productId;

}
