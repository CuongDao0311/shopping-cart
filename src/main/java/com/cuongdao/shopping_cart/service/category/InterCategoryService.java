package com.cuongdao.shopping_cart.service.category;

import com.cuongdao.shopping_cart.model.Category;

import java.util.List;

public interface InterCategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);
}
