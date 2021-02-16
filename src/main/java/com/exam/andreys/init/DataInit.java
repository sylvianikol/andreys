package com.exam.andreys.init;

import com.exam.andreys.service.CategoryService;
import com.exam.andreys.service.SexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final SexService sexService;

    @Autowired
    public DataInit(CategoryService categoryService, SexService sexService) {
        this.categoryService = categoryService;
        this.sexService = sexService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategories();
        this.sexService.initSexes();
    }
}
