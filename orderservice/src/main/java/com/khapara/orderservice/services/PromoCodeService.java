package com.khapara.orderservice.services;

import com.khapara.orderservice.dtos.promocode.ResPromoCodeDTO;
import com.khapara.orderservice.dtos.promocode.CheckPromoCodeDTO;
import com.khapara.orderservice.entities.PromoCode;
import com.khapara.orderservice.exception.ResourceNotFoundException;
import com.khapara.orderservice.repositories.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class PromoCodeService {

    @Autowired
    private static PromoCodeRepository promoCodeRep;

    public ResPromoCodeDTO applyPromoCode(CheckPromoCodeDTO dto) {

        PromoCode promoCode = promoCodeRep.findByCode(dto.getCode())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getCode() + " is not valid promo code"));

        return calculatePromoCodeDiscount(promoCode, dto.getPrice());
    }

    private static boolean validatePromoCode(PromoCode promoCode) {

        if (!promoCode.isActive() || promoCode.getExpiryDate() == null) return false;
        if (promoCode.getUsedCount() >= promoCode.getUsageLimit() || LocalDateTime.now().isAfter(promoCode.getExpiryDate())) {
            promoCode.setActive(false);
            promoCodeRep.save(promoCode);
            return false;
        }
        return true;

    }

    public static ResPromoCodeDTO calculatePromoCodeDiscount(PromoCode promoCode, long price) {
        if (validatePromoCode(promoCode)) {
            BigDecimal bigDecimalPrice = BigDecimal.valueOf(price);
            BigDecimal discountPercentage = BigDecimal.valueOf(promoCode.getDiscountPercentage());

            // discountAmount = price * (discountPercentage / 100) //
            BigDecimal discountAmount = bigDecimalPrice.multiply(discountPercentage).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            // totalAfterDiscount = price - discountAmount
            BigDecimal totalAfterDiscount = bigDecimalPrice.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);

            ResPromoCodeDTO resPromoCodeDTO = new ResPromoCodeDTO();
            resPromoCodeDTO.setTotal(bigDecimalPrice.setScale(2, RoundingMode.HALF_UP));
            resPromoCodeDTO.setDiscountAmount(discountAmount);
            resPromoCodeDTO.setTotalAfterDiscount(totalAfterDiscount);

            return resPromoCodeDTO;
        }

        return null;
    }

}
