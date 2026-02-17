package com.khapara.productservice.services;

import com.khapara.productservice.dtos.WishlistDTO;
import com.khapara.productservice.dtos.WishlistScreenDTO;
import com.khapara.productservice.entities.Product;
import com.khapara.productservice.entities.WishList;
import com.khapara.productservice.exception.ResourceNotFoundException;
import com.khapara.productservice.mappers.WishlistMapper;
import com.khapara.productservice.repositories.ProductRepository;
import com.khapara.productservice.repositories.ProductSizeRepository;
import com.khapara.productservice.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRep;
    @Autowired
    private ProductRepository productRep;
    @Autowired
    private ProductSizeRepository productSizeRep;

    public List<WishlistScreenDTO> listWishlistByUserid(Long userId) {
        return wishlistRep.findByUserId(userId)
                .stream()
                .map(wishList ->
                        {
                            Product product = productRep.findById(wishList.getProductId()).get();
                            return WishlistMapper.toWishlistScreenDTOs(wishList, product);
                        }
                )
                .toList();
    }

    public long getCountWishlist(Long userId) {
        return wishlistRep.countByUserId(userId);
    }

    public WishlistDTO saveWishlist(WishlistDTO wishlistDTO) {

        Optional<WishList> isAvailable = wishlistRep.findByProductIdAndUserId(wishlistDTO.getProductId(),
                wishlistDTO.getUserId());

        if (isAvailable.isPresent()) {
            return null;
        }

        Optional<Product> product = productRep.findById(wishlistDTO.getProductId());
        WishList wishList = WishlistMapper.toEntity(wishlistDTO);
        if (product.get().getSizes().isEmpty()) {
            wishList.setProductSizeId(null);
        }
        wishList = wishlistRep.save(wishList);
        return WishlistMapper.toDto(wishList);

    }

    public void removeProductFromWishlist(Long id, Long userId) {
        WishList wishList = wishlistRep.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found with ID: " + id + " User id: " + userId));
        wishlistRep.delete(wishList);
    }


}
