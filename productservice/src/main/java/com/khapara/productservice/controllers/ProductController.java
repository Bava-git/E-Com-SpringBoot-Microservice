package com.khapara.productservice.controllers;

import com.khapara.productservice.dtos.AddProductDTO;
import com.khapara.productservice.entities.Product;
import com.khapara.productservice.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productSer;

    public ProductController(ProductService productSer) {
        this.productSer = productSer;
    }

    @GetMapping
    public List<Product> listProducts() {
        return productSer.listProducts();
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody AddProductDTO addProductDTO) {
        Product saved = productSer.saveProduct(addProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


}
