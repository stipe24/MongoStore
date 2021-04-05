package com.stipe.mstore.service;

import com.stipe.mstore.model.Brand;

import java.util.List;

public interface BrandService {

    Brand save(Brand brand);

    List<Brand> getBrands();
}