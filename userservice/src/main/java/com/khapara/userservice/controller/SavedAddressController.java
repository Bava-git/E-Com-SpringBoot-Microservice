package com.khapara.userservice.controller;

import com.khapara.userservice.dto.SavedAddressDTO;
import com.khapara.userservice.service.SavedAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/private/address")
public class SavedAddressController {

    @Autowired
    private SavedAddressService shipAddSer;

    @GetMapping("/{userId}")
    public ResponseEntity<List<SavedAddressDTO>> listAddress(@PathVariable Long userId) {
        List<SavedAddressDTO> dtos = shipAddSer.listAddress(userId);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<SavedAddressDTO> saveAddress(@RequestBody SavedAddressDTO addressDTO) {
        SavedAddressDTO dtos = shipAddSer.saveAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }

    @PutMapping
    public ResponseEntity<SavedAddressDTO> updateAddress(@RequestBody SavedAddressDTO addressDTO){
        SavedAddressDTO dto = shipAddSer.updateAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> shipAddSer(@PathVariable Long id, @PathVariable Long userId) {
        shipAddSer.deleteAddress(id, userId);
        return ResponseEntity.noContent().build();
    }

}
