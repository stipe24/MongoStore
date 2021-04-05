package com.stipe.mstore.service;

import com.stipe.mstore.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Override
    public Boolean notFollowing(String follower, String following) {
        return followRepository.notFollowing(follower, following);
    }

    @Override
    public void follow(String follower, String following) {
        followRepository.follow(follower, following);
    }

    @Override
    public void unfollow(String follower, String following) {
        followRepository.unfollow(follower, following);
    }

    @Override
    public Integer countFollowing(String userId) {
        return followRepository.countFollowing(userId);
    }

    @Override
    public Integer countFollowers(String userId) {
        return followRepository.countFollowers(userId);
    }
}