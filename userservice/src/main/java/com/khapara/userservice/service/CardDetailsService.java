package com.khapara.userservice.service;

import com.khapara.userservice.dto.CardDetailsDTO;
import com.khapara.userservice.dto.UpdateDefaultCardDTO;
import com.khapara.userservice.entity.CardDetails;
import com.khapara.userservice.exception.ResourceNotFoundException;
import com.khapara.userservice.mapper.CardDetailsMapper;
import com.khapara.userservice.repository.CardDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardDetailsService {

    @Autowired
    private CardDetailsRepository cardDetailsRep;

    public List<CardDetailsDTO> listCardDetails(Long userId) {
        return cardDetailsRep.findByUserId(userId)
                .stream()
                .map(CardDetailsMapper::toDto)
                .toList();
    }

    public CardDetailsDTO saveCardDetails(CardDetailsDTO dto) {
        CardDetails cardDetails = cardDetailsRep.save(CardDetailsMapper.toEntity(dto));
        return CardDetailsMapper.toDto(cardDetails);
    }

    @Transactional
    public void updateDefaultCard(UpdateDefaultCardDTO updateDefaultCardDTO) {
        CardDetails card = cardDetailsRep.findByIdAndUserId(updateDefaultCardDTO.getId(), updateDefaultCardDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        cardDetailsRep.resetDefaultForUser(updateDefaultCardDTO.getUserId());
        card.setDefault(true);
        cardDetailsRep.save(card);
    }

    public void removeCard(Long id, Long userId) {
        CardDetails cardDetails = cardDetailsRep.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with ID: " + id + " User id: " + userId));
        cardDetailsRep.delete(cardDetails);
    }


}
