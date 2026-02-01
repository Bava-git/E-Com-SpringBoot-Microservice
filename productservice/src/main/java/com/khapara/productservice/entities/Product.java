package com.khapara.productservice.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product Name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String productName;

    @Size(max = 10, message = "Group Id cannot exceed 10 characters")
    private String groupId;

    @Size(max = 500, message = "slug cannot exceed 500 characters")
    private String slug;

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
    private List<ProductReview> reviews = new ArrayList<>();

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

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
