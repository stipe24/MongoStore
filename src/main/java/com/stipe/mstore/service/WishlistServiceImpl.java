package com.stipe.mstore.service;

import com.stipe.mstore.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public boolean notInWishlist(String productId, String userId) {
        return wishlistRepository.notInWishlist(productId, userId);
    }

    @Override
    public void addToWishlist(String productId, String userId) {
        wishlistRepository.addToWishlist(productId, userId);
    }

    @Override
    public void deleteFromWishlist(String productId, String userId) {
        wishlistRepository.deleteFromWishlist(productId, userId);
    }

    @Override
    public List<String> getProductIdsFromWishlist(String userId) {
        return wishlistRepository.getProductsIdsFromWishlist(userId);
    }
}