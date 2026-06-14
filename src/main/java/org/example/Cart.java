package org.example;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addCartItem(CartItem item) {
        cartItems.add(item);
    }

    public ArrayList<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void printCart() {
        System.out.println("Your cart:");
        for (CartItem c : cartItems) {
            System.out.println(c.toString());
        }
    }

    public void updateCart(CartItem newItem) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getBarcode().equals(newItem.getProduct().getBarcode()) && cartItem.getPrice() == newItem.getPrice()) {
                cartItem.setQuantity(newItem.getQuantity() + cartItem.getQuantity());
                return;
            }
        }
        cartItems.add(newItem);
    }
}
