package com.khapara.productservice.services;

import com.khapara.productservice.dtos.AddProductDTO;
import com.khapara.productservice.entities.Product;
import com.khapara.productservice.mappers.ProductMapper;
import com.khapara.productservice.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    //    private final ProductSizeRepository productSizeRep;
//    private final ProductImageRepository productImageRep;
//    private final ProductSpecificationRepository productSpecRep;
//    private final ProductKeyFeaturesRepository productKeyFeatRep;
    private final ProductRepository productRep;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRep, ModelMapper modelMapper) {
        this.productRep = productRep;
        this.modelMapper = modelMapper;
    }

    public List<Product> listProducts() {
        return productRep.findAll();
    }

    @Transactional
    public Product saveProduct(AddProductDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        return productRep.save(product);
    }

}
