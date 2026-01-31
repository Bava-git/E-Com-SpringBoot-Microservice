package com.khapara.productservice.repositories;

import com.khapara.productservice.entities.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}
