package com.stipe.mstore.controller;

import com.stipe.mstore.mapper.ProductToWishMapper;
import com.stipe.mstore.service.ProductService;
import com.stipe.mstore.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Objects;

@Controller
public class WishlistController {

    @Autowired
    private ProductService productService;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductToWishMapper mapper;


    @GetMapping("/wishlist")
    public String getWishlist(Principal principal, Model model) {
        if (Objects.isNull(principal)) {
            return "redirect:/login";
        }
        var ids = wishlistService.getProductIdsFromWishlist(principal.getName());
        var products = productService.getProductsFromIds(ids);
        model.addAttribute("products", mapper.mapProductToWish(products, principal.getName()));
        return "products";
    }

    @GetMapping("/wishlist/add/{productId}")
    public String addToWishlist(@PathVariable String productId, Principal principal) {
        if (Objects.nonNull(principal)) {
            wishlistService.addToWishlist(productId, principal.getName());
            return "redirect:/wishlist";
        }
        return "redirect:/login";
    }

    @GetMapping("/wishlist/delete/{productId}")
    public String deleteFromWishlist(@PathVariable String productId, Principal principal) {
        if (Objects.nonNull(principal)) {
            wishlistService.deleteFromWishlist(productId, principal.getName());
            return "redirect:/wishlist";
        }
        return "redirect:/login";
    }
}