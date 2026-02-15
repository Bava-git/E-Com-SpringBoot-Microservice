package com.khapara.orderservice.services;

import com.khapara.orderservice.clients.ProductClient;
import com.khapara.orderservice.dtos.CartDTO;
import com.khapara.orderservice.dtos.CartScreenDTO;
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

    public List<CartScreenDTO> listCartByUserid(Long userId) {
        return cartRep.findByUserId(userId)
                .stream()
                .map(cartItem ->
                        {
                            ProductDTO productDTO = productClient.getProduct(cartItem.getProductId());
                            return CartMapper.toCartScreenDTOs(cartItem, productDTO);
                        }
                )
                .toList();
    }

    public CartDTO saveCart(CartDTO cartDTO) {

        Optional<Cart> isAvailable = cartRep.findByProductIdAndUserId(cartDTO.getProductId(),
                cartDTO.getUserId());

        if (isAvailable.isPresent()) {
            isAvailable.get().setQuantity(cartDTO.getQuantity());
            isAvailable.get().setSizeId(cartDTO.getSizeId());
            return CartMapper.toDto(cartRep.save(isAvailable.get()));
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

    public void removeProductFromCart(Long id, Long userId) {
        Cart cart = cartRep.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with ID: " + id + " User id: " + userId));
        cartRep.delete(cart);
    }


}
