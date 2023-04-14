package com.ecomapp.product.repository;

import com.ecomapp.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findCategoryByCategoryTitle(String name);
}
