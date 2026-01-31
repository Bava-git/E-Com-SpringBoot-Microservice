package com.khapara.productservice.mappers;

import com.khapara.productservice.dtos.*;
import com.khapara.productservice.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toEntity(AddProductDTO dto) {
        if (dto == null) return null;

        Product product = new Product();
        // General product properties
        product.setProductName(dto.getProductName());
        product.setGroupId(dto.getGroupId());
        product.setProductBrand(dto.getProductBrand());
        product.setProductTagline(dto.getProductTagline());
        product.setProductPrice(dto.getProductPrice());
        product.setProductStock(dto.getProductStock());
        product.setProductColorName(dto.getProductColorName());
        product.setProductColorHex(dto.getProductColorHex());

        // Nested Images DTO mapping
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            List<ProductImage> images = dto.getImages().stream()
                    .map(imgDto -> {
                        ProductImage productImage = new ProductImage();
                        productImage.setProductImageHref(imgDto.getProductImageHref());
                        productImage.setProductImageAlt(imgDto.getProductImageAlt());
                        productImage.setProduct(product);
                        return productImage;
                    })
                    .toList();
            product.setImages(images);
        }

        // Nested sizes DTO mapping
        if (dto.getSizes() != null && !dto.getSizes().isEmpty()) {
            List<ProductSize> sizes = dto.getSizes().stream()
                    .map(sizeDto -> {
                        ProductSize productSize = new ProductSize();
                        productSize.setProductSizeLabel(sizeDto.getProductSizeLabel());
                        productSize.setProductSizeCode(sizeDto.getProductSizeCode());
                        productSize.setProduct(product);
                        return productSize;
                    })
                    .toList();
            product.setSizes(sizes);
        }

        // Nested specs DTO mapping
        if (dto.getSpecifications() != null && !dto.getSpecifications().isEmpty()) {
            List<ProductSpecification> specs = dto.getSpecifications().stream()
                    .map(specDto -> {
                        ProductSpecification productSpecification = new ProductSpecification();
                        productSpecification.setProductSpecLabel(specDto.getProductSpecLabel());
                        productSpecification.setProductSpecValue(specDto.getProductSpecValue());
                        productSpecification.setProduct(product);
                        return productSpecification;
                    })
                    .toList();
            product.setSpecifications(specs);
        }

        // Nested feature DTO mapping
        if (dto.getFeatures() != null) {
            List<ProductFeature> features = dto.getFeatures().stream()
                    .map(f -> {
                        ProductFeature pf = new ProductFeature();
                        pf.setProductFeatures(f); // map string directly
                        pf.setProduct(product);
                        return pf;
                    })
                    .toList();
            product.setFeatures(features);
        }

        return product;
    }


    public static AddProductDTO toDTO(Product product) {
        if (product == null) return null;

        AddProductDTO dto = new AddProductDTO();

        // General product properties
        dto.setProductName(product.getProductName());
        dto.setGroupId(product.getGroupId());
        dto.setProductBrand(product.getProductBrand());
        dto.setProductTagline(product.getProductTagline());
        dto.setProductPrice(product.getProductPrice());
        dto.setProductStock(product.getProductStock());
        dto.setProductColorName(product.getProductColorName());
        dto.setProductColorHex(product.getProductColorHex());

        // Nested Images mapping
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            List<ProductImageDTO> imageDTOs = product.getImages().stream()
                    .map(img -> {
                        ProductImageDTO imgDto = new ProductImageDTO();
                        imgDto.setProductImageHref(img.getProductImageHref());
                        imgDto.setProductImageAlt(img.getProductImageAlt());
                        return imgDto;
                    })
                    .toList();
            dto.setImages(imageDTOs);
        }

        // Nested Sizes mapping
        if (product.getSizes() != null && !product.getSizes().isEmpty()) {
            List<ProductSizeDTO> sizeDTOs = product.getSizes().stream()
                    .map(size -> {
                        ProductSizeDTO sizeDto = new ProductSizeDTO();
                        sizeDto.setProductSizeLabel(size.getProductSizeLabel());
                        sizeDto.setProductSizeCode(size.getProductSizeCode());
                        return sizeDto;
                    })
                    .toList();
            dto.setSizes(sizeDTOs);
        }

        // Nested Specifications mapping
        if (product.getSpecifications() != null && !product.getSpecifications().isEmpty()) {
            List<ProductSpecificationDTO> specDTOs = product.getSpecifications().stream()
                    .map(spec -> {
                        ProductSpecificationDTO specDto = new ProductSpecificationDTO();
                        specDto.setProductSpecLabel(spec.getProductSpecLabel());
                        specDto.setProductSpecValue(spec.getProductSpecValue());
                        return specDto;
                    })
                    .toList();
            dto.setSpecifications(specDTOs);
        }

        // Nested feature DTO mapping
        if (product.getFeatures() != null) {
            dto.setFeatures(product.getFeatures().stream().map(ProductFeature::getProductFeatures)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

}
