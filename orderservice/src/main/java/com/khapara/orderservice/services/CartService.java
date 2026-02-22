package com.khapara.orderservice.services;

import com.khapara.orderservice.clients.ProductClient;
import com.khapara.orderservice.dtos.cart.*;
import com.khapara.orderservice.entities.Cart;
import com.khapara.orderservice.exception.ResourceNotFoundException;
import com.khapara.orderservice.mappers.CartMapper;
import com.khapara.orderservice.repositories.CartRepository;
import com.khapara.productservice.dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRep;

    private final ProductClient productClient;

    public CartService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public CartItemAndPriceDTO listCartByUserid(Long userId) {
        List<CartScreenDTO> cartScreenDTOS = cartRep.findByUserId(userId)
                .stream()
                .map(cartItem ->
                        {
                            ProductDTO productDTO = productClient.getProduct(cartItem.getProductId());
                            return CartMapper.toCartScreenDTOs(cartItem, productDTO);
                        }
                )
                .toList();
        OrderPricesDTO orderPricesDTO = calculateOrderPrices(cartScreenDTOS);

        CartItemAndPriceDTO cartItemAndPriceDTO = new CartItemAndPriceDTO();
        cartItemAndPriceDTO.setCartItems(cartScreenDTOS);
        cartItemAndPriceDTO.setOrderPrices(orderPricesDTO);

        return cartItemAndPriceDTO;

    }

    public long getCountCart(Long userId) {
        return cartRep.countByUserId(userId);
    }

    public CartDTO saveCart(CartDTO cartDTO) {

        Optional<Cart> isAvailable = cartRep.findByProductIdAndUserId(cartDTO.getProductId(),
                cartDTO.getUserId());

        if (isAvailable.isPresent()) {
            if (isAvailable.get().getQuantity() != cartDTO.getQuantity()) {
                return updateQuantity(CartMapper.toUpdateQuantity(cartDTO));
            }
            return null;
        } else {
            ProductDTO productDTO = productClient.getProduct(cartDTO.getProductId());
            Cart cart = CartMapper.toEntity(cartDTO);
            if (productDTO.getSizes().isEmpty()) {
                cart.setSizeId(null);
            }
            cart = cartRep.save(cart);
            return CartMapper.toDto(cart);
        }

    }

    public CartDTO updateQuantity(UpdateQuantityDTO updateQuantityDTO) {
        Cart cart = cartRep.findByIdAndUserId(updateQuantityDTO.getId(), updateQuantityDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cart.setQuantity(updateQuantityDTO.getQuantity());
        cartRep.save(cart);
        return CartMapper.toDto(cart);
    }

    public void removeProductFromCart(Long id, Long userId) {
        Cart cart = cartRep.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with ID: " + id + " User id: " + userId));
        cartRep.delete(cart);
    }

    public static OrderPricesDTO calculateOrderPrices(List<CartScreenDTO> cartScreenDTOS) {
        // subtotal = sum(price * quantity)
        BigDecimal subtotal = cartScreenDTOS.stream()
                .map(item -> BigDecimal.valueOf(item.getPrice())
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // deliveryFee = 0 if subtotal > 100 or subtotal == 0, else 40
        BigDecimal deliveryFee = (subtotal.compareTo(BigDecimal.valueOf(100)) > 0 || subtotal.compareTo(BigDecimal.ZERO) == 0)
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(40);

        // marketplaceFee = 0 if subtotal == 0, else 5
        BigDecimal marketPlaceFee = (subtotal.compareTo(BigDecimal.ZERO) == 0)
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(5);

        // total = subtotal + deliveryFee + marketplaceFee
        BigDecimal total = subtotal.add(deliveryFee).add(marketPlaceFee)
                .setScale(2, RoundingMode.HALF_UP);

        return new OrderPricesDTO(
                subtotal.setScale(2, RoundingMode.HALF_UP),
                deliveryFee.setScale(2, RoundingMode.HALF_UP),
                marketPlaceFee.setScale(2, RoundingMode.HALF_UP),
                total
        );
    }


}
