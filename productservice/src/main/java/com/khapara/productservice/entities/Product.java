package com.khapara.productservice.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product Name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String productName;

    @Size(max = 10, message = "Group Id cannot exceed 10 characters")
    private String groupId;

    @NotBlank(message = "Brand Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String productBrand;

    @Size(max = 500, message = "Tagline cannot exceed 500 characters")
    private String productTagline;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double productPrice;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer productStock;

    @NotBlank(message = "Product Color Name is required")
    @Size(max = 100, message = "Product Color Name exceed 100 characters")
    private String productColorName;

    @NotBlank(message = "Product Color Hex is required")
    @Size(max = 100, message = "Product Color Hex exceed 100 characters")
    private String productColorHex;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductSize> sizes = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductSpecification> specifications = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductFeature> features = new ArrayList<>();

    public List<ProductFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<ProductFeature> features) {
        this.features = features;
    }

    public Product(Long id, String productName, String groupId, String productBrand, String productTagline, Double productPrice, Integer productStock, String productColorName, String productColorHex, List<ProductSize> sizes, List<ProductImage> images, List<ProductSpecification> specifications, List<ProductFeature> features, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Product Name is required") @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters") String getProductName() {
        return productName;
    }

    public void setProductName(@NotBlank(message = "Product Name is required") @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters") String productName) {
        this.productName = productName;
    }

    public @Size(max = 10, message = "Group Id cannot exceed 10 characters") String getGroupId() {
        return groupId;
    }

    public void setGroupId(@Size(max = 10, message = "Group Id cannot exceed 10 characters") String groupId) {
        this.groupId = groupId;
    }

    public @NotBlank(message = "Brand Name is required") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(@NotBlank(message = "Brand Name is required") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String productBrand) {
        this.productBrand = productBrand;
    }

    public @Size(max = 500, message = "Tagline cannot exceed 500 characters") String getProductTagline() {
        return productTagline;
    }

    public void setProductTagline(@Size(max = 500, message = "Tagline cannot exceed 500 characters") String productTagline) {
        this.productTagline = productTagline;
    }

    public @NotNull(message = "Price is required") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0") Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(@NotNull(message = "Price is required") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0") Double productPrice) {
        this.productPrice = productPrice;
    }

    public @NotNull(message = "Stock is required") @Min(value = 0, message = "Stock cannot be negative") Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(@NotNull(message = "Stock is required") @Min(value = 0, message = "Stock cannot be negative") Integer productStock) {
        this.productStock = productStock;
    }

    public @NotBlank(message = "Product Color Name is required") @Size(max = 100, message = "Product Color Name exceed 100 characters") String getProductColorName() {
        return productColorName;
    }

    public void setProductColorName(@NotBlank(message = "Product Color Name is required") @Size(max = 100, message = "Product Color Name exceed 100 characters") String productColorName) {
        this.productColorName = productColorName;
    }

    public @NotBlank(message = "Product Color Hex is required") @Size(max = 100, message = "Product Color Hex exceed 100 characters") String getProductColorHex() {
        return productColorHex;
    }

    public void setProductColorHex(@NotBlank(message = "Product Color Hex is required") @Size(max = 100, message = "Product Color Hex exceed 100 characters") String productColorHex) {
        this.productColorHex = productColorHex;
    }

    public List<ProductSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<ProductSize> sizes) {
        this.sizes = sizes;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public List<ProductSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<ProductSpecification> specifications) {
        this.specifications = specifications;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Product(Long id, String productName, String groupId, String productBrand, String productTagline, Double productPrice, Integer productStock, String productColorName, String productColorHex, List<ProductSize> sizes, List<ProductImage> images, List<ProductSpecification> specifications, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
