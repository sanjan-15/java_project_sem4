package com.company.billing;

import com.company.products.Product;

import java.io.Serializable;
import java.util.List;

public class Bill implements Serializable {
    private String customerName;
    private String phoneNumber;
    private List<Product> products;

    public Bill(String customerName, String phoneNumber) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void printBill() {
        System.out.println("Customer Name: " + customerName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Bill Details:");
        for (Product product : products) {
            System.out.println("- " + product.getName() + ": $" + product.getPrice());
        }
        System.out.println("Total: $" + calculateTotal());
    }
}
