package com.khapara.orderservice.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CartItemAndPriceDTO {

    private List<CartScreenDTO> cartItems;
    private OrderPricesDTO orderPrices;

}
