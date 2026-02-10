package com.khapara.orderservice.mappers;

import com.khapara.orderservice.dtos.CartDTO;
import com.khapara.orderservice.dtos.CartScreenDTO;
import com.khapara.orderservice.entities.Cart;
import com.khapara.productservice.dtos.ProductDTO;

public class CartMapper {

    public static Cart toEntity(CartDTO dto) {
        if (dto == null) return null;

        Cart cart = new Cart();
        cart.setUserId(dto.getUserId());
        cart.setProductId(dto.getProductId());
        cart.setSizeId(dto.getSizeId());
        cart.setQuantity(dto.getQuantity());

        return cart;
    }

    public static CartDTO toDto(Cart cart) {
        if (cart == null) return null;

        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUserId());
        dto.setProductId(cart.getProductId());
        dto.setSizeId(cart.getSizeId());
        dto.setQuantity(cart.getQuantity());

        return dto;
    }

    public static CartScreenDTO toCartScreenDTOs(Cart cart, ProductDTO productDTO) {
        if (cart == null || productDTO == null) return null;

        CartScreenDTO dto = new CartScreenDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUserId());
        dto.setProductId(cart.getProductId());
        dto.setSizeId(cart.getSizeId());
        dto.setName(productDTO.getProductName());
        dto.setPrice(productDTO.getProductPrice());
        dto.setImage(productDTO.getImages().getFirst().getProductImageHref());
        dto.setColor(productDTO.getProductColorName());
        dto.setQuantity(cart.getQuantity());

        if (productDTO.getSizes().isEmpty()) {
            dto.setSize(null);
        } else {
            dto.setSize(productDTO.getSizes().get(Math.toIntExact(cart.getSizeId())).getProductSizeLabel());
        }

        return dto;
    }


}
