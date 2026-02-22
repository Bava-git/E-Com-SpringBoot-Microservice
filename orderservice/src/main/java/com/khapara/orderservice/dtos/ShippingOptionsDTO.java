package com.khapara.orderservice.dtos;

import lombok.Data;

@Data
public class ShippingOptionsDTO {

    private Long id;
    private String name;
    private int price;
    private String eta;
    private boolean isPopular;

}
