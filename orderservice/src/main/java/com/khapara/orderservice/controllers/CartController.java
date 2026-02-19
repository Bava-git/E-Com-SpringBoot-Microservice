package com.khapara.orderservice.controllers;

import com.khapara.orderservice.dtos.CartDTO;
import com.khapara.orderservice.dtos.CartItemAndPriceDTO;
import com.khapara.orderservice.dtos.UpdateQuantityDTO;
import com.khapara.orderservice.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/cart")
public class CartController {

    @Autowired
    private CartService cartSer;

    @GetMapping("/{userId}")
    public ResponseEntity<CartItemAndPriceDTO> listCartByUserid(@PathVariable Long userId) {
        CartItemAndPriceDTO dtos = cartSer.listCartByUserid(userId);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{userId}/count")
    public ResponseEntity<Long> getCountCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartSer.getCountCart(userId));
    }

    @PostMapping
    public ResponseEntity<CartDTO> saveCart(@RequestBody CartDTO cartDTO) {
        CartDTO dtos = cartSer.saveCart(cartDTO);
        if (dtos == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cartDTO);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<CartDTO> updateQuantity(@RequestBody UpdateQuantityDTO updateQuantityDTO) {
        CartDTO dto = cartSer.updateQuantity(updateQuantityDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long id, @PathVariable Long userId) {
        cartSer.removeProductFromCart(id, userId);
        return ResponseEntity.noContent().build();
    }


}
