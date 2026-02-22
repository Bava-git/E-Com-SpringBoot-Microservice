package com.khapara.orderservice.dtos.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartItemAndPriceDTO {

    private List<CartScreenDTO> cartItems;
    private OrderPricesDTO orderPrices;

}
