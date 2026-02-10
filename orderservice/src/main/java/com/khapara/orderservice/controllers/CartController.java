package com.khapara.orderservice.controllers;

import com.khapara.orderservice.dtos.CartDTO;
import com.khapara.orderservice.dtos.CartScreenDTO;
import com.khapara.orderservice.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/cart")
public class CartController {

    @Autowired
    private CartService cartSer;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartScreenDTO>> listCartByUserid(@PathVariable Long userId) {
        List<CartScreenDTO> dtos = cartSer.listCartByUserid(userId);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<CartDTO> saveCart(@RequestBody CartDTO cartDTO) {
        CartDTO dtos = cartSer.saveCart(cartDTO);
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long id, @PathVariable Long userId) {
        cartSer.removeProductFromCart(id, userId);
        return ResponseEntity.noContent().build();
    }


}
