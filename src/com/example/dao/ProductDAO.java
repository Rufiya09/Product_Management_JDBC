package com.example.dao;

import com.example.model.Product;

import java.util.List;

public interface ProductDAO {
    boolean addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    Product getProduct(int productId);
    List<Product> getAllProducts();
    List<Product> getProductsByPriceRange(double minPrice, double maxPrice);
    List<Product> getProductsByBrand(String brand);
}