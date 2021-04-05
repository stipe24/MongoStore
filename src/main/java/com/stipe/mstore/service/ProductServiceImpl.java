package com.stipe.mstore.service;

import com.stipe.mstore.enums.MainCategory;
import com.stipe.mstore.helper.ProductsFilter;
import com.stipe.mstore.model.Product;
import com.stipe.mstore.repository.BrandRepository;
import com.stipe.mstore.repository.CategoryRepository;
import com.stipe.mstore.repository.ProductRepository;
import com.stipe.mstore.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product save(String userId, Product product) {
        product.setUserId(userId);
        if (!product.getBrandName().equals("")) {
            product.setBrandId(brandRepository.resolveBrandName(product.getBrandName()));
        }
        if (!product.getSizeName().equals("")) {
            product.setSizeId(sizeRepository.resolveSizeName(product.getSizeName()));
        }
        if (!product.getCategoryName().equals("")) {
            product.setCategoryId(categoryRepository.resolveCategoryName(product.getCategoryName(), product.getMainCategory().toString()));
        }
        return productRepository.saveNewProduct(product);
    }

    @Override
    public List<Product> getProducts(ProductsFilter filter) {
        if (Objects.nonNull(filter.getMainCategory()) && Objects.nonNull(filter.getCategoryName())) {
            filter.setCategoryId(categoryRepository.resolveCategoryName(filter.getCategoryName(), filter.getMainCategory().toString()));
        }
        return productRepository.getProducts(filter);
    }

    @Override
    public Product update(Product old, Product newProduct) {
        return null;
    }

    @Override
    public Product getById(String id) {
        return productRepository.getProductById(id);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public Integer countUsersProducts(String userId) {
        return productRepository.countUsersProducts(userId);
    }

    @Override
    public Integer countUsersSoldProducts(String userId) {
        return productRepository.countUsersSoldProducts(userId);
    }

    @Override
    public List<Product> search(String text) {
        return productRepository.search(text);
    }

    @Override
    public List<Product> getProductsFromIds(List<String> ids) {
        return productRepository.getProductsFromIds(ids);
    }
}