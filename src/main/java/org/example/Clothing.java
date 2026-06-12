package org.example;

public class Clothing extends Wearable {
    public Clothing(String barcode, String name, String brand, String size, String color) {
        // Fixes the category as Clothing.
        super(barcode, name, "Clothing", brand, size, color);
    }
}
