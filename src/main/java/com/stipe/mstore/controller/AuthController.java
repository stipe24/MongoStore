package com.stipe.mstore.controller;

import com.stipe.mstore.model.User;
import com.stipe.mstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public User userRegistration() {
        return new User();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user) {
        var createdUser = userService.save(user);
        return Objects.nonNull(createdUser) ? "redirect:/registration?success" : "redirect:/registration?failure";
    }
}