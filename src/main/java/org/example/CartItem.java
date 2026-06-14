package org.example;

public class CartItem {
    private Product product;
    private double price;
    private int quantity;

    public CartItem(Product product, double price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Price: %.2f, Quantity: %d",this.product.getName(), this.price, this.quantity);
    }
}
