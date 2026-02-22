package com.khapara.userservice.service;

import com.khapara.userservice.dto.SavedCardDTO;
import com.khapara.userservice.dto.UpdateDefaultCardDTO;
import com.khapara.userservice.entity.SavedCard;
import com.khapara.userservice.exception.ResourceNotFoundException;
import com.khapara.userservice.mapper.SavedCardMapper;
import com.khapara.userservice.repository.SavedCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SavedCardService {

    @Autowired
    private SavedCardRepository cardDetailsRep;

    public List<SavedCardDTO> listCardDetails(Long userId) {
        return cardDetailsRep.findByUserId(userId)
                .stream()
                .map(SavedCardMapper::toDto)
                .toList();
    }

    public SavedCardDTO saveCardDetails(SavedCardDTO dto) {
        SavedCard cardDetails = cardDetailsRep.save(SavedCardMapper.toEntity(dto));
        return SavedCardMapper.toDto(cardDetails);
    }

    @Transactional
    public void updateDefaultCard(UpdateDefaultCardDTO updateDefaultCardDTO) {
        SavedCard card = cardDetailsRep.findByIdAndUserId(updateDefaultCardDTO.getId(), updateDefaultCardDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        cardDetailsRep.resetDefaultForUser(updateDefaultCardDTO.getUserId());
        card.setDefault(true);
        cardDetailsRep.save(card);
    }

    public void removeCard(Long id, Long userId) {
        SavedCard cardDetails = cardDetailsRep.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with ID: " + id + " User id: " + userId));
        cardDetailsRep.delete(cardDetails);
    }


}
