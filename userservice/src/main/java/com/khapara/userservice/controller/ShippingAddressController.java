package com.khapara.userservice.controller;

import com.khapara.userservice.dto.ReqShippingAddressDTO;
import com.khapara.userservice.dto.ResShippingAddressDTO;
import com.khapara.userservice.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/private/address")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shipAddSer;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ResShippingAddressDTO>> listAddress(@PathVariable Long userId) {
        List<ResShippingAddressDTO> dtos = shipAddSer.listAddress(userId);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ResShippingAddressDTO> saveAddress(@RequestBody ReqShippingAddressDTO addressDTO) {
        ResShippingAddressDTO dtos = shipAddSer.saveAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }

    @PutMapping
    public ResponseEntity<ResShippingAddressDTO> updateAddress(@RequestBody ReqShippingAddressDTO addressDTO){
        ResShippingAddressDTO dto = shipAddSer.updateAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> shipAddSer(@PathVariable Long id, @PathVariable Long userId) {
        shipAddSer.deleteAddress(id, userId);
        return ResponseEntity.noContent().build();
    }

}
