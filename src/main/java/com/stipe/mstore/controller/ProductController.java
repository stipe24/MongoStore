package com.stipe.mstore.controller;

import com.stipe.mstore.enums.Color;
import com.stipe.mstore.enums.Currency;
import com.stipe.mstore.enums.Gender;
import com.stipe.mstore.enums.MainCategory;
import com.stipe.mstore.helper.ProductsFilter;
import com.stipe.mstore.mapper.ProductToWishMapper;
import com.stipe.mstore.model.Product;
import com.stipe.mstore.service.BrandService;
import com.stipe.mstore.service.ProductService;
import com.stipe.mstore.service.SizeService;
import com.stipe.mstore.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Objects;

@Controller
public class ProductController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private ProductToWishMapper mapper;

    @ModelAttribute
    public Product product() {
        return new Product();
    }

    @ModelAttribute
    public ProductsFilter productsFilter() {
        return new ProductsFilter();
    }

    @GetMapping("/products/new")
    public String newProduct(Principal principal, Model model) {
        if (Objects.isNull(principal)) {
            return "redirect:/login";
        }
        model.addAttribute("currencies", Currency.values());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("mainCategories", MainCategory.values());
        model.addAttribute("colors", Color.values());
        model.addAttribute("brands", brandService.getBrands());
        model.addAttribute("sizes", sizeService.getSizes());
        return "product-new";
    }

    @PostMapping("/products/new")
    public String newProduct(@RequestParam("imageFile") MultipartFile file, Product product, Principal principal) throws IOException {
        if (Objects.isNull(principal)) {
            return "redirect:/index";
        }
        if (!file.isEmpty()) {
            product.setImage(file.getBytes());
        }
        productService.save(principal.getName(), product);
        return "redirect:/profile";
    }

    @GetMapping("/products/{id}")
    public String getProductDetails(@PathVariable String id, Model model, Principal principal) {
        var product = productService.getById(id);
        if (Objects.nonNull(product)) {
            model.addAttribute("product", product);
            model.addAttribute("username", userService.getById(product.getUserId()).getUsername());
            return "product-details";
        }
        return "error";
    }

    @GetMapping("/products/{id}/img")
    public void getProductImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        var product = productService.getById(id);
        if (Objects.nonNull(product)) {
            response.setContentType("image/jpeg");
            if (Objects.nonNull(product.getImage())) {
                InputStream inputStream = new ByteArrayInputStream(product.getImage());
                IOUtils.copy(inputStream, response.getOutputStream());
            }
        }
    }

    @GetMapping("/products")
    public String getProducts(ProductsFilter filter, Model model, Principal principal) {
        var products = productService.getProducts(filter);
        model.addAttribute("products", mapper.mapProductToWish(products, Objects.isNull(principal) ? "" : principal.getName()));
        return "products";
    }
}