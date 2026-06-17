package org.example;

import java.util.ArrayList;
import java.time.LocalDate; //Used to get the current date of order placement

public class Order {
    private ArrayList<CartItem> items;
    private int orderID;
    private LocalDate orderDate;
    private double totalCost;

    private static int nextID = 1; //Order IDs will be one constantly increasing int.

    public Order(Cart cart) {
        this.items = new ArrayList<>();

        for (CartItem item : cart.getCartItems()) {
            this.items.add(new CartItem(item.getProduct(), item.getPrice(), item.getEshop(), item.getQuantity()));
        }

        this.totalCost = calculateTotal();
        this.orderDate = LocalDate.now();
        this.orderID = nextID++;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public int getOrderId() {
        return orderID;
    }

    public double calculateTotal() {
        double sum = 0;

        for (CartItem item : items) {
            sum += item.getPrice() * item.getQuantity();
        }

        return sum;
    }

    @Override
    public String toString() {
        return String.format("Order ID: %d, Date of placement: %s, Total cost: %.2f", this.orderID, this.orderDate, this.totalCost);
    }
}
