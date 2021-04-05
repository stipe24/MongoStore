package com.stipe.mstore.repository;

import com.stipe.mstore.enums.MainCategory;
import com.stipe.mstore.model.Category;
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
public class CategoryRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Category save(Category category) {
        return mongoTemplate.save(category);
    }

    private Category save(String name, String mainCategory) {
        return mongoTemplate.save(
          Category.builder()
                  .name(name)
                  .mainCategory(MainCategory.valueOf(mainCategory))
                  .build()
        );
    }

    public List<String> getCategories(String mainCategory) {
        var criteria = Criteria.where("mainCategory").is(mainCategory);
        return mongoTemplate.find(new Query(criteria), Category.class).stream().sorted(Comparator.comparing(Category::getName)).map(Category::getName).collect(Collectors.toList());
    }

    public String resolveCategoryName(String name, String mainCategory) {
        var criteria = Criteria.where("name").regex(name.toLowerCase(), "i").and("mainCategory").regex(mainCategory.toLowerCase(), "i");
        var category = mongoTemplate.findOne(new Query(criteria), Category.class);
        return Objects.nonNull(category) ? category.getId() : save(name, mainCategory).getId();
    }
}