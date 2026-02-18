package com.khapara.orderservice.controllers;

import com.khapara.orderservice.dtos.PromoCodeDTO;
import com.khapara.orderservice.services.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/checkout/promoCode")
public class PromoCodeController {

    @Autowired
    private PromoCodeService promoCodeSer;

    @PostMapping
    public ResponseEntity<Double> applyPromoCode(@RequestBody PromoCodeDTO dto) {
        double discountedPrice = promoCodeSer.applyPromoCode(dto);
        return ResponseEntity.ok(discountedPrice);
    }

}
