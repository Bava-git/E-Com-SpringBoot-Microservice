package com.khapara.productservice.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {

    private Long id;
    private String productName;
    private String slug;
    private String groupId;
    private String productBrand;
    private String productTagline;
    private Double productPrice;
    private Integer productStock;
    private String productColorName;
    private String productColorHex;

    // Review
    private Double productStarRating;
    private String productFeedback;

    // Nested
    private List<ProductSizeDTO> sizes = new ArrayList<>();
    private List<ProductImageDTO> images = new ArrayList<>();
    private List<ProductSpecificationDTO> specifications = new ArrayList<>();
    private List<String> features;

}
