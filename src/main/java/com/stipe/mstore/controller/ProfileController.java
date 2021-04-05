package com.stipe.mstore.controller;

import com.stipe.mstore.helper.ProductsFilter;
import com.stipe.mstore.model.User;
import com.stipe.mstore.service.FollowService;
import com.stipe.mstore.service.ProductService;
import com.stipe.mstore.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Objects;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @Autowired
    private ProductService productService;

    @ModelAttribute
    public ProductsFilter productsFilter() {
        return new ProductsFilter();
    }

    @GetMapping("/profile")
    public String getProfile(Principal principal, Model model) {
        if (Objects.isNull(principal)) {
            return "redirect:/login";
        }
        var id = principal.getName();
        var user = userService.getById(id);
        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("description", user.getDescription());
        model.addAttribute("filter", new ProductsFilter());
        model.addAttribute("following", followService.countFollowing(user.getId()));
        model.addAttribute("followers", followService.countFollowers(user.getId()));
        model.addAttribute("allProducts", productService.countUsersProducts(user.getId()));
        model.addAttribute("soldProducts", productService.countUsersSoldProducts(user.getId()));
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Principal principal, Model model) {
        if (Objects.nonNull(principal)) {
            model.addAttribute("user", userService.getById(principal.getName()));
            return "profile-edit";
        }
        return "redirect:/login";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam("imageFile")MultipartFile file, User user, Principal principal) throws IOException {
        if (Objects.nonNull(principal)) {
            if (!file.isEmpty()) {
                user.setImage(file.getBytes());
            }
            userService.update(principal.getName(), user);
            return "redirect:/profile";
        }
        return "redirect:/login";
    }

    @GetMapping("/profile/img")
    public void getProfileImage(Principal principal, HttpServletResponse response) throws IOException {
        if (Objects.nonNull(principal)) {
            var user = userService.getById(principal.getName());
            response.setContentType("image/jpeg");
            if (Objects.nonNull(user.getImage())) {
                InputStream inputStream = new ByteArrayInputStream(user.getImage());
                IOUtils.copy(inputStream, response.getOutputStream());
            }
        }
    }
}