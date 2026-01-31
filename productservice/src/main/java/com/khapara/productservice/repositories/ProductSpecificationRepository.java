package com.khapara.productservice.repositories;

import com.khapara.productservice.entities.ProductReview;
import com.khapara.productservice.entities.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Long> {
}
