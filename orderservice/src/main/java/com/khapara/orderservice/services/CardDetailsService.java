package com.khapara.orderservice.services;

import com.khapara.orderservice.dtos.CardDetailsDTO;
import com.khapara.orderservice.entities.CardDetails;
import com.khapara.orderservice.exception.ResourceNotFoundException;
import com.khapara.orderservice.mappers.CardDetailsMapper;
import com.khapara.orderservice.repositories.CardDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardDetailsService {

    @Autowired
    private CardDetailsRepository cardDetailsRep;

    private final CardDetailsMapper cardDetailsMapper;

    public CardDetailsService(CardDetailsMapper cardDetailsMapper) {
        this.cardDetailsMapper = cardDetailsMapper;
    }

    public List<CardDetailsDTO> listCardDetails(Long userId) {
        return cardDetailsRep.findByUserId(userId);
    }

    public CardDetailsDTO saveCardDetails(CardDetailsDTO dto) {
        CardDetails cardDetails = cardDetailsRep.save(cardDetailsMapper.toEntity(dto));
        return cardDetailsMapper.toDto(cardDetails);
    }

    public void removeCard(Long id, Long userId) {
        CardDetails cardDetails = cardDetailsRep.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with ID: " + id + " User id: " + userId));
        cardDetailsRep.delete(cardDetails);
    }


}
