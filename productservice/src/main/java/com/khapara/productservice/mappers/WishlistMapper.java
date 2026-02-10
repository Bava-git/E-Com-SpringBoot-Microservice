package com.khapara.productservice.mappers;

import com.khapara.productservice.dtos.WishlistDTO;
import com.khapara.productservice.dtos.WishlistScreenDTO;
import com.khapara.productservice.entities.Product;
import com.khapara.productservice.entities.WishList;

public class WishlistMapper {

    public static WishList toEntity(WishlistDTO dto) {
        if (dto == null) return null;

        WishList wishList = new WishList();
        wishList.setUserId(dto.getUserId());
        wishList.setProductId(dto.getProductId());
        wishList.setProductSizeId(dto.getProductSizeId());

        return wishList;
    }

    public static WishlistDTO toDto(WishList wishList) {
        if (wishList == null) return null;

        WishlistDTO dto = new WishlistDTO();
        dto.setId(wishList.getId());
        dto.setUserId(wishList.getUserId());
        dto.setProductId(wishList.getProductId());
        dto.setProductSizeId(wishList.getProductSizeId());

        return dto;
    }

    public static WishlistScreenDTO toWishlistScreenDTOs(WishList wishList, Product product) {
        if (wishList == null || product == null) return null;

        WishlistScreenDTO dto = new WishlistScreenDTO();
        dto.setId(wishList.getId());
        dto.setUserId(wishList.getUserId());
        dto.setProductId(wishList.getProductId());
        dto.setProductSizeId(wishList.getProductSizeId());
        dto.setName(product.getProductName());
        dto.setPrice(product.getProductPrice());
        dto.setImage(product.getImages().getFirst().getProductImageHref());
        dto.setColor(product.getProductColorName());

        if (product.getSizes().isEmpty()) {
            dto.setSize(null);
        } else {
            dto.setSize(product.getSizes().get(Math.toIntExact(wishList.getProductSizeId())).getProductSizeLabel());
        }

        return dto;
    }

}
