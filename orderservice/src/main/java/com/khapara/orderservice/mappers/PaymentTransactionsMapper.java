package com.khapara.orderservice.mappers;

import com.khapara.orderservice.dtos.paymentTransactions.PaymentTransactionsDTO;
import com.khapara.orderservice.dtos.paymentTransactions.ResPaymentTransactionsDTO;
import com.khapara.orderservice.entities.PaymentTransactions;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentTransactionsMapper {

    PaymentTransactions toEntity(PaymentTransactionsDTO dto);

    PaymentTransactionsDTO toDto(PaymentTransactions paymentTransactions);

    ResPaymentTransactionsDTO toResDto(PaymentTransactions paymentTransactions);
}
