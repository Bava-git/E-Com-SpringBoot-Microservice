package com.khapara.productservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product_feature")
public class ProductFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "product Features is required")
    @Size(max = 100, message = "product Features exceed 100 characters")
    private String productFeatures;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    public ProductFeature(Long id, String productFeatures, Product product) {
        this.id = id;
        this.productFeatures = productFeatures;
        this.product = product;
    }

    public ProductFeature() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "product Features is required") @Size(max = 100, message = "product Features exceed 100 characters") String getProductFeatures() {
        return productFeatures;
    }

    public void setProductFeatures(@NotBlank(message = "product Features is required") @Size(max = 100, message = "product Features exceed 100 characters") String productFeatures) {
        this.productFeatures = productFeatures;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
