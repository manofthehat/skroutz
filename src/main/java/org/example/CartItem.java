package org.example;

public class CartItem {
    private Product product;
    private double price;
    private Eshop eshop;
    private int quantity;

    public CartItem(Product product, double price, Eshop eshop, int quantity) {
        this.product = product;
        this.price = price;
        this.eshop =  eshop;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public double getPrice() {
        return this.price;
    }

    public Eshop getEshop() {
        return this.eshop;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    @Override
    public String toString() {
        return String.format("Eshop: %s, Name: %s, Price: %.2f, Quantity: %d", this.eshop.getWebsite(),this.product.getName(), this.price, this.quantity);
    }
}
