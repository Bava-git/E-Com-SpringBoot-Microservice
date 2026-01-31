package com.khapara.productservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "product_specification")
public class ProductSpecification {

    public ProductSpecification() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "product Spec Label is required") @Size(max = 100, message = "product Spec Label exceed 100 characters") String getProductSpecLabel() {
        return productSpecLabel;
    }

    public void setProductSpecLabel(@NotBlank(message = "product Spec Label is required") @Size(max = 100, message = "product Spec Label exceed 100 characters") String productSpecLabel) {
        this.productSpecLabel = productSpecLabel;
    }

    public @NotBlank(message = "product Spec Value is required") @Size(max = 100, message = "product Spec Value exceed 100 characters") String getProductSpecValue() {
        return productSpecValue;
    }

    public void setProductSpecValue(@NotBlank(message = "product Spec Value is required") @Size(max = 100, message = "product Spec Value exceed 100 characters") String productSpecValue) {
        this.productSpecValue = productSpecValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductSpecification(Long id, String productSpecLabel, String productSpecValue, Product product) {
        this.id = id;
        this.productSpecLabel = productSpecLabel;
        this.productSpecValue = productSpecValue;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "product Spec Label is required")
    @Size(max = 100, message = "product Spec Label exceed 100 characters")
    private String productSpecLabel;

    @NotBlank(message = "product Spec Value is required")
    @Size(max = 100, message = "product Spec Value exceed 100 characters")
    private String productSpecValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

}
