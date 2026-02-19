package com.khapara.userservice.controller;

import com.khapara.userservice.client.OrderClient;
import com.khapara.userservice.client.ProductClient;
import com.khapara.userservice.dto.CountDTO;
import com.khapara.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final ProductClient productClient;
    private final OrderClient orderClient;

    public UserController(ProductClient productClient, OrderClient orderClient) {
        this.productClient = productClient;
        this.orderClient = orderClient;
    }

    @GetMapping("/private/{userId}/count")
    public ResponseEntity<CountDTO> getCount(@PathVariable Long userId) {

        CountDTO countDTO = new CountDTO();
        countDTO.setCartCount(orderClient.getCartCount(userId));
        countDTO.setWishlistCount(productClient.getWishlistCount(userId));
        return ResponseEntity.ok(countDTO);

    }


}
