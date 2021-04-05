package com.stipe.mstore.controller;

import com.stipe.mstore.model.Brand;
import com.stipe.mstore.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public List<String> getBrands() {
        return brandService.getBrands().stream().map(Brand::getName).collect(Collectors.toList());
    }
}