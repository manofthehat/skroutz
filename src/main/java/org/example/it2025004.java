package org.example;

import java.util.Scanner;
import java.util.ArrayList;

public class it2025004 {
    public static double findLowestPrice(String barcode, ArrayList<Eshop> eshops) {
        double min = Double.MAX_VALUE; //Initialize min with the highest possible value.

        for (Eshop e : eshops) {
            for (ProductStock stock : e.getProducts()) {
                if (stock.getProduct().getBarcode().equals(barcode)) {
                    if (stock.getPrice() < min) {
                        min = stock.getPrice();
                    }
                }
            }
        }

        if (min == Double.MAX_VALUE) {
            System.out.println("A product with that barcode was not found.");
            return -1;
        }

        return min;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Define some shops and items for easier use of the app.

        ArrayList<Eshop> eshops = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Product> productMatches = new ArrayList<>();

        Eshop tempEshop;

        Product p0 = new Clothing("0000", "Shorts", "Admiral", "Large","Black");
        Product p1 = new Footwear("0001", "Boots", "Bershka", "42.5", "Brown");
        Product p2 = new Product("0002", "Aspirin", "Pharmacy", "Bayer");

        products.add(p0);
        products.add(p1);
        products.add(p2);

        Eshop e0 = new Eshop("magazi.gr", "123456789", "info@magazi.gr");
        Eshop e1 = new Eshop("agorazo.gr", "987654321", "contact@agorazo.gr");

        eshops.add(e0);
        eshops.add(e1);

        e0.addProduct(p0, 19.99, 10);
        e1.addProduct(p0, 18.50, 5);
        e1.addProduct(p2, 5.50, 100);

        char choice; // Ensures invalid inputs don't crash the program.
        String inputString;

        System.out.println("Welcome to Skroutz.");

        do {
            System.out.println("Choose one of the following options:");
            System.out.println("1. Add a Product/Eshop to the system.");
            System.out.println("2. Update a product's stock.");
            System.out.println("Q. Exit the app.");
            System.out.print("Input your choice: ");

            // Inputting a single char directly isn't possible. Input a string and only grab the first char is used instead.
            inputString = input.nextLine();
            choice = inputString.charAt(0);

            switch (choice) {
                    case '1': // Add a new eshop / product in the system.
                    for (Eshop e : eshops) {
                        System.out.println(e);
                    }
                    System.out.println("Enter the shop and product you would like to add to the system.");
                    System.out.print("Shop's Website: ");
                    String eshopWebsite = input.nextLine();
                    String eshopTaxID;
                    String eshopEmail;
                    tempEshop = null;
                    String productBarcode;
                    Product tempProduct = null;
                    for (Eshop e : eshops) {
                        // Makes inputting data easier.
                        if (e.getWebsite().equalsIgnoreCase(eshopWebsite)) {
                            tempEshop = e;
                            System.out.println("The shop " + tempEshop.getWebsite() + " was found.");
                            System.out.println(e);
                            break;
                        }
                    }
                    if (tempEshop == null) {
                        System.out.println("A shop with that website wasn't found. Enter the rest of its details:");
                        System.out.print("Tax ID: ");
                        eshopTaxID = input.nextLine();
                        System.out.print("E-mail: ");
                        eshopEmail = input.nextLine();
                        System.out.println("Adding new shop to the system...");
                        tempEshop = new Eshop(eshopWebsite, eshopTaxID, eshopEmail);
                        System.out.println(tempEshop);
                        eshops.add(tempEshop);
                    }
                    System.out.print("Enter the product's barcode: ");
                    productBarcode = input.nextLine();
                    for (Product p : products) {
                        if (p.getBarcode().equalsIgnoreCase(productBarcode)) {
                            tempProduct = p;
                            System.out.println("Product " + tempProduct.getName() + " was found.");
                            System.out.println("Adding it to shop " + tempEshop.getWebsite() + ".");
                            System.out.println(tempProduct);
                            break;
                        }
                    }
                    if (tempProduct == null) {
                        System.out.println("A product with this barcode wasn't found. Enter the rest of its details.");
                        System.out.print("Name: ");
                        String productName = input.nextLine();
                        System.out.print("Brand: ");
                        String productBrand = input.nextLine();
                        System.out.print("Category: ");
                        // Repeatedly ask for input until the user enters a valid product category.
                        while (tempProduct == null) {
                            String productCategory = input.nextLine();
                            if (productCategory.equalsIgnoreCase("Clothing")) {
                                System.out.print("Size: ");
                                String productSize = input.nextLine();
                                System.out.print("Color: ");
                                String productColor = input.nextLine();
                                tempProduct = new Clothing(productBarcode, productName, productBrand, productSize, productColor);
                                products.add(tempProduct);
                            } else if (productCategory.equalsIgnoreCase("Footwear")) {
                                System.out.print("Size: ");
                                String productSize = input.nextLine();
                                System.out.print("Color: ");
                                String productColor = input.nextLine();
                                tempProduct = new Footwear(productBarcode, productName, productBrand, productSize, productColor);
                                products.add(tempProduct);
                            } else if (productCategory.equalsIgnoreCase("Pharmacy")) {
                                tempProduct = new Product(productBarcode, productName, "Pharmacy", productBrand);
                                products.add(tempProduct);
                            } else {
                                System.out.println("Product category wasn't found.");
                            }
                        }
                    }
                    System.out.println("Product was successfully created. Enter its price and quantity:");
                    System.out.print("Price ");
                    double productPrice = input.nextDouble();
                    input.nextLine(); // Empties the buffer.
                    System.out.print("Quantity: ");
                    int productQuantity = input.nextInt();
                    input.nextLine();
                    System.out.println("Adding product " + tempProduct.getName() + " with price of " + productPrice + " and quantity " + productQuantity);
                    tempEshop.addProduct(tempProduct, productPrice, productQuantity);
                    break;
                    case '2': // Update price / quantity.
                    tempEshop = null;
                    String shopDetails;
                    System.out.print("Enter the shop's website or Tax ID: ");
                    shopDetails = input.nextLine();
                    for (Eshop e : eshops) {
                        if (shopDetails.equalsIgnoreCase(e.getWebsite()) || shopDetails.equalsIgnoreCase(e.getTaxID())) {
                            tempEshop = e;
                            for (ProductStock stock : tempEshop.getProducts()) {
                                System.out.println(stock);
                            }
                            break;
                        }

                    }
                    if (tempEshop != null) {
                        System.out.println("Enter a product's name or barcode to update.");
                        String productDetails = input.nextLine();
                        for (ProductStock stock : tempEshop.getProducts()) {
                            if (productDetails.equalsIgnoreCase(stock.getProduct().getName()) || productDetails.equalsIgnoreCase(stock.getProduct().getBarcode())) {
                                System.out.println("Enter the details you want to update.");
                                System.out.print("Price: ");
                                double price = input.nextDouble();
                                input.nextLine();
                                System.out.print("Quantity: ");
                                int quantity = input.nextInt();
                                input.nextLine();
                                System.out.println("Updating the stock of product " + stock.getProduct().getName() + "...");
                                stock.updatePrice(price);
                                stock.updateQuantity(quantity);
                                System.out.println("Successfully updated.");
                                for (ProductStock s : tempEshop.getProducts()) {
                                    System.out.println(s);
                                }
                                break;
                            }
                        }
                    } else {
                        System.out.println("Shop not found.");
                    }
                    break;

                    case '3': //Search and order product(s).
                    char continueSearch;
                    Product selectedProduct = null;
                    boolean productFound = false;

                    do {
                        System.out.print("Enter the name or the category of the product you would like to search for: ");
                        String searchDetails = input.nextLine();

                        System.out.println("Showing results for " + searchDetails + ":");
                        searchDetails = searchDetails.toLowerCase();

                        for (Product p : products) {
                            if (p.getName().toLowerCase().contains(searchDetails) || p.getCategory().toLowerCase().contains(searchDetails)) {
                                productMatches.add(p);
                            }
                        }

                        for (Product p : productMatches) {
                            int eshopCount = 0;
                            for (Eshop e : eshops) {
                                for (ProductStock stock : e.getProducts()) {
                                    if (p.getBarcode().equalsIgnoreCase(stock.getProduct().getBarcode())) {
                                    eshopCount++;
                                    }
                                }
                            }
                            double minPrice = findLowestPrice(p.getBarcode(), eshops);

                            System.out.println("Barcode: " + p.getBarcode() + ", Name: " + p.getName() + ", Eshops containing the product: " + eshopCount + " , Lowest price available: " +  minPrice);
                        }

                        System.out.print("Enter the barcode of the product you want to choose, or leave the barcode empty to cancel the current search: ");
                        String chosenProduct = input.nextLine();

                        if (chosenProduct.trim().isEmpty()) {
                            System.out.println("Canceling current search.");
                        } else {
                            for (Product p : productMatches) {
                                if (chosenProduct.equals(p.getBarcode())) {
                                    productFound = true;
                                    System.out.println("Chosen the product " + p.getName() + ".");
                                    selectedProduct = p;
                                    break;
                                } else {
                                    System.out.println("No product listed in the search was chosen.");
                                }
                            }
                            if (productFound == true) {
                                System.out.print("Enter the quantity of the product you would like to buy: ");
                            }
                        }
                        System.out.print("Would you like to make another search? [Y/N] ");
                        inputString = input.nextLine();
                        continueSearch = inputString.charAt(0);
                        productMatches.clear(); //Not clearing the arraylist will lead to matched results across searches stacking on each other, leading to wrong behavior.
                    } while (continueSearch == 'Y' || continueSearch == 'y');

                    break;

                    case 'Q': // Quitting command isn't case-sensitive. Both cases function the same.


                    case 'q':
                    System.out.println("Exiting...");
                    break;

                // Handle any other values.
                default:
                    System.out.println("Invalid Option.");
                    break;
            }

        } while (choice != 'Q' && choice != 'q');
    }
}
