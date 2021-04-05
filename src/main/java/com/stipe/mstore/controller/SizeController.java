package com.stipe.mstore.controller;

import com.stipe.mstore.model.Size;
import com.stipe.mstore.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("/sizes")
    public List<String> getSizes() {
        return sizeService.getSizes().stream().map(Size::getName).collect(Collectors.toList());
    }
}