package com.ecomapp.product.service;

import com.ecomapp.product.dto.CategoryDto;
import com.ecomapp.product.dto.CategoryRequest;
import com.ecomapp.product.entity.Category;
import com.ecomapp.product.entity.Product;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(CategoryRequest categoryRequest);
    boolean deleteById(Long id);

    Category update(CategoryDto categoryDto);

    List<Product> allProducts(Long catId);
    List<Product> allProductsByCategoryName(String name);
    Category findByCategoryTitle(String name);
}
