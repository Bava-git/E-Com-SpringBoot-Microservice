package com.khapara.productservice.controllers;

import com.khapara.productservice.client.UserClient;
import com.khapara.productservice.dtos.WishlistDTO;
import com.khapara.productservice.dtos.WishlistScreenDTO;
import com.khapara.productservice.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistSer;
    @Autowired
    private UserClient userClient;

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistScreenDTO>> listWishlistByUserid(@PathVariable Long userId) {
        List<WishlistScreenDTO> dtos = wishlistSer.listWishlistByUserid(userId);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{userId}/count")
    public ResponseEntity<Long> getCountWishlist(@PathVariable Long userId) {
        return ResponseEntity.ok(wishlistSer.getCountWishlist(userId));
    }

    @PostMapping
    public ResponseEntity<WishlistDTO> saveWishlist(@RequestBody WishlistDTO wishlistDTO) {
        WishlistDTO dtos = wishlistSer.saveWishlist(wishlistDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> removeProductFromWishlist(@PathVariable Long id, @PathVariable Long userId) {
        wishlistSer.removeProductFromWishlist(id, userId);
        return ResponseEntity.noContent().build();
    }


}
