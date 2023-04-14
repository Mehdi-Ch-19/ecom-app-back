package com.ecomapp.product.mapper;

import com.ecomapp.product.dto.CategoryDto;
import com.ecomapp.product.entity.Category;

public class CategoryMapper {
    public CategoryDto toDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        return categoryDto;
    }
}
