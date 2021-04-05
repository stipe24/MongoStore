package com.stipe.mstore.service;

import com.stipe.mstore.model.Category;
import com.stipe.mstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<String> getCategories(String mainCategory) {
        return categoryRepository.getCategories(mainCategory);
    }
}