package com.khapara.userservice.mapper;

import com.khapara.userservice.dto.CardDetailsDTO;
import com.khapara.userservice.entity.CardDetails;

public class CardDetailsMapper {

    public static CardDetails toEntity(CardDetailsDTO dto) {
        if (dto == null) return null;

        CardDetails cardDetails = new CardDetails();
        cardDetails.setId(dto.getId());
        cardDetails.setLast4Digits(dto.getLast4Digits());
        cardDetails.setCardHolderName(dto.getCardHolderName());
        cardDetails.setCardExpirationDate(dto.getCardExpirationDate());
        cardDetails.setDefault(dto.isDefault());
        cardDetails.setUserId(dto.getUserId());

        return cardDetails;

    }

    public static CardDetailsDTO toDto(CardDetails cardDetails) {
        if (cardDetails == null) return null;

        CardDetailsDTO dto = new CardDetailsDTO();
        dto.setId(cardDetails.getId());
        dto.setLast4Digits(cardDetails.getLast4Digits());
        dto.setCardHolderName(cardDetails.getCardHolderName());
        dto.setCardExpirationDate(cardDetails.getCardExpirationDate());
        dto.setDefault(cardDetails.isDefault());
        dto.setUserId(cardDetails.getUserId());

        return dto;
    }


}
