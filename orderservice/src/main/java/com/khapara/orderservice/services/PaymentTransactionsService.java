package com.khapara.orderservice.services;

import com.khapara.orderservice.dtos.PaymentTransactionsDTO;
import com.khapara.orderservice.entities.PaymentTransactions;
import com.khapara.orderservice.mappers.PaymentTransactionsMapper;
import com.khapara.orderservice.repositories.PaymentTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentTransactionsService {

    @Autowired
    private PaymentTransactionsRepository paymentTransactionsRep;

    @Autowired
    private PaymentTransactionsMapper paymentTransactionsMapper;

    public PaymentTransactionsDTO savePaymentTransactions(PaymentTransactionsDTO dto) {
        PaymentTransactions paymentTransactions = paymentTransactionsMapper.toEntity(dto);
        paymentTransactions = paymentTransactionsRep.save(paymentTransactions);
        return paymentTransactionsMapper.toDto(paymentTransactions);
    }

}
