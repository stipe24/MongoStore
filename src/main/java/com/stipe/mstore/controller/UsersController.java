package com.stipe.mstore.controller;

import com.stipe.mstore.helper.ProductsFilter;
import com.stipe.mstore.service.FollowService;
import com.stipe.mstore.service.ProductService;
import com.stipe.mstore.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Objects;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @Autowired
    private ProductService productService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        var users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUserDetails(@PathVariable String id, Principal principal, Model model) {
        var user = userService.getById(id);
        if (Objects.nonNull(user)) {
            model.addAttribute("id", user.getId());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("description", user.getDescription());
            model.addAttribute("filter", new ProductsFilter());
            model.addAttribute("following", followService.countFollowing(user.getId()));
            model.addAttribute("followers", followService.countFollowers(user.getId()));
            if (Objects.nonNull(principal) && !user.getId().equals(principal.getName())) {
                model.addAttribute("notFollowing", followService.notFollowing(principal.getName(), user.getId()));
            }
            model.addAttribute("allProducts", productService.countUsersProducts(user.getId()));
            model.addAttribute("soldProducts", productService.countUsersSoldProducts(user.getId()));
            return "user-details";
        }
        return "error";
    }

    @GetMapping("/users/{id}/img")
    public void getProfileImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        var user = userService.getById(id);
        if (Objects.nonNull(user)) {
            response.setContentType("image/jpeg");
            if (Objects.nonNull(user.getImage())) {
                InputStream inputStream = new ByteArrayInputStream(user.getImage());
                IOUtils.copy(inputStream, response.getOutputStream());
            }
        }
    }
}