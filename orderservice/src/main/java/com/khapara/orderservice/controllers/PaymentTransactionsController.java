package com.khapara.orderservice.controllers;

import com.khapara.orderservice.dtos.PaymentTransactionsDTO;
import com.khapara.orderservice.services.PaymentTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/checkout/paymentTransactions")
public class PaymentTransactionsController {

    @Autowired
    private PaymentTransactionsService paymentTransactionsSer;

    @PostMapping
    public ResponseEntity<PaymentTransactionsDTO> savePaymentTransactions(@RequestBody PaymentTransactionsDTO dto) {
        PaymentTransactionsDTO paymentTransactionsDTO = paymentTransactionsSer.savePaymentTransactions(dto);
        return ResponseEntity.ok(paymentTransactionsDTO);
    }

}
