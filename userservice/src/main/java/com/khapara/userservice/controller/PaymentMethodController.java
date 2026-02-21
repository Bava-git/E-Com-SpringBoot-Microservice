package com.khapara.userservice.controller;

import com.khapara.userservice.dto.payment.PaymentMethodDTO;
import com.khapara.userservice.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/private/paymentMethod")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodSer;

    @PostMapping
    public ResponseEntity<?> savePaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        Long paymentId = paymentMethodSer.savePaymentMethod(paymentMethodDTO);

        try {
            long id = paymentId;
            if (id > 0) {
                return ResponseEntity.ok(Map.of("status", "success", "paymentId", paymentId));
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid paymentId"));
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "paymentId must be numeric"));
        }

    }

}
