package com.khapara.productservice.repositories;

import com.khapara.productservice.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUserId(Long userId);

    Optional<WishList> findByIdAndUserId(Long id, Long userId);

    Optional<WishList> findByProductIdAndUserId(Long productId, Long userId);
}
