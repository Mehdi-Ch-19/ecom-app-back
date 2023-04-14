package com.ecomapp.product.service;

import com.ecomapp.product.dto.CategoryDto;
import com.ecomapp.product.dto.CategoryRequest;
import com.ecomapp.product.entity.Category;
import com.ecomapp.product.entity.Product;
import com.ecomapp.product.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService{

    private final CategoryRepo categoryRepo;

    public CategoryImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category save(CategoryRequest  categoryRequest) {
        Category category = Category.builder()
                .categoryTitle(categoryRequest.getCategoryTitle())
                .build();
          return categoryRepo.saveAndFlush(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Category update(CategoryDto categoryDto) {
        Category category = categoryRepo.findById(categoryDto.getCategoryId()).orElse(null);
        assert category != null;
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        return categoryRepo.save(category);

    }

    @Override
    public List<Product> allProducts(Long catId) {
        Category category = categoryRepo.findById(catId).orElse(null );
        assert category != null;
        return category.getProducts();
    }

    @Override
    public Category findByCategoryTitle(String name) {
        return categoryRepo.findCategoryByCategoryTitle(name);
    }
}
