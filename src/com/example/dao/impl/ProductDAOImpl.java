package com.example.dao.impl;

import com.example.dao.ProductDAO;
import com.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String DATABASE = "Product";
    private static final String USER = "root"; // replace with your actual username
    private static final String PASSWORD = "Rufiya@123*"; // replace with your actual password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ProductDAOImpl() {
        createDatabase();
        createTable();
    }

    private void createDatabase() {
        String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Database created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + DATABASE + ".Products (" +
                "product_id INT PRIMARY KEY, " +
                "product_name VARCHAR(100), " +
                "product_price DOUBLE, " +
                "quantity INT, " +
                "brand VARCHAR(50), " +
                "category VARCHAR(50)" +
                ")";
        try (Connection conn = DriverManager.getConnection(URL + "/" + DATABASE, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Products (product_id, product_name, product_price, quantity, brand, category) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL + "/" + DATABASE, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product.getProductId());
            pstmt.setString(2, product.getProductName());
            pstmt.setDouble(3, product.getProductPrice());
            pstmt.setInt(4, product.getQuantity());
            pstmt.setString(5, product.getBrand());
            pstmt.setString(6, product.getCategory());
            pstmt.executeUpdate();
            System.out.println("Product added successfully.");
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: A product with this ID already exists.");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE Products SET product_name = ?, product_price = ?, quantity = ?, brand = ?, category = ? WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(URL + "/" + DATABASE, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setDouble(2, product.getProductPrice());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setString(4, product.getBrand());
            pstmt.setString(5, product.getCategory());
            pstmt.setInt(6, product.getProductId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM Products WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(URL + "/" + DATABASE, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProduct(int productId) {
        String sql = "SELECT * FROM Products WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(URL + "/" + DATABASE, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("product_id"), rs.getString("product_name"),
                        rs.getDouble("product_price"), rs.getInt("quantity"),
                        rs.getString("brand"), rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Connection conn = DriverManager.getConnection(URL + "/" + DATABASE, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(rs.getInt("product_id"), rs.getString("product_name"),
                        rs.getDouble("product_price"), rs.getInt("quantity"),
                        rs.getString("brand"), rs.getString("category")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE product_price BETWEEN ? AND ?";
        try (Connection conn = DriverManager.getConnection(URL + "/" + DATABASE, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, minPrice);
            pstmt.setDouble(2, maxPrice);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("product_id"), rs.getString("product_name"),
                        rs.getDouble("product_price"), rs.getInt("quantity"),
                        rs.getString("brand"), rs.getString("category")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE brand = ?";
        try (Connection conn = DriverManager.getConnection(URL + "/" + DATABASE, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, brand);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("product_id"), rs.getString("product_name"),
                        rs.getDouble("product_price"), rs.getInt("quantity"),
                        rs.getString("brand"), rs.getString("category")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
