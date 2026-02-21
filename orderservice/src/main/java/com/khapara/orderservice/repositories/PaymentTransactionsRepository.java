package com.khapara.orderservice.repositories;

import com.khapara.orderservice.entities.PaymentTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransactionsRepository extends JpaRepository<PaymentTransactions, Long> {


}
