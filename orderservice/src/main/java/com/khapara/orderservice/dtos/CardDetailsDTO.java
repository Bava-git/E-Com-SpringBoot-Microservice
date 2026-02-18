package com.khapara.orderservice.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CardDetailsDTO {

    private Long id;
    private String last4Digits;
    private String cardHolderName;
    private LocalDate cardExpirationDate;
    private boolean isDefault;
    private Long userId;


}
