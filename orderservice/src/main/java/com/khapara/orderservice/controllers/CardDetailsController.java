package com.khapara.orderservice.controllers;

import com.khapara.orderservice.dtos.CardDetailsDTO;
import com.khapara.orderservice.services.CardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/checkout/card")
public class CardDetailsController {

    @Autowired
    private CardDetailsService cardDetailsSer;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CardDetailsDTO>> listCardDetailsByUserid(@PathVariable Long userId) {
        List<CardDetailsDTO> dtos = cardDetailsSer.listCardDetails(userId);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<CardDetailsDTO> saveCart(@RequestBody CardDetailsDTO cardDetailsDTO) {
        CardDetailsDTO dto = cardDetailsSer.saveCardDetails(cardDetailsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> removeCard(@PathVariable Long id, @PathVariable Long userId) {
        cardDetailsSer.removeCard(id, userId);
        return ResponseEntity.noContent().build();
    }


}
