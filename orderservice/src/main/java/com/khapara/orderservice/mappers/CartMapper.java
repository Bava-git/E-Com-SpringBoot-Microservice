package com.khapara.orderservice.mappers;

import com.khapara.orderservice.dtos.cart.CartDTO;
import com.khapara.orderservice.dtos.cart.CartScreenDTO;
import com.khapara.orderservice.dtos.cart.UpdateQuantityDTO;
import com.khapara.orderservice.entities.Cart;
import com.khapara.productservice.dtos.ProductDTO;
import com.khapara.productservice.dtos.ProductSizeDTO;

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

        if (productDTO.getSizes() != null && !productDTO.getSizes().isEmpty()) {
            dto.setSize(
                    productDTO.getSizes().stream()
                            .filter(size -> size.getId().equals(cart.getSizeId()))
                            .map(ProductSizeDTO::getProductSizeLabel)
                            .findFirst()
                            .orElse(null)
            );
        } else {
            dto.setSize(null);
        }

        return dto;
    }

    public static UpdateQuantityDTO toUpdateQuantity(CartDTO cartDTO) {
        if (cartDTO == null) return null;

        UpdateQuantityDTO dto = new UpdateQuantityDTO();
        dto.setId(cartDTO.getId());
        dto.setQuantity(cartDTO.getQuantity());
        dto.setUserId(cartDTO.getUserId());

        return dto;
    }


}
