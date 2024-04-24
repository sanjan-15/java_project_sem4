package com.company.billing;

import com.company.products.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Serializable {
    private List<Product> products;

    public Bill() {
        products = new ArrayList<>();
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
            total += product.getPrice(); // Corrected method name to getPrice()
        }
        return total;
    }

    public void printBill() {
        System.out.println("Bill Details:");
        for (Product product : products) {
            System.out.println("- " + product.getName() + ": $" + product.getPrice()); // Corrected method names to getName() and getPrice()
        }
        System.out.println("Total: $" + calculateTotal());
    }
}
