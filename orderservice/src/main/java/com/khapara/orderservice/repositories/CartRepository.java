package com.khapara.orderservice.repositories;

import com.khapara.orderservice.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);

    Optional<Cart> findByIdAndUserId(Long id, Long userId);

    Optional<Cart> findByProductIdAndUserId(Long productId, Long userId);

    long countByUserId(Long userId);
}
