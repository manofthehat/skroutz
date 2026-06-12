package org.example;

// Specialized class providing extra size and color details on top of the Product class.

public class Wearable extends Product {
    private String size; // Using string to represent both numerical and actual size values.
    private String color;

    public Wearable(String barcode, String name, String category, String brand, String size, String color) {
        super(barcode, name, category, brand);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String newSize) {
        this.size = newSize;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String newColor) {
        this.color = newColor;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(",Size: %s, Color: %s", this.size, this.color);
    }}
