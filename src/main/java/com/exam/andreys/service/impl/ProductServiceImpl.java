package com.exam.andreys.service.impl;

import com.exam.andreys.model.entity.Product;
import com.exam.andreys.model.service.ProductServiceModel;
import com.exam.andreys.repository.ProductRepository;
import com.exam.andreys.service.CategoryService;
import com.exam.andreys.service.ProductService;
import com.exam.andreys.service.SexService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final SexService sexService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, SexService sexService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.sexService = sexService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setCategory(this.categoryService.findByName(productServiceModel.getCategory()));
        product.setSex(this.sexService.findBySex(productServiceModel.getSex()));
        try {
            productRepository.save(product);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
