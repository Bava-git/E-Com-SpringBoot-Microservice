package com.khapara.productservice.dtos;

import java.util.ArrayList;
import java.util.List;

public class AddProductDTO {

    private String productName;
    private String groupId;
    private String productBrand;
    private String productTagline;
    private Double productPrice;
    private Integer productStock;
    private String productColorName;
    private String productColorHex;
    private List<ProductSizeDTO> sizes = new ArrayList<>();
    private List<ProductImageDTO> images = new ArrayList<>();
    private List<ProductSpecificationDTO> specifications = new ArrayList<>();
    private List<String> features;

    public AddProductDTO(String productName, String groupId, String productBrand, String productTagline, Double productPrice, Integer productStock, String productColorName, String productColorHex, List<ProductSizeDTO> sizes, List<ProductImageDTO> images, List<ProductSpecificationDTO> specifications, List<String> features) {
        this.productName = productName;
        this.groupId = groupId;
        this.productBrand = productBrand;
        this.productTagline = productTagline;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productColorName = productColorName;
        this.productColorHex = productColorHex;
        this.sizes = sizes;
        this.images = images;
        this.specifications = specifications;
        this.features = features;
    }

    public AddProductDTO() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductTagline() {
        return productTagline;
    }

    public void setProductTagline(String productTagline) {
        this.productTagline = productTagline;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductColorName() {
        return productColorName;
    }

    public void setProductColorName(String productColorName) {
        this.productColorName = productColorName;
    }

    public String getProductColorHex() {
        return productColorHex;
    }

    public void setProductColorHex(String productColorHex) {
        this.productColorHex = productColorHex;
    }

    public List<ProductSizeDTO> getSizes() {
        return sizes;
    }

    public void setSizes(List<ProductSizeDTO> sizes) {
        this.sizes = sizes;
    }

    public List<ProductImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ProductImageDTO> images) {
        this.images = images;
    }

    public List<ProductSpecificationDTO> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<ProductSpecificationDTO> specifications) {
        this.specifications = specifications;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
