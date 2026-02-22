package com.khapara.orderservice.dtos.promocode;

import lombok.Data;

@Data
public class CheckPromoCodeDTO {

    private String code;
    private Long userId;
    private long price;

}
