package com.exam.andreys.service;

import com.exam.andreys.model.entity.Category;
import com.exam.andreys.model.enums.CategoryName;

public interface CategoryService {
    void initCategories();

    Category findByName(CategoryName name);
}
