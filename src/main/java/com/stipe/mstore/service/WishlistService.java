package com.stipe.mstore.service;

import java.util.List;

public interface WishlistService {

    boolean notInWishlist(String productId, String userId);

    void addToWishlist(String productId, String userId);

    void deleteFromWishlist(String productId, String userId);

    List<String> getProductIdsFromWishlist(String userId);
}