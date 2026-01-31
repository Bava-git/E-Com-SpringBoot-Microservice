package com.khapara.productservice.dtos;

public class ProductImageDTO {

    private String productImageHref;
    private String productImageAlt;

    public ProductImageDTO(String productImageHref, String productImageAlt) {
        this.productImageHref = productImageHref;
        this.productImageAlt = productImageAlt;
    }

    public ProductImageDTO() {

    }

    public String getProductImageHref() {
        return productImageHref;
    }

    public void setProductImageHref(String productImageHref) {
        this.productImageHref = productImageHref;
    }

    public String getProductImageAlt() {
        return productImageAlt;
    }

    public void setProductImageAlt(String productImageAlt) {
        this.productImageAlt = productImageAlt;
    }
}
