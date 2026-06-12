package org.example;

public class Product {
    private String barcode; // Unique identifier given by the user. Using string to support more barcode variety.
    private String name;
    private String category;
    private String brand;

    public Product(String barcode, String name, String category, String brand) {
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String newCategory) {
        this.category = newCategory;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String newBrand) {
        this.brand = newBrand;
    }

    @Override
    public String toString() {
        return String.format("Barcode: %s, Name: %s, Category: %s, Brand: %s ", this.barcode, this.name, this.category, this.brand);
    }
}
