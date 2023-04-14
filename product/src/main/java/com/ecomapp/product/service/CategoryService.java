package com.ecomapp.product.service;

import com.ecomapp.product.dto.CategoryDto;
import com.ecomapp.product.dto.CategoryRequest;
import com.ecomapp.product.entity.Category;
import com.ecomapp.product.entity.Product;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(CategoryRequest categoryRequest);
    void deleteById(Long id);

    Category update(CategoryDto categoryDto);

    List<Product> allProducts(Long catId);
    Category findByCategoryTitle(String name);
}
