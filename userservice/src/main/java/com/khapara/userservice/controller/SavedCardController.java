package com.khapara.userservice.controller;

import com.khapara.userservice.dto.SavedCardDTO;
import com.khapara.userservice.dto.UpdateDefaultCardDTO;
import com.khapara.userservice.service.SavedCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/private/cardDetails")
public class SavedCardController {

    @Autowired
    private SavedCardService cardDetailsSer;

    @GetMapping("/{userId}")
    public ResponseEntity<List<SavedCardDTO>> listCardDetailsByUserid(@PathVariable Long userId) {
        List<SavedCardDTO> dtos = cardDetailsSer.listCardDetails(userId);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<SavedCardDTO> saveCart(@RequestBody SavedCardDTO cardDetailsDTO) {
        SavedCardDTO dto = cardDetailsSer.saveCardDetails(cardDetailsDTO);
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
