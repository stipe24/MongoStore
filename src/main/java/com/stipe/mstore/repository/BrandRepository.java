package com.stipe.mstore.repository;

import com.stipe.mstore.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BrandRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Brand saveBrand(Brand brand) {
        return mongoTemplate.save(brand);
    }

    public List<Brand> getAllBrands() {
        return mongoTemplate.findAll(Brand.class).stream().sorted(Comparator.comparing(Brand::getName)).collect(Collectors.toList());
    }

    public String resolveBrandName(String name) {
        var criteria = Criteria.where("name").regex(name.toLowerCase(), "i");
        var brand = mongoTemplate.findOne(new Query(criteria), Brand.class);
        return Objects.nonNull(brand) ? brand.getId() : mongoTemplate.save(Brand.builder().name(name).build()).getId();
    }
}