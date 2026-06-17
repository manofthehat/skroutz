package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class it2025004 {
    public static double findLowestPrice(String barcode, ArrayList<Eshop> eshops) {
        double min = Double.MAX_VALUE; //Initialize min with the highest possible value.

        for (Eshop e : eshops) {
            for (ProductStock stock : e.getProducts()) {
                if (stock.getProduct().getBarcode().equalsIgnoreCase(barcode)) {
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

    public static Customer getCustomer(ArrayList<Customer> customers, Scanner input) {
        System.out.print("Enter your email: ");
        String email = input.nextLine();

        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Hello " + c.getUsername() + ".");
                return c;
            }
        }

        System.out.println("No account found. Creating new customer...");

        System.out.print("Enter your username: ");
        String username = input.nextLine().trim();

        Customer newCustomer = new Customer(username, email);
        customers.add(newCustomer);

        System.out.println("Account created successfully!");

        return newCustomer;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Define some shops and items for easier use of the app.

        ArrayList<Eshop> eshops = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Product> productMatches = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();

        Eshop tempEshop;
        Cart cart = new Cart();

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

        char choice;
        String inputString;

        System.out.println("Welcome to Skroutz.");

        do {
            System.out.println("Choose one of the following options:");
            System.out.println("1. Add a Product/Eshop to the system.");
            System.out.println("2. Update a product's stock.");
            System.out.println("3. Search for products and place an order.");
            System.out.println("4. Search for order history - print reports.");
            System.out.println("5. Save current state to a file.");
            System.out.println("Q. Exit the app.");
            System.out.print("Input your choice: ");

            inputString = input.nextLine().trim();

            if (inputString.equalsIgnoreCase("Q")) {
                choice = 'Q';
            } else {
                try {
                    int option = Integer.parseInt(inputString);

                    if (option >= 1 && option <= 5) {
                        choice = inputString.charAt(0);
                    } else {
                        choice = ' ';
                    }

                } catch (NumberFormatException e) {
                    choice = ' ';
                }
            }

            switch (choice) {
                    case '1': // Add a new eshop / product in the system.
                    for (Eshop e : eshops) {
                        System.out.println(e);
                    }
                    System.out.println("Enter the shop and product you would like to add to the system.");
                    System.out.print("Shop's Website: ");

                    String eshopWebsite;

                    while (true) {
                        eshopWebsite = input.nextLine();

                        if (!eshopWebsite.trim().isEmpty()) {
                            break;
                        }

                        System.out.println("Website cannot be empty. Try again: ");
                    }
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
                    while (true) {
                        productBarcode = input.nextLine();
                        if (!productBarcode.trim().isEmpty()) {
                            break;
                        }

                        System.out.println("Product Barcode cannot be empty. Try again: ");
                    }
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
                        String productName;

                        while (true) {
                            productName = input.nextLine();

                            if (!productName.trim().isEmpty()) {
                                break;
                            }

                            System.out.print("Name cannot be empty. Try again: ");
                        }


                        System.out.print("Brand: ");
                        String productBrand;

                        while (true) {
                            productBrand = input.nextLine();

                            if (!productBrand.trim().isEmpty()) {
                                break;
                            }

                            System.out.print("Brand cannot be empty. Try again: ");
                        }

                        System.out.print("Category: ");
                        // Repeatedly ask for input until the user enters a valid product category.
                        while (tempProduct == null) {
                            String productCategory = input.nextLine();
                            if (productCategory.equalsIgnoreCase("Clothing")) {
                                System.out.print("Size: ");
                                String productSize;

                                while (true) {
                                    productSize = input.nextLine();

                                    if (!productSize.trim().isEmpty()) {
                                        break;
                                    }

                                    System.out.println("Size cannot be empty. Try again: ");
                                }
                                System.out.print("Color: ");
                                String productColor;

                                while (true) {
                                    productColor = input.nextLine();

                                    if (!productColor.trim().isEmpty()) {
                                        break;
                                    }

                                    System.out.println("Color cannot be empty. Try again: ");
                                }
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
                    System.out.print("Price: ");
                    double productPrice;
                    int productQuantity;

                    while (true) {
                        if (input.hasNextDouble()) {
                            productPrice = input.nextDouble();
                            input.nextLine();

                            if (productPrice > 0) {
                                break;
                            }
                            System.out.print("Price must be > 0. Try again: ");
                        } else {
                                System.out.print("Invalid price. Try again: ");
                                input.nextLine();
                            }
                    }

                    System.out.print("Quantity: ");
                    while (true) {
                        if (input.hasNextInt()) {
                            productQuantity = input.nextInt();
                            input.nextLine();

                            if (productQuantity > 0) {
                                break;
                            }
                            System.out.print("Quantity must be > 0. Try again: ");
                        } else {
                            System.out.print("Invalid input. Enter a number: ");
                            input.nextLine();
                        }
                    }

                    System.out.println("Adding product " + tempProduct.getName() + " with price of " + productPrice + " and quantity " + productQuantity);
                    tempEshop.addProduct(tempProduct, productPrice, productQuantity);
                    break;
                    case '2': // Update price / quantity.
                    tempEshop = null;
                    String shopDetails;
                    System.out.print("Enter the shop's website or Tax ID: ");

                    while (true) {
                        shopDetails = input.nextLine();

                        if (!shopDetails.trim().isEmpty()) {
                            break;
                        }

                        System.out.println("The shop's website cannot be empty. Try again: ");
                    }
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
                        String productDetails;

                        while (true) {
                            productDetails  = input.nextLine();

                            if (!productDetails.trim().isEmpty()) {
                                break;
                            }

                            System.out.println("These details cannot be empty. Try again: ");
                        }
                        for (ProductStock stock : tempEshop.getProducts()) {
                            if (productDetails.equalsIgnoreCase(stock.getProduct().getName()) || productDetails.equalsIgnoreCase(stock.getProduct().getBarcode())) {
                                System.out.println("Enter the details you want to update.");
                                System.out.print("Price: ");
                                double price;

                                while (true) {
                                    price = input.nextDouble();
                                    input.nextLine();

                                    if (price > 0) {
                                        break;
                                    }

                                    System.out.println("Price must be positive. Try again: ");
                                }
                                System.out.print("Quantity: ");
                                int quantity;

                                while (true) {
                                    quantity = input.nextInt();

                                    if (quantity > 0) {
                                        break;
                                    }

                                    System.out.println("Quantity must be positive. Try again: ");
                                    }
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

                    do {
                        productMatches.clear(); //Not clearing the arraylist will lead to matched results across searches stacking on each other, leading to wrong behavior.
                        boolean showAll = false; // In case the user enters an empty string while searching for a product.
                        boolean productFound = false;
                        Product selectedProduct = null;
                        String shopChoice;
                        Eshop selectedEshop = null;
                        ProductStock selectedShop = null;
                        int orderQuantity;


                        // Searching phase.
                        System.out.print("Enter the name or the category of the product you would like to search for (leave it empty to search for all products): ");
                        String searchDetails = input.nextLine();

                        if (searchDetails.trim().isEmpty()) {
                            System.out.println("Showing all results:");
                            showAll = true;
                        } else {
                            System.out.println("Showing results for " + searchDetails + ":");
                            searchDetails = searchDetails.toLowerCase();
                        }

                        for (Product p : products) {
                            if ( showAll || p.getName().toLowerCase().contains(searchDetails) || p.getCategory().toLowerCase().contains(searchDetails)) {
                                productMatches.add(p);
                            }
                        }

                        for (Product p : productMatches) {
                            int eshopCount = 0; // Used to show the amount of shops selling this product.
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

                        // Selecting phase.
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
                                }
                            }
                            if (productFound) {
                                System.out.println("A product was chosen, displaying all shops selling it:");

                                for (Eshop e : eshops) {
                                    for (ProductStock stock: e.getProducts()) {
                                        if (stock.getProduct().getBarcode().equals(selectedProduct.getBarcode())) {
                                            System.out.println(e + ", " + stock);
                                        }
                                    }
                                }

                                // Selecting shop phase.
                                System.out.println("Enter your desired shop's tax ID: ");
                                shopChoice = input.nextLine();

                                boolean found = false;

                                for (Eshop e : eshops) {
                                    for (ProductStock stock : e.getProducts()) {
                                        if (e.getTaxID().equalsIgnoreCase(shopChoice) && stock.getProduct().getBarcode().equalsIgnoreCase(selectedProduct.getBarcode())) {
                                            selectedShop = stock;
                                            selectedEshop = e;
                                            found = true;
                                            break;
                                        }
                                    }

                                    if (found) {
                                        break;
                                    }
                                }

                                // Quantity selection phase.
                                if (selectedShop != null) {

                                    System.out.print("A shop was chosen, please enter the quantity of the product you would like to buy: ");

                                    orderQuantity = input.nextInt();
                                    input.nextLine();

                                    if (orderQuantity <= 0) {
                                        System.out.println("Quantity must be positive.");
                                    }
                                    else if (orderQuantity > selectedShop.getQuantity()) {
                                        System.out.println("Not enough stock available.");
                                    }
                                    else { // Add item to cart and update the stock.
                                        cart.updateCart( new CartItem(selectedProduct, selectedShop.getPrice(), selectedEshop, orderQuantity));

                                        selectedShop.updateQuantity(selectedShop.getQuantity() - orderQuantity);

                                        cart.printCart();
                                        System.out.println("The product was successfully added to the cart.\n");
                                    }

                                } else {
                                    System.out.println("Shop selection failed.");
                                }

                            } else {
                                System.out.println("No product listed in the search was chosen.");
                            }
                        }

                        // Ask until the user says yes, then move on to the ordering process.
                        System.out.print("Would you like to make another search? [Y/N] ");
                        inputString = input.nextLine();
                        if (inputString == null || inputString.trim().isEmpty()) {
                            continueSearch = 'N';
                        } else {
                            continueSearch = inputString.trim().charAt(0);
                        }
                    } while (continueSearch == 'Y' || continueSearch == 'y');

                    System.out.println("Current cart:");
                    cart.printCart();

                    if (cart.getCartItems().isEmpty()) {
                        System.out.println("Cart is empty.");
                        break;
                    }

                    System.out.print("Would you like to edit quantities before checkout? [Y/N]: ");
                    inputString = input.nextLine();
                    char editCart;

                    if (inputString.trim().isEmpty()) {
                        editCart = 'N';
                    } else {
                        editCart = inputString.trim().charAt(0);
                    }

                    if (editCart == 'Y' || editCart == 'y') {
                        for (CartItem item : cart.getCartItems()) {
                            System.out.println("Current item: " + item);
                            System.out.print("Enter new quantity (or 0 to remove): ");
                            int newQuantity;

                            while (true) {
                                newQuantity = input.nextInt();
                                input.nextLine();

                                if (newQuantity >= 0) {
                                    break;
                                }

                                System.out.println(("Quantity must be non negative. Try again: "));
                            }

                            if (newQuantity == 0) {
                                item.setQuantity(0);
                            } else {
                                item.setQuantity(newQuantity);
                            }
                        }
                    }

                    for (int i = 0; i < cart.getCartItems().size(); i++) {
                        if (cart.getCartItems().get(i).getQuantity() == 0) {
                        cart.getCartItems().remove(i); // Assuming items with 0 quantity are to be removed.
                        i--;
                        }
                    }

                    if (cart.getCartItems().isEmpty()) {
                        System.out.println("Cart is empty.");
                        break;
                    }

                    System.out.print("Would you like to place an order with the current cart items? [Y/N]: ");
                    inputString = input.nextLine();

                    char placeOrder;

                    if (inputString == null || inputString.trim().isEmpty()) {
                        placeOrder = 'N';
                    } else {
                        placeOrder = inputString.trim().charAt(0);
                    }



                    if (placeOrder == 'Y' || placeOrder == 'y') {
                        Customer newCustomer = getCustomer(customers, input);

                        Order order = new Order(cart);
                        orders.add(order);
                        cart = new Cart(); //Empty the existing cart by constructing a new cart instance.
                        newCustomer.placeOrder(order);
                        System.out.println("The order was successfully placed.");
                    }

                    break;

                    case '4': //Search orders, print reports.
                        Customer foundCustomer = null;

                        if (customers.isEmpty()) {
                            System.out.println("There are no customers registered. Returning to menu.");
                            break;
                        }

                        System.out.print("Enter the username or E-mail of the customer you would like to see the orders of: ");
                        inputString = input.nextLine();

                        for (Customer c : customers) { // Find existing customer by email or create a new one if they're not found.
                            if (inputString.equalsIgnoreCase(c.getUsername()) || inputString.equalsIgnoreCase(c.getEmail())) {
                                System.out.println("A customer with this username or E-mail was found.");
                                foundCustomer = c;
                                break;
                            }
                        }

                        if (foundCustomer == null) {
                            System.out.println("A customer with this username or E-mail wasn't found");
                        } else {
                            System.out.println("Customer orders:");

                            for (Order order : foundCustomer.getOrders()) {
                                System.out.println(order);
                                for (CartItem item : order.getItems()) {
                                    System.out.println(item);
                                }
                            }
                        }

                        break;

                case '5': // Save to file.
                    try {
                        PrintWriter writer = new PrintWriter("eshops.txt");

                        for (Eshop e : eshops) {
                            int orderCount = 0;
                            int productCount = e.getProducts().size();
                            double totalEarnings = 0;

                            for (Order o : orders) {
                                boolean belongsToEshop = false;

                                for (CartItem item : o.getItems()) {
                                    if (e.getTaxID().equalsIgnoreCase(item.getEshop().getTaxID())) {
                                        totalEarnings += item.getPrice() * item.getQuantity();
                                        belongsToEshop = true;
                                    }
                                }

                                if (belongsToEshop) {
                                    orderCount++;
                                }
                            }

                            writer.printf("%s, %s, %d, %d, %.2f\n", e.getWebsite(), e.getTaxID(), productCount, orderCount, totalEarnings);
                        }

                        writer.close();
                        System.out.println("File saved successfully.");
                    } catch (FileNotFoundException e) {
                        System.out.println("Error creating file.");
                    }

                    break;

                    case 'Q': //Quitting command isn't case-sensitive. Both cases function the same.


                    case 'q':
                    System.out.println("Exiting...");
                    break;

                //Handle any other values.
                default:
                    System.out.println("Invalid Option.");
                    break;
            }

        } while (choice != 'Q' && choice != 'q');
    }
}
