package org.example;

import java.util.ArrayList;

public class Customer {
    private String username;
    private String email;
    private ArrayList<Order> orders;

    public Customer(String username, String email) {
        this.username = username;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }
}
