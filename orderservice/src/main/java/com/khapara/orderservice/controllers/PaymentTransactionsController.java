package com.khapara.orderservice.controllers;

import com.khapara.orderservice.dtos.paymentTransactions.PaymentTransactionsDTO;
import com.khapara.orderservice.dtos.paymentTransactions.ResPaymentTransactionsDTO;
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
    public ResponseEntity<ResPaymentTransactionsDTO> savePaymentTransactions(@RequestBody PaymentTransactionsDTO dto) {
        ResPaymentTransactionsDTO resPaymentTransactionsDTO = paymentTransactionsSer.savePaymentTransactions(dto);
        return ResponseEntity.ok(resPaymentTransactionsDTO);
    }

}
