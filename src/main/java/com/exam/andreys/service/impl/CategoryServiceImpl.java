package com.exam.andreys.service.impl;

import com.exam.andreys.model.entity.Category;
import com.exam.andreys.model.enums.CategoryName;
import com.exam.andreys.repository.CategoryRepository;
import com.exam.andreys.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() == 0) {

            Arrays.stream(CategoryName.values())
                    .forEach(value -> {
                        Category category = new Category();
                        category.setName(value);

                        this.categoryRepository.save(category);
                    });
        }
    }

    @Override
    public Category findByName(CategoryName name) {
        return this.categoryRepository.findByName(name).orElse(null);
    }
}
