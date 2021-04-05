package com.stipe.mstore.repository;

import com.stipe.mstore.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class SizeRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Size saveSize(Size size) {
        return mongoTemplate.save(size);
    }

    private Size save(String name) {
        return mongoTemplate.save(
          Size.builder()
                  .name(name)
                  .build()
        );
    }

    public List<Size> getAllSizes() {
        return mongoTemplate.findAll(Size.class);
    }

    public String resolveSizeName(String name) {
        var criteria = Criteria.where("name").regex(name.toLowerCase(), "i");
        var size = mongoTemplate.findOne(new Query(criteria), Size.class);
        return Objects.nonNull(size) ? size.getId() : save(name).getId();
    }
}