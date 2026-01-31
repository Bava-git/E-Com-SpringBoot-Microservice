package com.khapara.productservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product_review")
public class ProductReview {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "customer Name is required") @Size(min = 3, max = 100, message = "customer Name exceed 100 characters") String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(@NotBlank(message = "customer Name is required") @Size(min = 3, max = 100, message = "customer Name exceed 100 characters") String customerName) {
        this.customerName = customerName;
    }

    public @NotNull(message = "product Star Rating is required") @DecimalMin(value = "0.0", inclusive = false, message = "product Star Rating must be greater than 0") Double getProductStarRating() {
        return productStarRating;
    }

    public void setProductStarRating(@NotNull(message = "product Star Rating is required") @DecimalMin(value = "0.0", inclusive = false, message = "product Star Rating must be greater than 0") Double productStarRating) {
        this.productStarRating = productStarRating;
    }

    public @Size(min = 10, max = 500, message = "Feedback cannot exceed 500 characters") String getProductFeedback() {
        return productFeedback;
    }

    public void setProductFeedback(@Size(min = 10, max = 500, message = "Feedback cannot exceed 500 characters") String productFeedback) {
        this.productFeedback = productFeedback;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductReview(Long id, String customerName, Double productStarRating, String productFeedback, LocalDateTime createdAt, Product product) {
        this.id = id;
        this.customerName = customerName;
        this.productStarRating = productStarRating;
        this.productFeedback = productFeedback;
        this.createdAt = createdAt;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "customer Name is required")
    @Size(min = 3, max = 100, message = "customer Name exceed 100 characters")
    private String customerName;

    @NotNull(message = "product Star Rating is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "product Star Rating must be greater than 0")
    private Double productStarRating;

    @Size(min = 10, max = 500, message = "Feedback cannot exceed 500 characters")
    private String productFeedback;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
