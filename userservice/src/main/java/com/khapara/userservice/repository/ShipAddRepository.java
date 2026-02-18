package com.khapara.userservice.repository;

import com.khapara.userservice.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipAddRepository extends JpaRepository<ShippingAddress, Long> {

    List<ShippingAddress> findByUserId(Long userId);

    Optional<ShippingAddress> findByIdAndUserId(Long id, Long userId);
}
