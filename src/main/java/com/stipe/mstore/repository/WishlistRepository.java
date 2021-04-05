package com.stipe.mstore.repository;

import com.stipe.mstore.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WishlistRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean notInWishlist(String productId, String userId) {
        var criteria = Criteria.where("userId").is(userId).and("productId").is(productId);
        return mongoTemplate.find(new Query(criteria), Wishlist.class).isEmpty();
    }

    private void save(String productId, String userId) {
        mongoTemplate.save(
                Wishlist.builder()
                        .productId(productId)
                        .userId(userId)
                        .time(Instant.now().toEpochMilli())
                        .build()
        );
    }

    public void addToWishlist(String productId, String userId) {
        if (notInWishlist(productId, userId)) {
            save(productId, userId);
        }
    }

    public void deleteFromWishlist(String productId, String userId) {
        if (!notInWishlist(productId, userId)) {
            var criteria = Criteria.where("userId").is(userId).and("productId").is(productId);
            mongoTemplate.remove(new Query(criteria), Wishlist.class);
        }
    }

    public List<String> getProductsIdsFromWishlist(String userId) {
        var criteria = Criteria.where("userId").is(userId);
        return mongoTemplate.find(new Query(criteria), Wishlist.class).stream().map(Wishlist::getProductId).collect(Collectors.toList());
    }
}