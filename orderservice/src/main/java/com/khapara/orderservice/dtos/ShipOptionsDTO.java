package com.khapara.orderservice.dtos;

import lombok.Data;

@Data
public class ShipOptionsDTO {

    private Long id;
    private String name;
    private double price;
    private String eta;
    private boolean isPopular;

}
