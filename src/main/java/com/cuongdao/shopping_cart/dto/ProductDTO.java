package com.cuongdao.shopping_cart.dto;

import com.cuongdao.shopping_cart.model.Category;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Data
@Component
public class ProductDTO {
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
    private List<ImageDTO> images;
}
