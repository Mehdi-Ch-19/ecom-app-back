package com.ecomapp.product.controller;

import com.ecomapp.product.dto.CategoryDto;
import com.ecomapp.product.dto.CategoryRequest;
import com.ecomapp.product.entity.Category;
import com.ecomapp.product.entity.Product;
import com.ecomapp.product.exeption.CategoryNameAleardyExists;
import com.ecomapp.product.service.CategoryService;
import com.ecomapp.product.service.ProductServiceInterf;
import com.ecomapp.product.util.SecureWithToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CategoryRequest category){
        Category category1 = categoryService.findByCategoryTitle(category.getCategoryTitle());
        if (category1 != null){
            throw new CategoryNameAleardyExists("category name exists ");
        }
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> allcategories(){
        return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{categoryId}/products")
    //@SecureWithToken
    public ResponseEntity<List<Product>> allProductsByCategory(@PathVariable Long categoryId){
        List<Product> productList = categoryService.allProducts(categoryId);
        if (productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(productList,HttpStatus.OK);
    }
    @GetMapping("/name/{categoryName}/products")
    //@SecureWithToken
    public ResponseEntity<List<Product>> allProductsByCategoryName(@PathVariable String categoryName){
        List<Product> productList = categoryService.allProductsByCategoryName(categoryName);
        if (productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(productList,HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<?> updateName(CategoryDto categoryDto){
        Category update = categoryService.update(categoryDto);
        return new ResponseEntity(update,HttpStatus.OK);
    }
}
