package com.company.billing;

import com.company.products.Product;

//import java.io.*;
import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class BillingSystem {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/billing_system";
    private static final String DB_USER = "OOPJProject";
    private static final String DB_PASSWORD = "Java";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
            return;
        }
    
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database successfully.");
            
            // Initialize scanner and bill
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            Bill bill = new Bill();
    
            // User interaction to input product details
            while (true) {
                try {
                    System.out.println("Enter product name (or 'done' to finish):");
                    String productName = scanner.nextLine();
                    if (productName.equalsIgnoreCase("done")) {
                        break;
                    }
    
                    System.out.println("Enter product price:");
                    double productPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
    
                    Product product = new Product(productName, productPrice);
                    bill.addProduct(product);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price. Please enter a valid number.");
                    scanner.nextLine(); // Clear buffer
                }
            }
            // Create instances of User
User user1 = new User(1, "John Doe", "john@example.com", "password", "Male", "1234567890", UserRole.Customer);
User user2 = new User(2, "Jane Smith", "jane@example.com", "password", "Female", "9876543210", UserRole.Employee);

// Insert user data into the database
try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, email, password, gender, phone_no, role) VALUES (?, ?, ?, ?, ?, ?)")) {
    statement.setString(1, user1.getName());
    statement.setString(2, user1.getEmail());
    statement.setString(3, user1.getPassword());
    statement.setString(4, user1.getGender());
    statement.setString(5, user1.getPhoneNo());
    statement.setString(6, user1.getRole().toString());
    statement.executeUpdate();
    
    statement.setString(1, user2.getName());
    statement.setString(2, user2.getEmail());
    statement.setString(3, user2.getPassword());
    statement.setString(4, user2.getGender());
    statement.setString(5, user2.getPhoneNo());
    statement.setString(6, user2.getRole().toString());
    statement.executeUpdate();
} catch (SQLException e) {
    System.out.println("Error inserting users into the database: " + e.getMessage());
    e.printStackTrace();
}

// Create an instance of PurchaseHistory
PurchaseHistory purchase = new PurchaseHistory(1, "1234567890", "Product1, Product2", Date.valueOf("2024-04-23"), Time.valueOf("12:30:00"), "john@example.com");

// Insert purchase data into the database
try (PreparedStatement statement = connection.prepareStatement("INSERT INTO purchase_history (customer_phone, customer_items, purchased_date, purchase_time, emp_email) VALUES (?, ?, ?, ?, ?)")) {
    statement.setString(1, purchase.getCustomerPhone());
    statement.setString(2, purchase.getCustomerItems());
    statement.setDate(3, purchase.getPurchasedDate());
    statement.setTime(4, purchase.getPurchaseTime());
    statement.setString(5, purchase.getEmpEmail());
    statement.executeUpdate();
} catch (SQLException e) {
    System.out.println("Error inserting purchase history into the database: " + e.getMessage());
    e.printStackTrace();
}

// Create instances of Product
Product product1 = new Product("Product1", 10.99);
Product product2 = new Product("Product2", 20.49);

// Insert product data into the database
try (PreparedStatement statement = connection.prepareStatement("INSERT INTO products (item_name, item_price, item_category, item_description) VALUES (?, ?, ?, ?)")) {
    statement.setString(1, product1.getName());
    statement.setDouble(2, product1.getPrice());
    statement.setString(3, "Category1"); // You can specify the category and description as needed
    statement.setString(4, "Description for Product1");
    statement.executeUpdate();
    
    statement.setString(1, product2.getName());
    statement.setDouble(2, product2.getPrice());
    statement.setString(3, "Category2"); // You can specify the category and description as needed
    statement.setString(4, "Description for Product2");
    statement.executeUpdate();
} catch (SQLException e) {
    System.out.println("Error inserting products into the database: " + e.getMessage());
    e.printStackTrace();
}

            // Print bill details
            System.out.println("\nBilling Details:");
            bill.printBill();
    
            // Save bill to database
            saveBillToDatabase(connection, bill);
            System.out.println("Billing information saved to database.");
    
            // Load and print saved bill
            Bill savedBill = loadBillFromDatabase(connection);
            if (savedBill != null) {
                System.out.println("\nSaved Billing Details:");
                savedBill.printBill();
            }
    
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
            return;
        }
    }
    
    private static void saveBillToDatabase(Connection connection, Bill bill) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO bills (product_name, price) VALUES (?, ?)")) {
            for (Product product : bill.getProducts()) {
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error saving bill to database: " + e.getMessage());
            e.printStackTrace(); // Print stack trace
        }
    }
    
    private static Bill loadBillFromDatabase(Connection connection) {
        Bill bill = new Bill();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT product_name, price FROM bills")) {
            while (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                double price = resultSet.getDouble("price");
                Product product = new Product(productName, price);
                bill.addProduct(product);
            }
        } catch (SQLException e) {
            System.out.println("Error loading bill from database: " + e.getMessage());
            e.printStackTrace(); // Print stack trace
        }
        return bill;
    }
    
}
