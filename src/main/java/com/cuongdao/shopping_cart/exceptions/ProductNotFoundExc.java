package com.cuongdao.shopping_cart.exceptions;

public class ProductNotFoundExc extends RuntimeException{
    public ProductNotFoundExc(String message) {
        super(message);
    }
}
