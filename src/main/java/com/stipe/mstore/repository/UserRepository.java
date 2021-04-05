package com.stipe.mstore.repository;

import com.stipe.mstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findUserByEmail(String email) {
        var criteria = Criteria.where("email").is(email);
        return mongoTemplate.findOne(new Query(criteria), User.class);
    }

    public User findUserById(String id) {
        var criteria = Criteria.where("id").is(id);
        return mongoTemplate.findOne(new Query(criteria), User.class);
    }

    public User saveUser(User user) {
        if (Objects.isNull(findUserByEmail(user.getEmail()))) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singletonList("USER"));
            return mongoTemplate.save(user);
        }
        return null;
    }

    public User updateUser(String id, User newUser) {
        var oldUser = findUserById(id);
        if (Objects.nonNull(newUser.getImage())) {
            oldUser.setImage(newUser.getImage());
        }
        oldUser.setUsername(newUser.getUsername());
        oldUser.setDescription(newUser.getDescription());
        return mongoTemplate.save(oldUser);
    }

    public List<User> getUsers(){
        return mongoTemplate.findAll(User.class);
    }
}