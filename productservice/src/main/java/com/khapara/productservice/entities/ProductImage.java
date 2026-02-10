package com.khapara.productservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "product_image")
@Data
public class ProductImage {

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
