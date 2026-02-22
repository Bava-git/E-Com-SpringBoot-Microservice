package com.khapara.orderservice.dtos.promocode;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResPromoCodeDTO {

    private BigDecimal total;
    private BigDecimal discountAmount;
    private BigDecimal totalAfterDiscount;

}
