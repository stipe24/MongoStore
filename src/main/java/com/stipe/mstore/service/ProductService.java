package com.stipe.mstore.service;

import com.stipe.mstore.helper.ProductsFilter;
import com.stipe.mstore.model.Product;

import java.util.List;

public interface ProductService {

    Product save(String userId, Product product);

    List<Product> getProducts(ProductsFilter filter);

    Product update(Product old, Product newProduct);

    Product getById(String id);

    void delete(String id);

    Integer countUsersProducts(String userId);

    Integer countUsersSoldProducts(String userId);

    List<Product> search(String text);

    List<Product> getProductsFromIds(List<String> ids);
}