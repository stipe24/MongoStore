package com.stipe.mstore.service;

import com.stipe.mstore.model.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<String> getCategories(String mainCategory);
}