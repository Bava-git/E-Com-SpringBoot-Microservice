package com.khapara.orderservice.repositories;

import com.khapara.orderservice.entities.ItemTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTrackingRepository extends JpaRepository<ItemTracking, Long> {


}
