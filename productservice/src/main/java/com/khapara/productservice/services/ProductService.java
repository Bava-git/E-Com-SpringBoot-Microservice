package com.khapara.productservice.services;

import com.khapara.productservice.dtos.HomeScreenProductDTO;
import com.khapara.productservice.dtos.ProductDTO;
import com.khapara.productservice.entities.Product;
import com.khapara.productservice.entities.ProductReview;
import com.khapara.productservice.exception.ResourceNotFoundException;
import com.khapara.productservice.mappers.ProductMapper;
import com.khapara.productservice.repositories.ProductRepository;
import com.khapara.productservice.repositories.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRep;
    @Autowired
    private ProductReviewRepository productReviewRep;

    public ProductDTO oneProduct(Long id) {
        Product product = productRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        List<ProductReview> reviews = productReviewRep.findByProductId(id);
        return ProductMapper.toDTO(product, reviews);
    }

    public List<ProductDTO> listProducts() {
        return productRep.findAll().stream().map(product -> {
            List<ProductReview> reviews = productReviewRep.findByProductId(product.getId());
            return ProductMapper.toDTO(product, reviews);
        }).toList();
    }

    public List<HomeScreenProductDTO> getProductsForHomeScreen() {
        return productRep.findAll().stream()
                .flatMap(product -> ProductMapper.toHomeScreenDTOs(product).stream())
                .toList();
    }

    public List<ProductDTO> filterProductByGroupId(String groupId) {
        List<Product> products = productRep.findByGroupId(groupId);
        if (products.isEmpty()) throw new ResourceNotFoundException("Products not found with groupId: " + groupId);
        return products.stream()
                .map(product -> {
                    List<ProductReview> reviews = productReviewRep.findByProductId(product.getId());
                    return ProductMapper.toDTO(product, reviews);
                }).toList();
    }

    public List<ProductDTO> filterProductBySlug(String slug) {
        Optional<Product> product = productRep.findBySlug(slug);
        if (product.isEmpty()) throw new ResourceNotFoundException("Product not found with slug: " + slug);
        List<ProductDTO> products = filterProductByGroupId(product.get().getGroupId());
        return products;
    }

    @Transactional
    public ProductDTO saveProduct(ProductDTO dto) {
        Product savedProduct = productRep.save(ProductMapper.toEntity(dto));
        List<ProductReview> reviews = productReviewRep.findByProductId(savedProduct.getId());
        return ProductMapper.toDTO(savedProduct, reviews);
    }

    @Transactional
    public List<ProductDTO> saveMultipleProducts(List<ProductDTO> dtos) {
        List<Product> savedProducts = productRep.saveAll(dtos.stream().map(ProductMapper::toEntity).toList());
        return savedProducts.stream().map(product -> {
            List<ProductReview> reviews = productReviewRep.findByProductId(product.getId());
            return ProductMapper.toDTO(product, reviews);
        }).toList();
    }

    public void deleteProduct(Long id) {
        Product product = productRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        productRep.delete(product);
    }

}
