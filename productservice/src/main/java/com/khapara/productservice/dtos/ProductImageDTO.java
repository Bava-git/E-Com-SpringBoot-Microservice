package com.khapara.productservice.dtos;

import lombok.Data;

@Data
public class ProductImageDTO {

    private Long id;
    private String productImageHref;
    private String productImageAlt;

}
