package com.khapara.productservice.repositories;

import com.khapara.productservice.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
