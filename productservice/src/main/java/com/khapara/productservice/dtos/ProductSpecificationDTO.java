package com.khapara.productservice.dtos;

public class ProductSpecificationDTO {

    private String productSpecLabel;
    private String productSpecValue;

    public ProductSpecificationDTO() {

    }

    public String getProductSpecLabel() {
        return productSpecLabel;
    }

    public void setProductSpecLabel(String productSpecLabel) {
        this.productSpecLabel = productSpecLabel;
    }

    public String getProductSpecValue() {
        return productSpecValue;
    }

    public void setProductSpecValue(String productSpecValue) {
        this.productSpecValue = productSpecValue;
    }

    public ProductSpecificationDTO(String productSpecLabel, String productSpecValue) {
        this.productSpecLabel = productSpecLabel;
        this.productSpecValue = productSpecValue;
    }
}
