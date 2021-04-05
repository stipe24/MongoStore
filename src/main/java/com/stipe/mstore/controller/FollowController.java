package com.stipe.mstore.controller;

import com.stipe.mstore.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Objects;

@Controller
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/follow")
    public String follow(String following, Principal principal) {
        if (Objects.nonNull(principal)) {
            followService.follow(principal.getName(), following);
            return "redirect:/profile";
        }
        return "login";
    }

    @GetMapping("/unfollow")
    public String unfollow(String following, Principal principal) {
        if (Objects.nonNull(principal)) {
            followService.unfollow(principal.getName(), following);
            return "redirect:/profile";
        }
        return "login";
    }
}