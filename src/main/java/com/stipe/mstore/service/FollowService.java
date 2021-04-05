package com.stipe.mstore.service;

public interface FollowService {

    Boolean notFollowing(String follower, String following);

    void follow(String follower, String following);

    void unfollow(String follower, String following);

    Integer countFollowing(String userId);

    Integer countFollowers(String userId);
}