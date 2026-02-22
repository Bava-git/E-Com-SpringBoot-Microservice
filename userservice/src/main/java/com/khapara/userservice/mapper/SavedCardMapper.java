package com.khapara.userservice.mapper;

import com.khapara.userservice.dto.SavedCardDTO;
import com.khapara.userservice.entity.SavedCard;

public class SavedCardMapper {

    public static SavedCard toEntity(SavedCardDTO dto) {
        if (dto == null) return null;

        SavedCard savedCard = new SavedCard();
        savedCard.setId(dto.getId());
        savedCard.setLast4Digits(dto.getLast4Digits());
        savedCard.setCardHolderName(dto.getCardHolderName());
        savedCard.setCardExpirationDate(dto.getCardExpirationDate());
        savedCard.setDefault(dto.isDefault());
        savedCard.setUserId(dto.getUserId());

        return savedCard;

    }

    public static SavedCardDTO toDto(SavedCard cardDetails) {
        if (cardDetails == null) return null;

        SavedCardDTO dto = new SavedCardDTO();
        dto.setId(cardDetails.getId());
        dto.setLast4Digits(cardDetails.getLast4Digits());
        dto.setCardHolderName(cardDetails.getCardHolderName());
        dto.setCardExpirationDate(cardDetails.getCardExpirationDate());
        dto.setDefault(cardDetails.isDefault());
        dto.setUserId(cardDetails.getUserId());

        return dto;
    }


}
