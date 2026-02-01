package com.khapara.productservice.controllers;

import com.khapara.productservice.dtos.HomeScreenProductDTO;
import com.khapara.productservice.dtos.ProductDTO;
import com.khapara.productservice.entities.Product;
import com.khapara.productservice.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productSer;

    public ProductController(ProductService productSer) {
        this.productSer = productSer;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts() {
        List<ProductDTO> products = productSer.listProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<ProductDTO> oneProduct(@PathVariable Long id) {
        ProductDTO dto = productSer.oneProduct(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/by-groupId/{groupId}")
    public ResponseEntity<List<ProductDTO>> filterProductByGroupId(@PathVariable String groupId) {
        List<ProductDTO> products = productSer.filterProductByGroupId(groupId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-slug/{slug}")
    public ResponseEntity<List<ProductDTO>> filterProductBySlug(@PathVariable String slug) {
        List<ProductDTO> products = productSer.filterProductBySlug(slug);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/forHome")
    public ResponseEntity<List<HomeScreenProductDTO>> getHomeScreenProducts() {
        return ResponseEntity.ok(productSer.getProductsForHomeScreen());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO addProductDTO) {
        ProductDTO productDTO = productSer.saveProduct(addProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ProductDTO>> saveMultipleProducts(@Valid @RequestBody List<ProductDTO> productDTOS) {
        List<ProductDTO> responseDtos = productSer.saveMultipleProducts(productDTOS);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productSer.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
