package com.stipe.mstore.mapper;

import com.stipe.mstore.helper.ProductWish;
import com.stipe.mstore.model.Product;
import com.stipe.mstore.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductToWishMapper {

    @Autowired
    private WishlistService wishlistService;

    public List<ProductWish> mapProductToWish(List<Product> products, String userId) {
        return products.stream().map(p ->
                ProductWish.builder()
                        .id(p.getId())
                        .isNew(p.getIsNew())
                        .isSold(p.getIsSold())
                        .name(p.getName())
                        .inWishlist(resolveWishlist(p.getId(), userId))
                        .price(p.getPrice())
                        .currency(p.getCurrency())
                        .build()
        ).collect(Collectors.toList());
    }

    private Boolean resolveWishlist(String productId, String userId) {
        if (userId.isEmpty()) {
            return false;
        }
        return !wishlistService.notInWishlist(productId, userId);
    }
}