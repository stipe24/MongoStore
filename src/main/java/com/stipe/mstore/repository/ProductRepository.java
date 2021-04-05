package com.stipe.mstore.repository;

import com.stipe.mstore.helper.ProductsFilter;
import com.stipe.mstore.model.Product;
import com.stipe.mstore.utils.ProductsQueryCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product saveNewProduct(Product product) {
        product.setTime(Instant.now().toEpochMilli());
        product.setViews(0L);
        return mongoTemplate.save(product);
    }

    public Product getProductById(String id) {
        var product = mongoTemplate.findById(id, Product.class);
        return incrementProductViews(product);
    }

    private Product incrementProductViews(Product product) {
        product.setViews(product.getViews() + 1L);
        return mongoTemplate.save(product);
    }

    public List<Product> getProducts(ProductsFilter filter) {
        var query = ProductsQueryCreator.createQuery(filter);
        return mongoTemplate.find(query, Product.class);
    }

    public void deleteProduct(String id) {
        var criteria = Criteria.where("id").is(id);
        mongoTemplate.remove(new Query(criteria), Product.class);
    }

    public List<Product> search(String text) {
        var criteria = Criteria.where("name").regex(text.toLowerCase(), "i");
        return mongoTemplate.find(new Query(criteria), Product.class);
    }

    public Integer countUsersProducts(String userId) {
        var criteria = Criteria.where("userId").is(userId);
        return mongoTemplate.find(new Query(criteria), Product.class).size();
    }

    public Integer countUsersSoldProducts(String userId) {
        var criteria = Criteria.where("userId").is(userId).and("isSold").is(true);
        return mongoTemplate.find(new Query(criteria), Product.class).size();
    }

    public List<Product> getProductsFromIds(List<String> ids) {
        var criteria = Criteria.where("id").in(ids);
        return mongoTemplate.find(new Query(criteria), Product.class);
    }
}