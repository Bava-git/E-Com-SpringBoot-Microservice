package com.khapara.userservice.service;

import com.khapara.userservice.dto.payment.PaymentMethodDTO;
import com.khapara.userservice.entity.PaymentMethod;
import com.khapara.userservice.mapper.PaymentMethodMapper;
import com.khapara.userservice.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRep;

    public Long savePaymentMethod(PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = PaymentMethodMapper.toEntity(paymentMethodDTO);
        paymentMethod = paymentMethodRep.save(paymentMethod);
        return paymentMethod.getId();
    }


}
