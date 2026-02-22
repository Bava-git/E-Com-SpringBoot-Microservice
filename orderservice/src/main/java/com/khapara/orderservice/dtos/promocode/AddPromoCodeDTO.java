package com.khapara.orderservice.dtos.promocode;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AddPromoCodeDTO {

    private String code;
    private String description;
    private double percentage;
    private long usedCount;
    private long limit;
    private LocalDateTime expiryDate;
    private boolean isActive;

}
