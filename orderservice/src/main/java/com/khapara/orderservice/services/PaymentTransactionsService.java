package com.khapara.orderservice.services;

import com.khapara.orderservice.dtos.cart.CartItemAndPriceDTO;
import com.khapara.orderservice.dtos.cart.OrderPricesDTO;
import com.khapara.orderservice.dtos.paymentTransactions.PaymentTransactionsDTO;
import com.khapara.orderservice.dtos.paymentTransactions.ResPaymentTransactionsDTO;
import com.khapara.orderservice.dtos.promocode.CheckPromoCodeDTO;
import com.khapara.orderservice.dtos.promocode.ResPromoCodeDTO;
import com.khapara.orderservice.entities.PaymentTransactions;
import com.khapara.orderservice.entities.ShippingOptions;
import com.khapara.orderservice.entities.erum.PaymentStatus;
import com.khapara.orderservice.exception.ResourceNotFoundException;
import com.khapara.orderservice.mappers.PaymentTransactionsMapper;
import com.khapara.orderservice.repositories.PaymentTransactionsRepository;
import com.khapara.orderservice.repositories.ShippingOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PaymentTransactionsService {

    // Repositories
    @Autowired
    private PaymentTransactionsRepository paymentTransactionsRep;
    @Autowired
    private ShippingOptionsRepository shippingOptionsRep;

    // Services
    @Autowired
    private CartService cartSer;
    @Autowired
    private PromoCodeService promoCodeSer;

    @Autowired
    private PaymentTransactionsMapper paymentTransactionsMapper;

    public ResPaymentTransactionsDTO savePaymentTransactions(PaymentTransactionsDTO dto) {

        ShippingOptions shippingOptions = shippingOptionsRep.findById(dto.getShippingOptionsId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getShippingOptionsId() + " is not valid option"));
        long shippingCharges = shippingOptions.getPrice();

        CartItemAndPriceDTO cartItemAndPriceDTO = cartSer.listCartByUserid(dto.getUserId());
        OrderPricesDTO orderPricesDTO = cartItemAndPriceDTO.getOrderPrices();
        long cartTotal = orderPricesDTO.getTotal().setScale(0, RoundingMode.HALF_UP).longValue();

        BigDecimal bigDecimalTotal = BigDecimal.valueOf(cartTotal);
        bigDecimalTotal = bigDecimalTotal.add(BigDecimal.valueOf(shippingCharges));

        CheckPromoCodeDTO checkPromoCodeDTO = new CheckPromoCodeDTO();
        checkPromoCodeDTO.setCode(dto.getPromoCode());
        checkPromoCodeDTO.setPrice(bigDecimalTotal.setScale(0, RoundingMode.HALF_UP).longValue());
        ResPromoCodeDTO resPromoCodeDTO = promoCodeSer.applyPromoCode(checkPromoCodeDTO);

        PaymentTransactions paymentTransactions = getPaymentTransactions(dto, orderPricesDTO, resPromoCodeDTO);

        paymentTransactions = paymentTransactionsRep.save(paymentTransactions);
        return paymentTransactionsMapper.toResDto(paymentTransactions);
    }

    private static PaymentTransactions getPaymentTransactions(PaymentTransactionsDTO dto, OrderPricesDTO orderPricesDTO, ResPromoCodeDTO resPromoCodeDTO) {
        PaymentTransactions paymentTransactions = new PaymentTransactions();
        paymentTransactions.setSubtotal(
                orderPricesDTO.getSubtotal().setScale(0, RoundingMode.HALF_UP).longValue()
        );
        paymentTransactions.setDeliveryFee(
                orderPricesDTO.getDeliveryFee().setScale(0, RoundingMode.HALF_UP).longValue()
        );
        paymentTransactions.setMarketPlaceFee(
                orderPricesDTO.getMarketPlaceFee().setScale(0, RoundingMode.HALF_UP).longValue()
        );
        paymentTransactions.setTotal(
                resPromoCodeDTO.getTotalAfterDiscount().setScale(0, RoundingMode.HALF_UP).longValue()
        );
        paymentTransactions.setPaymentMethodId(dto.getPaymentMethodId());
        // temp success
        paymentTransactions.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentTransactions.setUserId(dto.getUserId());
        return paymentTransactions;
    }

}
