package com.khapara.productservice.dtos;

public class ProductSizeDTO {
    private String productSizeLabel;
    private String productSizeCode;

    public ProductSizeDTO() {

    }

    public String getProductSizeLabel() {
        return productSizeLabel;
    }

    public void setProductSizeLabel(String productSizeLabel) {
        this.productSizeLabel = productSizeLabel;
    }

    public String getProductSizeCode() {
        return productSizeCode;
    }

    public void setProductSizeCode(String productSizeCode) {
        this.productSizeCode = productSizeCode;
    }

    public ProductSizeDTO(String productSizeLabel, String productSizeCode) {
        this.productSizeLabel = productSizeLabel;
        this.productSizeCode = productSizeCode;
    }
}
