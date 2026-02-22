package com.khapara.orderservice.dtos.cart;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderPricesDTO {
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal marketPlaceFee;
    private BigDecimal total;

    public OrderPricesDTO(BigDecimal subtotal, BigDecimal deliveryFee, BigDecimal marketPlaceFee, BigDecimal total) {
        this.subtotal = subtotal;
        this.deliveryFee = deliveryFee;
        this.marketPlaceFee = marketPlaceFee;
        this.total = total;
    }

}

