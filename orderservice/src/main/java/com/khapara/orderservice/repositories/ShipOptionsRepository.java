package com.khapara.orderservice.repositories;

import com.khapara.orderservice.entities.ShipOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipOptionsRepository extends JpaRepository<ShipOptions, Long> {
}
