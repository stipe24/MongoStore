package com.stipe.mstore.service;

import com.stipe.mstore.model.Brand;
import com.stipe.mstore.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand save(Brand brand) {
        return brandRepository.saveBrand(brand);
    }

    @Override
    public List<Brand> getBrands() {
        return brandRepository.getAllBrands();
    }
}