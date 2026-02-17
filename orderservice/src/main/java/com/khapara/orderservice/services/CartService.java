package com.khapara.orderservice.services;

import com.khapara.orderservice.clients.ProductClient;
import com.khapara.orderservice.dtos.*;
import com.khapara.orderservice.entities.Cart;
import com.khapara.orderservice.exception.ResourceNotFoundException;
import com.khapara.orderservice.mappers.CartMapper;
import com.khapara.orderservice.repositories.CartRepository;
import com.khapara.productservice.dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public OrderPricesDTO calculateOrderPrices(List<CartScreenDTO> cartScreenDTOS) {
        double subtotal = cartScreenDTOS.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        double deliveryFee = (subtotal > 100 || subtotal == 0) ? 0 : 40;
        double marketPlaceFee = (subtotal == 0) ? 0 : 5;
        double total = Math.round(subtotal + deliveryFee + marketPlaceFee);

        return new OrderPricesDTO(subtotal, deliveryFee, marketPlaceFee, total);
    }


}
