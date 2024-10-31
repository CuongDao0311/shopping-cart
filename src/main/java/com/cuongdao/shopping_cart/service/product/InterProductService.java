package com.cuongdao.shopping_cart.service.product;

import com.cuongdao.shopping_cart.model.Product;
import com.cuongdao.shopping_cart.dto.ProductDTO;

import java.util.List;

public interface InterProductService {

    Product addProduct(ProductDTO dto);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryId(Long categoryId);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByCategoryAndBrand(String category, String Brand);
    List<Product> getProductsByName(String name);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductDTO dto, Long id);
    Long countProductsByBrandAndName(String brand, String name);
}
