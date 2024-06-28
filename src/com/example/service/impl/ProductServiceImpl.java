package com.example.service.impl;

import com.example.dao.ProductDAO;
import com.example.dao.impl.ProductDAOImpl;
import com.example.model.Product;
import com.example.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public boolean addProduct(Product product) {
        return productDAO.addProduct(product);
    }


    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }

    @Override
    public Product getProduct(int productId) {
        return productDAO.getProduct(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productDAO.getProductsByPriceRange(minPrice, maxPrice);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productDAO.getProductsByBrand(brand);
    }
}
