package com.khapara.userservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SavedCardDTO {

    private Long id;
    private String last4Digits;
    private String cardHolderName;
    private LocalDateTime cardExpirationDate;
    private boolean isDefault;
    private Long userId;


}
