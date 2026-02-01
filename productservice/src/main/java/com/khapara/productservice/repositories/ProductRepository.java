package com.khapara.productservice.repositories;

import com.khapara.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByGroupId(String groupId);
    Optional<Product> findBySlug(String slug);

}

