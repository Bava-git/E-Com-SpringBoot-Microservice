package com.khapara.userservice.mapper;

import com.khapara.userservice.dto.payment.PaymentMethodDTO;
import com.khapara.userservice.entity.PaymentMethod;
import com.khapara.userservice.entity.erum.PaymentType;
import jakarta.ws.rs.BadRequestException;

public class PaymentMethodMapper {

    public static PaymentMethod toEntity(PaymentMethodDTO dto) {
        if (dto == null) return null;

        PaymentMethod paymentMethod = new PaymentMethod();

        try {
            PaymentType type = PaymentType.valueOf(dto.getPaymentType().toUpperCase());
            paymentMethod.setPaymentType(type);
            paymentMethod.setUserId(dto.getUserId());

            if (type == PaymentType.CARD) {
                // Accept Card Payment
                paymentMethod.setCardLast4Digits(dto.getCardLast4Digits());
                paymentMethod.setCardHolderName(dto.getCardHolderName());
                paymentMethod.setCardExpirationDate(dto.getCardExpirationDate());
                // Reject UPI Payment
                paymentMethod.setUpiId(null);
            } else if (type == PaymentType.UPI) {
                // Accept UPI Payment
                paymentMethod.setUpiId(dto.getUpiId());
                // Reject Card Payment
                paymentMethod.setCardLast4Digits(null);
                paymentMethod.setCardHolderName(null);
                paymentMethod.setCardExpirationDate(null);
            }

        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid payment type: " + dto.getPaymentType());
        }

        return paymentMethod;
    }

}
