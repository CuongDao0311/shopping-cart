package com.cuongdao.shopping_cart.repository;

import com.cuongdao.shopping_cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName();

    Long countByBrandAndName(String brand, String name);

    List<Product> findByCategoryId(Long categoryId);
}
