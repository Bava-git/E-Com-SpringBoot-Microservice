package com.khapara.orderservice.clients;

import com.khapara.productservice.dtos.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("/products/by-id/{id}")
    ProductDTO getProduct(@PathVariable("id") Long id);


}
