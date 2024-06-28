package com.example.client;

import com.example.model.Product;
import com.example.service.ProductService;
import com.example.service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductService productService = new ProductServiceImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add product");
            System.out.println("2. Update product");
            System.out.println("3. Delete product");
            System.out.println("4. Get product by ID");
            System.out.println("5. Get all products");
            System.out.println("6. Get products by price range");
            System.out.println("7. Get products by brand");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double productPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    Product newProduct = new Product(productId, productName, productPrice, quantity, brand, category);
                    if (productService.addProduct(newProduct)) {
                        System.out.println("Product added successfully.");
                    }
                    break;
                
                case 2:
                    System.out.print("Enter product ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new product name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new product price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new brand: ");
                    String newBrand = scanner.nextLine();
                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();
                    Product updatedProduct = new Product(updateId, newName, newPrice, newQuantity, newBrand, newCategory);
                    productService.updateProduct(updatedProduct);
                    System.out.println("Product updated successfully.");
                    break;
                
                
                case 3:
                    System.out.print("Enter product ID to delete: ");
                    int deleteId = scanner.nextInt();
                    productService.deleteProduct(deleteId);
                    System.out.println("Product deleted successfully.");
                    break;
               
                
                case 4:
                    System.out.print("Enter product ID to get: ");
                    int getId = scanner.nextInt();
                    Product product = productService.getProduct(getId);
                    System.out.println("Product details: " + product);
                    break;
               
                
                case 5:
                    List<Product> products = productService.getAllProducts();
                    System.out.println("All Products:");
                    products.forEach(System.out::println);
                    break;
               
                
                case 6:
                    System.out.print("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    List<Product> productsByPriceRange = productService.getProductsByPriceRange(minPrice, maxPrice);
                    System.out.println("Products in price range:");
                    productsByPriceRange.forEach(System.out::println);
                    break;
               
                
                case 7:
                    System.out.print("Enter brand name: ");
                    String brandName = scanner.nextLine();
                    List<Product> productsByBrand = productService.getProductsByBrand(brandName);
                    System.out.println("Products by brand:");
                    productsByBrand.forEach(System.out::println);
                    break;
              
                
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
              
                
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
