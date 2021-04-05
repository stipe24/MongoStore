package com.stipe.mstore.repository;

import com.stipe.mstore.model.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public class FollowRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Boolean notFollowing(String follower, String following) {
        var criteria = Criteria.where("followerId").is(follower).and("followingId").is(following);
        return mongoTemplate.find(new Query(criteria), Follow.class).isEmpty();
    }

    private void save(String follower, String following) {
        mongoTemplate.save(
                Follow.builder()
                .followerId(follower)
                .followingId(following)
                .time(Instant.now().toEpochMilli())
                .build()
        );
    }

    public void follow(String follower, String following) {
        if (notFollowing(follower, following)) {
            save(follower, following);
        }
    }

    public void unfollow(String follower, String following) {
        if (!notFollowing(follower, following)) {
            var criteria = Criteria.where("followerId").is(follower).and("followingId").is(following);
            mongoTemplate.remove(new Query(criteria), Follow.class);
        }
    }

    public Integer countFollowing(String userId) {
        var criteria = Criteria.where("followerId").is(userId);
        return mongoTemplate.find(new Query(criteria), Follow.class).size();
    }

    public Integer countFollowers(String userId) {
        var criteria = Criteria.where("followingId").is(userId);
        return mongoTemplate.find(new Query(criteria), Follow.class).size();
    }
}