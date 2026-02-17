package com.khapara.orderservice.dtos;

import lombok.Data;

@Data
public class OrderPricesDTO {

    private double subtotal;
    private double deliveryFee;
    private double marketPlaceFee;
    private double total;

    public OrderPricesDTO(double subtotal, double deliveryFee, double marketPlaceFee, double total) {
        this.subtotal = subtotal;
        this.deliveryFee = deliveryFee;
        this.marketPlaceFee = marketPlaceFee;
        this.total = total;
    }
}
