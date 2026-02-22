package com.khapara.orderservice.repositories;

import com.khapara.orderservice.entities.ShippingOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingOptionsRepository extends JpaRepository<ShippingOptions, Long> {
}
