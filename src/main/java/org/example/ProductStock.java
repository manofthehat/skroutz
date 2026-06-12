package org.example;

// Allows addition of new products in an eshop.
// Ensures the price and quantity of the products are different in between eshops.

public class ProductStock {
    private Product product;
    private double price;
    private int quantity;

    public ProductStock(Product product, double price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public Product getProduct() {
        return this.product;
    }

    @Override
    public String toString() {
        // Connects the base product details with the variable data of an eshop's stock.
        return product.toString() + String.format(",Price: %.2f, Quantity: %d", this.price, this.quantity);
    }
}
