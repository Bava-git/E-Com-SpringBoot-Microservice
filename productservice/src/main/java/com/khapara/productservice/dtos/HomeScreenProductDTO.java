package com.khapara.productservice.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HomeScreenProductDTO {
    private Long id;
    private String groupId;
    private String productName;
    private String slug;
    private String productColorName;
    private Double productPrice;
    private List<ProductImageDTO> images;
    private ProductSizeDTO size;
}

