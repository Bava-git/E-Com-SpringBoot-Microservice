package com.khapara.productservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product_size")
public class ProductSize {

    public ProductSize() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "product Size Label is required") @Size(max = 100, message = "product Size Label exceed 100 characters") String getProductSizeLabel() {
        return productSizeLabel;
    }

    public void setProductSizeLabel(@NotBlank(message = "product Size Label is required") @Size(max = 100, message = "product Size Label exceed 100 characters") String productSizeLabel) {
        this.productSizeLabel = productSizeLabel;
    }

    public @NotBlank(message = "product Size Code is required") @Size(max = 100, message = "product Size Code exceed 100 characters") String getProductSizeCode() {
        return productSizeCode;
    }

    public void setProductSizeCode(@NotBlank(message = "product Size Code is required") @Size(max = 100, message = "product Size Code exceed 100 characters") String productSizeCode) {
        this.productSizeCode = productSizeCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductSize(Long id, String productSizeLabel, String productSizeCode, Product product) {
        this.id = id;
        this.productSizeLabel = productSizeLabel;
        this.productSizeCode = productSizeCode;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "product Size Label is required")
    @Size(max = 100, message = "product Size Label exceed 100 characters")
    private String productSizeLabel;

    @NotBlank(message = "product Size Code is required")
    @Size(max = 100, message = "product Size Code exceed 100 characters")
    private String productSizeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

}
