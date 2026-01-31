package com.khapara.productservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product_image")
public class ProductImage {

    public ProductImage() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "product Image Href is required") @Size(max = 200, message = "product Image Href exceed 100 characters") String getProductImageHref() {
        return productImageHref;
    }

    public void setProductImageHref(@NotBlank(message = "product Image Href is required") @Size(max = 200, message = "product Image Href exceed 100 characters") String productImageHref) {
        this.productImageHref = productImageHref;
    }

    public @NotBlank(message = "product Image Alt is required") @Size(max = 100, message = "product Image Alt exceed 100 characters") String getProductImageAlt() {
        return productImageAlt;
    }

    public void setProductImageAlt(@NotBlank(message = "product Image Alt is required") @Size(max = 100, message = "product Image Alt exceed 100 characters") String productImageAlt) {
        this.productImageAlt = productImageAlt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductImage(Long id, String productImageHref, String productImageAlt, Product product) {
        this.id = id;
        this.productImageHref = productImageHref;
        this.productImageAlt = productImageAlt;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "product Image Href is required")
    @Size(max = 200, message = "product Image Href exceed 100 characters")
    private String productImageHref;

    @NotBlank(message = "product Image Alt is required")
    @Size(max = 100, message = "product Image Alt exceed 100 characters")
    private String productImageAlt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

}
