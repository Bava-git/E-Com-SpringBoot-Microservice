package com.khapara.userservice.mapper;

import com.khapara.userservice.dto.CardDetailsDTO;
import com.khapara.userservice.entity.CardDetails;

public class CardDetailsMapper {

    public static CardDetails toEntity(CardDetailsDTO dto) {
        if (dto == null) return null;

        CardDetails cardDetails = CardDetails.builder()
                .id(dto.getId())
                .last4Digits(dto.getLast4Digits())
                .cardHolderName(dto.getCardHolderName())
                .cardExpirationDate(dto.getCardExpirationDate())
                .isDefault(dto.isDefault())
                .userId(dto.getUserId())
                .build();

        return cardDetails;

    }

    public static CardDetailsDTO toDto(CardDetails cardDetails) {
        if (cardDetails == null) return null;

        CardDetailsDTO dto = CardDetailsDTO.builder()
                .id(cardDetails.getId())
                .last4Digits(cardDetails.getLast4Digits())
                .cardHolderName(cardDetails.getCardHolderName())
                .cardExpirationDate(cardDetails.getCardExpirationDate())
                .isDefault(cardDetails.isDefault())
                .userId(cardDetails.getUserId())
                .build();

        return dto;
    }


}
