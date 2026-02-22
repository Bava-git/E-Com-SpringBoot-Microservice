package com.khapara.orderservice.controllers;

import com.khapara.orderservice.dtos.ShippingAddressDTO;
import com.khapara.orderservice.services.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/order/shippingAddress")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressSer;

    @PostMapping
    public ResponseEntity<ShippingAddressDTO> saveShippingAddress(@RequestBody ShippingAddressDTO dto) {
        ShippingAddressDTO shippingAddressDTO = shippingAddressSer.saveShippingAddress(dto);
        return ResponseEntity.ok(shippingAddressDTO);
    }

}
