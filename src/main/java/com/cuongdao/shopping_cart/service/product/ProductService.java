package com.cuongdao.shopping_cart.service.product;

import com.cuongdao.shopping_cart.exceptions.ProductNotFoundExc;
import com.cuongdao.shopping_cart.model.Category;
import com.cuongdao.shopping_cart.model.Product;
import com.cuongdao.shopping_cart.repository.CategoryRepository;
import com.cuongdao.shopping_cart.repository.ProductRepository;
import com.cuongdao.shopping_cart.requests.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements InterProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(ProductDTO dto) {
        Category category = Optional
                .ofNullable(categoryRepository.findByName(dto.getCategory().getName()))
                .orElseGet(() -> categoryRepository.save(dto.getCategory()));
        dto.setCategory(category);
        return productRepository.save(createProduct(dto));
    }

    private Product createProduct(ProductDTO dto) {
        return new Product(
                dto.getName(),
                dto.getBrand(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getInventory(),
                dto.getCategory()
        );
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundExc("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository
                .findById(id)
                .ifPresentOrElse(
                        productRepository::delete,
                        () -> {throw new ProductNotFoundExc("Product not found");});
    }

    @Override
    public Product updateProduct(ProductDTO dto, Long id) {
        return productRepository
                .findById(id)
                .map(product -> updateExistingProduct(product, dto))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundExc("Product not found"));
    }

    private Product updateExistingProduct(Product product, ProductDTO dto){
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setInventory(dto.getInventory());

        Category category = categoryRepository.findByName(dto.getCategory().getName());
        product.setCategory(category);
        return product;
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
