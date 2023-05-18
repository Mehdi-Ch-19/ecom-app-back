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
    private final ProductServiceInterf productServiceInterf;
    public CategoryImpl(CategoryRepo categoryRepo, ProductServiceInterf productServiceInterf) {
        this.categoryRepo = categoryRepo;
        this.productServiceInterf = productServiceInterf;
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
        List<Product> products =  category.getProducts();
        products.forEach(product -> {
            product.setReviews(productServiceInterf.findAllReviewsByProduct(product.getId()));
        });
        return products;
    }

    @Override
    public List<Product> allProductsByCategoryName(String name) {
        Category category = categoryRepo.findCategoryByCategoryTitle(name);
        assert category != null;
        List<Product> products =  category.getProducts();
        products.forEach(product -> {
            product.setReviews(productServiceInterf.findAllReviewsByProduct(product.getId()));
        });
        return products;
    }

    @Override
    public Category findByCategoryTitle(String name) {
        return categoryRepo.findCategoryByCategoryTitle(name);
    }
}
