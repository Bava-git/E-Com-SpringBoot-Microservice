package com.khapara.orderservice.dtos;

import lombok.Data;

@Data
public class PromoCodeDTO {

    private String code;
    private Long userId;
    private double price;

}
