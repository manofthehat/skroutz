package org.example;

import java.util.ArrayList;

// Class defining the Eshops of the Skroutz App.
// Each eshop has its own stock.

public class Eshop {
    private String website;
    private String taxID;
    private String email;

    // Stock management.
    private ArrayList<ProductStock> products;

    public Eshop(String website, String taxID, String email) {
        this.website = website;
        this.taxID = taxID;
        this.email = email;
        this.products = new ArrayList<ProductStock>();
    }

    public String getWebsite() {
        return this.website;
    }

    public String getTaxID() {
        return this.taxID;
    }

    public ArrayList<ProductStock> getProducts() {
        return this.products;
    }

    public void addProduct(Product newProduct, double newPrice, int newQuantity) {
        // Update price / quantity if product exists
        // Otherwise create a new product in the stock
        boolean barcodeExists = false;

        for (ProductStock product : products) {
            if (product.getProduct().getBarcode().equals(newProduct.getBarcode()) ) {
                barcodeExists = true;
                product.updatePrice(newPrice);
                product.updateQuantity(newQuantity);
                break;
            }
        }

        if (!barcodeExists) {

            products.add(new ProductStock(newProduct, newPrice, newQuantity));
        }
    }

    @Override
    public String toString() {
        return String.format("Website: %s, Tax ID: %s", this.website, this.taxID);
    }
}
