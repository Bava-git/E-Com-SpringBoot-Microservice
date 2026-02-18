package com.khapara.userservice.controller;

import com.khapara.userservice.dto.ShipAddDTO;
import com.khapara.userservice.service.ShipAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/private/address")
public class ShipAddController {

    @Autowired
    private ShipAddService shipAddSer;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ShipAddDTO>> listAddress(@PathVariable Long userId) {
        List<ShipAddDTO> dtos = shipAddSer.listAddress(userId);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ShipAddDTO> saveAddress(@RequestBody ShipAddDTO addressDTO) {
        ShipAddDTO dtos = shipAddSer.saveAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }

    @PutMapping
    public ResponseEntity<ShipAddDTO> updateAddress(@RequestBody ShipAddDTO addressDTO){
        ShipAddDTO dto = shipAddSer.updateAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> shipAddSer(@PathVariable Long id, @PathVariable Long userId) {
        shipAddSer.deleteAddress(id, userId);
        return ResponseEntity.noContent().build();
    }

}
