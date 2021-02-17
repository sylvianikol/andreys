package com.exam.andreys.service;

import com.exam.andreys.model.service.ProductServiceModel;

import java.util.List;

public interface ProductService {
    boolean add(ProductServiceModel productServiceModel);

    List<ProductServiceModel> getAllProducts();

    ProductServiceModel getByName(String name);

    void deleteByName(String name);
}
