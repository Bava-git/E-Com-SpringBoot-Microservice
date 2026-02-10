package com.khapara.productservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "product_size")
@Data
public class ProductSize {

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
