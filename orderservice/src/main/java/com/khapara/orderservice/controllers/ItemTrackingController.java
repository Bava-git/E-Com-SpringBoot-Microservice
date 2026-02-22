package com.khapara.orderservice.controllers;

import com.khapara.orderservice.dtos.ItemTrackingDTO;
import com.khapara.orderservice.services.ItemTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order/itemTracking")
public class ItemTrackingController {

    @Autowired
    private ItemTrackingService itemTrackingSer;

    @PostMapping
    public ResponseEntity<List<ItemTrackingDTO>> saveItemTracking(@RequestBody List<ItemTrackingDTO> dto) {
        List<ItemTrackingDTO> itemTrackingDTOList = itemTrackingSer.saveItemTracking(dto);
        return ResponseEntity.ok(itemTrackingDTOList);
    }

}
