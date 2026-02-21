package com.khapara.userservice.controller;

import com.khapara.userservice.dto.CardDetailsDTO;
import com.khapara.userservice.dto.UpdateDefaultCardDTO;
import com.khapara.userservice.service.CardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/private/cardDetails")
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

    @PutMapping("/updateDefault")
    public ResponseEntity<?> updateDefaultCard(@RequestBody UpdateDefaultCardDTO updateDefaultCardDTO) {
        cardDetailsSer.updateDefaultCard(updateDefaultCardDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Default card updated");
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> removeCard(@PathVariable Long id, @PathVariable Long userId) {
        cardDetailsSer.removeCard(id, userId);
        return ResponseEntity.noContent().build();
    }


}
