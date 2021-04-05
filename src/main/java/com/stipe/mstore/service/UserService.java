package com.stipe.mstore.service;

import com.stipe.mstore.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(User user);

    User update(String id, User user);

    User getById(String id);

    List<User> getUsers();
}