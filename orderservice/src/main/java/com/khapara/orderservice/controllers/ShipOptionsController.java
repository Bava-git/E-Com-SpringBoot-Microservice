package com.khapara.orderservice.controllers;

import com.khapara.orderservice.dtos.ShipOptionsDTO;
import com.khapara.orderservice.repositories.ShipOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order/checkout/shippingOptions")
public class ShipOptionsController {

    @Autowired
    private ShipOptionsRepository shipOptionsRep;

    @GetMapping
    public List<ShipOptionsDTO> listShippingOptions() {
        return shipOptionsRep.findAll().stream()
                .map(option -> {
                    ShipOptionsDTO dto = new ShipOptionsDTO();
                    dto.setId(option.getId());
                    dto.setName(option.getName());
                    dto.setPrice(option.getPrice());
                    dto.setEta(LocalDate.now()
                            .plusDays(option.getEta())
                            .toString()
                            .split("T")[0]);
                    dto.setPopular(option.isPopular());
                    return dto;
                })
                .toList();
    }


}
