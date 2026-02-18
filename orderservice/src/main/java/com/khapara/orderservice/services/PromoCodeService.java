package com.khapara.orderservice.services;

import com.khapara.orderservice.dtos.PromoCodeDTO;
import com.khapara.orderservice.entities.PromoCode;
import com.khapara.orderservice.exception.ResourceNotFoundException;
import com.khapara.orderservice.repositories.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PromoCodeService {

    @Autowired
    private PromoCodeRepository promoCodeRep;

    public double applyPromoCode(PromoCodeDTO dto) {

        PromoCode promoCode = promoCodeRep.findByCode(dto.getCode())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getCode() + " is not valid promo code"));

        if (validatePromoCode(promoCode)) {
            double discountedPrice = dto.getPrice() - (dto.getPrice() * promoCode.getDiscountPercentage() / 100.0);
            discountedPrice = Math.ceil(discountedPrice);
            return discountedPrice;
        }

        return 0;
    }

    private boolean validatePromoCode(PromoCode promoCode) {

        if (!promoCode.isActive() || promoCode.getExpiryDate() == null) return false;
        if (promoCode.getUsedCount() >= promoCode.getUsageLimit() || LocalDateTime.now().isAfter(promoCode.getExpiryDate())) {
            promoCode.setActive(false);
            promoCodeRep.save(promoCode);
            return false;
        }
        return true;

    }

}
