package com.khapara.productservice.repositories;

import com.khapara.productservice.entities.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
}
