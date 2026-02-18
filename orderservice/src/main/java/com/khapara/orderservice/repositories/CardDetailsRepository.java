package com.khapara.orderservice.repositories;

import com.khapara.orderservice.dtos.CardDetailsDTO;
import com.khapara.orderservice.entities.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {
    List<CardDetailsDTO> findByUserId(Long userId);

    Optional<CardDetails> findByIdAndUserId(Long id, Long userId);
}
