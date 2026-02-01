package com.khapara.productservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product_review")
@Data
public class ProductReview {

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
